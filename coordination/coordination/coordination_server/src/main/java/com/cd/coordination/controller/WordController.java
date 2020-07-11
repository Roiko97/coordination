package com.cd.coordination.controller;


import com.cd.coordination.WordService;
import com.cd.coordination.global.RedisPoolUtil;
import com.cd.coordination.global.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller("wordController")
@RequestMapping("/word")
public class WordController {

    @Autowired
    WordService wordService;


    @RequestMapping("/cooperationExport")
    @ResponseBody
    public ServerResponse cooperationExport(@RequestParam("sessionId") String sessionId,HttpServletRequest request, HttpServletResponse response){
        String student_id = RedisPoolUtil.get(sessionId);
        wordService.cooperationExport(student_id,request,response);
        return ServerResponse.createBySuccess();
    }

    @RequestMapping("/demandWrite")
    @ResponseBody
    public String demandWrite(@RequestParam("sessionId") String sessionId,@RequestParam String date, @RequestParam String title){
        String student_id = RedisPoolUtil.get(sessionId);
        return wordService.demandWrite(student_id,date,title).toString();
    }

    @RequestMapping("/demo")
    @ResponseBody
    public String demo(@RequestParam("sessionId") String sessionId,@RequestParam String id, @RequestParam String level){
        String student_id = RedisPoolUtil.get(sessionId);
        return wordService.demo(student_id,id,level).toString();
    }

    @RequestMapping("/exWord")
    @ResponseBody
    public ServerResponse exWord(@RequestParam("sessionId") String sessionId,HttpServletRequest request, HttpServletResponse response){
        String student_id = RedisPoolUtil.get(sessionId);
        System.out.println(student_id);
        wordService.exWord(student_id,request,response);
        return ServerResponse.createBySuccess();
    }

    @RequestMapping("/tablefind")
    @ResponseBody
    public String tablefind(@RequestParam("sessionId") String sessionId){
        String student_id = RedisPoolUtil.get(sessionId);
        return wordService.tablefind(student_id).toString();
    }

    @RequestMapping("/tableWrite")
    @ResponseBody
    public String tableWrite(@RequestParam("sessionId") String sessionId,@RequestParam String data){
        String student_id = RedisPoolUtil.get(sessionId);
        return wordService.tableWrite(student_id,data).toString();
    }

    @RequestMapping("/templateSelect")
    @ResponseBody
    public String templateSelect(@RequestParam("sessionId") String sessionId,@RequestParam String mark, @RequestParam String id){
        String student_id = RedisPoolUtil.get(sessionId);
        return wordService.templateSelect(mark,id).toString();
    }

    @RequestMapping("/testExport")
    @ResponseBody
    public ServerResponse testExport(@RequestParam("sessionId") String sessionId,HttpServletRequest request, HttpServletResponse response){
        String student_id = RedisPoolUtil.get(sessionId);
        wordService.testExport(student_id,request,response);
        return ServerResponse.createBySuccess();
    }

}
