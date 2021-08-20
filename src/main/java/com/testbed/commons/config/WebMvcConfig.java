package com.testbed.commons.config;

import com.testbed.commons.interceptor.PrintRequestInterceptor;
import com.testbed.commons.jwt.AuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

  private final AuthInterceptor authInterceptor;
  private final PrintRequestInterceptor printRequestInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(printRequestInterceptor).addPathPatterns("/**");
    registry
        .addInterceptor(authInterceptor)
        .addPathPatterns("/**")
        .excludePathPatterns(
            "/",
            "/favicon.ico",
            "/sign-up",
            "/sign-in",
            "/users/refresh",
            "/encrypt",
            "/decrypt",
            "/exception/**",
            "/upload",
            "/hash/**");
  }
}
