//DECLARACIÓN DE PAQUETES:
package com.relatospapelcatalogov10.com.co.relatospapelcatalogov10.persistencia.repository;

//IMPORTACIÓN DE LIBRERIAS:
import com.relatospapelcatalogov10.com.co.relatospapelcatalogov10.persistencia.entity.Autor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ.
* @Since 28/01/2026.
* DECLARACIÓN DE LA CLASE INTERFACE DEL REPOSITORIO QUIEN ES EL QUE HACE EL ENLACE DIRECTO HACIA LA BASE DE DATOS.
*/
public interface AutorRepository extends JpaRepository<Autor,Long> {
    
    //1. LISTADO DE REGISTROS FILTRADOS.
    @Query(value = "SELECT * FROM tbl_autores ORDER BY id_autor ASC", nativeQuery = true)
    List<Autor> findAllAutoresOrderedByIdAsc();
    
    @Query(value = "SELECT * FROM tbl_autores ORDER BY id_autor ASC", nativeQuery = true)
    Slice<Autor> findAllAutoresOrderedByIdAscPag(Pageable pageable);
    
    //2. LISTADO DE REGISTROS FILTRADOS.
    @Query(value = "" +
          "SELECT " +
          "* " +
          "FROM " +
          "tbl_autores " +
          "WHERE " +
          "(tbl_autores.nombres_autor LIKE CONCAT('%', :keyword, '%') OR " +
          "tbl_autores.primer_apellido_autor LIKE CONCAT('%', :keyword, '%') OR " +
          "tbl_autores.segundo_apellido_autor LIKE CONCAT('%', :keyword, '%')) " +
          "ORDER BY tbl_autores.id_autor ASC", nativeQuery = true)
    List<Autor> searchAutoresByKeywordOrderedByIdAsc(@Param("keyword") String keyword);
    
    @Query(value = "" +
          "SELECT " +
          "* " +
          "FROM " +
          "tbl_autores " +
          "WHERE " +
          "(tbl_autores.nombres_autor LIKE CONCAT('%', :keyword, '%') OR " +
          "tbl_autores.primer_apellido_autor LIKE CONCAT('%', :keyword, '%') OR " +
          "tbl_autores.segundo_apellido_autor LIKE CONCAT('%', :keyword, '%')) " +
          "ORDER BY tbl_autores.id_autor ASC", nativeQuery = true)
    Slice<Autor> searchAutoresByKeywordOrderedByIdAscPag(Pageable pageable, @Param("keyword") String keyword);
    
    Optional<Autor> findByIdAutor(Long idAutor);
}
