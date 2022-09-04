
package fr.m2i.filtre;

import java.io.IOException;
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

/**
 * Servlet Filter implementation class CorsFilter
 */
@WebFilter("/*")
public class CorsFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public CorsFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

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
		

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		Enumeration<String> headerNames = httpRequest.getHeaderNames();	
		Enumeration<String> token = httpRequest.getHeaders("id");
		System.out.println( token.nextElement());
		httpRequest.getSession().setAttribute("id", token.nextElement());
		
		String headerName="";
	    if (headerNames != null) {
	            while (headerNames.hasMoreElements()) {
	            	headerName=headerNames.nextElement();
	            	String headerValue = httpRequest.getHeader(headerName);
                    System.out.println("NAme "+headerName);
	            	System.out.println("Value "+headerValue);
	            	
	            	
	            
	            	
	            	//if (headerValue.equals("test") || headerValue.equals("57") || headerValue.equals("content-type,test")) {
	            		((HttpServletResponse)response).setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
	                    ((HttpServletResponse)response).setHeader("Access-Control-Allow-Credentials", "true" );
	                    ((HttpServletResponse)response).setHeader("Access-Control-Allow-Headers","origin, content-type, accept, test, authorization,id");//Client-Security-Token
	                    ((HttpServletResponse)response).setHeader( "Access-Control-Allow-Methods","GET, POST, PUT, DELETE, OPTIONS, HEAD");
	                    
	            	//}
	            }
	            chain.doFilter(request, response);
	    }
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
