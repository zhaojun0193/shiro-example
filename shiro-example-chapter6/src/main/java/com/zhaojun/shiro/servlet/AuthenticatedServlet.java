package com.zhaojun.shiro.servlet;

import org.apache.shiro.SecurityUtils;
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
@WebServlet("/authenticated")
public class AuthenticatedServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Subject subject = SecurityUtils.getSubject();

        if(subject.isAuthenticated()){
            req.getRequestDispatcher("/WEB-INF/view/authenticated.jsp").forward(req,resp);
        }else {
            req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req,resp);
        }
    }
}
