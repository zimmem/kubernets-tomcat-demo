package com.zimmem.kubernets.demo;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kubernets.demo.dubbo.provider.HelloService;

public class DubboServlet extends HttpServlet {

    private HelloService helloService = null;

    @Override
    public void init() throws ServletException {
        super.init();
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-conf/spring-dubbo.xml");
        helloService = context.getBean(HelloService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintStream ps = new PrintStream(resp.getOutputStream());
        ps.println("I'm " + InetAddress.getLocalHost().getHostName());
        ps.print(helloService.sayHello());
        ps.flush();
        ps.close();
    }

}
