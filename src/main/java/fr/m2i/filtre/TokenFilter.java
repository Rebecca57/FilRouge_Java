package fr.m2i.filtre;

import java.io.IOException;
import java.text.ParseException;
import java.util.Collection;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nimbusds.jose.JOSEException;

import methods.TokenMethods;

/**
 * Servlet Filter implementation class CorsFilter
 */
@WebFilter("/api/events/add/fdgdfg")
public class TokenFilter extends HttpFilter implements Filter {
	TokenMethods tokenMet= new TokenMethods();
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * @see HttpFilter#HttpFilter()
  

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		Enumeration<String> headerNames = httpRequest.getHeaderNames();	
		Enumeration<String> token = httpRequest.getHeaders("Bearer");
		
		
		String headerName="";
		  if (headerNames != null) {
	            while (headerNames.hasMoreElements()) {
	            	 headerName=headerNames.nextElement();
	            	String headerValue = httpRequest.getHeader(headerName);
                  System.out.println("NAme "+headerName);
	            	System.out.println("Value "+headerValue);
	            }
	            
		  }
		
		
		if (token != null) {
			System.out.println("$1");
			while(token.hasMoreElements()) {
				System.out.println("$2");
				/*try {
					System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
					System.out.println("tokenMet.validateToken(token.nextElement()");
					System.out.println(this.tokenMet.validateToken(token.nextElement()));
					if (this.tokenMet.validateToken(token.nextElement())) {
					
						((HttpServletResponse)response).setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
	                    ((HttpServletResponse)response).setHeader("Access-Contro l-Allow-Credentials", "true");
	                    ((HttpServletResponse)response).setHeader("Access-Control-Allow-Headers","origin, content-type, accept, test");//Client-Security-Token
	                    ((HttpServletResponse)response).setHeader( "Access-Control-Allow-Methods","GET, POST, PUT, DELETE, OPTIONS, HEAD");
	                    
	            	//}
	            
	            chain.doFilter(request, response);
	            
						
					}
					
				} catch (JOSEException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
			}
		}
		
		
		
		/*
		String headerName="";
		System.out.println(headerNames.toString());
	    if (headerNames != null) {
	            while (headerNames.hasMoreElements()) {
	            	headerName=headerNames.nextElement();
	            	String headerValue = httpRequest.getHeader(headerName);
	            	System.out.println("ACCES **********");
                    System.out.println("NAme "+headerName);
	            	System.out.println("Value "+headerValue);
	            
	            	
	            	//if (headerValue.equals("test") || headerValue.equals("57") || headerValue.equals("content-type,test")) {
	            		((HttpServletResponse)response).setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
	                    ((HttpServletResponse)response).setHeader("Access-Control-Allow-Credentials", "true");
	                    ((HttpServletResponse)response).setHeader("Access-Control-Allow-Headers","origin, content-type, accept, test");//Client-Security-Token
	                    ((HttpServletResponse)response).setHeader( "Access-Control-Allow-Methods","GET, POST, PUT, DELETE, OPTIONS, HEAD");
	                    
	            	//}
	            }
	            chain.doFilter(request, response);
	    }*/
	    //((HttpServletResponse)response).setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		//((HttpServletResponse)response).setHeader("Access-Control-Allow-Credentials", "true");
		//((HttpServletResponse)response).setHeader("Access-Control-Allow-Methods", "GET,HEAD,OPTIONS,POST,PUT");
		//((HttpServletResponse)response).setHeader("Access-Control-Allow-Headers", "Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");
        //chain.doFilter(request, response);
		/**((HttpServletResponse)response).setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        ((HttpServletResponse)response).setHeader("Access-Control-Allow-Credentials", "true");
        ((HttpServletResponse)response).setHeader("Access-Control-Allow-Headers","origin, content-type, accept,Client-Security-Token");
        ((HttpServletResponse)response).setHeader( "Access-Control-Allow-Methods","GET, POST, PUT, DELETE, OPTIONS, HEAD");
        chain.doFilter(request, response);**/
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
