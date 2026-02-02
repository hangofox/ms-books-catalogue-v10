//DECLARACIÓN DE PAQUETES:
package com.relatospapelcatalogov10.com.co.relatospapelcatalogov10.web.controller;

//IMPORTACIÓN DE LIBRERIAS:
import com.relatospapelcatalogov10.com.co.relatospapelcatalogov10.dominio.Constantes.MensajesConstantes;
import com.relatospapelcatalogov10.com.co.relatospapelcatalogov10.dominio.dto.RespuestaDTO;
import com.relatospapelcatalogov10.com.co.relatospapelcatalogov10.dominio.dto.AutorDTO;
import com.relatospapelcatalogov10.com.co.relatospapelcatalogov10.dominio.service.AutorService;
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
@RequestMapping("/autor")//DECLARACIÓN DE LA RESPUESTA PRINCIPAL DEL MAPEO DE LOS CRUDS.
public class AutorController {
    
    @Autowired//INYECTAMOS EL SERVICIO.
    private AutorService autorService;
    
    //CONTROLADORES DE CRUDS (CREACIÓN, LECTURA (LISTAR Y CONSULTAR), EDICIÓN Y ELIMINACIÓN DE UN REGISTRO).
    
    //1. LISTADO DE REGISTROS FILTRADOS.
    //LISTAR REGISTROS:
    @GetMapping("/listAllAutores")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS.
    public ResponseEntity<RespuestaDTO> listarAutores(){
        RespuestaDTO respuesta = new RespuestaDTO();
        respuesta.setAutoresDTO(autorService.listarAutores());
        respuesta.setMensaje(MensajesConstantes.MSG_REGISTROS_LISTADOS_EXITO);
        respuesta.setBanderaexito(true);
        HttpStatus httpStatus = HttpStatus.OK;
        respuesta.setStatus(httpStatus.value() + " " + httpStatus.getReasonPhrase());
        return new ResponseEntity<>(respuesta, httpStatus);
    }
    
    //LISTAR REGISTROS ORDENADOS POR ID DE FORMA ASCENDENTE:
    @GetMapping("/listAllAutoresOrderedbyIdAsc")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS.
    public ResponseEntity<RespuestaDTO> listarAutoresOrdenadosporIdAsc(){
        RespuestaDTO respuesta = new RespuestaDTO();
        respuesta.setAutoresDTO(autorService.listarAutoresOrdenadosporIdAsc());
        respuesta.setMensaje(MensajesConstantes.MSG_REGISTROS_LISTADOS_EXITO);
        respuesta.setBanderaexito(true);
        HttpStatus httpStatus = HttpStatus.OK;
        respuesta.setStatus(httpStatus.value() + " " + httpStatus.getReasonPhrase());
        return new ResponseEntity<>(respuesta, httpStatus);
    }
    
    //LISTAR REGISTROS ORDENADOS POR ID DE FORMA ASCENDENTE CON PAGINACIÓN:
    @GetMapping("/listAllAutoresOrderedbyIdAscPag")
    public ResponseEntity<RespuestaDTO> listarAutoresOrdenadosporIdAscPag(
           @RequestParam(defaultValue = "0") int page,
           @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        RespuestaDTO respuesta = new RespuestaDTO();
        respuesta.setAutoresDTO(autorService.listarAutoresOrdenadosporIdAscPag(pageable).getContent());
        respuesta.setMensaje(MensajesConstantes.MSG_REGISTROS_LISTADOS_EXITO);
        respuesta.setBanderaexito(true);
        HttpStatus httpStatus = HttpStatus.OK;
        respuesta.setStatus(httpStatus.value() + " " + httpStatus.getReasonPhrase());
        return new ResponseEntity<>(respuesta, httpStatus);
    }
    
    //2. LISTADO DE REGISTROS FILTRADOS.
    //LISTAR REGISTROS FILTRADOS POR PALABRA CLAVE Y ORDENADOS POR ID DE FORMA ASCENDENTE:
    @GetMapping("/listAllAutoresbyKeywordAndOrderedbyIdAsc/{keyword}")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS.
    public ResponseEntity<RespuestaDTO> listarAutoresporPalabraClaveyOrdenadosporIdAsc(@PathVariable String keyword){
        RespuestaDTO respuesta = new RespuestaDTO();
        respuesta.setAutoresDTO(autorService.listarAutoresporPalabraClaveyOrdenadosporIdAsc(keyword));
        respuesta.setMensaje(MensajesConstantes.MSG_REGISTROS_LISTADOS_EXITO);
        respuesta.setBanderaexito(true);
        HttpStatus httpStatus = HttpStatus.OK;
        respuesta.setStatus(httpStatus.value() + " " + httpStatus.getReasonPhrase());
        return new ResponseEntity<>(respuesta, httpStatus);
    }
    
