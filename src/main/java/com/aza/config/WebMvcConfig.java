package com.aza.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
   @Override
   public void addCorsMappings(CorsRegistry registry) {
      registry.addMapping("/**") // 요청 처리하려는 경로를 명시적으로 지정
            .allowedOrigins("http://localhost:3000") // 허용할 출처를 명시적으로 지정
            .allowedMethods("GET", "POST", "PUT", "DELETE") // 허용할 HTTP 메서드 지정
            .allowedHeaders("*") // 허용할 헤더 지정
            .allowCredentials(true); // 자격 증명 허용 여부 설정
   }
}