package cn.com.gree.controller;

import cn.com.gree.service.DevicesService;
import cn.com.gree.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Api(description = "获取设备和设备部分最新数据")
@RestController
@RequestMapping("device")
public class DeviceController {

    @Resource(name = "DevicesService")
    private DevicesService devicesService;

    @ApiOperation(value = "获取设备和设备部分最新数据",notes = "3.4需求")
    @RequestMapping(value = "getAllDevices",method = RequestMethod.GET)
    public Result getAllDevices(){
        List list = devicesService.getLocalAllDevices();
        if(list.size() == 0){
            return new Result(false,"获取设备失败");
        }
        return new Result(true,"",list);
    }
}
