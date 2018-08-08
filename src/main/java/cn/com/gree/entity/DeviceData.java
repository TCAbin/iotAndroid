package cn.com.gree.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_device_data")
public class DeviceData {

    /** id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 设备温度 */
    @Column
    private Double temperature;

    /** 设备湿度 */
    @Column
    private Double humidity;

    /** 设备status
     * 1 online 在线
     * 2 offline 离线
     * 3 inbox  ？？
     * 4 abnormal 异常
     * 5 未知
     * */
    @Column
    private Integer deviceStatus;

    /** pm1.0浓度 */
    @Column
    private Integer PM1_0;

    /** pm2.5浓度 */
    @Column
    private Integer PM2_5;

    /** pm10浓度 */
    @Column
    private Integer PM10;

    /** 0.3升空气中直径在0.3um以上颗粒物个数 */
    @Column
    private Integer UM0_3;

    /** 0.3升空气中直径在0.5um以上颗粒物个数 */
    @Column
    private Integer UM0_5;

    /** 0.3升空气中直径在1.0um以上颗粒物个数 */
    @Column
    private Integer UM1_0;

    /** 0.3升空气中直径在2.5um以上颗粒物个数 */
    @Column
    private Integer UM2_5;

    /** 0.3升空气中直径在5um以上颗粒物个数 */
    @Column
    private Integer UM5;

    /** 0.3升空气中直径在10um以上颗粒物个数 */
    @Column
    private Integer UM10;

    /** 数据时间 */
    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date eventTime;

    /** 写入数据时间 */
    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date time;

    /** 关联的设备 */
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "device_id")
    private Devices device;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Integer getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(Integer deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public Integer getPM1_0() {
        return PM1_0;
    }

    public void setPM1_0(Integer PM1_0) {
        this.PM1_0 = PM1_0;
    }

    public Integer getPM2_5() {
        return PM2_5;
    }

    public void setPM2_5(Integer PM2_5) {
        this.PM2_5 = PM2_5;
    }

    public Integer getPM10() {
        return PM10;
    }

    public void setPM10(Integer PM10) {
        this.PM10 = PM10;
    }

    public Integer getUM0_3() {
        return UM0_3;
    }

    public void setUM0_3(Integer UM0_3) {
        this.UM0_3 = UM0_3;
    }

    public Integer getUM0_5() {
        return UM0_5;
    }

    public void setUM0_5(Integer UM0_5) {
        this.UM0_5 = UM0_5;
    }

    public Integer getUM1_0() {
        return UM1_0;
    }

    public void setUM1_0(Integer UM1_0) {
        this.UM1_0 = UM1_0;
    }

    public Integer getUM2_5() {
        return UM2_5;
    }

    public void setUM2_5(Integer UM2_5) {
        this.UM2_5 = UM2_5;
    }

    public Integer getUM5() {
        return UM5;
    }

    public void setUM5(Integer UM5) {
        this.UM5 = UM5;
    }

    public Integer getUM10() {
        return UM10;
    }

    public void setUM10(Integer UM10) {
        this.UM10 = UM10;
    }

    public Date getEventTime() {
        return eventTime;
    }

    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Devices getDevice() {
        return device;
    }

    public void setDevice(Devices device) {
        this.device = device;
    }
}
