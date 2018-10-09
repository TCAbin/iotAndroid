package cn.com.gree.controller;

import cn.com.gree.entity.Do.UpdateDeviceDo;
import cn.com.gree.service.DevicesService;
import cn.com.gree.service.UserService;
import cn.com.gree.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestBody;
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

    @Resource(name = "UserService")
    private UserService userService;

    @ApiOperation(value = "获取设备和设备部分最新数据",notes = "3.4需求")
    @RequestMapping(value = "getAllDevices",method = RequestMethod.GET)
    public Result getAllDevices(){
        List list = devicesService.getLocalAllDevices();
        if(list == null){
            return new Result(false,"获取设备失败");
        }
        return new Result(true,"查询成功",list);
    }

    @ApiOperation(value = "更新设备地区")
    @RequestMapping(value = "updateDevicesArea",method = RequestMethod.POST)
    public Result updateDevicesArea(@RequestBody @ApiParam UpdateDeviceDo deviceDo){
        if(deviceDo.getBarCode() == null || deviceDo.getBarCode().equals("")){
            return new Result(false,"条形码不能为空");
        }
        if(deviceDo.getArea() == null || deviceDo.getArea().equals("")){
            return new Result(false,"地区不能为空");
        }
        if(deviceDo.getPassword() == null || deviceDo.getPassword().equals("")){
            return new Result(false,"密码不能为空");
        }
        boolean verify = userService.verifyOptionPassword(deviceDo.getPassword());
        if(!verify){
            return new Result(false,"密码错误");
        }
        int flag = devicesService.updateArea(deviceDo.getArea(),deviceDo.getBarCode());
        if(flag == 0){
            return new Result(true,"更新成功");
        }
        return new Result(false,"更新失败,地区名已存在");
    }
}
