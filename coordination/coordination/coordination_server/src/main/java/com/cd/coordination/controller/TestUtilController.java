package com.cd.coordination.controller;

import com.cd.coordination.TestUnitService;
import com.cd.coordination.global.RedisPoolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller("testUtilController")
@RequestMapping("/testUtil")
public class TestUtilController {


    @Autowired
    TestUnitService testUnitService;

    @RequestMapping("/communicateFind")
    @ResponseBody
    public String communicateFind(@RequestParam("sessionId") String sessionId,@RequestParam String number){
        String student_id = RedisPoolUtil.get(sessionId);
        return testUnitService.communicateFind(student_id,number).toString();
    }

    @RequestMapping("/communicateInsert")
    @ResponseBody
    public String communicateInsert(@RequestParam("sessionId") String sessionId,@RequestParam String data,@RequestParam String heading){
        String student_id = RedisPoolUtil.get(sessionId);
        return testUnitService.communicateInsert(student_id,data,heading).toString();
    }

    @RequestMapping("/testUnitFind")
    @ResponseBody
    public String testUnitFind(@RequestParam("sessionId") String sessionId,@RequestParam String number){
        String student_id = RedisPoolUtil.get(sessionId);
        return testUnitService.testUnitFind(student_id,number).toString();
    }

    @RequestMapping("/testUnit")
    @ResponseBody
    public String testUnit(@RequestParam("sessionId") String sessionId,@RequestParam(value="field",required=false) String field, @RequestParam(value="result",required=false) String result, @RequestParam(value="subject",required=false) String subject,
                           @RequestParam(value="frequency",required=false) String frequency,@RequestParam(value="process",required=false) String process,@RequestParam(value="testprocess",required=false) String testprocess,@RequestParam(value="conclusion",required=false) String conclusion,@RequestParam(value="remark",required=false) String remark,@RequestParam(value="operator",required=false) String operator){
        String student_id = RedisPoolUtil.get(sessionId);
        return testUnitService.testUnit(student_id,field,result,subject,frequency,process,testprocess,conclusion,remark,operator).toString();
    }

    @RequestMapping("/testUnitRevice")
    @ResponseBody
    public String testUnitRevice(@RequestParam("sessionId") String sessionId,@RequestParam  int id){
        String student_id = RedisPoolUtil.get(sessionId);
        return testUnitService.testUnitRevice(student_id,id).toString();
    }

}
