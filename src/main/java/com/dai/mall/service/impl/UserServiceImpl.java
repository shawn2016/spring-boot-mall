package com.dai.mall.service.impl;

import com.dai.mall.exception.ImoocMallException;
import com.dai.mall.exception.ImoocMallExceptionEnum;
import com.dai.mall.model.dao.UserMapper;
import com.dai.mall.model.pojo.User;
import com.dai.mall.service.UserService;
import com.imooc.anti.Constant;
import com.imooc.anti.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public void register(String userName, String password) throws ImoocMallException {
        User result = userMapper.selectByName(userName);
        if (result !=null) {
            throw new ImoocMallException(ImoocMallExceptionEnum.NAME_EXISTED);
        }
    }

    @Override
    public User login(String userName, String password) throws ImoocMallException {
        String md5Password = null;

        try {
            md5Password = MD5Utils.getMD5Str(password, Constant.ICODE);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        User user = userMapper.selectLogin(userName,password);
        if (user ==null) {
            throw new ImoocMallException(ImoocMallExceptionEnum.WRONG_PASSWORD);
        }

        return user;
    }

    @Override
    public void updateInformation(User user) throws ImoocMallException {
        //更新个性签名
        int updateCount = userMapper.updateByPrimaryKeySelective(user);
        if (updateCount > 1) {
            throw new ImoocMallException(ImoocMallExceptionEnum.UPDATE_FAILED);
        }
    }

    @Override
    public boolean checkAdminRole(User user) {
        //1是普通用户，2是管理员
        return user.getRole().equals(2);
    }
}
