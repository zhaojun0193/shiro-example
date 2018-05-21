package com.zhaojun.shiro.servlet;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhaojun0193
 * @create 2018/5/18
 */
@WebServlet("/formfilterlogin")
public class FormFilterLoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String errorClassName = (String) req.getAttribute("shiroLoginFailure");
        if (UnknownAccountException.class.getName().equals(errorClassName)) {
            req.setAttribute("error", "用户名/密码错误");
        } else if (IncorrectCredentialsException.class.getName().equals(errorClassName)) {
            req.setAttribute("error", "用户名/密码错误");
        } else if (errorClassName != null) {
            req.setAttribute("error", "未知错误：" + errorClassName);
        }
        req.getRequestDispatcher("/WEB-INF/view/formfilterlogin.jsp").forward(req, resp);
    }
}
