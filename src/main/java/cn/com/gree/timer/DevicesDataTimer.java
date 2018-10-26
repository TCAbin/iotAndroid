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

/**
 * @author Abin
 * @date 2018/8/7 14:31
 * 数据定时获取，数据获取保存在本地数据库，
 * 作为数据副本，历史记录等等
 * 数据每五分钟获取一次
 */
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
        boolean flag = true;
        for(Devices d : devices) {
            if(d.isEnable()){
                DeviceData dd = deviceDataService.getDeviceDataFromInterface(d);
                if(dd != null) {
                    result.add(dd);
                }else{
                    flag = false;
                    break;
                }
            }
        }
        if(flag){
            for(DeviceData deviceData : result){
                baseDao.update(deviceData);
            }
        }
        result.clear();
    }

}
