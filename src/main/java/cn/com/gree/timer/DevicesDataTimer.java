package cn.com.gree.timer;

import cn.com.gree.dao.BaseDao;
import cn.com.gree.entity.DeviceData;
import cn.com.gree.entity.Devices;
import cn.com.gree.service.DeviceDataService;
import cn.com.gree.service.DevicesService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class DevicesDataTimer {

    @Resource(name = "BaseDao")
    private BaseDao baseDao;

    @Resource(name = "DevicesService")
    private DevicesService devicesService;

    @Resource(name = "DeviceDataService")
    private DeviceDataService deviceDataService;

    /**
     * @author Abin
     * @date 2018/8/7 17:41
     * 每五分钟获取一次数据
     */
    @Scheduled(cron = "0 0/5 * * * *")
    private void getAllDeviceData(){
        setDeviceData();
    }

    /**
     * @author Abin
     * @date 2018/8/7 17:49
     * 获取所有设备信息
     */
    private void setDeviceData(){
        List<Devices> devices = devicesService.getAllDevices();
        List<DeviceData> result = new ArrayList<>();
        for(Devices d : devices) {
            DeviceData dd = deviceDataService.getDeviceDataFromInterface(d);
            if(dd != null) {
                result.add(dd);
            }
        }
        if(result.size() == devices.size()){
            for(DeviceData deviceData : result){
                baseDao.update(deviceData);
            }
        }
    }

}
