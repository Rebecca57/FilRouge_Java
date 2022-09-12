
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
	public TokenMethods tm = new TokenMethods();

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
		httpResponse.setHeader("Access-Control-Allow-Headers","origin, content-type, accept, authorization");//Client-Security-Token
		httpResponse.setHeader( "Access-Control-Allow-Methods","GET, POST, PUT, DELETE, OPTIONS, HEAD");
		
		Enumeration<String> headerNames = httpRequest.getHeaderNames();	
		String headerName="";

	    if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
            	
            	headerName=headerNames.nextElement();
            	String headerValue = httpRequest.getHeader(headerName);       	
            	//Itarate on the headers value
            	if (headerName.equals("authorization") ) {
            		//get the token
        			String token = headerValue.substring("Bearer ".length());
        			try {
        				//Validate the token
						boolean valid = tm.validateToken(token);
						if (valid) {
							//parse token and get role
							SignedJWT decodedJWT = SignedJWT.parse(token);
							String accessRight = (String) decodedJWT.getPayload()
									.toJSONObject().get("accessRight");
							System.out.println(isAdmin(accessRight));
							System.out.println(accessRight);
							if(isAdmin(accessRight)) {
								//Access to ressource only if role is Admin
				        		chain.doFilter(request, response);
				        	}
						}
						
					} catch (JOSEException | ParseException e) {
						e.printStackTrace();
					}	                    
            	}
            }       
	    }  
	}


	public void init(FilterConfig fConfig) throws ServletException {
	}
	
	private boolean isAdmin(String access) {
		return (access.equals("SuperAdministrator") || access.equals("SuperAdministrator"));
	}
	

    public CorsFilter() {
        super();
    }


	public void destroy() {
	}

}
