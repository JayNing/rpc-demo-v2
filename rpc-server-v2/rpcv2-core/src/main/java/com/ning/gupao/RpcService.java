package com.ning.gupao;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author JAY
 * @Date 2019/8/3 15:35
 * @Description 通过注解方式实现RPC
 **/

@Target(ElementType.TYPE) //作用域
@Retention(RetentionPolicy.RUNTIME) //运行时
@Component
public @interface RpcService {

    Class<?> value(); //拿到服务的接口

    /**
     * 版本号
     */
    String version() default "";

}
