package com.cd.coordination.controller;


import com.cd.coordination.LevelService;
import com.cd.coordination.global.RedisPoolUtil;
import com.mysql.cj.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller("levelController")
@RequestMapping("level")
public class LevelController {

    @Autowired
    LevelService levelService;

    @RequestMapping("/demandLevel")
    @ResponseBody
    public String demandLevel(@RequestParam("sessionId") String sessionId){
        String student_id = RedisPoolUtil.get(sessionId);
        return levelService.demandLevel(student_id).toString();
    }

    @RequestMapping("/intermediateLevel")
    @ResponseBody
    public String intermediateLevel(@RequestParam("sessionId") String sessionId){
        String student_id = RedisPoolUtil.get(sessionId);
        return levelService.intermediateLevel(student_id).toString();
    }

    @ResponseBody
    @RequestMapping("/missionLevel")
    public String missionLevel(@RequestParam("sessionId") String sessionId) throws Exception {
        String student_id = RedisPoolUtil.get(sessionId);
        return levelService.missionLevel(student_id).toString();
    }

    @RequestMapping("/primaryLevel")
    @ResponseBody
    public String primaryLevel(@RequestParam("sessionId") String sessionId){
        String student_id = RedisPoolUtil.get(sessionId);
        return levelService.primaryLevel(student_id).toString();
    }

    @ResponseBody
    @RequestMapping("/seniorLevel")
    public String seniorLevel(@RequestParam("sessionId") String sessionId){
        String student_id = RedisPoolUtil.get(sessionId);
        return levelService.seniorLevel(student_id).toString();
    }
}
