package ProyectoCartas.config;


import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                        .title("ProyectoCartas")
                        .version("1.0")
                        .description("Documentación de la API para el sistema de carpeta digital de cartas"));
    }

}
