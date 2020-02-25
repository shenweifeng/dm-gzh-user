package com.guozhengood.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:task/*.xml")
public class QuartzScheduleConfig {

}
