package com.dai.mall.service;

import com.dai.mall.exception.ImoocMallException;
import com.dai.mall.model.pojo.User;

public interface UserService {

    void register(String userName,String password) throws ImoocMallException;

    User login(String userName, String password) throws ImoocMallException;

    void updateInformation(User user) throws ImoocMallException;

    boolean checkAdminRole(User user);

}
