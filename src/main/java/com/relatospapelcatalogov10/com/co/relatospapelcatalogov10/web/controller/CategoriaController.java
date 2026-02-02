//DECLARACIÓN DE PAQUETES:
package com.relatospapelcatalogov10.com.co.relatospapelcatalogov10.web.controller;

//IMPORTACIÓN DE LIBRERIAS:
import com.relatospapelcatalogov10.com.co.relatospapelcatalogov10.dominio.Constantes.MensajesConstantes;
import com.relatospapelcatalogov10.com.co.relatospapelcatalogov10.dominio.dto.RespuestaDTO;
import com.relatospapelcatalogov10.com.co.relatospapelcatalogov10.dominio.dto.CategoriaDTO;
import com.relatospapelcatalogov10.com.co.relatospapelcatalogov10.dominio.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ.
* @Since 28/01/2026.
* Declaración del controlador.
*/
@RestController//DECLARACIÓN DEL CONTROLADOR PARA LOS CRUDS.
@RequestMapping("/categoria")//DECLARACIÓN DE LA RESPUESTA PRINCIPAL DEL MAPEO DE LOS CRUDS.
public class CategoriaController {
    
    @Autowired//INYECTAMOS EL SERVICIO.
    private CategoriaService categoriaService;
    
    //CONTROLADORES DE CRUDS (CREACIÓN, LECTURA (LISTAR Y CONSULTAR), EDICIÓN Y ELIMINACIÓN DE UN REGISTRO).
    
    //1. LISTADO DE REGISTROS FILTRADOS.
    //LISTAR REGISTROS:
    @GetMapping("/listAllCategorias")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS.
    public ResponseEntity<RespuestaDTO> listarCategorias(){
        RespuestaDTO respuesta = new RespuestaDTO();
        respuesta.setCategoriasDTO(categoriaService.listarCategorias());
        respuesta.setMensaje(MensajesConstantes.MSG_REGISTROS_LISTADOS_EXITO);
        respuesta.setBanderaexito(true);
        HttpStatus httpStatus = HttpStatus.OK;
        respuesta.setStatus(httpStatus.value() + " " + httpStatus.getReasonPhrase());
        return new ResponseEntity<>(respuesta, httpStatus);
    }
    
    //LISTAR REGISTROS ORDENADOS POR ID DE FORMA ASCENDENTE:
    @GetMapping("/listAllCategoriasOrderedbyIdAsc")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS.
    public ResponseEntity<RespuestaDTO> listarCategoriasOrdenadosporIdAsc(){
        RespuestaDTO respuesta = new RespuestaDTO();
        respuesta.setCategoriasDTO(categoriaService.listarCategoriasOrdenadosporIdAsc());
        respuesta.setMensaje(MensajesConstantes.MSG_REGISTROS_LISTADOS_EXITO);
        respuesta.setBanderaexito(true);
        HttpStatus httpStatus = HttpStatus.OK;
        respuesta.setStatus(httpStatus.value() + " " + httpStatus.getReasonPhrase());
        return new ResponseEntity<>(respuesta, httpStatus);
    }
    
    //LISTAR REGISTROS ORDENADOS POR ID DE FORMA ASCENDENTE CON PAGINACIÓN:
    @GetMapping("/listAllCategoriasOrderedbyIdAscPag")
    public ResponseEntity<RespuestaDTO> listarCategoriasOrdenadosporIdAscPag(
           @RequestParam(defaultValue = "0") int page,
           @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        RespuestaDTO respuesta = new RespuestaDTO();
        respuesta.setCategoriasDTO(categoriaService.listarCategoriasOrdenadosporIdAscPag(pageable).getContent());
        respuesta.setMensaje(MensajesConstantes.MSG_REGISTROS_LISTADOS_EXITO);
        respuesta.setBanderaexito(true);
        HttpStatus httpStatus = HttpStatus.OK;
        respuesta.setStatus(httpStatus.value() + " " + httpStatus.getReasonPhrase());
        return new ResponseEntity<>(respuesta, httpStatus);
    }
    
    //2. LISTADO DE REGISTROS FILTRADOS.
    //LISTAR REGISTROS FILTRADOS POR PALABRA CLAVE Y ORDENADOS POR ID DE FORMA ASCENDENTE:
    @GetMapping("/listAllCategoriasbyKeywordAndOrderedbyIdAsc/{keyword}")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS.
    public ResponseEntity<RespuestaDTO> listarCategoriasporPalabraClaveyOrdenadosporIdAsc(@PathVariable String keyword){
        RespuestaDTO respuesta = new RespuestaDTO();
        respuesta.setCategoriasDTO(categoriaService.listarCategoriasporPalabraClaveyOrdenadosporIdAsc(keyword));
        respuesta.setMensaje(MensajesConstantes.MSG_REGISTROS_LISTADOS_EXITO);
        respuesta.setBanderaexito(true);
        HttpStatus httpStatus = HttpStatus.OK;
        respuesta.setStatus(httpStatus.value() + " " + httpStatus.getReasonPhrase());
        return new ResponseEntity<>(respuesta, httpStatus);
    }
    
