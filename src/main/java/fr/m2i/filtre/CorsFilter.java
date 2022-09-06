
package fr.m2i.filtre;

import java.io.IOException;
import java.text.ParseException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;

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
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import methods.TokenMethods;

//Filter for Admin privilegies
@WebFilter(urlPatterns={"/api/users/add","/api/users/delete",
		"/api/users/all","/api/users/updateFieldActive","/api/users/updateFieldCanShare",
		"/api/users/updateFieldAccessRight"})

public class CorsFilter extends HttpFilter implements Filter {
       
	private static final long serialVersionUID = 1L;

	/**
     * @see HttpFilter#HttpFilter()"
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

		
		((HttpServletResponse)response).setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        ((HttpServletResponse)response).setHeader("Access-Control-Allow-Credentials", "true");

        ((HttpServletResponse)response).setHeader("Access-Control-Allow-Headers","origin, content-type, accept, authorization");//Client-Security-Token

        ((HttpServletResponse)response).setHeader( "Access-Control-Allow-Methods","GET, POST, PUT, DELETE, OPTIONS, HEAD");


		String headerName="";
		Enumeration<String> body = httpRequest.getParameterNames();
		System.out.println(body);
	    if (headerNames != null) {
	            while (headerNames.hasMoreElements()) {
	            	headerName=headerNames.nextElement();
	            	String headerValue = httpRequest.getHeader(headerName);
                    System.out.print("Name "+headerName);
	            	System.out.print("Value "+headerValue+"\n");
	            	

	            	if (headerName.equals("authorization") ) {
	            		
            			String token = headerValue.substring("Bearer ".length());
            			System.out.println(token);
            			try {
							boolean valid = TokenMethods.validateToken(token);
							if (valid) {
								//SignedJWT decodedJWT = SignedJWT.parse(token);
				                //String header1 = decodedJWT.getHeader().toString();
				                //System.out.println(header1);
								SignedJWT decodedJWT = SignedJWT.parse(token);
								boolean admin = (boolean) decodedJWT.getPayload().toJSONObject().get("superAdmin");
					        	if(!admin) {
					        		return;
					        	}
							}else {
								return;
							}
							
						} catch (JOSEException | ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return;
						}	                    
	            	}

	            }
	            
	    }
	
	    chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
