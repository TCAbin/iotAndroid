package cn.com.gree.service.impl;

import cn.com.gree.dao.BaseDao;
import cn.com.gree.entity.User;
import cn.com.gree.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("UserService")
public class UserServiceImpl implements UserService {

    @Resource(name = "BaseDao")
    private BaseDao baseDao;

    /**
     * @author Abin
     * @date 2018/8/7 14:33
     * 更改设备地区，需要隐私操作
     * 故在地区更改的时候同时需要验证密码
     */
    @Override
    public boolean verifyOptionPassword(String password) {
        List<User> users = baseDao.getAll(User.class);
        if(users == null || users.size() != 1){
            return false;
        }
        User user = users.get(0);
        return user.getOptionPassWord().equals(password);
    }
}
