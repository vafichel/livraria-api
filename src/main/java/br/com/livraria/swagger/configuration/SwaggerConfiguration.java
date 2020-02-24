package br.com.livraria.swagger.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	
	@Bean
	public Docket api() {

		
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.livraria"))
				.build()
				.apiInfo(getApiInfo());
	}

	private ApiInfo getApiInfo() {
		// TODO Auto-generated method stub
		
		return new ApiInfoBuilder()
				.title("Livraria API")
				.description("Esta API faz o cadastro, consulta, alteração, exclusão de Livros "
						+ "e permite consulta a API pública GoogleBooks ")
				.contact(new Contact("Vanessa Ap. Fichel", "", "vafichel@gmail.com"))
				.version("1.0.0")
				.build();
	}

}
