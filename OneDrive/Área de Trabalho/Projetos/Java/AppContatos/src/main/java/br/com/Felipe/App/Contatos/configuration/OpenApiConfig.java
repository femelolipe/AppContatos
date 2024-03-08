package br.com.Felipe.App.Contatos.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.components(
						new Components().addSecuritySchemes("bearerAuth", 
								new SecurityScheme().type(SecurityScheme.Type.HTTP)
									.scheme("bearer").bearerFormat("JWt")))
				.info(new Info()
						.title("App de cadastro de pessoas e suas agendas")
						.description("Este aplicativo faz controle de cadastro de pessoas "
								+ "bem como o cadastro da sua respectiva agenda.")
						.contact(new Contact()
									.name("Felipe Melo")
									.email("felipe@email.com")
									.url("http://localhost")
								)
						.version("Versão 0.0.1-SNAPSHOT")
				)
				.addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
	}
}
