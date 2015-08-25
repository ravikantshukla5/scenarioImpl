package com.ramselabs.scenario.filter;

import java.io.IOException;

import javax.faces.application.ResourceHandler;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ramselabs.scenario.controller.LoginController;


/**
 * Filter checks if LoginBean has loginIn property set to true.
 * If it is not set then request is being redirected to the login.xhml page.
 *
 *
 *
 */
public class LoginFilter implements Filter {
 
    /**
     * Checks if user is logged in. If not it redirects to the login.xhtml page.
     */
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
    	HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        
    	if (!request.getRequestURI().contains("secured")) {
            //do not apply filter for non-secured resources
    		chain.doFilter(request, response);
    		return;
    	}
    	
        LoginController loginController = (LoginController)((HttpServletRequest)req).getSession().getAttribute("loginController");
        String loginURL = request.getContextPath() + "/views/login.xhtml";

        boolean loggedIn = loginController != null && loginController.isLoggedIn();
        boolean loginRequest = request.getRequestURI().startsWith(loginURL);
        boolean resourceRequest = request.getRequestURI().startsWith(request.getContextPath()+ ResourceHandler.RESOURCE_IDENTIFIER);

        if (loggedIn || loginRequest || resourceRequest) {
            if (!resourceRequest) { // Prevent restricted pages from being cached.
                response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
                response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
                response.setDateHeader("Expires", 0); // Proxies.
            }

            chain.doFilter(request, response);
        } else {
            response.sendRedirect(loginURL+"?faces-redirect=true");
        }
    } 
    public void init(FilterConfig config) throws ServletException {
       System.out.println("filter-init");
    }
 
    public void destroy() {
        // Nothing to do here!
    }  
     
}
