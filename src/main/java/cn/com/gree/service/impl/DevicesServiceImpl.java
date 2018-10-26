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

    /**
     * @author Abin
     * @date 2018/8/7 14:37
     * 格式化本地最新数据
     */
    @Override
    public List<Map<String,Object>> getLocalAllDevices() {
        List<DeviceData> deviceDataList = dataService.getAllLatestDeviceData();
        List<Map<String,Object>> mapList = null;
        Map<String,Object> map = null;
        Map<String,String> dataMap = null;
        List<Object> list = null;
        if(deviceDataList != null && deviceDataList.size() != 0){
            mapList = new ArrayList<>();
            for(DeviceData deviceData : deviceDataList){
                Devices devices = deviceData.getDevice();
                if(!devices.isEnable()){
                    continue;
                }
                map = new HashMap<>();
                list = new ArrayList<>();
                map.put("area",devices.getArea());
                map.put("device_name",devices.getDeviceName());
                map.put("device_status", DeviceStatusTranslator.intToChinese(deviceData.getDeviceStatus()));
                map.put("eventTime",DateTransform.toDateStr(deviceData.getEventTime()));
                map.put("barCode",devices.getBarCode());
                dataMap = new HashMap<>();
                dataMap.put("key","temperature");
                dataMap.put("value",deviceData.getTemperature() + Symbol.TEMPERATURE_UNIT);
                list.add(dataMap);
                dataMap = new HashMap<>();
                dataMap.put("key","humidity");
                dataMap.put("value",deviceData.getHumidity() + Symbol.HUMIDITY_UNIT);
                list.add(dataMap);
                dataMap = new HashMap<>();
                dataMap.put("key","PM2.5");
                dataMap.put("value",deviceData.getPM2_5() + Symbol.PM_UNIT);
                list.add(dataMap);
                map.put("info",list);
                mapList.add(map);
            }
        }
        return mapList;
    }

    /**
     * @author Abin
     * @date 2018/8/7 14:38
     * 修改地区
     */
    @Override
    public int updateArea(String area, String barCode) {
        StringBuilder sql = new StringBuilder(" select d from Devices d where d.area = '" + area + "' and enable = true ");
        List<Devices> devicesList = baseDao.getByJpql(sql.toString());
        if(devicesList != null && devicesList.size() != 0){
            return 1;
        }
        sql.setLength(0);
        sql.append(" select d from Devices d where d.barCode = '").append(barCode).append("' ");
        Devices devices = (Devices) baseDao.getSingleResultByJpql(sql.toString());
        devices.setArea(area);
        baseDao.update(devices);
        return 0;
    }

}
