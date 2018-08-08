package cn.com.gree.service.impl;

import cn.com.gree.constant.Symbol;
import cn.com.gree.dao.BaseDao;
import cn.com.gree.entity.DeviceData;
import cn.com.gree.entity.Devices;
import cn.com.gree.service.DeviceDataService;
import cn.com.gree.service.DevicesService;
import cn.com.gree.utils.DateTransform;
import cn.com.gree.utils.translator.DeviceStatusTranslator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("DevicesService")
public class DevicesServiceImpl implements DevicesService {

    @Resource(name = "BaseDao")
    private BaseDao baseDao;

    @Resource(name = "DeviceDataService")
    private DeviceDataService dataService;

    @Override
    public List<Devices> getAllDevices() {
        return baseDao.getAll(Devices.class);
    }

    @Override
    public List<Map<String,String>> getLocalAllDevices() {
        List<DeviceData> deviceDataList = dataService.getAllLatestDeviceData();
        List<Map<String,String>> mapList = new ArrayList<>();
        Map<String,String> map = null;
        if(deviceDataList != null && deviceDataList.size() != 0){
            map = new HashMap<>();
            for(DeviceData deviceData : deviceDataList){
                map.put("area",deviceData.getDevice().getArea());
                map.put("device_name",deviceData.getDevice().getDeviceName());
                map.put("device_status", DeviceStatusTranslator.intToChinese(deviceData.getDeviceStatus()));
                map.put("eventTime",DateTransform.toDateStr(deviceData.getEventTime()));
                map.put("temperature",deviceData.getTemperature() + Symbol.TEMPERATURE_UNIT);
                map.put("humidity",deviceData.getHumidity() + Symbol.HUMIDITY_UNIT);
                map.put("PM2_5",deviceData.getPM2_5() + Symbol.PM_UNIT);
                mapList.add(map);
            }
        }
        return mapList;
    }

}
