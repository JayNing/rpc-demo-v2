package com.ning.gupao;

import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Map;

/**
 * @Author JAY
 * @Date 2019/8/3 14:38
 * @Description TODO
 **/
public class ProcessHandler implements Runnable{

    private Socket socket;
    private Map<String, Object> handlerMap;

    public ProcessHandler(Map<String, Object> handlerMap, Socket socket) {
        this.handlerMap = handlerMap;
        this.socket = socket;
    }

    @Override
    public void run() {
       //通过socket获取客户端发来的调用请求类/方法/参数
        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();
            //反射调用rpc请求
            Object result = invoke(rpcRequest);
            //将结果返回给客户端
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject("服务端调用了客户端发来的请求，结果：" + result);
            objectOutputStream.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (objectInputStream != null){
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (objectOutputStream != null){
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Object invoke(RpcRequest rpcRequest) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        String className = rpcRequest.getClassName();
        String methodName = rpcRequest.getMethodName();
        Object[] params = rpcRequest.getParams();
        String version = rpcRequest.getVersion();
        Class<?>[] paramTypes=new Class[params.length]; //获得每个参数的类型
        if (params != null && params.length > 0){
            for (int i = 0; i<params.length; i++){
                paramTypes[i] = params[i].getClass();
            }
        }
        if (!StringUtils.isEmpty(version)){
            className = className + "_" + version;
        }
        Object service = handlerMap.get(className);
        Class<?> aClass = service.getClass();
        Method method = aClass.getMethod(methodName, paramTypes);
        return method.invoke(service, params);
    }
}
