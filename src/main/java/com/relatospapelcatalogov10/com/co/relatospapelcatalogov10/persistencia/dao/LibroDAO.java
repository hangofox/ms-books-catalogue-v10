//DECLARACIÓN DE PAQUETES:
package com.relatospapelcatalogov10.com.co.relatospapelcatalogov10.persistencia.dao;

//IMPORTACIÓN DE LIBRERIAS:
import com.relatospapelcatalogov10.com.co.relatospapelcatalogov10.dominio.dto.LibroDTO;
import com.relatospapelcatalogov10.com.co.relatospapelcatalogov10.persistencia.entity.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ.
* @Since 02/02/2026.
* Declaración del método DAO.
*/
@Component//DECLARACIÓN DEL COMPONENTE PARA LOS METODOS DEL DAO.
public class LibroDAO {

    @Autowired//INYECTAMOS EL DAO DE AUTOR.
    private AutorDAO autorDAO;

    /**
    * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ.
    * @Since 02/02/2026.
    * @param libroDTO
    * Recibe un DTO para crear un objeto libro.
    * @return libro
    */
    public Libro libro(LibroDTO libroDTO){
        Libro libro = new Libro();
        libro.setIdLibro(libroDTO.getIdLibro());
        libro.setTituloLibro(libroDTO.getTituloLibro().toUpperCase());
        libro.setAnioPublicacionLibro(libroDTO.getAnioPublicacionLibro());
        libro.setSinopsisLibro(libroDTO.getSinopsisLibro());
        libro.setCodigoIsbnLibro(libroDTO.getCodigoIsbnLibro());
        libro.setPrecioLibro(libroDTO.getPrecioLibro());
        libro.setFormatoLibro(libroDTO.getFormatoLibro());
        libro.setNombreArchivoImagenLibro(libroDTO.getNombreArchivoImagenLibro());
        libro.setEstadoLibro(libroDTO.getEstadoLibro().toUpperCase());

        //MAPEAR EL AUTOR SI EXISTE EN EL DTO:
        if (libroDTO.getAutorDTO() != null) {
            libro.setAutor(autorDAO.autor(libroDTO.getAutorDTO()));
        }

        return libro;
    }

    /**
    * @Autor HERNAN ADOLFO NUÑEZ GONZALEZ.
    * @Since 02/02/2026.
    * @param libro
    * Recibe un objeto libro para crear un DTO.
    * @return libroDTO
    */
    public LibroDTO libroDTO(Libro libro){
        LibroDTO libroDTO = new LibroDTO();
        libroDTO.setIdLibro(libro.getIdLibro());
        libroDTO.setTituloLibro(libro.getTituloLibro().toUpperCase());
        libroDTO.setAnioPublicacionLibro(libro.getAnioPublicacionLibro());
        libroDTO.setSinopsisLibro(libro.getSinopsisLibro());
        libroDTO.setCodigoIsbnLibro(libro.getCodigoIsbnLibro());
        libroDTO.setPrecioLibro(libro.getPrecioLibro());
        libroDTO.setFormatoLibro(libro.getFormatoLibro());
        libroDTO.setNombreArchivoImagenLibro(libro.getNombreArchivoImagenLibro());
        libroDTO.setEstadoLibro(libro.getEstadoLibro().toUpperCase());

        //MAPEAR EL AUTOR SI EXISTE EN LA ENTIDAD:
        if (libro.getAutor() != null) {
            libroDTO.setAutorDTO(autorDAO.autorDTO(libro.getAutor()));
        }

        return libroDTO;
    }
}
