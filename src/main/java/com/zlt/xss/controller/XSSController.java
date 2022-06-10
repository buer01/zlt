package com.zlt.xss.controller;

import com.zlt.xss.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("XSS")//表示指定类，常用于整合
@RestController
@Api
public class XSSController {
    public static String htmlEncode(String source) {
        if (source == null) {
            return "";
        }
        String html = "";
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < source.length(); i++) {
            char c = source.charAt(i);
            switch (c) {
                case '<':
                    buffer.append("&lt;");
                    break;
                case '>':
                    buffer.append("&gt;");
                    break;
                case '&':
                    buffer.append("&amp;");
                    break;
                case 10:
                case 13:
                    break;
                default:
                    buffer.append(c);
            }
        }
        html = buffer.toString();
        return html;
    }

    @GetMapping("xss1")
    @ApiOperation("没有进行过滤，攻击者可以输入js代码进行攻击")
    @ResponseBody
    public String xss1(String username) throws Exception {
        User user = new User();
        user.setUsername(username);
        return user.getUsername();
    }//"></div><script>alert();</script>

    @GetMapping("xss2")
    @ApiOperation("利用函数对输入内容进行过滤。该函数是对输入的字符串中一些字符进行过滤")
    @ResponseBody
    public String xss2(String username) throws Exception {
        username = username.replace("script", "");
        User user = new User();
        System.out.println(username);
        user.setUsername(username);
        return user.getUsername();
    }//"></div><scscriptript>alert("1")</scrscriptipt>


    @GetMapping("xss3")
    @ApiOperation("无法使用script语句攻击，转用dom安全事件")
    @ResponseBody
    public String xss3(String username) throws Exception {
        username = htmlEncode(username);
        User user = new User();
        user.setUsername(username);
        System.out.println(username);
        return user.getUsername();
    }//122" onclick='alert()'

}