//DECLARACIÓN DE PAQUETES:
package com.relatospapelcatalogov10.com.co.relatospapelcatalogov10.persistencia.repository;

//IMPORTACIÓN DE LIBRERIAS:
import com.relatospapelcatalogov10.com.co.relatospapelcatalogov10.persistencia.entity.Categoria;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

/**
* @Categoria HERNAN ADOLFO NUÑEZ GONZALEZ.
* @Since 28/01/2026.
* DECLARACIÓN DE LA CLASE INTERFACE DEL REPOSITORIO QUIEN ES EL QUE HACE EL ENLACE DIRECTO HACIA LA BASE DE DATOS.
*/
public interface CategoriaRepository extends JpaRepository<Categoria,Long> {
    
    //1. LISTADO DE REGISTROS FILTRADOS.
    @Query(value = "SELECT * FROM tbl_categorias ORDER BY id_categoria ASC", nativeQuery = true)
    List<Categoria> findAllCategoriasOrderedByIdAsc();
    
    @Query(value = "SELECT * FROM tbl_categorias ORDER BY id_categoria ASC", nativeQuery = true)
    Slice<Categoria> findAllCategoriasOrderedByIdAscPag(Pageable pageable);
    
    //2. LISTADO DE REGISTROS FILTRADOS.
    @Query(value = "" +
          "SELECT " +
          "* " +
          "FROM " +
          "tbl_categorias " +
          "WHERE " +
          "(tbl_categorias.nombre_categoria LIKE CONCAT('%', :keyword, '%')) " +
          "ORDER BY tbl_categorias.id_categoria ASC", nativeQuery = true)
    List<Categoria> searchCategoriasByKeywordOrderedByIdAsc(@Param("keyword") String keyword);
    
    @Query(value = "" +
          "SELECT " +
          "* " +
          "FROM " +
          "tbl_categorias " +
          "WHERE " +
          "(tbl_categorias.nombre_categoria LIKE CONCAT('%', :keyword, '%')) " +
          "ORDER BY tbl_categorias.id_categoria ASC", nativeQuery = true)
    Slice<Categoria> searchCategoriasByKeywordOrderedByIdAscPag(Pageable pageable, @Param("keyword") String keyword);
    
    Optional<Categoria> findByIdCategoria(Long idCategoria);
    
    Categoria findByNombreCategoria(String nombreCategoria);
}
