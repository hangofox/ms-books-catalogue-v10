//DECLARACIÓN DE PAQUETES:
package com.msbookscataloguev10.com.co.msbookscataloguev10.web.controller;

//IMPORTACIÓN DE LIBRERIAS:
import com.msbookscataloguev10.com.co.msbookscataloguev10.dominio.Constantes.MensajesConstantes;
import com.msbookscataloguev10.com.co.msbookscataloguev10.dominio.dto.RespuestaDTO;
import com.msbookscataloguev10.com.co.msbookscataloguev10.dominio.dto.LibroDTO;
import com.msbookscataloguev10.com.co.msbookscataloguev10.dominio.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ.
* @Since 02/02/2026.
* Declaración del controlador.
*/
@RestController//DECLARACIÓN DEL CONTROLADOR PARA LOS CRUDS.
public class LibroController {
    
    @Autowired//INYECTAMOS EL SERVICIO.
    private LibroService libroService;
    
    //CONTROLADORES DE CRUDS (CREACIÓN, LECTURA (LISTAR Y CONSULTAR), EDICIÓN Y ELIMINACIÓN DE UN REGISTRO).

    //ENDPOINT ÚNICO PARA LISTAR/FILTRAR/ORDENAR/PAGINAR LIBROS CON QUERY PARAMS:
    @GetMapping("/libros")
    public ResponseEntity<Slice<LibroDTO>> listarLibros(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Long idCategoria,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false, defaultValue = "asc") String orderMode,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Slice<LibroDTO> librosSlice = libroService.listarLibros(keyword, idCategoria, orderBy, orderMode, pageable);
        return new ResponseEntity<>(librosSlice, HttpStatus.OK);
    }

    
    //CREAR REGISTRO:
    @PostMapping("/libros")//DECLARACIÓN DEL MAPEO DEL CRUD CREAR REGISTRO.
    //@PutMapping("/createLibro")//DECLARACIÓN DEL MAPEO DEL CRUD CREAR REGISTRO.
    public ResponseEntity<RespuestaDTO> crearLibro(@RequestBody LibroDTO libroDTO){
        System.out.println(libroDTO);
        RespuestaDTO respuesta = libroService.crearLibro(libroDTO);
        HttpStatus httpStatus = respuesta.isBanderaexito() ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(respuesta, httpStatus);
    }
    
    //LEER CONSULTA DE REGISTRO POR ID:
    @GetMapping("/libros/{idLibro}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO.
    public ResponseEntity<RespuestaDTO> consultarLibrobyId(@PathVariable Long idLibro){
        RespuestaDTO respuesta = libroService.consultarLibroporId(idLibro);
        HttpStatus httpStatus = respuesta.isBanderaexito() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(respuesta, httpStatus);
    }
    
    //MODIFICAR REGISTRO:
    @PutMapping("/libros/{idLibro}")//DECLARACIÓN DEL MAPEO DEL CRUD MODIFICAR REGISTRO.
    public ResponseEntity<RespuestaDTO> actualizarLibro(@PathVariable Long idLibro, @RequestBody LibroDTO libroDTO){
        libroDTO.setIdLibro(idLibro);
        RespuestaDTO respuesta = libroService.actualizarLibro(libroDTO);
        HttpStatus httpStatus = respuesta.isBanderaexito() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(respuesta, httpStatus);
    }
    
    //ELIMINAR REGISTRO:
    @DeleteMapping("/libros/{idLibro}")//DECLARACIÓN DEL MAPEO DEL CRUD ELIMINAR REGISTRO.
    public ResponseEntity<RespuestaDTO> eliminarLibro(@PathVariable Long idLibro){
        RespuestaDTO respuesta = libroService.eliminarLibro(idLibro);
        HttpStatus httpStatus = respuesta.isBanderaexito() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(respuesta, httpStatus);
    }

    //ENDPOINTS RELACIÓN LIBRO-CATEGORIA

    @PostMapping("/libros/{idLibro}/categorias/{idCategoria}")
    public ResponseEntity<RespuestaDTO> agregarCategoriaALibro(@PathVariable Long idLibro, @PathVariable Long idCategoria) {
        RespuestaDTO respuesta = libroService.agregarCategoriaALibro(idLibro, idCategoria);
        HttpStatus httpStatus = respuesta.isBanderaexito() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(respuesta, httpStatus);
    }

    //ELIMINAR CATEGORIA DE LIBRO:
    @DeleteMapping("/libros/{idLibro}/categorias/{idCategoria}")
    public ResponseEntity<RespuestaDTO> eliminarCategoriaDeLibro(@PathVariable Long idLibro, @PathVariable Long idCategoria) {
        RespuestaDTO respuesta = libroService.eliminarCategoriaDeLibro(idLibro, idCategoria);
        HttpStatus httpStatus = respuesta.isBanderaexito() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(respuesta, httpStatus);
    }

    //REEMPLAZAR CATEGORIAS DE UN LIBRO:
    @PutMapping("/libros/{idLibro}/categorias")
    public ResponseEntity<RespuestaDTO> reemplazarCategoriasDeLibro(@PathVariable Long idLibro, @RequestBody List<Long> categoriasIds) {
        RespuestaDTO respuesta = libroService.reemplazarCategoriasDeLibro(idLibro, categoriasIds);
        HttpStatus httpStatus = respuesta.isBanderaexito() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(respuesta, httpStatus);
    }

}
