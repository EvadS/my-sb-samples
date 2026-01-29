//package com.smeda.application.receiving.config.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//
//@Configuration
//@EnableResourceServer
//public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
//
//	@Autowired
//	private TokenStore tokenStore;
//
//	@Autowired
//	private CustomLogoutSuccessHandler customLogoutSuccessHandler;
//
//
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) {
//        resources.tokenStore(tokenStore);
//    }
//    @Override
//	public void configure(HttpSecurity http) throws Exception {
//		     http.authorizeRequests()
//		    .antMatchers("/user/add/**").permitAll()
//		    .antMatchers("/user/confirm/registration/**").permitAll()
//		    .antMatchers("/user/forgotPassword/**").permitAll()
//		    .antMatchers("/user/resetPassword/**").permitAll()
//					 .antMatchers("/actuator/**").permitAll()
//			.anyRequest().authenticated()
//			.and()
//			.logout().logoutUrl("/oauth/logout").logoutSuccessHandler(customLogoutSuccessHandler);
//
//	}
//
//}