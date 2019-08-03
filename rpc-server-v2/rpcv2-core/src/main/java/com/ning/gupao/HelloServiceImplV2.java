package com.ning.gupao;

/**
 * @Author JAY
 * @Date 2019/8/3 14:26
 * @Description TODO
 **/
@RpcService(value = IHelloService.class, version = "v2.0")
public class HelloServiceImplV2 implements IHelloService {
    @Override
    public String sayHello(String msg) {
        System.out.println("【v2.0】HelloServiceImpl sayHello : " + msg);
        return "SUCCESS";
    }
}
