package com.hydp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hydp.dto.LoginFormDTO;
import com.hydp.dto.Result;
import com.hydp.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
public interface IUserService extends IService<User> {

    Result sendCode(String phone, HttpSession session, HttpServletRequest request);

    Result login(LoginFormDTO loginForm, HttpSession session);

    Result sign();

    Result signCount();

    List<Long> queryHotUser();
}
