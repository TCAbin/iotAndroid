package cn.com.gree.service.impl;

import cn.com.gree.constant.Symbol;
import cn.com.gree.dao.BaseDao;
import cn.com.gree.entity.DeviceData;
import cn.com.gree.entity.Devices;
import cn.com.gree.entity.TokenData;
import cn.com.gree.service.DeviceDataService;
import cn.com.gree.service.TokenDataService;
import cn.com.gree.utils.DateTransform;
import cn.com.gree.utils.IOTUtils.DataCollector;
import cn.com.gree.utils.translator.DeviceStatusTranslator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("DeviceDataService")
public class DeviceDataServiceImpl implements DeviceDataService {

    @Resource(name = "BaseDao")
    private BaseDao baseDao;

    @Resource(name = "TokenDataService")
    private TokenDataService tokenDataService;

    @Override
    public DeviceData getDeviceDataFromInterface(Devices d) {
        DeviceData deviceData = null;
        try {
            // 获取最新的token
            TokenData td = (TokenData) baseDao.getSingleResultByLimit(" select t from TokenData t order by t.date desc ",1);
            if(td != null){
                Map<String,String> map = DataCollector.getRemoteData(d.getDeviceId(),td.getToken());
                deviceData = new DeviceData();
                deviceData.setTime(new Date());
                deviceData.setEventTime(DateTransform.convert(DateTransform.toDate(map.get("eventTime")),8));
                deviceData.setTemperature(Double.valueOf(map.get("temperature")));
                deviceData.setHumidity(Double.valueOf(map.get("humidity")));
                deviceData.setDeviceStatus(Integer.valueOf(map.get("status")));
                deviceData.setPM1_0(Integer.valueOf(map.get("PM1_0")));
                deviceData.setPM2_5(Integer.valueOf(map.get("PM2_5")));
                deviceData.setPM10(Integer.valueOf(map.get("PM10")));
                deviceData.setUM0_3(Integer.valueOf(map.get("UM0_3")));
                deviceData.setUM0_5(Integer.valueOf(map.get("UM0_5")));
                deviceData.setUM1_0(Integer.valueOf(map.get("UM1_0")));
                deviceData.setUM2_5(Integer.valueOf(map.get("UM2_5")));
                deviceData.setUM5(Integer.valueOf(map.get("UM5")));
                deviceData.setUM10(Integer.valueOf(map.get("PM10")));
                d.setDeviceName(map.get("name"));
                deviceData.setDevice(d);
            }else {
                tokenDataService.refreshToken();
            }
        } catch (Exception e) {
            tokenDataService.refreshToken();
            System.out.println("设置设备" + d.getDeviceId() + "数据失败。" + e.getMessage());
        }
        return deviceData;
    }

    @Override
    public List<DeviceData> getAllLatestDeviceData() {
        String str = " select o.* from t_device_data o , " +
                "(select device_id,max(time) as time from t_device_data group by device_id) a " +
                "where a.device_id = o.device_id and o.time = a.time ";
        return baseDao.getByNativeSQL(DeviceData.class,str);
    }

    @Override
    public Map<String, String> getLatestDeviceDataByBarCode(String barCode) {
        Map<String, String> obj = new HashMap<>();
        List<DeviceData> dataList = getAllLatestDeviceData();
        for(DeviceData deviceData : dataList){
            if(barCode.equals(deviceData.getDevice().getBarCode())){
                obj.put("area",deviceData.getDevice().getArea());
                obj.put("deviceName",deviceData.getDevice().getDeviceName());
                obj.put("deviceStatus",DeviceStatusTranslator.intToChinese(deviceData.getDeviceStatus()));
                obj.put("eventTime",DateTransform.toDateStr(deviceData.getEventTime()));
                obj.put("temperature",deviceData.getTemperature() + Symbol.TEMPERATURE_UNIT);
                obj.put("humidity",deviceData.getHumidity() + Symbol.HUMIDITY_UNIT);
                obj.put("PM1_0",deviceData.getPM1_0() + Symbol.PM_UNIT);
                obj.put("PM2_5",deviceData.getPM2_5() + Symbol.PM_UNIT);
                obj.put("PM10",deviceData.getPM10() + Symbol.PM_UNIT);
                obj.put("UM0_3",deviceData.getUM0_3() + Symbol.UM_UNIT);
                obj.put("UM0_5",deviceData.getUM0_5() + Symbol.UM_UNIT);
                obj.put("UM1_0",deviceData.getUM1_0() + Symbol.UM_UNIT);
                obj.put("UM2_5",deviceData.getUM2_5() + Symbol.UM_UNIT);
                obj.put("UM5",deviceData.getUM5() + Symbol.UM_UNIT);
                obj.put("UM10",deviceData.getUM10() + Symbol.UM_UNIT);
                break;
            }
        }
        return obj;
    }
}
