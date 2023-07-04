package com.vise.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.vise.entity.Producto;
import com.vise.service.IProductoService;

import io.swagger.v3.oas.annotations.Operation;

/**
* Título                    : ProductoController.java
* Descripción        		: Controlador para servicios relacionados a Producto,java
* Fecha de creación         : 2023-06-21
* Versión       	        : 1.0.0.0
* 
* @author Hugo Aldrete
*/
@Controller
public class ProductoController {

	@Autowired
	private IProductoService productoService;
	
	private static final Logger log = LogManager.getLogger(ProductoController.class);
	
	@Operation(summary = "Consulta de todos los productos.")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String viewHomePage(Model model) {
		try {
		    List<Producto> listProductos = productoService.listAll();
		    model.addAttribute("listProductos", listProductos);
		    
		    return "index";
		}catch (Exception e) {
			log.error("Error al consultar productos.");
			return "";
		}
	}
	
	@Operation(summary = "Consulta de productos por marca.")
	@RequestMapping(value = "/findByBrand/{marca}", method = RequestMethod.GET)
	public ModelAndView findByBrand(@PathVariable(name = "marca")String marca) {
		try {
		    List<Producto> listProductos = productoService.listByBrand(marca);
		    ModelAndView mav = new ModelAndView("product_brand");
		    mav.addObject("listProductos", listProductos);
		    
		    return mav;
		}catch (Exception e) {
			log.error("Error al consultar por marca.");
			return null;
		}
	}
	
	@Operation(summary = "Consulta de productos por precio.")
	@RequestMapping(value = "/findByPrecio/{precio}", method = RequestMethod.GET)
	public ModelAndView findByPrecio(@PathVariable(name = "precio")float precio) {
		try {
		    List<Producto> listProductos = productoService.listByPrecio(precio);
		    ModelAndView mav = new ModelAndView("product_brand");
		    mav.addObject("listProductos", listProductos);
		    
		    return mav;
		}catch (Exception e) {
			log.error("Error al consultar por precio.");
			return null;
		}
	}
	
	@Operation(summary = "Captura nuevo producto.")
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String showNewProductPage(Model model) {
		try {
		    Producto producto = new Producto();
		    model.addAttribute("producto", producto);
		     
		    return "new_product";
		}catch (Exception e) {
			log.error("Error al agregar producto.");
			return "";
		}
	}
	
	@Operation(summary = "Guarda producto.")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("producto") Producto producto) {
		try {
			productoService.saveProducto(producto);
			
		    return "redirect:/";
		}catch (Exception e) {
			System.out.println("PRUEBA");
			log.error("Error al guardar producto.");
			return "";
		}
	}
	
	@Operation(summary = "Edita producto.")
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
		try {
		    ModelAndView mav = new ModelAndView("edit_product");
		    Producto producto = productoService.getProductoById(id);
		    mav.addObject("producto", producto);
	     
		    return mav;
		}catch (Exception e) {
			log.error("Error al editar producto.");
			return null;
		}
	}
	
	@Operation(summary = "Elimina producto.")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public String deleteProduct(@PathVariable(name = "id") int id) {
		try {
			productoService.deleteProductoById(id);
		
			return "redirect:/";
		}catch (Exception e) {
			log.error("Error al eliminar producto.");
			return "";
		}
	}
}
