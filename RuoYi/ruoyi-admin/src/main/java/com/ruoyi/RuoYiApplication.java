package com.ruoyi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 * 
 * @author ruoyi
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@MapperScan("com.ruoyi.*.mapper")
public class RuoYiApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(RuoYiApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  活动管理后台启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                "____   _   _    ___    ___         \n" +
                "/ __| | | | |  / __|  / __|        \n" +
                "\\__ \\ | |_| | | (__  | (__       \n" +
                "|___/  \\__,_|  \\___|  \\___|     \n");
    }
}