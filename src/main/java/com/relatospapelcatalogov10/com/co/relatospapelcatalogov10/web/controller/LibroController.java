//DECLARACIÓN DE PAQUETES:
package com.relatospapelcatalogov10.com.co.relatospapelcatalogov10.web.controller;

//IMPORTACIÓN DE LIBRERIAS:
import com.relatospapelcatalogov10.com.co.relatospapelcatalogov10.dominio.Constantes.MensajesConstantes;
import com.relatospapelcatalogov10.com.co.relatospapelcatalogov10.dominio.dto.RespuestaDTO;
import com.relatospapelcatalogov10.com.co.relatospapelcatalogov10.dominio.dto.LibroDTO;
import com.relatospapelcatalogov10.com.co.relatospapelcatalogov10.dominio.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ.
* @Since 02/02/2026.
* Declaración del controlador.
*/
@RestController//DECLARACIÓN DEL CONTROLADOR PARA LOS CRUDS.
@RequestMapping("/libro")//DECLARACIÓN DE LA RESPUESTA PRINCIPAL DEL MAPEO DE LOS CRUDS.
public class LibroController {
    
    @Autowired//INYECTAMOS EL SERVICIO.
    private LibroService libroService;
    
    //CONTROLADORES DE CRUDS (CREACIÓN, LECTURA (LISTAR Y CONSULTAR), EDICIÓN Y ELIMINACIÓN DE UN REGISTRO).
    
    //1. LISTADO DE REGISTROS FILTRADOS.
    //LISTAR REGISTROS:
    @GetMapping("/listAllLibros")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS.
    public ResponseEntity<RespuestaDTO> listarLibros(){
        RespuestaDTO respuesta = new RespuestaDTO();
        respuesta.setLibrosDTO(libroService.listarLibros());
        respuesta.setMensaje(MensajesConstantes.MSG_REGISTROS_LISTADOS_EXITO);
        respuesta.setBanderaexito(true);
        HttpStatus httpStatus = HttpStatus.OK;
        respuesta.setStatus(httpStatus.value() + " " + httpStatus.getReasonPhrase());
        return new ResponseEntity<>(respuesta, httpStatus);
    }
    
    //LISTAR REGISTROS ORDENADOS POR ID DE FORMA ASCENDENTE:
    @GetMapping("/listAllLibrosOrderedbyIdAsc")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS.
    public ResponseEntity<RespuestaDTO> listarLibrosOrdenadosporIdAsc(){
        RespuestaDTO respuesta = new RespuestaDTO();
        respuesta.setLibrosDTO(libroService.listarLibrosOrdenadosporIdAsc());
        respuesta.setMensaje(MensajesConstantes.MSG_REGISTROS_LISTADOS_EXITO);
        respuesta.setBanderaexito(true);
        HttpStatus httpStatus = HttpStatus.OK;
        respuesta.setStatus(httpStatus.value() + " " + httpStatus.getReasonPhrase());
        return new ResponseEntity<>(respuesta, httpStatus);
    }
    
    //LISTAR REGISTROS ORDENADOS POR ID DE FORMA ASCENDENTE CON PAGINACIÓN:
    @GetMapping("/listAllLibrosOrderedbyIdAscPag")
    public ResponseEntity<RespuestaDTO> listarLibrosOrdenadosporIdAscPag(
           @RequestParam(defaultValue = "0") int page,
           @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        RespuestaDTO respuesta = new RespuestaDTO();
        respuesta.setLibrosDTO(libroService.listarLibrosOrdenadosporIdAscPag(pageable).getContent());
        respuesta.setMensaje(MensajesConstantes.MSG_REGISTROS_LISTADOS_EXITO);
        respuesta.setBanderaexito(true);
        HttpStatus httpStatus = HttpStatus.OK;
        respuesta.setStatus(httpStatus.value() + " " + httpStatus.getReasonPhrase());
        return new ResponseEntity<>(respuesta, httpStatus);
    }
    
    //2. LISTADO DE REGISTROS FILTRADOS.
    //LISTAR REGISTROS FILTRADOS POR PALABRA CLAVE Y ORDENADOS POR ID DE FORMA ASCENDENTE:
    @GetMapping("/listAllLibrosbyKeywordAndOrderedbyIdAsc/{keyword}")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS.
    public ResponseEntity<RespuestaDTO> listarLibrosporPalabraClaveyOrdenadosporIdAsc(@PathVariable String keyword){
        RespuestaDTO respuesta = new RespuestaDTO();
        respuesta.setLibrosDTO(libroService.listarLibrosporPalabraClaveyOrdenadosporIdAsc(keyword));
        respuesta.setMensaje(MensajesConstantes.MSG_REGISTROS_LISTADOS_EXITO);
        respuesta.setBanderaexito(true);
        HttpStatus httpStatus = HttpStatus.OK;
        respuesta.setStatus(httpStatus.value() + " " + httpStatus.getReasonPhrase());
        return new ResponseEntity<>(respuesta, httpStatus);
    }
    
