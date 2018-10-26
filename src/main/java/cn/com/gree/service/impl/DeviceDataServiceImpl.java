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
import java.text.SimpleDateFormat;
import java.util.*;

@Service("DeviceDataService")
public class DeviceDataServiceImpl implements DeviceDataService {

    @Resource(name = "BaseDao")
    private BaseDao baseDao;

    @Resource(name = "TokenDataService")
    private TokenDataService tokenDataService;

    /**
     * @author Abin
     * @date 2018/8/7 14:35
     * 根据设备ID，从华为平台获取对应的设备最新的一条数据
     * 访问华为平台时，需要带上token
     * 访问不成功，则直接刷新token
     */
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
                deviceData.setUM10(Integer.valueOf(map.get("UM10")));
                d.setDeviceName(map.get("name"));
                deviceData.setDevice(d);
            }else {
                tokenDataService.refreshToken();
            }
        } catch (Exception e) {
            tokenDataService.refreshToken();
            System.out.println("时间 : " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) +
                    " ,设置设备" + d.getDeviceId() + "数据失败。" + e.getMessage());
        }
        return deviceData;
    }

    /**
     * @author Abin
     * @date 2018/8/7 14:35
     * 获取所有设备的最新数据
     */
    @Override
    public List<DeviceData> getAllLatestDeviceData() {
        String str = " select o.* from t_device_data o , " +
                "(select device_id,max(time) as time from t_device_data group by device_id) a " +
                "where a.device_id = o.device_id and o.time = a.time ";
        return baseDao.getByNativeSQL(DeviceData.class,str);
    }

    /**
     * @author Abin
     * @date 2018/8/7 14:34
     * 根据barCode获取最新设备的数据
     */
    @Override
    public Map<String,Object> getLatestDeviceDataByBarCode(String barCode) {
        Map<String, Object> obj = null;
        List<DeviceData> dataList = getAllLatestDeviceData();
        for(DeviceData deviceData : dataList){
            if(barCode.equals(deviceData.getDevice().getBarCode())){
                obj = new HashMap<>();
                obj.put("area",deviceData.getDevice().getArea());
                obj.put("deviceName",deviceData.getDevice().getDeviceName());
                obj.put("deviceStatus",DeviceStatusTranslator.intToChinese(deviceData.getDeviceStatus()));
                obj.put("eventTime",DateTransform.toDateStr(deviceData.getEventTime()));
                obj.put("temperature",deviceData.getTemperature() + Symbol.TEMPERATURE_UNIT);
                obj.put("humidity",deviceData.getHumidity() + Symbol.HUMIDITY_UNIT);
                obj.put("info",getInfo(deviceData));
                obj.put("range",getRange(deviceData.getDevice()));
                break;
            }
        }
        return obj;
    }

    /**
     * @author Abin
     * @date 2018/8/7 14:17
     * 获取对应设备PM um等数据
     */
    private Map<String,String> getInfo(DeviceData deviceData){
        Map<String,String> map = new LinkedHashMap<>();
        map.put("PM1.0",deviceData.getPM1_0() + Symbol.PM_UNIT);
        map.put("PM2.5",deviceData.getPM2_5() + Symbol.PM_UNIT);
        map.put("PM10",deviceData.getPM10() + Symbol.PM_UNIT);
        map.put("≥0.3um",deviceData.getUM0_3() + Symbol.UM_UNIT);
        map.put("≥0.5um",deviceData.getUM0_5() + Symbol.UM_UNIT);
        map.put("≥1.0um",deviceData.getUM1_0() + Symbol.UM_UNIT);
        map.put("≥2.5um",deviceData.getUM2_5() + Symbol.UM_UNIT);
        map.put("≥5.0um",deviceData.getUM5() + Symbol.UM_UNIT);
        map.put("≥10um",deviceData.getUM10() + Symbol.UM_UNIT);
        return map;
    }


    /**
     * @author Abin
     * @date 2018/8/7 14:18
     * 获取温湿度、PM、UM范围值
     */
    private Map<String,Integer> getRange(Devices devices){
        Map<String,Integer> map = new HashMap<>();
        map.put("minHumidityRange",devices.getMinHumidityRange());
        map.put("maxHumidityRange",devices.getMaxHumidityRange());
        map.put("minPM",devices.getMinPM());
        map.put("maxPM",devices.getMaxPM());
        map.put("maxUM",devices.getMaxUM());
        map.put("minUM",devices.getMinUM());
        return map;
    }
}
