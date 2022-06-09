package com.zlt.ssrf.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
public class flag {
    @RequestMapping(value = "flag",method = RequestMethod.GET)
    public String flag(HttpServletRequest request){
        //判断ip来源
        String ip = "";
        if (request.getHeader("x-forwarded-for") == null) {
            ip = request.getRemoteAddr();
            System.out.println("remote");
        }else{
            ip = request.getHeader("x-forwarded-for");
            System.out.println("x-forwarded-for");
            return "好小子,还会伪造";
        }
        System.out.println(ip);
        if(!ip.equals("127.0.0.1")){
            return "ip is not local";
        }
        else {
            return "flag{rmktd-dasa-yuj-afsda}";
        }
    }
}
