package com.example.userservice.security;

import com.example.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.util.matcher.IpAddressMatcher;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurity  {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final Environment env;

    AuthenticationManager authenticationManager;
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
////        http.authorizeRequests().antMatchers("/users/**").permitAll();
//        http.authorizeRequests()
////                .antMatchers("/error/**").permitAll()
//                .antMatchers("/**")
////                .hasIpAddress("192.168.200.117")
////                .hasIpAddress("192.168.0.51")
//                .hasIpAddress("127.0.0.1")
//                .and()
//                .addFilter(getAuthenticationFilter());
//
//        http.headers().frameOptions().disable();
//    }
//
//    private AuthenticationFilter getAuthenticationFilter() throws Exception {
//        AuthenticationFilter authenticationFilter = new AuthenticationFilter();
//        authenticationFilter.setAuthenticationManager(authenticationManager());
//
//        return authenticationFilter;
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
//    }

    // spring.boot 2.7 부터는 WebSecurityConfigurerAdapter가 아닌
    // SecurityFilterChain 을 사용 합니다.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
        authenticationManager = authenticationManagerBuilder.build();

        //AuthenticationFilter authenticationFilter = new AuthenticationFilter();
        //authenticationFilter.setAuthenticationManager(authenticationManager);

        AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager , userService , env);

        http.csrf().disable();

        http.authorizeRequests()
                //.antMatchers("/error/**").permitAll() // public abstract java.lang.String javax.servlet.ServletRequest.getRemoteAddr() is not supported 보기 싫을때 활성화
                .antMatchers("/**")
                .hasIpAddress("127.0.0.1")
                .and()
                .authenticationManager(authenticationManager)
                .addFilter(authenticationFilter)
        ;
        http.headers().frameOptions().disable();

        return http.build();
    }

    //ex) 기존의 경우 AuthenticationManagerBuilder 를 오버라이드 하여 사용 하였지만 filterChain 안에서 호출 하여 설정 합니다.
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
	}


    //ex)filter를 authenticationAmanger에 주입 하던 getAuthenticationFilter역시 filterChain 내부에서 사용 합니다.
	private AuthenticationFilter getAuthenticationFilter() throws Exception {
		AuthenticationFilter authenticationFilter = new AuthenticationFilter();
		authenticationFilter.setAuthenticationManager(authenticationManager);
		return authenticationFilter;
	}
}
