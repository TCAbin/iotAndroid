package cn.com.gree.controller;

import cn.com.gree.service.DeviceDataService;
import cn.com.gree.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@Api(description = "数据获取接口")
@RestController
@RequestMapping("data")
public class DataController {

    @Resource(name = "DeviceDataService")
    private DeviceDataService dataService;

    @ApiOperation(value = "获取单个设备最新数据",notes = "3.3需求")
    @ApiImplicitParam(name = "BarCode",value = "条码值",required = true,dataType = "String",paramType = "query")
    @RequestMapping(value = "getDataByBarCode",method = RequestMethod.GET)
    public Result getLatestDeviceDataByBarCode(String BarCode){
        Map map = dataService.getLatestDeviceDataByBarCode(BarCode);
        if(map.size() == 0){
            return new Result(false,"获取设备数据失败");
        }
        return new Result(true,"",map);
    }
}
