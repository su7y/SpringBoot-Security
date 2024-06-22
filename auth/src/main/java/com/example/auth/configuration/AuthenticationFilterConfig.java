package com.example.auth.configuration;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.auth.entity.User;
import com.example.auth.service.UserService;
import com.example.auth.serviceImpl.JwtLoadItems;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthenticationFilterConfig extends OncePerRequestFilter {
	
	@Autowired
	private JwtLoadItems jwtLoadItems;
	
	@Autowired
	private UserService userService;

	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull FilterChain filterChain)
			throws ServletException, IOException {
		
		 final String gettingToken = request.getHeader("Authorization");
		 
		 //this for check header body came or not
		 if(gettingToken == null || !gettingToken.startsWith("Bearer ")) {
			 
			 System.out.println("this is null Bearer"+gettingToken);
			 filterChain.doFilter(request, response);
			 return ;
		 }
		 
		 final String gettedToken = gettingToken.substring(7);
		 final String exactlyUserEmail = jwtLoadItems.exactUserEmail(gettedToken);
		 
		
		 
		 Authentication authenticate = SecurityContextHolder.getContext().getAuthentication();
		 
		 if(gettedToken != null && !jwtLoadItems.isLogoutUser(gettedToken)) {
			 
		      if(exactlyUserEmail != null && authenticate == null) {
			 
			 
			        User user=this.userService.getUserByEmailId(exactlyUserEmail);
			        if(jwtLoadItems.isValidedToken(gettedToken, user)) {
				 
				      UsernamePasswordAuthenticationToken setAuthToken = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
				 
				      setAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				      SecurityContextHolder.getContext().setAuthentication(setAuthToken);
				  
				      System.out.println("this is a token set up"+setAuthToken);
				 
			 }
		 }
		 
		filterChain.doFilter(request, response);
		 }else { 
           response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		 }
	}

}
