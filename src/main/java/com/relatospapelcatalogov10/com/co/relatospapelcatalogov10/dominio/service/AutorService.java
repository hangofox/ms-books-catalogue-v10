//DECLARACIÓN DE PAQUETES:
package com.relatospapelcatalogov10.com.co.relatospapelcatalogov10.dominio.service;

//IMPORTACIÓN DE LIBRERIAS:
import com.relatospapelcatalogov10.com.co.relatospapelcatalogov10.dominio.dto.AutorDTO;
import com.relatospapelcatalogov10.com.co.relatospapelcatalogov10.dominio.dto.RespuestaDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import java.util.List;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ.
* @Since 28/01/2026.
* Declaración de los métodos de respuesta en la interface para los cruds (creación, lectura (listar y consultar),
* edición y eliminación de un registro).
*/
//DECLARACIÓN DE LA INTERFACE DE LA CLASE PRINCIPAL DEL SERVICIO:
public interface AutorService {
    //DECLARACIÓN DE LOS METODOS DE RESPUESTA EN LA INTERFACE PARA LOS CRUDS QUE SON LOS METODOS PARA LA
    //CREACIÓN, LECTURA (LISTAR Y CONSULTAR), EDICIÓN Y ELIMINACIÓN DE UN REGISTRO:
    //1. LISTADO DE REGISTROS FILTRADOS.
    List<AutorDTO> listarAutores();
    List<AutorDTO> listarAutoresOrdenadosporIdAsc();
    Slice<AutorDTO> listarAutoresOrdenadosporIdAscPag(Pageable pageable);
    //2. LISTADO DE REGISTROS FILTRADOS.
    List<AutorDTO> listarAutoresporPalabraClaveyOrdenadosporIdAsc(String keyword);
    Slice<AutorDTO> listarAutoresporPalabraClaveyOrdenadosporIdAscPag(Pageable pageable, String keyword);
    RespuestaDTO crearAutor(AutorDTO autorDTO);
    RespuestaDTO consultarAutorporId(Long idAutor);
    RespuestaDTO actualizarAutor(AutorDTO autorDTO);
    RespuestaDTO eliminarAutor(Long idAutor);
}
