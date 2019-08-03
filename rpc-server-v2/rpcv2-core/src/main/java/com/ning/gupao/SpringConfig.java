package com.ning.gupao;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @Author JAY
 * @Date 2019/8/3 15:50
 * @Description TODO
 **/
@Configurable
@ComponentScan(basePackages = "com.ning.gupao")
public class SpringConfig {

    @Bean("jnRpcServer")
    public JNRpcServer jnRpcServer(){
        return new JNRpcServer(8080);
    }

}
