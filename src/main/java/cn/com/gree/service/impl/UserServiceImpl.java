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
