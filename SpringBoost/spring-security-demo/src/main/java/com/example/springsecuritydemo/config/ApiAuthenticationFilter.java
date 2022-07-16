package com.example.springsecuritydemo.config;

import com.example.springsecuritydemo.entity.dto.CredentialDto;
import com.example.springsecuritydemo.entity.dto.LoginDTO;
import com.example.springsecuritydemo.util.JwtUtil;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class ApiAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("Checking login information");
        try{
            String jsonData = request.getReader().lines().collect(Collectors.joining());
            Gson gson = new Gson();
            LoginDTO loginDTO = gson.fromJson(jsonData, LoginDTO.class);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.getUserName(), loginDTO.getPassword());
            return authenticationManager.authenticate(authenticationToken);
        }catch (IOException e){
            return null;
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();
        String accessToken = JwtUtil.generateToken(user.getUsername(), user.getAuthorities().iterator().next().getAuthority(), request.getRequestURL().toString(), JwtUtil.ONE_DAY * 7);
        String refreshToken = JwtUtil.generateToken(user.getUsername(), user.getAuthorities().iterator().next().getAuthority(), request.getRequestURL().toString(), JwtUtil.ONE_DAY * 14);
        CredentialDto credentialDto = new CredentialDto(accessToken, refreshToken);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        Gson gson = new Gson();
        response.getWriter().println(gson.toJson(credentialDto));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        HashMap<String, String> errors = new HashMap<>();
        errors.put("message", " Invalid information");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        Gson gson = new Gson();
        response.getWriter().println(gson.toJson(errors));
    }
}
