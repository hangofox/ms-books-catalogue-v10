//DECLARACIÓN DE PAQUETES:
package com.relatospapelcatalogov10.com.co.relatospapelcatalogov10.persistencia.repository;

//IMPORTACIÓN DE LIBRERIAS:
import com.relatospapelcatalogov10.com.co.relatospapelcatalogov10.persistencia.entity.Libro;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ.
* @Since 02/02/2026.
* DECLARACIÓN DE LA CLASE INTERFACE DEL REPOSITORIO QUIEN ES EL QUE HACE EL ENLACE DIRECTO HACIA LA BASE DE DATOS.
*/
public interface LibroRepository extends JpaRepository<Libro,Long> {

    //1. LISTADO DE REGISTROS FILTRADOS.
    @Query(value = "SELECT * FROM tbl_libros ORDER BY id_libro ASC", nativeQuery = true)
    List<Libro> findAllLibrosOrderedByIdAsc();

    @Query(value = "SELECT * FROM tbl_libros ORDER BY id_libro ASC", nativeQuery = true)
    Slice<Libro> findAllLibrosOrderedByIdAscPag(Pageable pageable);

    //2. LISTADO DE REGISTROS FILTRADOS.
    @Query(value = "" +
          "SELECT " +
          "* " +
          "FROM " +
          "tbl_libros " +
          "WHERE " +
          "(tbl_libros.titulo_libro LIKE CONCAT('%', :keyword, '%') OR " +
          "tbl_libros.sinopsis_libro LIKE CONCAT('%', :keyword, '%') OR " +
          "tbl_libros.codigo_isbn_libro LIKE CONCAT('%', :keyword, '%') OR " +
          "tbl_libros.precio_libro LIKE CONCAT('%', :keyword, '%') OR " +
          "tbl_libros.formato_libro LIKE CONCAT('%', :keyword, '%') OR " +
          "tbl_libros.estado_libro LIKE CONCAT('%', :keyword, '%') OR " +
          "tbl_libros.id_autor IN (SELECT id_autor FROM tbl_autores WHERE " +
          "nombres_autor LIKE CONCAT('%', :keyword, '%') OR " +
          "primer_apellido_autor LIKE CONCAT('%', :keyword, '%') OR " +
          "segundo_apellido_autor LIKE CONCAT('%', :keyword, '%'))) " +
          "ORDER BY tbl_libros.id_libro ASC", nativeQuery = true)
    List<Libro> searchLibrosByKeywordOrderedByIdAsc(@Param("keyword") String keyword);

    @Query(value = "" +
          "SELECT " +
          "* " +
          "FROM " +
          "tbl_libros " +
          "WHERE " +
          "(tbl_libros.titulo_libro LIKE CONCAT('%', :keyword, '%') OR " +
          "tbl_libros.sinopsis_libro LIKE CONCAT('%', :keyword, '%') OR " +
          "tbl_libros.codigo_isbn_libro LIKE CONCAT('%', :keyword, '%') OR " +
          "tbl_libros.precio_libro LIKE CONCAT('%', :keyword, '%') OR " +
          "tbl_libros.formato_libro LIKE CONCAT('%', :keyword, '%') OR " +
          "tbl_libros.estado_libro LIKE CONCAT('%', :keyword, '%') OR " +
          "tbl_libros.id_autor IN (SELECT id_autor FROM tbl_autores WHERE " +
          "nombres_autor LIKE CONCAT('%', :keyword, '%') OR " +
          "primer_apellido_autor LIKE CONCAT('%', :keyword, '%') OR " +
          "segundo_apellido_autor LIKE CONCAT('%', :keyword, '%'))) " +
          "ORDER BY tbl_libros.id_libro ASC", nativeQuery = true)
    Slice<Libro> searchLibrosByKeywordOrderedByIdAscPag(Pageable pageable, @Param("keyword") String keyword);

    Optional<Libro> findByIdLibro(Long idLibro);

}
