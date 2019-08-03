package com.ning.gupao;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * @Author JAY
 * @Date 2019/8/3 15:09
 * @Description TODO
 **/
public class RpcClientInvocationHandler implements InvocationHandler {

    private String host;
    private String version;
    private int port;

    public RpcClientInvocationHandler(String host, int port, String version) {
        this.host = host;
        this.port = port;
        this.version = version;
    }

    @Override
    public Object invoke(Object service, Method method, Object[] args) throws Throwable {

        Socket socket = new Socket(host,port);
        //封装请求参数
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setParams(args);
        rpcRequest.setVersion(version);

        ObjectOutputStream objectOutputStream = null;
        ObjectInputStream objectInputStream = null;
        Object result = null;
        try {
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(rpcRequest);
            objectOutputStream.flush();

            objectInputStream = new ObjectInputStream(socket.getInputStream());
            result = objectInputStream.readObject();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (objectOutputStream != null){
              objectOutputStream.close();
            }
            if (objectInputStream != null){
                objectInputStream.close();
            }
        }

        return result;
    }
}
