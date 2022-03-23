package com.example.boot.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置类
 * Created by macro on 2019/4/8.
 */
@Configuration
@MapperScan({"com.example.boot.mbg.mapper","com.example.boot.dao"})
public class MyBatisConfig {
}
