package cn.com.gree.service;

import cn.com.gree.entity.Devices;

import java.util.List;
import java.util.Map;

public interface DevicesService {

    /**
     * @author Abin
     * @date 2018/8/7 17:17
     * 获取所有设备
     */
    List<Devices> getAllDevices();

    /**
     * @author Abin
     * @date 2018/8/8 8:03
     * 3.4需求 查询本地设备
     */
    List<Map<String,Object>> getLocalAllDevices();


    /**
     * @author Abin
     * @date 2018/9/22 11:23
     * 修改设备地区
     */
    int updateArea(String area,String barCode);

}
