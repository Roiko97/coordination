package com.cd.coordination.controller;

import com.cd.coordination.global.RedisPoolUtil;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller("jumpController")
@RequestMapping("jump")
public class JumpController {

    @RequestMapping(value = "/home")
    public String home() {
        return "on";
    }

    @RequestMapping("/toSummarize")
    public String toSummarize(HttpSession session){
        String student_id = (String)session.getAttribute("student_id");
        System.out.println("走到这里");
        if(student_id == null)
            return "redirect:home";
        else
            return "Summarize";
    }

    @RequestMapping("/Summarize")
    public String Summarize(@Param("id") String id,HttpSession session){
        String redisStudentId = RedisPoolUtil.get(id); //session_ID
        System.out.println("前端"+redisStudentId);
        if(redisStudentId ==null){
            return "redirect:home";
        }else{
            session.setAttribute("student_id",redisStudentId);
            return "Summarize";
        }
    }

    @RequestMapping("/toCommunicate")
    public String toCommunicate(HttpSession session){
        String student_id = (String)session.getAttribute("student_id");
        if(student_id == null)
            return "redirect:home";
        else
            return "Communicate";
    }

    @RequestMapping("/toDemand")
    public String toDemand(HttpSession session){
        String student_id = (String)session.getAttribute("student_id");
        if(student_id == null)
            return "redirect:home";
        else
            return "Demand";
    }
    @RequestMapping("/toDevelopment")
    public String toDevelopment(HttpSession session){
        String student_id = (String)session.getAttribute("student_id");
        if(student_id == null)
            return "redirect:home";
        else
            return "Development";
    }
    @RequestMapping("/toDocument")
    public String toDocument(HttpSession session){
        String student_id = (String)session.getAttribute("student_id");
        if(student_id == null)
            return "redirect:home";
        else
            return "Document";
    }
    @RequestMapping("/toFileExport")
    public String toFileExport(HttpSession session){
        String student_id = (String)session.getAttribute("student_id");
        if(student_id == null)
            return "redirect:home";
        else
            return "FileExport";
    }
    @RequestMapping("/toTestUnit")
    public String toTestUnit(HttpSession session){
        String student_id = (String)session.getAttribute("student_id");
        if(student_id == null)
            return "redirect:home";
        else
            return "TestUnit";
    }
}
