package com.vise.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vise.entity.Producto;
import com.vise.repository.ProductoRepository;

/**
* Título                    : ProductoService.java
* Descripción        		: Service para la lógica de funciones para Producto.java
* Fecha de creación         : 2023-06-21
* Versión       	        : 1.0.0.0
* 
* @author Hugo Aldrete
*/
@Service
@Transactional
public class ProductoServiceImpl implements IProductoService{

	@Autowired
    private ProductoRepository repo;
     
	/**
	* Título                    : listAll
	* Descripción        		: Función para consultar todos los productos guardados.
	* Fecha de creación         : 2023-07-01
	* Versión       	        : 1.0.0.0
	* 
	* @author Hugo Aldrete
	*/
	@Transactional(readOnly = true)
	@Override
    public List<Producto> listAll() {
    	try {
    	List<Producto> lstProductos = new ArrayList<>();
    	lstProductos = repo.findAll();
    	if(lstProductos == null) {
    		return new ArrayList<Producto>();
    	}
    	return lstProductos;
    	}catch (Exception e) {
    		
			throw new RuntimeException("Error al consultar todos los productos.");
		}
    }
    
	/**
	* Título                    : listByBrand
	* Descripción        		: Función para consultar los productos por marca.
	* Fecha de creación         : 2023-07-01
	* Versión       	        : 1.0.0.0
	* 
	* @author Hugo Aldrete
	*/
	@Transactional(readOnly = true)
	@Override
    public List<Producto> listByBrand(String marca) {
    	try {
    	List<Producto> lstProductos = new ArrayList<>();
    	lstProductos = repo.findProductos(marca);
    	if(lstProductos == null) {
    		return new ArrayList<Producto>();
    	}
    	return lstProductos;
    	}catch (Exception e) {
    		throw new RuntimeException("Error al consultar por marca.");
		}
    }
    
	/**
	* Título                    : listByPrecio
	* Descripción        		: Función para consultar los productos por precio mayor.
	* Fecha de creación         : 2023-07-01
	* Versión       	        : 1.0.0.0
	* 
	* @author Hugo Aldrete
	*/
	@Transactional(readOnly = true)
	@Override
    public List<Producto> listByPrecio(float precio) {
    	try {
    	List<Producto> lstProductos = new ArrayList<>();
    	lstProductos = repo.findProductosByPrecio(precio);
    	if(lstProductos == null) {
    		return new ArrayList<Producto>();
    	}
    	return lstProductos;
    	}catch (Exception e) {
    		throw new RuntimeException("Error al consultar por precio.");
		}
    }
    
	/**
	* Título                    : saveProducto
	* Descripción        		: Función para guardar producto.
	* Fecha de creación         : 2023-07-01
	* Versión       	        : 1.0.0.0
	* 
	* @author Hugo Aldrete
	*/
	@Transactional
	@Override
    public void saveProducto(Producto producto) {
    	try {
        repo.save(producto);
    	}catch (Exception e) {
    		throw new RuntimeException("Error al guardar información.");
		}
    }
     
	/**
	* Título                    : getProductoById
	* Descripción        		: Función para consultar producto por id.
	* Fecha de creación         : 2023-07-01
	* Versión       	        : 1.0.0.0
	* 
	* @author Hugo Aldrete
	*/
	@Transactional(readOnly = true)
	@Override
    public Producto getProductoById(long id) {
    	try {
    	Producto productoBD = new Producto();
    	productoBD = repo.findById(id).get(); 
        return productoBD;
        }catch (Exception e) {
			throw new RuntimeException("Error al consultar por id.");
		}
    }
     
	/**
	* Título                    : deleteProductoById
	* Descripción        		: Función para eliminar producto por id.
	* Fecha de creación         : 2023-07-01
	* Versión       	        : 1.0.0.0
	* 
	* @author Hugo Aldrete
	*/
	@Transactional
	@Override
    public void deleteProductoById(long id) {
    	try {
        repo.deleteById(id);
        }catch (Exception e) {
        	throw new RuntimeException("Error al eliminar producto.");
		}
    }
}
