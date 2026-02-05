//DECLARACIÓN DE PAQUETES:
package com.msbookscataloguev10.com.co.msbookscataloguev10.dominio.dto;

//IMPORTACIÓN DE LIBRERIAS:
import lombok.Data;
import java.util.List;

/**
 * @Autor PD04. HERNAN ADOLFO NUÑEZ GONZALEZ.
 * @Since 02/02/2026.
 * Declaración de la entidad.
 * @Actualizacion David Paez 04/02/2026.
 */
@Data//DECLARACIÓN DE LA DATA PARA LOS DATOS DE LA TABLA DE LA BASE DE DATOS PARA LOS DTO.
//Anotacion de lombok que me crea automaticamente los get, set constructor.
public class LibroDTO {

    //DECLARACIÓN DE LAS VARIABLES DE LOS CAMPOS DE LA TABLA DE LA BASE DE DATOS PARA LOS DTO:
    private Long idLibro;
    //private Long idAutor;
    private String tituloLibro;
    private String fechaPublicacionLibro;
    private String sinopsisLibro;
    private String codigoIsbnLibro;
    private double precioLibro;
    private String formatoLibro;
    private String nombreArchivoImagenLibro;
    private String estadoLibro;
    
    private AutorDTO autorDTO;

    //IDS PARA ASIGNAR CATEGORIAS (REQUEST)
    private List<Long> categoriasIds;

    //CATEGORIAS COMPLETAS (RESPONSE)
    private List<CategoriaDTO> categorias;

    public AutorDTO getAutorDTO() {
        return autorDTO;
    }
    public void setAutorDTO(AutorDTO autorDTO) {
        this.autorDTO = autorDTO;
    }
}