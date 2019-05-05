package com.baowei.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

import static com.sun.tools.doclets.formats.html.markup.HtmlStyle.header;

@Controller
public class LoginController {
    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpServletResponse response;

    @RequestMapping("/check")
    @ResponseBody
    public String check(HttpSession session) throws Exception {

        String str = "";
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if(username.equals("admin") && password.equals("123456")){
            session.setAttribute("loginUser", username);
            Cookie c1 = new Cookie("loginName", username);
            c1.setPath("/");
            response.addCookie(c1);
            str = "redirect:/";
        }
        else{
            str = "redirect:/login.html";
        }

        return str;
    }
}
