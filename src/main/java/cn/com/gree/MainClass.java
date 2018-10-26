package cn.com.gree;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MainClass extends SpringBootServletInitializer {

    /**
     * @author Abin
     * @date 2018/8/7 14:19
     * springboot内置入口，开发时启用
     */
    public static void main(String[] args) {
        SpringApplication.run(MainClass.class,args);
    }

    /**
     * @author Abin
     * @date 2018/8/7 12:19
     * tomcat入口，打包后放置于tomcat启动时使用
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MainClass.class);
    }
}
