package com.zlt.xxe.controller;

import com.zlt.xxe.entity.XXE;

import com.zlt.xxe.utils.ThreadPoolUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.*;

import org.xml.sax.helpers.XMLReaderFactory;
import org.xml.sax.XMLReader;
import org.xml.sax.InputSource;

import java.io.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.concurrent.*;


import com.sun.corba.se.impl.orbutil.closure.Future;
import com.sun.corba.se.impl.orbutil.threadpool.TimeoutException;


/**
 * java xxe vul code
 */
@CrossOrigin
@RestController
@RequestMapping("/xxe")
@Api
public class XXEController {
    private static String EXCEPT = "xxe except";//错误信息
    @PostMapping("/xmlReader/vuln")
    @ApiOperation("xmlReader解析xml测试")
    public String xmlReaderVuln(XXE xxe) {
        //使用线程池获取线程
        ExecutorService thread = ThreadPoolUtils.getThread();

        // 使用ExecutorService执行Callable类型的任务，并将结果保存在future变量中
        FutureTask<String> future = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                try {
                    //获取payload
                    System.out.println(xxe.getPayload());
                    String payload = xxe.getPayload();
                    //waf过滤
//                    payload = payload.replaceAll("file:/","");
//                    payload = payload.replaceAll("SYSTEM","");
//                    payload = payload.replaceAll("ENTITY","");
                    System.out.println(payload);
                    //读取payload
                    XMLReader xmlReader = XMLReaderFactory.createXMLReader();
                    xmlReader.parse(new InputSource(new StringReader(payload)));  // parse xml
                } catch (Exception e) {
                    e.printStackTrace();
                    return EXCEPT;
                }
                return "xmlReader xxe vuln code";
            }
        });
        try {
            //执行任务
            thread.execute(future);

            //任务处理超时时间设为 8秒
            String obj = future.get(1000 * 4, TimeUnit.MILLISECONDS);
            return obj;

        } catch (Exception e) {
            e.printStackTrace();
            return EXCEPT+"/timeout自动响应. if you have get my.ini, ignore except.";
        }
//        try {
//            //获取payload
//            System.out.println(xxe.getPayload());
//            String payload = xxe.getPayload();
////            payload = payload.replaceAll("file:/","");
////            payload = payload.replaceAll("SYSTEM","");
////            payload = payload.replaceAll("ENTITY","");
//            System.out.println(payload);
//            //读取payload
//            XMLReader xmlReader = XMLReaderFactory.createXMLReader();
//            xmlReader.parse(new InputSource(new StringReader(payload)));  // parse xml
//            return "xmlReader xxe vuln code";
//        } catch (Exception e) {
//            return EXCEPT;
//        }
    }

    // documentbuilder有回显
    @PostMapping(value = "/DocumentBuilder/vuln01")
    @ApiOperation("有回显DocumentBuilder")
    public String DocumentBuilderVuln01(XXE xxe) {
        try {
            System.out.println(xxe);
            System.out.println(xxe.getPayload());
            String payload = xxe.getPayload();
            //waf过滤
//            payload = payload.replaceAll("file:/","");
//            payload = payload.replaceAll("SYSTEM","");
//            payload = payload.replaceAll("ENTITY","");
            System.out.println(payload);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            StringReader sr = new StringReader(payload);
            InputSource is = new InputSource(sr);
            Document document = db.parse(is);  // parse xml

            // 遍历xml节点name和value
            StringBuilder buf = new StringBuilder();
            NodeList rootNodeList = document.getChildNodes();
            for (int i = 0; i < rootNodeList.getLength(); i++) {
                Node rootNode = rootNodeList.item(i);
                NodeList child = rootNode.getChildNodes();
                for (int j = 0; j < child.getLength(); j++) {
                    Node node = child.item(j);
                    buf.append(String.format("%s: %s\n", node.getNodeName(), node.getTextContent()));
                }
            }
            sr.close();
            return buf.toString();
        } catch (Exception e) {
            return EXCEPT;
        }
    }
}