    //LISTAR REGISTROS FILTRADOS POR PALABRA CLAVE Y ORDENADOS POR ID DE FORMA ASCENDENTE CON PAGINACIÓN:
    @GetMapping("/listAllCategoriasbyKeywordAndOrderedbyIdAscPag/{keyword}")
    public ResponseEntity<RespuestaDTO> listarCategoriasporPalabraClaveyOrdenadosporIdAscPag(
           @RequestParam(name = "page", defaultValue = "0") int page,
           @RequestParam(name = "size", defaultValue = "10") int size,
           @PathVariable("keyword") String keyword
    ) {
        Pageable pageable = PageRequest.of(page, size);
        RespuestaDTO respuesta = new RespuestaDTO();
        respuesta.setCategoriasDTO(categoriaService.listarCategoriasporPalabraClaveyOrdenadosporIdAscPag(pageable, keyword).getContent());
        respuesta.setMensaje(MensajesConstantes.MSG_REGISTROS_LISTADOS_EXITO);
        respuesta.setBanderaexito(true);
        HttpStatus httpStatus = HttpStatus.OK;
        respuesta.setStatus(httpStatus.value() + " " + httpStatus.getReasonPhrase());
        return new ResponseEntity<>(respuesta, httpStatus);
    }
    
    //CREAR REGISTRO:
    @PostMapping("/createCategoria")//DECLARACIÓN DEL MAPEO DEL CRUD CREAR REGISTRO.
    //@PutMapping("/createCategoria")//DECLARACIÓN DEL MAPEO DEL CRUD CREAR REGISTRO.
    public ResponseEntity<RespuestaDTO> crearCategoria(@RequestBody CategoriaDTO categoriaDTO){
        System.out.println(categoriaDTO);
        RespuestaDTO respuesta = categoriaService.crearCategoria(categoriaDTO);
        HttpStatus httpStatus;
        if (respuesta.isBanderaexito()) {
            httpStatus = HttpStatus.CREATED;
        } else if (MensajesConstantes.MSG_REGISTRO_NOMBRE_YA_EXISTE.equals(respuesta.getMensaje())) {
            httpStatus = HttpStatus.CONFLICT;
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        respuesta.setStatus(httpStatus.value() + " " + httpStatus.getReasonPhrase());
        return new ResponseEntity<>(respuesta, httpStatus);
    }
    
    //LEER CONSULTA DE REGISTRO POR ID:
    @GetMapping("/getCategoriabyId/{idCategoria}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO.
    public ResponseEntity<RespuestaDTO> consultarCategoriabyId(@PathVariable Long idCategoria){
        RespuestaDTO respuesta = categoriaService.consultarCategoriaporId(idCategoria);
        HttpStatus httpStatus = respuesta.isBanderaexito() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        respuesta.setStatus(httpStatus.value() + " " + httpStatus.getReasonPhrase());
        return new ResponseEntity<>(respuesta, httpStatus);
    }
    
    //LEER CONSULTA DE REGISTRO POR NOMBRE:
    @GetMapping("/getCategoriabyNombre/{nombreCategoria}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO.
    public ResponseEntity<RespuestaDTO> consultarCategoriabyNombre(@PathVariable String nombreCategoria){
        RespuestaDTO respuesta = categoriaService.consultarCategoriaporNombre(nombreCategoria);
        HttpStatus httpStatus = respuesta.isBanderaexito() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        respuesta.setStatus(httpStatus.value() + " " + httpStatus.getReasonPhrase());
        return new ResponseEntity<>(respuesta, httpStatus);
    }
    
    //MODIFICAR REGISTRO:
    //@PostMapping("/updateCategoria")//DECLARACIÓN DEL MAPEO DEL CRUD CREAR REGISTRO.
    @PutMapping("/updateCategoria")//DECLARACIÓN DEL MAPEO DEL CRUD MODIFICAR REGISTRO.
    public ResponseEntity<RespuestaDTO> actualizarCategoria(@RequestBody CategoriaDTO categoriaDTO){
        RespuestaDTO respuesta = categoriaService.actualizarCategoria(categoriaDTO);
        HttpStatus httpStatus = respuesta.isBanderaexito() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        respuesta.setStatus(httpStatus.value() + " " + httpStatus.getReasonPhrase());
        return new ResponseEntity<>(respuesta, httpStatus);
    }
    
    //ELIMINAR REGISTRO:
    @DeleteMapping("/deleteCategoria/{idCategoria}")//DECLARACIÓN DEL MAPEO DEL CRUD ELIMINAR REGISTRO.
    public ResponseEntity<RespuestaDTO> eliminarCategoria(@PathVariable Long idCategoria){
        RespuestaDTO respuesta = categoriaService.eliminarCategoria(idCategoria);
        HttpStatus httpStatus = respuesta.isBanderaexito() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        respuesta.setStatus(httpStatus.value() + " " + httpStatus.getReasonPhrase());
        return new ResponseEntity<>(respuesta, httpStatus);
    }
}
