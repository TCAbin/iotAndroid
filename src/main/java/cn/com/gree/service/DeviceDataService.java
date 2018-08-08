package cn.com.gree.service;

import cn.com.gree.entity.DeviceData;
import cn.com.gree.entity.Devices;

import java.util.List;
import java.util.Map;

public interface DeviceDataService {


    /**
     * @author Abin
     * @date 2018/8/7 17:25
     * 从接口获取对应设备最新一条数据
     */
    DeviceData getDeviceDataFromInterface(Devices d);

    /**
     * @author Abin
     * @date 2018/8/8 8:09
     * 从本地数据库获取所有设备最新的数据
     */
    List<DeviceData> getAllLatestDeviceData();

    /**
     * @author Abin
     * @date 2018/8/8 8:39
     * 3.3查询需求 根据条码值查询该机最新一条数据
     */
    Map<String,String> getLatestDeviceDataByBarCode(String barCode);

}
