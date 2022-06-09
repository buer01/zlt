package com.zlt.SSTI.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import org.apache.velocity.app.Velocity;

import java.io.StringWriter;
@CrossOrigin
@RestController
@RequestMapping("/ssti")
@Api
public class SSTIController {
    /**
     * SSTI of Java velocity. The latest Velocity version still has this problem.
     * Fix method: Avoid to use Velocity.evaluate method.
     * <p>
     * http://localhost:8080/ssti/velocity?template=%23set($e=%22e%22);$e.getClass().forName(%22java.lang.Runtime%22).getMethod(%22getRuntime%22,null).invoke(null,null).exec(%22open%20-a%20Calculator%22)
     * Open a calculator in MacOS.
     *
     * @param template exp
     */
    @GetMapping("/velocity")
    @ApiOperation("velocity-ssti注入")
    public String velocity(String template) throws UnsupportedEncodingException {
        //template = template.replaceAll("exec","");
        //template = template.replaceAll("Runtime","");
        //template = template.replaceAll("bash","");
        System.out.println(template);
        Velocity.init();

        VelocityContext context = new VelocityContext();

        context.put("author", "Elliot A.");
        context.put("address", "217 E Broadway");
        context.put("phone", "555-1337");

        StringWriter swOut = new StringWriter();
        try{
            Velocity.evaluate(context, swOut, "test", template);
            return swOut.toString();
        }catch (Exception e){
            e.printStackTrace();
            return "SSTI EXCEPT";
        }
    }
}
