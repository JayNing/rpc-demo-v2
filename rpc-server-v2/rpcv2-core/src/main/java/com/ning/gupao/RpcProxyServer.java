package com.ning.gupao;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author JAY
 * @Date 2019/8/3 14:35
 * @Description TODO
 **/
public class RpcProxyServer {

   /* ExecutorService executorService =
            new ThreadPoolExecutor(3,5,3000,TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>());


    public void publisher(Object service,int port){

        ServerSocket serverSocket = null;

        try {
            //建立链接
            serverSocket = new ServerSocket(port);

            while (true){//不断接受请求
                Socket socket = serverSocket.accept();
                //每一个socket 交给一个processorHandler来处理
                executorService.execute(new ProcessHandler(service, socket));
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
    }*/
}
