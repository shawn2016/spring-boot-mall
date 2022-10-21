package com.dai.mall.controller;

import com.dai.mall.common.ApiRestResponse;
import com.dai.mall.common.Constant;
import com.dai.mall.exception.ImoocMallException;
import com.dai.mall.exception.ImoocMallExceptionEnum;
import com.dai.mall.model.pojo.User;
import com.dai.mall.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation("test")
    @GetMapping("/test")
    @ResponseBody
    public String personalPage(){
        return "hello!!";
    }

    /*
    * 注册
    * */
    @ApiOperation("用户注册")
    @PostMapping("/register")
    @ResponseBody
    public ApiRestResponse register(@RequestParam("userName") String userName, @RequestParam("password") String password){
//        判空
        if (!StringUtils.hasText(userName)) {
            return ApiRestResponse.error(ImoocMallExceptionEnum.NEED_USER_NAME);
        }

        if (!StringUtils.hasText(password)) {
            return ApiRestResponse.error(ImoocMallExceptionEnum.NEED_PASSWORD);
        }
        if(password.length()<8){
          return ApiRestResponse.error(ImoocMallExceptionEnum.PASSWORD_TOO_SHORT);
        }

        userService.register(userName,password);
        return ApiRestResponse.success();
    }
    /*
    *
    * 登录
    *
    * */
    @PostMapping("/login")
    @ApiOperation("用户登录")
    @ResponseBody
    public ApiRestResponse login(@RequestParam("userName") String userName, @RequestParam("password") String password, HttpSession session){
        if (!StringUtils.hasText(userName)) {
            return ApiRestResponse.error(ImoocMallExceptionEnum.NEED_USER_NAME);
        }

        if (!StringUtils.hasText(password)) {
            return ApiRestResponse.error(ImoocMallExceptionEnum.NEED_PASSWORD);
        }
        User user = userService.login(userName,password);

        user.setPassword(null);

        session.setAttribute(Constant.IMOOC_MALL_USER,user);
        return  ApiRestResponse.success();
    }

    /*
     *
     * 更新个性签名
     *
     * */
    @PostMapping("/user/update")
    @ApiOperation("更新个性签名")
    @ResponseBody

    public ApiRestResponse updateUserInfo(HttpSession session,@RequestParam String signature){
        User currentUser = (User) session.getAttribute(Constant.IMOOC_MALL_USER);
        if (currentUser==null) {
            return ApiRestResponse.error(ImoocMallExceptionEnum.NEED_LOGIN);
        }
        User user = new User();
        user.setId(currentUser.getId());
        user.setPersonalizedSignature(signature);
        userService.updateInformation(user);
        return ApiRestResponse.success();
    }


    /**
     * 登出，清除session
     */
    @PostMapping("/user/logout")
    @ResponseBody
    public ApiRestResponse logout(HttpSession session) {
        session.removeAttribute(Constant.IMOOC_MALL_USER);
        return ApiRestResponse.success();
    }

    /**
     * 管理员登录接口
     */
    @PostMapping("/adminLogin")
    @ResponseBody
    public ApiRestResponse adminLogin(@RequestParam("userName") String userName,
                                      @RequestParam("password") String password, HttpSession session)
            throws ImoocMallException {
        if (StringUtils.isEmpty(userName)) {
            return ApiRestResponse.error(ImoocMallExceptionEnum.NEED_USER_NAME);
        }
        if (StringUtils.isEmpty(password)) {
            return ApiRestResponse.error(ImoocMallExceptionEnum.NEED_PASSWORD);
        }
        User user = userService.login(userName, password);
        //校验是否是管理员
        if (userService.checkAdminRole(user)) {
            //是管理员，执行操作
            //保存用户信息时，不保存密码
            user.setPassword(null);
            session.setAttribute(Constant.IMOOC_MALL_USER, user);
            return ApiRestResponse.success(user);
        } else {
            return ApiRestResponse.error(ImoocMallExceptionEnum.NEED_ADMIN);
        }
    }

}
