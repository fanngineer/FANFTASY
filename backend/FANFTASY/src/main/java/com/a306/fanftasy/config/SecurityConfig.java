package com.a306.fanftasy.config;

import com.a306.fanftasy.domain.user.repository.UserRepository;
import com.a306.fanftasy.security.RestAuthenticationEntryPoint;
import com.a306.fanftasy.util.JwtFilter;
import com.a306.fanftasy.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled =true,
        prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//  @Override
//  public void configure(WebSecurity web) throws Exception {
//    // resources 모든 접근을 허용하는 설정을 해버리면
//    // HttpSecurity 설정한 ADIM권한을 가진 사용자만 resources 접근가능한 설정을 무시해버린다.
//    //스웨거 요청에 대해서 security 작동안함
//    web.ignoring()
//        .antMatchers("/**");
//  }
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder)throws Exception{
        //authenticationManagerBuilder
//                .userDetailsService(customUserDetailsService)
//                .passwordEncoder(passwordEncoder());
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean()throws Exception{
        return super.authenticationManagerBean();
    }



    @Override
    public void configure(WebSecurity web) throws Exception {
        //filter 거치지않도록 설정
        web.ignoring()
                .antMatchers("/api/user/login","/api/user/join");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception{

        JwtTokenUtil jwtTokenUtil=new JwtTokenUtil();
        http.csrf().disable();

        http.httpBasic().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);//토큰 기반 인증을 위해 세션 생성x
//                .and()
//                .authorizeRequests()
        http.authorizeRequests()//권한 설정
                .antMatchers("/api/user/login","/api/user/join")
                .permitAll() //메인페이지는 모든 사용자에게 가능하게
                .anyRequest().authenticated();
        //jwt 토큰 필터 추가
        http.addFilterBefore(new JwtFilter(jwtTokenUtil),
                UsernamePasswordAuthenticationFilter.class);
    }
//        http
//                .addFilterBefore(new JwtFilter(jwtTokenUtil),
//                UsernamePasswordAuthenticationFilter.class)
//                .cors()//CORS 황성화
//                .and()
//                .sessionManagement()//세션관리 활성화
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)//세션 생성 X -> RESTful 웹 서비스에서 권장함.
//                .and()
//                .csrf()//CSRF 보호 비활성화 ->RESTful에서는 어차피 쓸모 없어서 꺼도 됨.
//                .disable()
//                .formLogin()//login 페이지 비활성화
//                .disable()
//                .httpBasic()//http 기본 인증 비활성화 토큰기반으로 인증
//                .disable()
//                .exceptionHandling()//인증예외처리 인증되지않은 요청에 대한 응답을 처리하는데 사용됩니다.
//                .authenticationEntryPoint(new RestAuthenticationEntryPoint())
//                .and()
//                .authorizeRequests()
//                .antMatchers("/api/join","/api/login")
//                .permitAll()
//                .anyRequest()//.hasRole("USER")
//                .authenticated();
//                //jwt 토큰 필터 추가



    }
//  @Bean
//    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//        return httpSecurity
//                .httpBasic().disable()
//                .csrf().disable()
//               .cors().and()
//                 .authorizeRequests()
//                .antMatchers("/user/login", "/user/join", "/user/artist/*", "/nft/market/*", "/nft/artist/*", "/nft/*") .permitAll()
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .build();
// }
//}


