package com.mkst.mini.systemplus;

import com.mkst.mini.systemplus.common.config.Global;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 * 
 * @author admin
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@MapperScan("com.mkst.mini.systemplus.*.mapper")
public class SystemPlusApplication {
    public static void main(String[] args){
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(SystemPlusApplication.class, args);
        StringBuilder sb = new StringBuilder();
        sb.append("\r\n======================================================================\r\n");
        sb.append("\r\n    欢迎使用 "+ Global.getName()+" Version: "+Global.getVersion()+ "- Powered By http://www.szmkst.com\r\n");
        sb.append("\r\n======================================================================\r\n");
        System.out.println(sb.toString());
    }

}