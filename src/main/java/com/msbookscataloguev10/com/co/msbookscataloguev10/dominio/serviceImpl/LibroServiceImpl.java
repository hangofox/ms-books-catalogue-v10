//DECLARACIÓN DE PAQUETES:
package com.msbookscataloguev10.com.co.msbookscataloguev10.dominio.serviceImpl;

//IMPORTACIÓN DE LIBRERIAS:
import com.msbookscataloguev10.com.co.msbookscataloguev10.dominio.Constantes.MensajesConstantes;
import com.msbookscataloguev10.com.co.msbookscataloguev10.dominio.dto.RespuestaDTO;
import com.msbookscataloguev10.com.co.msbookscataloguev10.dominio.dto.LibroDTO;
import com.msbookscataloguev10.com.co.msbookscataloguev10.dominio.service.LibroService;
import com.msbookscataloguev10.com.co.msbookscataloguev10.persistencia.dao.LibroDAO;
import com.msbookscataloguev10.com.co.msbookscataloguev10.persistencia.entity.Categoria;
import com.msbookscataloguev10.com.co.msbookscataloguev10.persistencia.entity.Libro;
import com.msbookscataloguev10.com.co.msbookscataloguev10.persistencia.repository.CategoriaRepository;
import com.msbookscataloguev10.com.co.msbookscataloguev10.persistencia.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import com.msbookscataloguev10.com.co.msbookscataloguev10.persistencia.entity.Autor;
import com.msbookscataloguev10.com.co.msbookscataloguev10.persistencia.repository.AutorRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


/**
 * @Autor PD04. HERNAN ADOLFO NUÑEZ GONZALEZ.
 * @Since 02/02/2026.
 * Declaración de la entidad.
 * @Actualizacion David Paez 04/02/2026.
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

    //validar y cargar categorias
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private AutorRepository autorRepository;





    //MÉTODO ÚNICO PARA LISTAR/FILTRAR/ORDENAR/PAGINAR LIBROS:

    @Override
    public Slice<LibroDTO> listarLibros(String keyword, Long idCategoria, String orderBy, String orderMode, Pageable pageable) {

        Slice<Libro> libros;

        boolean hasKeyword = keyword != null && !keyword.trim().isEmpty();
        boolean hasCategoria = idCategoria != null;

        if (hasKeyword && hasCategoria) {
            libros = libroRepository.findLibrosByKeywordAndCategoriaWithOrder(keyword, idCategoria, orderBy, orderMode, pageable);
        } else if (hasKeyword) {
            libros = libroRepository.findLibrosByKeywordWithOrder(keyword, orderBy, orderMode, pageable);
        } else if (hasCategoria) {
            libros = libroRepository.findLibrosByCategoriaWithOrder(idCategoria, orderBy, orderMode, pageable);
        } else {
            libros = libroRepository.findAllLibrosWithOrder(orderBy, orderMode, pageable);
        }

        return libros.map(libro -> libroDAO.libroDTO(libro));
    }



    //CREAR REGISTRO:
    @Override//SOBREESCRIBIMOS EL METODO DE CREAR REGISTRO.
    public RespuestaDTO crearLibro(LibroDTO libroDTO) {

        RespuestaDTO respuestaDTO = new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_NO_CREADO, false);

        // 1) Ignorar ID (autoincremental)
        libroDTO.setIdLibro(null);

        // 2) Validar que venga el idAutor
        if (libroDTO.getAutorDTO() == null || libroDTO.getAutorDTO().getIdAutor() == null) {
            return new RespuestaDTO("Debes enviar autorDTO.idAutor", false);
        }

        Long idAutor = libroDTO.getAutorDTO().getIdAutor();

        // 3) Buscar el autor REAL en BD (esto evita el transient)
        Autor autor = autorRepository.findById(idAutor)
                .orElseThrow(() -> new IllegalArgumentException("No existe autor con id: " + idAutor));

        // 4) Construir el libro desde el DAO
        Libro libro = libroDAO.libro(libroDTO);

        // 5) Reemplazar el autor del libro por el autor MANAGED (de BD)
        libro.setAutor(autor);

        // 6) Guardar
        libroRepository.save(libro);

        return new RespuestaDTO(MensajesConstantes.MSG_REGISTRO_CREADO_EXITO, true);
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
    //RELACIÓN LIBRO-CATEGORIA
    @Override
    public RespuestaDTO agregarCategoriaALibro(Long idLibro, Long idCategoria) {

        Optional<Libro> libroOpt = libroRepository.findByIdLibro(idLibro);
        if (!libroOpt.isPresent()) {
            return new RespuestaDTO("LIBRO NO ENCONTRADO", false);
        }

        Optional<Categoria> catOpt = categoriaRepository.findByIdCategoria(idCategoria);
        if (!catOpt.isPresent()) {
            return new RespuestaDTO("CATEGORIA NO ENCONTRADA", false);
        }

        Libro libro = libroOpt.get();
        if (libro.getCategorias() == null) {
            libro.setCategorias(new HashSet<>());
        }

        libro.getCategorias().add(catOpt.get());
        libroRepository.save(libro);

        return new RespuestaDTO("CATEGORIA AGREGADA AL LIBRO CORRECTAMENTE", true);
    }

    @Override
    public RespuestaDTO eliminarCategoriaDeLibro(Long idLibro, Long idCategoria) {

        Optional<Libro> libroOpt = libroRepository.findByIdLibro(idLibro);
        if (!libroOpt.isPresent()) {
            return new RespuestaDTO("LIBRO NO ENCONTRADO", false);
        }

        Libro libro = libroOpt.get();
        if (libro.getCategorias() == null || libro.getCategorias().isEmpty()) {
            return new RespuestaDTO("EL LIBRO NO TIENE CATEGORIAS ASIGNADAS", false);
        }

        // remover por idCategoria
        boolean removed = libro.getCategorias().removeIf(c -> c.getIdCategoria().equals(idCategoria));
        if (!removed) {
            return new RespuestaDTO("LA CATEGORIA NO ESTÁ ASIGNADA A ESTE LIBRO", false);
        }

        libroRepository.save(libro);
        return new RespuestaDTO("CATEGORIA ELIMINADA DEL LIBRO CORRECTAMENTE", true);
    }

    @Override
    public RespuestaDTO reemplazarCategoriasDeLibro(Long idLibro, List<Long> categoriasIds) {

        Optional<Libro> libroOpt = libroRepository.findByIdLibro(idLibro);
        if (!libroOpt.isPresent()) {
            return new RespuestaDTO("LIBRO NO ENCONTRADO", false);
        }

        Libro libro = libroOpt.get();

        Set<Categoria> nuevasCategorias = new HashSet<>();
        if (categoriasIds != null) {
            for (Long idCat : categoriasIds) {
                Optional<Categoria> catOpt = categoriaRepository.findByIdCategoria(idCat);
                if (!catOpt.isPresent()) {
                    return new RespuestaDTO("CATEGORIA NO ENCONTRADA: " + idCat, false);
                }
                nuevasCategorias.add(catOpt.get());
            }
        }

        libro.setCategorias(nuevasCategorias);
        libroRepository.save(libro);

        return new RespuestaDTO("CATEGORIAS REEMPLAZADAS CORRECTAMENTE", true);
    }

}
