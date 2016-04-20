/**
 * 
 */
package com.zimmem.kubernets.demo;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhaowen_zhuang
 */
public class MainServlet extends HttpServlet {

    private static final long serialVersionUID = 7939222279745985773L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        InetAddress netAddress = InetAddress.getLocalHost();
        PrintStream ps = new PrintStream(resp.getOutputStream());
        ps.println(netAddress.getHostName());
        
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            NetworkInterface networkInterface = (NetworkInterface) interfaces.nextElement();
            ps.println(networkInterface.getName() + " " + networkInterface.getDisplayName());
            networkInterface.getInterfaceAddresses().forEach(i -> {
                ps.println(i.getAddress().getHostName() + " " + i.getAddress().getHostAddress());
            });
            ps.println();
            
        }
        
        ps.close();
    }

}
