package cn.com.gree.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_devices")
public class Devices {

    /** id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 设备ID
     * 00f6274b-4a47-478c-8a7f-7ebd3c3e25f9
     */
    @Column(unique = true)
    private String deviceId;

    /** 设备名称 */
    @Column(length = 30)
    private String deviceName;

    /** 区域 */
    @Column(length = 30,unique = true)
    private String area;

    /** imei */
    @Column(length = 30,unique = true)
    private String IMEI;

    /** 条码值 */
    @Column(length = 30,unique = true)
    private String barCode;

//    /** 关联的设备 */
//    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
//    @JoinColumn(name = "device_id")
//    private List<DeviceData> deviceData;

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

//    public List<DeviceData> getDeviceData() {
//        return deviceData;
//    }
//
//    public void setDeviceData(List<DeviceData> deviceData) {
//        this.deviceData = deviceData;
//    }
}
