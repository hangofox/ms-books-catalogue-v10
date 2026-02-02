//DECLARACIÓN DE PAQUETES:
package com.relatospapelcatalogov10.com.co.relatospapelcatalogov10.web.config;

//IMPORTACIÓN DE LIBRERIAS:
//import com.relatospapelv10.mil.co.relatospapelv10.persistencia.entity.ParametrosSistema;
//import com.relatospapelv10.mil.co.relatospapelv10.persistencia.repository.ParametrosSistemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import jakarta.servlet.MultipartConfigElement;
import java.util.Optional;

/**
* @Autor PD04. HERNAN ADOLFO NUÑEZ GONZALEZ.
* @Since 01/08/2023.
* Declaración del método de la carpeta de archivos temporales.
*/
@Configuration//DECLARACIÓN DE COMPONENT PARA INYECTAR LA CONFIGURACIÓN DE SEGURIDAD.
public class FileUploadConfig {
    
    /*@Autowired//INYECTAMOS EL REPOSITORIO.
    private ParametrosSistemaRepository parametrosSistemaRepository;
    
    @Bean//DEVUELVE UN OBJETO QUE DEBE SER REGISTRADO EN EL CONTENEDOR DE SPRING.
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        Long idParametrosSistema = Long.valueOf(1);//INICIALIZAMOS POR DEFECTO LA VARIABLE QUE ES LA LLAVE PRIMARIA DE LA TABLA DE PARÁMETROS DEL SISTEMA QUE ES UN SOLO REGISTRO.
        Optional<ParametrosSistema> parametrosSistemaId = parametrosSistemaRepository.findByIdParametrosSistema(Long.valueOf(idParametrosSistema));
        
        if (parametrosSistemaId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS CARGA LA RUTA DE DESTINO DE LA CARPETA DE CARGUE TEMPORAL DE ARCHIVOS CON EL ID PROPORCIONADO.
           parametrosSistemaId.get().getRutaDestinoCarpetaCargueTemporalArchivos();
           //CONFORMAMOS LA RUTA DE DESTINO DE LA CARPETA DE CARGUE TEMPORAL DE ARCHIVOS COMBINANDO LA RUTA DE LA CARPETA PRINCIPAL DEL SERVIDOR DE APLICACIONES Y LA CARPETA DEL CARGUE TEMPORAL DE ARCHIVOS:
           String rutaDestinoCarpetaCargueTemporalArchivos = parametrosSistemaId.get().getRutaDestinoCarpetaPrincipalServidorAplicaciones() + parametrosSistemaId.get().getRutaDestinoCarpetaCargueTemporalArchivos();
           //System.out.println("📁 Ruta temporal configurada desde base de datos: " + rutaDestinoCarpetaCargueTemporalArchivos);
           //RUTA DONDE SE GAURDARÁN TEMPORALMENTE LOS ARCHIVOS SUBIDOS:
           //EJEMPLO: "C:/aplicaciones1/archivos_relatospapelv10/uploads_temp"
           factory.setLocation(rutaDestinoCarpetaCargueTemporalArchivos);
        }
        if (parametrosSistemaId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS YMUESTRA EL REGISTRO CON UN MENSAJE RUTA DE DESTINO DE LA CARPETA DE CARGUE TEMPORAL DE ARCHIVOS NO CARGADA CON EL ID PROPORCIONADO.
           System.out.println("No se encontró el parámetro. Usando ruta temporal por defecto del sistema operativo........");
        }
        
        return factory.createMultipartConfig();
    }*/
}
