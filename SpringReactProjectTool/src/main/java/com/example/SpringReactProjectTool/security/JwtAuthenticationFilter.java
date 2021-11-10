package com.example.SpringReactProjectTool.security;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.dom.DOMNodeHelper.EmptyNodeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import static com.example.SpringReactProjectTool.security.SecurityConstants.HEADER_STRING;
import static com.example.SpringReactProjectTool.security.SecurityConstants.TOKEN_PREFIX;

import com.example.SpringReactProjectTool.domain.User;
import com.example.SpringReactProjectTool.services.CustomUserDetailsService;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			
			String jwt=getTokenFromRequest(request);
			if(StringUtils.hasText(jwt)&&jwtTokenProvider.validateToken(jwt))
			{
				Long id=jwtTokenProvider.getUserIdFromJWT(jwt);
				User userDetails=customUserDetailsService.loadUserById(id);
				
				UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(userDetails
						,null, Collections.emptyList());   //third parameter is list of roles but we do not have that,so empty list we passes
			
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authentication);
			}
			
		}catch(Exception e)
		{
			logger.error("could not set user authentication in security context",e);
		}
		
		filterChain.doFilter(request, response);
		
	}
	
	public String getTokenFromRequest(HttpServletRequest request) {
		String bearerToken=request.getHeader(HEADER_STRING);
		if(StringUtils.hasText(bearerToken)&&bearerToken.startsWith(TOKEN_PREFIX))
		{
			return bearerToken.substring(7, bearerToken.length());
		}
		
		return null;
	}
	
	

}
