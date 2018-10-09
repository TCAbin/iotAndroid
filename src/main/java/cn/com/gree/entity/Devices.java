package cn.com.gree.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_devices")
public class Devices {

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 设备ID
     * 00f6274b-4a47-478c-8a7f-7ebd3c3e25f9
     */
    @Column(length = 100, unique = true)
    private String deviceId;

    /**
     * 设备名称
     */
    @Column(length = 100)
    private String deviceName;

    /**
     * 区域
     */
    @Column(length = 100)
    private String area;

    /**
     * imei
     */
    @Column(length = 100, unique = true)
    private String IMEI;

    /**
     * 条码值
     */
    @Column(length = 100, unique = true)
    private String barCode;

    /**
     * 湿度最小值
     */
    @Column
    private Integer minHumidityRange;

    /**
     * 湿度最大值
     */
    @Column
    private Integer maxHumidityRange;

    /**
     * PM最小值
     */
    @Column
    private Integer minPM;

    /**
     * PM最大值
     */
    @Column
    private Integer maxPM;

    /**
     * UM最小值
     */
    @Column
    private Integer minUM;

    /**
     * UM最大值
     */
    @Column
    private Integer maxUM;

    /**
     * 是否启用
     */
    @Column
    private boolean enable;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public Integer getMinHumidityRange() {
        return minHumidityRange;
    }

    public void setMinHumidityRange(Integer minHumidityRange) {
        this.minHumidityRange = minHumidityRange;
    }

    public Integer getMaxHumidityRange() {
        return maxHumidityRange;
    }

    public void setMaxHumidityRange(Integer maxHumidityRange) {
        this.maxHumidityRange = maxHumidityRange;
    }

    public Integer getMinPM() {
        return minPM;
    }

    public void setMinPM(Integer minPM) {
        this.minPM = minPM;
    }

    public Integer getMaxPM() {
        return maxPM;
    }

    public void setMaxPM(Integer maxPM) {
        this.maxPM = maxPM;
    }

    public Integer getMinUM() {
        return minUM;
    }

    public void setMinUM(Integer minUM) {
        this.minUM = minUM;
    }

    public Integer getMaxUM() {
        return maxUM;
    }

    public void setMaxUM(Integer maxUM) {
        this.maxUM = maxUM;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
