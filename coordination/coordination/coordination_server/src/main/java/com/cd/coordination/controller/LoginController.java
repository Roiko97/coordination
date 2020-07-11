package com.cd.coordination.controller;

import com.cd.coordination.UserService;
import com.cd.coordination.dao.UserDao;
import com.cd.coordination.global.RedisPoolUtil;
import org.apache.catalina.Session;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller("loginController")
@RequestMapping("/login")
public class LoginController {


    @Qualifier("userDao")
    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/testLogin")
    public String testLogin() {

        Map<String,String> map = new HashMap<>();
        map.put("hello","world");
        System.out.println(map);
        RedisPoolUtil.set("sessionId",map.toString());
        System.out.println("get:" +RedisPoolUtil.get("sessionId"));
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/userLogin")
    public String  userLogin(HttpSession session ,@RequestParam("student_id") String student_id,
                            @RequestParam("password") String password) {
        JSONObject jsonObject = userService.userLogin(student_id, password);
        RedisPoolUtil.setEx(session.getId(), student_id, 60 * 60);
        jsonObject.put("sessionId",session.getId());
        jsonObject.put("studentId",student_id);

        System.out.println(jsonObject);
        return jsonObject.toString();

    }

    @ResponseBody
    @RequestMapping("/userRevice")
    public String userRevice(@RequestParam("sessionId") String sessionId,@RequestParam("password") String password) {

        String student_id = RedisPoolUtil.get(sessionId);
        String old_pwd = userService.userPsw(student_id);
        return userService.userRevice(student_id, password, old_pwd).toString();
    }

    @RequestMapping("/userMessage")
    @ResponseBody
    public String userMessage(@RequestParam("sessionId") String sessionId) {
        String student_id = RedisPoolUtil.get(sessionId);
        return userService.userMessage(student_id).toString();
    }

    @RequestMapping("/userCancellation")
    @ResponseBody
    public String userCancellation(@RequestParam("sessionId") String sessionId ){
        RedisPoolUtil.del(sessionId);
        System.out.println("成功");
        return "true";
    }

}
