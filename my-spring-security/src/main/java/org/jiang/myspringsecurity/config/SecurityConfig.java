package org.jiang.myspringsecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // httpbasic登录模式
        /*http.httpBasic()
                .and().authorizeRequests().anyRequest().authenticated();*/

        http.formLogin()
                .loginPage("/loginPage.html")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/index.html")
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
                .authorizeRequests()
                .antMatchers("/loginPage.html", "/login").permitAll()
                .antMatchers("/static/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();

        /*http
                .csrf().disable()
                .formLogin()
                .and()
                .authorizeRequests()
                .anyRequest().permitAll();*/
    }
}
