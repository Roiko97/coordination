package com.cd.coordination.controller;


import com.cd.coordination.TeamService;
import com.cd.coordination.global.RedisPoolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//import org.json.JSONObject;

@Controller("teamController")
@RequestMapping("/team")
public class TeamController {

    @Autowired
    TeamService teamService;


    @RequestMapping("/teamFind")
    @ResponseBody
    public String teamFind(@RequestParam("sessionId") String sessionId, HttpSession session){//@RequestParam("student_id") String std_id){
        System.out.println("正在执行teamFind"+sessionId);
        String student_id = RedisPoolUtil.get(sessionId);
        System.out.println("this !!!"+student_id);
        return teamService.teamFind(student_id).toString();
    }


    @RequestMapping("/teamCreate")
    @ResponseBody
    public String teamCreate(@RequestParam("sessionId") String sessionId,@RequestParam  String heading, @RequestParam String endT){
        String student_id = RedisPoolUtil.get(sessionId);
        return teamService.teamCreate(student_id,heading,endT);
    }

    @RequestMapping("/teamChange")
    @ResponseBody
    public String teamChange(@RequestParam("sessionId") String sessionId,@RequestParam  String mark){
        String student_id = RedisPoolUtil.get(sessionId);
        return teamService.teamChange(student_id,mark);
    }

    @RequestMapping("/sendMessage")
    @ResponseBody
    public String sendMessage(@RequestParam("sessionId") String sessionId,@RequestParam(value="receiveid",required=false)String receiveid){
        String student_id = RedisPoolUtil.get(sessionId);
        return teamService.sendMessage(student_id,receiveid);
    }

    @RequestMapping("/receiveMessage")
    @ResponseBody
    public String receiveMessage(@RequestParam("sessionId") String sessionId,@RequestParam  String result , @RequestParam String mark ){
        String student_id = RedisPoolUtil.get(sessionId);
        return teamService.receiveMessage(result,mark,student_id);
    }

    @RequestMapping("/positionRevice")
    @ResponseBody
    public String positionRevice(@RequestParam("sessionId") String sessionId,@RequestParam String position){
        String student_id = RedisPoolUtil.get(sessionId);
        return teamService.positionRevice(student_id,position);
    }

    @RequestMapping("/leaveTeam")
    @ResponseBody
    public String leaveTeam(@RequestParam("sessionId") String sessionId){
        String student_id = RedisPoolUtil.get(sessionId);
        return teamService.leaveTeam(student_id);
    }

    @RequestMapping("/memberFind")
    @ResponseBody
    public String memberFind(@RequestParam("sessionId") String sessionId){
        String student_id = RedisPoolUtil.get(sessionId);
        System.out.println("memberFind="+teamService.memberFind(student_id).toString());
        return teamService.memberFind(student_id).toString();
    }

    @RequestMapping("/checkMessage")
    @ResponseBody
    public String checkMessage(@RequestParam("sessionId") String sessionId){
        System.out.println("checkmessage");
        String student_id = RedisPoolUtil.get(sessionId);
        return teamService.checkMessage(student_id).toString();
    }
}