    //LISTAR REGISTROS FILTRADOS POR PALABRA CLAVE Y ORDENADOS POR ID DE FORMA ASCENDENTE CON PAGINACIÓN:
    @GetMapping("/listAllAutoresbyKeywordAndOrderedbyIdAscPag/{keyword}")
    public ResponseEntity<RespuestaDTO> listarAutoresporPalabraClaveyOrdenadosporIdAscPag(
           @RequestParam(name = "page", defaultValue = "0") int page,
           @RequestParam(name = "size", defaultValue = "10") int size,
           @PathVariable("keyword") String keyword
    ) {
        Pageable pageable = PageRequest.of(page, size);
        RespuestaDTO respuesta = new RespuestaDTO();
        respuesta.setAutoresDTO(autorService.listarAutoresporPalabraClaveyOrdenadosporIdAscPag(pageable, keyword).getContent());
        respuesta.setMensaje(MensajesConstantes.MSG_REGISTROS_LISTADOS_EXITO);
        respuesta.setBanderaexito(true);
        HttpStatus httpStatus = HttpStatus.OK;
        respuesta.setStatus(httpStatus.value() + " " + httpStatus.getReasonPhrase());
        return new ResponseEntity<>(respuesta, httpStatus);
    }
    
    //CREAR REGISTRO:
    @PostMapping("/createAutor")//DECLARACIÓN DEL MAPEO DEL CRUD CREAR REGISTRO.
    //@PutMapping("/createAutor")//DECLARACIÓN DEL MAPEO DEL CRUD CREAR REGISTRO.
    public ResponseEntity<RespuestaDTO> crearAutor(@RequestBody AutorDTO autorDTO){
        System.out.println(autorDTO);
        RespuestaDTO respuesta = autorService.crearAutor(autorDTO);
        HttpStatus httpStatus = respuesta.isBanderaexito() ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
        respuesta.setStatus(httpStatus.value() + " " + httpStatus.getReasonPhrase());
        return new ResponseEntity<>(respuesta, httpStatus);
    }
    
    //LEER CONSULTA DE REGISTRO POR ID:
    @GetMapping("/getAutorbyId/{idAutor}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO.
    public ResponseEntity<RespuestaDTO> consultarAutorbyId(@PathVariable Long idAutor){
        RespuestaDTO respuesta = autorService.consultarAutorporId(idAutor);
        HttpStatus httpStatus = respuesta.isBanderaexito() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        respuesta.setStatus(httpStatus.value() + " " + httpStatus.getReasonPhrase());
        return new ResponseEntity<>(respuesta, httpStatus);
    }
    
    //MODIFICAR REGISTRO:
    //@PostMapping("/updateAutor")//DECLARACIÓN DEL MAPEO DEL CRUD CREAR REGISTRO.
    @PutMapping("/updateAutor")//DECLARACIÓN DEL MAPEO DEL CRUD MODIFICAR REGISTRO.
    public ResponseEntity<RespuestaDTO> actualizarAutor(@RequestBody AutorDTO autorDTO){
        RespuestaDTO respuesta = autorService.actualizarAutor(autorDTO);
        HttpStatus httpStatus = respuesta.isBanderaexito() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        respuesta.setStatus(httpStatus.value() + " " + httpStatus.getReasonPhrase());
        return new ResponseEntity<>(respuesta, httpStatus);
    }
    
    //ELIMINAR REGISTRO:
    @DeleteMapping("/deleteAutor/{idAutor}")//DECLARACIÓN DEL MAPEO DEL CRUD ELIMINAR REGISTRO.
    public ResponseEntity<RespuestaDTO> eliminarAutor(@PathVariable Long idAutor){
        RespuestaDTO respuesta = autorService.eliminarAutor(idAutor);
        HttpStatus httpStatus = respuesta.isBanderaexito() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        respuesta.setStatus(httpStatus.value() + " " + httpStatus.getReasonPhrase());
        return new ResponseEntity<>(respuesta, httpStatus);
    }
}
