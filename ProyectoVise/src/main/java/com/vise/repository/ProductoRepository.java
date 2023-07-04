package com.vise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vise.entity.Producto;

/**
* Título                    : ProductoRepository.java
* Descripción        		: CRUD de Producto.java
* Fecha de creación         : 2023-06-21
* Versión       	        : 1.0.0.0
* 
* @author Hugo Aldrete
*/
public interface ProductoRepository extends JpaRepository<Producto, Long>{

	static final String queryFindByBrand = "SELECT * FROM producto WHERE Marca LIKE %:marca%";
	
	static final String queryFindByPrecio = "SELECT * FROM producto WHERE precio > :precio";
	
	static final String queryDeleteById = "DELETE FROM producto WHERE idProducto = :id";
	
	@Query(value = queryFindByBrand, nativeQuery = true)
	public List<Producto> findProductos(@Param("marca") String marca);
	
	@Query(value = queryFindByPrecio, nativeQuery = true)
	public List<Producto> findProductosByPrecio(@Param("precio") float precio);
	
	@Modifying
	@Query(value = queryDeleteById, nativeQuery = true)
	public void deleteById(@Param("id") int id);
}
