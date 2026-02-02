//DECLARACIÓN DE PAQUETES:
package com.relatospapelcatalogov10.com.co.relatospapelcatalogov10.dominio.serviceImpl;

//IMPORTACIÓN DE LIBRERIAS:
import com.relatospapelcatalogov10.com.co.relatospapelcatalogov10.dominio.Constantes.MensajesConstantes;
import com.relatospapelcatalogov10.com.co.relatospapelcatalogov10.dominio.dto.RespuestaDTO;
import com.relatospapelcatalogov10.com.co.relatospapelcatalogov10.dominio.dto.LibroDTO;
import com.relatospapelcatalogov10.com.co.relatospapelcatalogov10.dominio.service.LibroService;
import com.relatospapelcatalogov10.com.co.relatospapelcatalogov10.persistencia.dao.LibroDAO;
import com.relatospapelcatalogov10.com.co.relatospapelcatalogov10.persistencia.entity.Libro;
import com.relatospapelcatalogov10.com.co.relatospapelcatalogov10.persistencia.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ.
* @Since 02/02/2026.
* Esta es la declaración de la implementación del servicio.
* Se inyectan DAOS y repositorios.
*/
@Service//DECLARACIÓN DE LA IMPLEMENTACIÓN DEL SERVICIO.
//DECLARACIÓN DE LA CLASE DE LA IMPLEMENTACIÓN DEL SERVICIO:
public class LibroServiceImpl implements LibroService {
    @Autowired//INYECTAMOS EL DAO.
    private LibroDAO libroDAO;
    
    @Autowired//INYECTAMOS EL REPOSITORIO.
    private LibroRepository libroRepository;
    
    //1. LISTADO DE REGISTROS FILTRADOS.
    //LISTAR REGISTROS:
    @Override//SOBREESCRIBIMOS EL METODO DE LISTAR REGISTROS.
    public List<LibroDTO> listarLibros() {
        List<Libro> libros = libroRepository.findAll();
        List<LibroDTO> libroDTOS = new ArrayList<>();
        
        for (Libro libro : libros){
            libroDTOS.add(libroDAO.libroDTO(libro));
        }
        
        return libroDTOS;
    }
    
    //LISTAR REGISTROS ORDENADOS POR ID DE FORMA ASCENDENTE:
    @Override//SOBREESCRIBIMOS EL METODO DE LISTAR REGISTROS.
    public List<LibroDTO> listarLibrosOrdenadosporIdAsc() {
        List<Libro> libros = libroRepository.findAllLibrosOrderedByIdAsc();
        List<LibroDTO> libroDTOS = new ArrayList<>();
        
        for (Libro libro : libros){
            libroDTOS.add(libroDAO.libroDTO(libro));
        }
        
        return libroDTOS;
    }
    
    //LISTAR REGISTROS ORDENADOS POR ID DE FORMA ASCENDENTE CON PAGINACIÓN:
    @Override//SOBREESCRIBIMOS EL METODO DE LISTAR REGISTROS.
    public Slice<LibroDTO> listarLibrosOrdenadosporIdAscPag(Pageable pageable) {
        Slice<Libro> libros = libroRepository.findAllLibrosOrderedByIdAscPag(pageable);
        return libros.map(libro -> libroDAO.libroDTO(libro));
    }
    
    //2. LISTADO DE REGISTROS FILTRADOS.
    //LISTAR REGISTROS FILTRADOS POR PALABRA CLAVE Y ORDENADOS POR ID DE FORMA ASCENDENTE:
    @Override//SOBREESCRIBIMOS EL METODO DE LISTAR REGISTROS.
    public List<LibroDTO> listarLibrosporPalabraClaveyOrdenadosporIdAsc(String keyword) {
        List<Libro> libros = libroRepository.searchLibrosByKeywordOrderedByIdAsc(keyword);
        List<LibroDTO> libroDTOS = new ArrayList<>();
        
        for (Libro libro : libros) {
            libroDTOS.add(libroDAO.libroDTO(libro));
        }
        
        return libroDTOS;
    }
    
