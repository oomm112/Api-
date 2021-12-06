package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

import com.example.demo.config.jwt.JwtAccessDeniedHandler;
import com.example.demo.config.jwt.JwtAuthenticationEntryPoint;
import com.example.demo.config.jwt.JwtSecurityConfig;
import com.example.demo.config.jwt.TokenProvider;

@EnableWebSecurity
//@PreAuthorize 어노테이션을 메소드 단위로 추가하기위해서 사용
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	private final TokenProvider tokenProvider;
	private final CorsFilter corsFilter;
	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
	

    public SecurityConfig(
            TokenProvider tokenProvider,
            CorsFilter corsFilter,
            JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
            JwtAccessDeniedHandler jwtAccessDeniedHandler
    ) {
        this.tokenProvider = tokenProvider;
        this.corsFilter = corsFilter;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
    }
    
    //PasswordEncoder를 빈객체로 주입
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //스웨거 인증 필요없이 사용가능 위해
	@Override
	public void configure(WebSecurity web) {
		web
		.ignoring()
		.antMatchers(
				"/swagger-ui.html#/"
				);
	}

	 @Override
	    protected void configure(HttpSecurity httpSecurity) throws Exception {
	        httpSecurity
	                // token을 사용하는 방식이기 때문에 csrf를 disable합니다.
	                .csrf().disable()

	                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)

	                .exceptionHandling()
	                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
	                .accessDeniedHandler(jwtAccessDeniedHandler)

	                // enable h2-console
	                .and()
	                .headers()
	                .frameOptions()
	                .sameOrigin()

	                // 세션을 사용하지 않기 때문에 STATELESS로 설정
	                .and()
	                .sessionManagement()
	                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

	                .and()
	                .authorizeRequests()
	                .antMatchers("/api/hi").permitAll()
	                .antMatchers("/api/authenticate").permitAll()
	                .antMatchers("/api/signup").permitAll()

	                .anyRequest().authenticated()

	                .and()
	                .apply(new JwtSecurityConfig(tokenProvider));
	    }
}
