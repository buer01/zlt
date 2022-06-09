package com.zlt.ssrf.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

@RestController
@CrossOrigin
public class SsrfLow {
    @RequestMapping(value = "ssrflow",method = RequestMethod.GET)
    public String ssrf(String url , HttpServletRequest request){


//        //判断ip来源
//        String ip = "";
//        if (request.getHeader("x-forwarded-for") == null) {
//            ip = request.getRemoteAddr();
//            System.out.println("remote");
//        }else{
//            ip = request.getHeader("x-forwarded-for");
//            System.out.println("x-forwarded-for");
//        }
//        System.out.println(ip);
//        if(!ip.equals("127.0.0.1")){
//            return "ip is not local";
//        }


        String htmlContent;
        try {
            URL u = new URL(url);
            URLConnection urlConnection = u.openConnection();
            BufferedReader base = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuffer html = new StringBuffer();

            while ((htmlContent = base.readLine()) != null) {
                html.append(htmlContent);

            }
            base.close();
            System.out.println(html.toString());
//            print.flush();

            return html.toString();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR!");
//            print.flush();
            return "error";
        }

    }

}
