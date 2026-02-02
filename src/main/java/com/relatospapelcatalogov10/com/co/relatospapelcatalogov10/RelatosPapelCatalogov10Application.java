//DECLARACIÓN DE PAQUETES:
package com.relatospapelcatalogov10.com.co.relatospapelcatalogov10;

//IMPORTACIÓN DE LIBRERIAS:
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//DECLARACIÓN DE LA CLASE PRINCIPAL:
@SpringBootApplication
@EnableDiscoveryClient
public class RelatosPapelCatalogov10Application extends SpringBootServletInitializer {
	/**
	* Método requerido para permitir que WebLogic gestione el despliegue del WAR.
	* Sobrescribimos el método configure para registrar esta clase como punto de entrada.
	*/
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(RelatosPapelCatalogov10Application.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(RelatosPapelCatalogov10Application.class, args);
	}
}
/*//DECLARACIÓN DE LA CLASE PRINCIPAL:
@SpringBootApplication
public class torresesingejcnacv10Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(torresesingejcnacv10Application.class, args);
	}
}*/
