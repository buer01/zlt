package com.zlt.rce.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

@RestController
@CrossOrigin
public class RCEMiddle {
    @RequestMapping("rcemid")
    public String rce(String cmd){
        BufferedReader br = null;
        try {
//            String command = cmd;
            String t = cmd.replace("&&","");
            String t2 = t.replace(";","");
            String cmdnew = t2;

            //linux
            String[] command = new String[] { "/bin/bash", "-c", "ping -c 1 "+cmdnew };
            Process p = Runtime.getRuntime().exec(command);
            br = new BufferedReader(new InputStreamReader(p.getInputStream(), Charset.forName("GBK")));
            String line = null;
            StringBuilder sb=new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line+"\n");
            }
            return sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return  "fail to exec";
    }
}
