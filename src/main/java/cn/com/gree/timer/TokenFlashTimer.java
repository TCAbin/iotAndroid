package cn.com.gree.timer;

import cn.com.gree.service.TokenDataService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class TokenFlashTimer {


    @Resource(name = "TokenDataService")
    private TokenDataService tokenDataService;

    /**
     * @author 260145
     * @date 2018/6/26 9:33
     * 每小时刷新token
     */
    @Scheduled(cron = "0 47 * * * *")
    private void getToken(){
        tokenDataService.refreshToken();
    }

}
