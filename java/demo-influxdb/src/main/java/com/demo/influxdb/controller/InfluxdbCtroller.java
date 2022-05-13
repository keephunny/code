package com.demo.influxdb.controller;

import org.influxdb.InfluxDB;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 说明:
 * <p>
 * <br>
 * 创建时间：2022-01-22 19:01
 *
 * @author
 */
@RestController
@RequestMapping("/influxdb")
public class InfluxdbCtroller {
    private Logger logger = LoggerFactory.getLogger(InfluxdbCtroller.class);


    @Autowired
    private InfluxDB influxDB;

    private final String measurement = "sensor";

    @Value("${spring.influx.database}")
    private String database;


    @GetMapping("/insert")
    public void insert() {
        // create retention policy "default" on mydb duration 366d replication 1;
        Point point = Point.measurement(measurement)
                .tag("deviceId", "sensor1")
                .addField("temp", 3)
                .addField("voltage", 145)
                .addField("press", "4")
                .addField("signal", "4")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .build();

        //写入
        influxDB.write(database,"default",point);
    }

    @GetMapping("/inserts")
    public void inserts() {
        List<String> list = new ArrayList<String>();
        Point point = null;
        for (int i = 0; i < 100; i++) {
            point = Point.measurement(measurement)
                    .tag("deviceId", "sensor" + i)
                    .addField("temp", 3)
                    .addField("voltage", 145 + i)
                    .addField("press", "4i")
                    .addField("signal", "4i")
                    .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                    .build();
            list.add(point.lineProtocol());
        }
        influxDB.setRetentionPolicy("default");
        //写入
        influxDB.write(list);
    }

    /**
     * 批量插入第二种方式
     */
    @GetMapping("/index1")
    public void batchInsert() {
        BatchPoints batchPoints = BatchPoints
                .database(database)
                .consistency(InfluxDB.ConsistencyLevel.ALL)
                .build();
        //遍历sqlserver获取数据
        for (int i = 0; i < 50; i++) {
            //创建单条数据对象——表名
            Point point = Point.measurement(measurement)
                    //tag属性——只能存储String类型
                    .tag("deviceId", "sensor" + i)
                    .addField("temp", 3)
                    .addField("voltage", 145 + i)
                    .addField("press", "4i")
                    .addField("signal", "4i").build();
            //将单条数据存储到集合中
            batchPoints.point(point);
        }
        //批量插入
        influxDB.write(batchPoints);
    }


    /**
     * 获取数据
     */
    @GetMapping("/query")
    public QueryResult query(int page) {
        int pageSize = 10;
        // InfluxDB支持分页查询,因此可以设置分页查询条件
        String pageQuery = "";//" LIMIT " + pageSize + " OFFSET " + (page - 1) * pageSize;

        String queryCondition = "";  //查询条件暂且为空
        // 此处查询所有内容,如果
        String queryCmd = "SELECT * FROM "
                // 查询指定设备下的日志信息
                // 要指定从 RetentionPolicyName.measurement中查询指定数据,默认的策略可以不加；
                // + 策略name + "." + measurement
                + measurement
                // 添加查询条件(注意查询条件选择tag值,选择field数值会严重拖慢查询速度)
                + queryCondition
                // 查询结果需要按照时间排序
                + " ORDER BY time DESC"
                // 添加分页查询条件
                + pageQuery;

        logger.info("queryCmd:{}",queryCmd);
        QueryResult queryResult = influxDB.query(new Query(queryCmd, database));
        logger.info("query result => {}", queryResult);
        return queryResult;
    }

}
