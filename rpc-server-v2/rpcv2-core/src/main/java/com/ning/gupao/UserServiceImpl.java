package com.ning.gupao;

/**
 * @Author JAY
 * @Date 2019/8/3 16:10
 * @Description TODO
 **/
@RpcService(value = IUserService.class)
public class UserServiceImpl implements IUserService {
    @Override
    public String getUsername(String userId) {
        return "用户名：" + userId;
    }
}
