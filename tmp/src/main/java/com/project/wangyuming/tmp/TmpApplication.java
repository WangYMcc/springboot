package com.project.wangyuming.tmp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackages = {"com.project.wangyuming.tmp.*"})
@ServletComponentScan
@EnableCaching
@EnableSwagger2
@MapperScan(value = {"com.project.wangyuming.tmp.dao"})
public class TmpApplication {
    public static void main(String[] args){
        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(TmpApplication.class, args);

    }
}
