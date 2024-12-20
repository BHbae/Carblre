package com.carblre;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
public class ThymeleafConfig {

	@Bean
	public TemplateEngine emailTemplateEngine() {
		TemplateEngine templateEngine = new TemplateEngine();
		templateEngine.setTemplateResolver(emailTemplateResolver());
		return templateEngine;
	}

	private ITemplateResolver emailTemplateResolver() {
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setPrefix("templates/"); // Thymeleaf 템플릿 파일 위치
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("HTML");
		templateResolver.setCharacterEncoding("UTF-8");
		return templateResolver;
	}
}
