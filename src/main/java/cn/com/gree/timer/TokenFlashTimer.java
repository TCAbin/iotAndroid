package cn.com.gree.timer;

import cn.com.gree.service.TokenDataService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Abin
 * @date 2018/8/7 14:30
 * token定时器，由于华为平台token有效期为一个小时
 * 故每小时刷新token
 */
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
