package com.springboot.demo.bean_name_generator;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(nameGenerator = BeanNameGeneratorApplication.SpringBeanNameGenerator.class)
@SpringBootApplication
public class BeanNameGeneratorApplication {


	public static class SpringBeanNameGenerator extends AnnotationBeanNameGenerator {
		@Override
		protected String buildDefaultBeanName(BeanDefinition beanDefinition) {
			return beanDefinition.getBeanClassName();
		}
	}

	public static ApplicationContext applicationContext;

	public static void main(String[] args) {
		applicationContext = SpringApplication.run(BeanNameGeneratorApplication.class, args);
		GeneratorName generatorName1 = applicationContext.getBean(GeneratorName.class);
		generatorName1.getName();
		GeneratorName generatorName = (GeneratorName) applicationContext.getBean("com.springboot.demo.bean_name_generator.GeneratorName");
		generatorName.getName();
	}

}
