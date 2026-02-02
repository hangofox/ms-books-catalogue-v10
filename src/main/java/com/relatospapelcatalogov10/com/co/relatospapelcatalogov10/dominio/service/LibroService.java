//DECLARACIÓN DE PAQUETES:
package com.relatospapelcatalogov10.com.co.relatospapelcatalogov10.dominio.service;

//IMPORTACIÓN DE LIBRERIAS:
import com.relatospapelcatalogov10.com.co.relatospapelcatalogov10.dominio.dto.LibroDTO;
import com.relatospapelcatalogov10.com.co.relatospapelcatalogov10.dominio.dto.RespuestaDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import java.util.List;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ.
* @Since 02/02/2026.
* Declaración de los métodos de respuesta en la interface para los cruds (creación, lectura (listar y consultar),
* edición y eliminación de un registro).
*/
//DECLARACIÓN DE LA INTERFACE DE LA CLASE PRINCIPAL DEL SERVICIO:
public interface LibroService {
    //DECLARACIÓN DE LOS METODOS DE RESPUESTA EN LA INTERFACE PARA LOS CRUDS QUE SON LOS METODOS PARA LA
    //CREACIÓN, LECTURA (LISTAR Y CONSULTAR), EDICIÓN Y ELIMINACIÓN DE UN REGISTRO:
    //1. LISTADO DE REGISTROS FILTRADOS.
    List<LibroDTO> listarLibros();
    List<LibroDTO> listarLibrosOrdenadosporIdAsc();
    Slice<LibroDTO> listarLibrosOrdenadosporIdAscPag(Pageable pageable);
    //2. LISTADO DE REGISTROS FILTRADOS.
    List<LibroDTO> listarLibrosporPalabraClaveyOrdenadosporIdAsc(String keyword);
    Slice<LibroDTO> listarLibrosporPalabraClaveyOrdenadosporIdAscPag(Pageable pageable, String keyword);
    RespuestaDTO crearLibro(LibroDTO libroDTO);
    RespuestaDTO consultarLibroporId(Long idLibro);
    RespuestaDTO actualizarLibro(LibroDTO libroDTO);
    RespuestaDTO eliminarLibro(Long idLibro);
}
