package com.ning.gupao;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author JAY
 * @Date 2019/8/3 15:50
 * @Description TODO
 **/
public class JNRpcServer implements ApplicationContextAware, InitializingBean {

    private int port;

    ExecutorService executorService =
            new ThreadPoolExecutor(3,5,3000,TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>());


    private Map<String, Object> handlerMap = new HashMap<>();

    public JNRpcServer(int port) {
        this.port = port;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //创建socket连接
        ServerSocket serverSocket = null;

        try {
            //建立链接
            serverSocket = new ServerSocket(port);

            while (true){//不断接受请求
                Socket socket = serverSocket.accept();
                //每一个socket 交给一个processorHandler来处理
                executorService.execute(new ProcessHandler(handlerMap, socket));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (serverSocket != null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        //加载注解bean
        //获取使用了RpcService注解的所有bean
        Map<String, Object> serviceBeanMap = applicationContext.getBeansWithAnnotation(RpcService.class);
        if (!serviceBeanMap.isEmpty()){
            for (Object servcieBean : serviceBeanMap.values()) {
                //获取bean的注解参数
                RpcService annotation = servcieBean.getClass().getAnnotation(RpcService.class);
                String beanName = annotation.value().getName();
                String version = annotation.version();
                if (!StringUtils.isEmpty(version)){
                    beanName = beanName + "_" + version;
                }
                handlerMap.put(beanName,servcieBean);
            }
        }
    }
}
