package com.vise.service;

import java.util.List;

import com.vise.entity.Producto;

public interface IProductoService {

	public List<Producto> listAll();
	
	public List<Producto> listByBrand(String marca);
	
	public List<Producto> listByPrecio(float precio);
	
	public void saveProducto(Producto producto);
	
	public Producto getProductoById(long id);
	
	public void deleteProductoById(long id);
}
