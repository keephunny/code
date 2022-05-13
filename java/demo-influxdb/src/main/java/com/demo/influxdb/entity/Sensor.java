package com.demo.influxdb.entity;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

/**
 * 说明:
 * <p>
 * <br>
 * 创建时间：2022-01-22 22:16
 *
 * @author
 */
@Measurement(name = "sensor")
public class Sensor {
    @Column(name="deviceId",tag=true)
    private String deviceId;

    @Column(name="temp")
    private float temp;

    @Column(name="voltage")
    private float voltage;

    @Column(name="press")
    private float press;

    @Column(name="signal")
    private float signal;

    @Column(name="time")
    private long time;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public float getVoltage() {
        return voltage;
    }

    public void setVoltage(float voltage) {
        this.voltage = voltage;
    }

    public float getPress() {
        return press;
    }

    public void setPress(float press) {
        this.press = press;
    }

    public float getSignal() {
        return signal;
    }

    public void setSignal(float signal) {
        this.signal = signal;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
