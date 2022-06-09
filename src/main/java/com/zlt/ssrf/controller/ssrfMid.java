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
public class ssrfMid {
    @RequestMapping(value = "ssrfmid",method = RequestMethod.GET)
    public String ssrf(String url , HttpServletRequest request){

        //过滤ip
        if(url.contains("127.0.0.1")||url.contains("localhost")||url.contains("flag")){
            return "url is not allowed";
        }


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
