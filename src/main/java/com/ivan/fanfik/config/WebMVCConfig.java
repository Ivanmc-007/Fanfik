package com.ivan.fanfik.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer {
   // для нахождения статических ресурсов
   // выстраиваем соответсвие статических ресурсов и url адресов
   @Override
   public void addResourceHandlers(ResourceHandlerRegistry registry) {
      registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
   }

   @Override
   public void addViewControllers(ViewControllerRegistry registry) {
      registry.addViewController("/{spring:\\w+}").setViewName("forward:/");
      registry.addViewController("/**/{spring:\\w+}").setViewName("forward:/");
      registry.addViewController("/{spring:\\w+}/**{spring:?!(\\.js|\\.css)$}").setViewName("forward:/");
   }

   @Override
   public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
      for (HttpMessageConverter converter : converters) {
         if (converter instanceof org.springframework.http.converter.json.MappingJackson2HttpMessageConverter) {
            ObjectMapper mapper = ((MappingJackson2HttpMessageConverter) converter).getObjectMapper();
            mapper.registerModule(new Hibernate5Module());
            // replace Hibernate4Module() with the proper class for your hibernate version.
         }
      }
   }

}