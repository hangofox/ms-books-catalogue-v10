//DECLARACIÓN DE PAQUETES:
package com.msbookscataloguev10.com.co.msbookscataloguev10.dominio.service;

//IMPORTACIÓN DE LIBRERIAS:
import com.msbookscataloguev10.com.co.msbookscataloguev10.dominio.dto.LibroDTO;
import com.msbookscataloguev10.com.co.msbookscataloguev10.dominio.dto.RespuestaDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import java.util.List;

/**
 * @Autor PD04. HERNAN ADOLFO NUÑEZ GONZALEZ.
 * @Since 02/02/2026.
 * Declaración de la entidad.
 * @Actualizacion David Paez 04/02/2026.
* Declaración de los métodos de respuesta en la interface para los cruds (creación, lectura (listar y consultar),
* edición y eliminación de un registro).
*/
//DECLARACIÓN DE LA INTERFACE DE LA CLASE PRINCIPAL DEL SERVICIO:
public interface LibroService {
    //DECLARACIÓN DE LOS METODOS DE RESPUESTA EN LA INTERFACE PARA LOS CRUDS QUE SON LOS METODOS PARA LA
    //CREACIÓN, LECTURA (LISTAR Y CONSULTAR), EDICIÓN Y ELIMINACIÓN DE UN REGISTRO:
    //MÉTODO ÚNICO PARA LISTAR/FILTRAR/ORDENAR/PAGINAR LIBROS:
    Slice<LibroDTO> listarLibros(String keyword, Long idCategoria, String orderBy, String orderMode, Pageable pageable);
    RespuestaDTO crearLibro(LibroDTO libroDTO);
    RespuestaDTO consultarLibroporId(Long idLibro);
    RespuestaDTO actualizarLibro(LibroDTO libroDTO);
    RespuestaDTO eliminarLibro(Long idLibro);

    //GESTIÓN DE RELACIÓN LIBRO - CATEGORIA:
    RespuestaDTO agregarCategoriaALibro(Long idLibro, Long idCategoria);
    RespuestaDTO eliminarCategoriaDeLibro(Long idLibro, Long idCategoria);
    RespuestaDTO reemplazarCategoriasDeLibro(Long idLibro, List<Long> categoriasIds);

}
