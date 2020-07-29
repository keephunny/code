package com.alarm.monitor.controller;

import com.alarm.monitor.utils.ByteUtils;
import com.alarm.monitor.utils.SerialPortManager;
import com.alarm.monitor.vo.RespResult;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.sound.midi.Soundbank;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping(value = "/led/alarmLed")
@Api(tags = "声光报警", value = "声光报警", description = "声光报警")
public class AlarmLedController {
    private final Logger logger = LoggerFactory.getLogger(AlarmLedController.class);

    @Value("${commName}")
    private String commName;
    @Value("${alarmMaxTime}")
    private int alarmMaxTime;
    private  boolean CloseStatus = false;
    private  boolean OpenStatus = false;
    private SerialPort mSerialport;

    @GetMapping(value = "open")
    @ApiOperation(value = "打开声光报警器", notes = "alarmLevel:1 2 3 报警级别。voice：1打开蜂鸣 0关闭蜂鸣")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "alarmLevel", value = "1 2 3 报警级别"),
            @ApiImplicitParam(name = "voice", value = "1打开蜂鸣 0关闭蜂鸣")
    })
    public RespResult open(int alarmLevel, @RequestParam(required = true, defaultValue = "0") int voice) {
        RespResult respResult = new RespResult();
        if (alarmLevel < 1 || alarmLevel > 3) {
            respResult.setCode(400, "alarmLevel只能是1 2 3");
            return respResult;
        }
        String[] ary = {"01050003FF007C3A", "01050001FF00DDFA", "01050002FF002DFA"};
        //如果已打开 不要重复打开
        if (!OpenStatus) {
            openSerialPort();
            OpenStatus = true;
        }
        if (CloseStatus) {
            CloseStatus = false;
        }

//        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
//        cachedThreadPool.execute(new Runnable() {
//            @Override
//            public void run() {
        double d = 0;
        int sleep = 0;
        double totalSleep = 0;
        while (true) {
            d += 0.1;
            sleep = (int) Math.abs(Math.sin(d) * 200);
            totalSleep += ((sleep * 2d) / 1000);
            if (CloseStatus || (alarmMaxTime > 0 && totalSleep > alarmMaxTime)) {
                logger.info("报警中止");
                closeSerialPort();
                return respResult;
            }
            String result = SerialPortManager.sendToPort(mSerialport, ByteUtils.hexStr2Byte(ary[alarmLevel - 1]));
            if (CloseStatus || result == null) {
                logger.info("报警中止");
                closeSerialPort();
                return respResult;
            }
            if (voice == 1) {
                SerialPortManager.sendToPort(mSerialport, ByteUtils.hexStr2Byte("01050004FF00CDFB"));
            }
            try {
                Thread.sleep(sleep);
                SerialPortManager.sendToPort(mSerialport, ByteUtils.hexStr2Byte("01050005FF009C3B"));
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//            }
//        });


//        return respResult;
    }

    @GetMapping(value = "close")
    @ApiOperation(value = "关闭声光报警器", notes = "")
    public RespResult close() {
        logger.info("发送关闭指令");
        RespResult respResult = new RespResult();
        CloseStatus = true;
        OpenStatus = false;
        //openSerialPort();
        try {
            SerialPortManager.sendToPort(mSerialport, ByteUtils.hexStr2Byte("01050005FF009C3B"));
        } catch (Exception e) {
            respResult.setCode(500, "串口设备取消指令失败 01050005FF009C3B");
            logger.error("串口设备取消指令失败 01050005FF009C3B", e);
        }
//        closeSerialPort();
        return respResult;
    }

    @GetMapping(value = "kill")
    @ApiOperation(value = "强制关闭设备", notes = "")
    public RespResult kill() {
        logger.info("强制关闭设备");
        RespResult respResult = new RespResult();
        CloseStatus = true;
        openSerialPort();
        try {
            SerialPortManager.sendToPort(mSerialport, ByteUtils.hexStr2Byte("01050005FF009C3B"));
        } catch (Exception e) {
            respResult.setCode(500, "串口设备取消指令失败 01050005FF009C3B");
            logger.error("串口设备取消指令失败 01050005FF009C3B", e);
        }
        closeSerialPort();
        return respResult;
    }


    @GetMapping(value = "test")
    @ApiOperation(value = "测试", notes = "")
    public RespResult test() {
        RespResult respResult = new RespResult();

        new Timer().schedule(new TimerTask() {
            int i = 0;

            @Override
            public void run() {
                i++;
                if (i > 100) {

                    return;
                }
                System.out.println(i);
//                SerialPortManager.sendToPort(mSerialport, ByteUtils.hexStr2Byte("01050003FF007C3A"));
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                SerialPortManager.sendToPort(mSerialport, ByteUtils.hexStr2Byte("01050005FF009C3B"));
//                try {
//                    Thread.sleep(300);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

            }
        }, 0);

        return respResult;
    }

    /**
     * 打开串口
     */
    private RespResult openSerialPort() {
        RespResult respResult = new RespResult();
        // 获取串口名称
        // 获取波特率，默认为9600
        int baudrate = 9600;
        // 检查串口名称是否获取正确
        try {
            mSerialport = SerialPortManager.openPort(commName, baudrate);
            if (mSerialport != null) {
                logger.info("串口已打开");
                return respResult;
            }
        } catch (PortInUseException e) {
            logger.error("串口已被占用！");
            respResult.setCode(500, "串口已被占用");
            return respResult;
        }

        // 添加串口监听
        SerialPortManager.addListener(mSerialport, new SerialPortManager.DataAvailableListener() {
            @Override
            public void dataAvailable() {
                byte[] data = null;
                try {
                    if (mSerialport == null) {
                        logger.info("串口对象为空，监听失败！");
                    } else {
                        // 读取串口数据
                        data = SerialPortManager.readFromPort(mSerialport);
                        logger.info(ByteUtils.byteArrayToHexString(data));
                    }
                } catch (Exception e) {
                    logger.error("", e);
                    // 发生读取错误时显示错误信息后退出系统
                    System.exit(0);
                }
            }
        });
        return respResult;
    }

    /**
     * 关闭串口
     */
    private void closeSerialPort() {
        try {
            SerialPortManager.sendToPort(mSerialport, ByteUtils.hexStr2Byte("01050005FF009C3B"));
        } catch (Exception e) {
            logger.error("串口设备取消指令失败 01050005FF009C3B", e);
        }
        OpenStatus = false;
        SerialPortManager.closePort(mSerialport);
        logger.info("串口已关闭");
        mSerialport = null;
    }
}
