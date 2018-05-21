package com.zhaojun.shiro.servlet;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

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
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String resultMessage = null;

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(username,password);

        try{
            subject.login(token);
        }catch (UnknownAccountException e){
            resultMessage = "用户名/密码错误";
        }catch (IncorrectCredentialsException e){
            resultMessage = "用户名/密码错误";
        }catch (AuthenticationException e){
            //其他错误，比如锁定，如果想单独处理请单独catch处理
            resultMessage = "其他错误：" + e.getMessage();
        }

        if(resultMessage != null){
            req.setAttribute("result",resultMessage);
            req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req,resp);
        }else {
            req.getRequestDispatcher("/WEB-INF/view/loginSuccess.jsp").forward(req,resp);
        }
    }
}
