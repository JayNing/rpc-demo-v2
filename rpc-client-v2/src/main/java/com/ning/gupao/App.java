package com.ning.gupao;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        RpcProxyClient rpcProxyClient = new RpcProxyClient();
//        IHelloService helloService = rpcProxyClient.clientProxy(IHelloService.class,"localhost",8080,"v2.0");
//        String msg = helloService.sayHello("你好");
//        System.out.println(msg);

        RpcProxyClient rpcProxyClient = new RpcProxyClient();
        IUserService service = rpcProxyClient.clientProxy(IUserService.class,"localhost",8080,"");
        String msg = service.getUsername("你好");
        System.out.println(msg);
    }
}
