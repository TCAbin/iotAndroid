package cn.com.gree.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "校验管理员密码")
@RestController
@RequestMapping("user")
public class UserController {

//    @Resource(name = "UserService")
//    private UserService userService;
//
//    @ApiOperation(value = "验证管理员密码")
//    @ApiImplicitParam(name = "password",value = "密码",required = true,paramType = "query",dataType = "String")
//    @RequestMapping(value = "verifyOptionPassword",method = RequestMethod.POST)
//    public Result verifyOptionPassword(String password){
//        boolean flag = userService.verifyOptionPassword(password);
//        if(flag){
//            return new Result(true,"通过验证");
//        }else{
//            return new Result(false,"密码验证失败");
//        }
//    }
}
