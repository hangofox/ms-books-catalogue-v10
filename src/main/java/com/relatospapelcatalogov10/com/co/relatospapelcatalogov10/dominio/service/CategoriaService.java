//DECLARACIÓN DE PAQUETES:
package com.relatospapelcatalogov10.com.co.relatospapelcatalogov10.dominio.service;

//IMPORTACIÓN DE LIBRERIAS:
import com.relatospapelcatalogov10.com.co.relatospapelcatalogov10.dominio.dto.CategoriaDTO;
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
public interface CategoriaService {
    //DECLARACIÓN DE LOS METODOS DE RESPUESTA EN LA INTERFACE PARA LOS CRUDS QUE SON LOS METODOS PARA LA
    //CREACIÓN, LECTURA (LISTAR Y CONSULTAR), EDICIÓN Y ELIMINACIÓN DE UN REGISTRO:
    //1. LISTADO DE REGISTROS FILTRADOS.
    List<CategoriaDTO> listarCategorias();
    List<CategoriaDTO> listarCategoriasOrdenadosporIdAsc();
    Slice<CategoriaDTO> listarCategoriasOrdenadosporIdAscPag(Pageable pageable);
    //2. LISTADO DE REGISTROS FILTRADOS.
    List<CategoriaDTO> listarCategoriasporPalabraClaveyOrdenadosporIdAsc(String keyword);
    Slice<CategoriaDTO> listarCategoriasporPalabraClaveyOrdenadosporIdAscPag(Pageable pageable, String keyword);
    RespuestaDTO crearCategoria(CategoriaDTO categoriaDTO);
    RespuestaDTO consultarCategoriaporId(Long idCategoria);
    RespuestaDTO consultarCategoriaporNombre(String nombreCategoria);
    RespuestaDTO actualizarCategoria(CategoriaDTO CategoriaDTO);
    RespuestaDTO eliminarCategoria(Long idCategoria);
}
