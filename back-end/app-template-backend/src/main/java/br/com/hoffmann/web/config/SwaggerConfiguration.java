package br.com.hoffmann.web.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Time Backend",
                        email = "hoffmann.gusttavo@gmail.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://voll.med/api/licenca"
                ),
                title = "Hoffmann API",
                description = "API Rest da aplicação Hoffmann",
                termsOfService = "Termos de serviços"
        )
)
public class SwaggerConfiguration {


}