    //LISTAR REGISTROS FILTRADOS POR PALABRA CLAVE Y ORDENADOS POR ID DE FORMA ASCENDENTE CON PAGINACIÓN:
    @GetMapping("/listAllLibrosbyKeywordAndOrderedbyIdAscPag/{keyword}")
    public ResponseEntity<RespuestaDTO> listarLibrosporPalabraClaveyOrdenadosporIdAscPag(
           @RequestParam(name = "page", defaultValue = "0") int page,
           @RequestParam(name = "size", defaultValue = "10") int size,
           @PathVariable("keyword") String keyword
    ) {
        Pageable pageable = PageRequest.of(page, size);
        RespuestaDTO respuesta = new RespuestaDTO();
        respuesta.setLibrosDTO(libroService.listarLibrosporPalabraClaveyOrdenadosporIdAscPag(pageable, keyword).getContent());
        respuesta.setMensaje(MensajesConstantes.MSG_REGISTROS_LISTADOS_EXITO);
        respuesta.setBanderaexito(true);
        HttpStatus httpStatus = HttpStatus.OK;
        respuesta.setStatus(httpStatus.value() + " " + httpStatus.getReasonPhrase());
        return new ResponseEntity<>(respuesta, httpStatus);
    }
    
    //CREAR REGISTRO:
    @PostMapping("/createLibro")//DECLARACIÓN DEL MAPEO DEL CRUD CREAR REGISTRO.
    //@PutMapping("/createLibro")//DECLARACIÓN DEL MAPEO DEL CRUD CREAR REGISTRO.
    public ResponseEntity<RespuestaDTO> crearLibro(@RequestBody LibroDTO libroDTO){
        System.out.println(libroDTO);
        RespuestaDTO respuesta = libroService.crearLibro(libroDTO);
        HttpStatus httpStatus = respuesta.isBanderaexito() ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
        respuesta.setStatus(httpStatus.value() + " " + httpStatus.getReasonPhrase());
        return new ResponseEntity<>(respuesta, httpStatus);
    }
    
    //LEER CONSULTA DE REGISTRO POR ID:
    @GetMapping("/getLibrobyId/{idLibro}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO.
    public ResponseEntity<RespuestaDTO> consultarLibrobyId(@PathVariable Long idLibro){
        RespuestaDTO respuesta = libroService.consultarLibroporId(idLibro);
        HttpStatus httpStatus = respuesta.isBanderaexito() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        respuesta.setStatus(httpStatus.value() + " " + httpStatus.getReasonPhrase());
        return new ResponseEntity<>(respuesta, httpStatus);
    }
    
    //MODIFICAR REGISTRO:
    //@PostMapping("/updateLibro")//DECLARACIÓN DEL MAPEO DEL CRUD CREAR REGISTRO.
    @PutMapping("/updateLibro")//DECLARACIÓN DEL MAPEO DEL CRUD MODIFICAR REGISTRO.
    public ResponseEntity<RespuestaDTO> actualizarLibro(@RequestBody LibroDTO libroDTO){
        RespuestaDTO respuesta = libroService.actualizarLibro(libroDTO);
        HttpStatus httpStatus = respuesta.isBanderaexito() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        respuesta.setStatus(httpStatus.value() + " " + httpStatus.getReasonPhrase());
        return new ResponseEntity<>(respuesta, httpStatus);
    }
    
    //ELIMINAR REGISTRO:
    @DeleteMapping("/deleteLibro/{idLibro}")//DECLARACIÓN DEL MAPEO DEL CRUD ELIMINAR REGISTRO.
    public ResponseEntity<RespuestaDTO> eliminarLibro(@PathVariable Long idLibro){
        RespuestaDTO respuesta = libroService.eliminarLibro(idLibro);
        HttpStatus httpStatus = respuesta.isBanderaexito() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        respuesta.setStatus(httpStatus.value() + " " + httpStatus.getReasonPhrase());
        return new ResponseEntity<>(respuesta, httpStatus);
    }
}
