//DECLARACIÓN DE PAQUETES:
package com.relatospapelcatalogov10.com.co.relatospapelcatalogov10.dominio.dto;

//IMPORTACIÓN DE LIBRERIAS:
import lombok.Data;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ.
* @Since 02/02/2026.
* Declaración del método DTO.
*/
@Data//DECLARACIÓN DE LA DATA PARA LOS DATOS DE LA TABLA DE LA BASE DE DATOS PARA LOS DTO.
//Anotacion de lombok que me crea automaticamente los get, set constructor.
public class LibroDTO {

    //DECLARACIÓN DE LAS VARIABLES DE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS PARA LOS DTO:
    private Long idLibro;
    //private Long idAutor;
    private String tituloLibro;
    private int anioPublicacionLibro;
    private String sinopsisLibro;
    private String codigoIsbnLibro;
    private double precioLibro;
    private String formatoLibro;
    private String nombreArchivoImagenLibro;
    private String estadoLibro;
    
    private AutorDTO autorDTO;
    
    public AutorDTO getAutorDTO() {
        return autorDTO;
    }
    public void setAutorDTO(AutorDTO autorDTO) {
        this.autorDTO = autorDTO;
    }
}