    //LISTAR REGISTROS FILTRADOS POR PALABRA CLAVE Y ORDENADOS POR ID DE FORMA ASCENDENTE CON PAGINACIÓN:
    @Override//SOBREESCRIBIMOS EL METODO DE LISTAR REGISTROS.
    public Slice<LibroDTO> listarLibrosporPalabraClaveyOrdenadosporIdAscPag(Pageable pageable, String keyword) {
        Slice<Libro> libros = libroRepository.searchLibrosByKeywordOrderedByIdAscPag(pageable, keyword);
        return libros.map(libro -> libroDAO.libroDTO(libro));
    }
    
    //CREAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE CREAR REGISTRO.
    public RespuestaDTO crearLibro(LibroDTO libroDTO) {
        Long maxIdLibro = null;
        //Optional<Libro> libroId = libroRepository.findByIdLibro(libroDTO.getIdLibro());
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_CREADO, false);
        
        libroDTO.setIdLibro(null);//SE IGNORA EL ID ENVIADO PARA QUE LA BASE DE DATOS ASIGNE UNO NUEVO AUTOINCREMENTAL.
        libroRepository.save(libroDAO.libro(libroDTO));
        respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_CREADO_EXITO, true);
        
        return respuestaDTO;
    }
    
    //LEER CONSULTA DE REGISTRO POR ID:
    @Override//SOBREESCRIBIMOS EL METODO DE LEER CONSULTA DE REGISTRO.
    public RespuestaDTO consultarLibroporId(Long idLibro) {
        Optional<Libro> libroId = libroRepository.findByIdLibro(Long.valueOf(idLibro));
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
        //LibroDTO libroDTO = null;
        if (libroId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA EXITOSA CON EL ID PROPORCIONADO.
           //libroDTO.setNombreTipoDocumentoIdentificacion("CEDULA");
           respuestaDTO.setLibroDTO(libroDAO.libroDTO(libroId.get()));
           respuestaDTO.setMensaje(MensajesConstantes.MSG_REGISTRO_CONSULTADO_EXITO);
           respuestaDTO.setBanderaexito(true);
        }
        if (libroId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS MUESTRA EL REGISTRO CON UN MENSAJE DE CONSULTA NO EXITOSA CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
           respuestaDTO.setLibroDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //MODIFICAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE MODIFICAR REGISTRO.
    public RespuestaDTO actualizarLibro(LibroDTO libroDTO) {
        Optional<Libro> libroId = libroRepository.findByIdLibro(libroDTO.getIdLibro());
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_ACTUALIZADO, false);
        
        if (libroId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS SE VERIFICA EL ID DEL REGISTRO CON EL ID PROPORCIONADO.
           Libro libro = libroDAO.libro(libroDTO);
           libroRepository.save(libro);
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ACTUALIZADO_EXITO, true);
        }
        if (libroId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS SE MUESTRA UN MENSAJE DE REGISTRO NO MODIFICADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_ACTUALIZADO, false);
           respuestaDTO.setLibroDTO(null);
        }
        
        return respuestaDTO;
    }
    
    //ELIMINAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE ELIMINAR REGISTRO.
    public RespuestaDTO eliminarLibro(Long idLibro) {
        Optional<Libro> libroId = libroRepository.findById(idLibro);
        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
        
        if (libroId.isPresent()==true) {//SI ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS ELIMINA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO ELIMINADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO.setLibroDTO(libroDAO.libroDTO(libroId.get()));
            libroRepository.delete(libroId.get());
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ELIMINADO_EXITO, true);
        }
        if (libroId.isPresent()==false) {//SI NO ENCONTRO EL ID DEL REGISTRO EN LA TABLA DE LA BASE DE DATOS NO ELIMINA EL REGISTRO Y MUESTRA UN MENSAJE DE REGISTRO NO ELIMINADO EXITOSAMENTE CON EL ID PROPORCIONADO.
           respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_ID_NO_ENCONTRADO, false);
           respuestaDTO.setLibroDTO(null);
        }
        
        return respuestaDTO;
    }
}
