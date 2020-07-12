package com.maverick.springboot.app.productos.models.entity.service;

import java.util.List;

import com.maverick.springboot.commons.models.entity.Producto;


public interface ProductosService {

	public List<Producto> buscarTodo();
	public Producto findById(Long id);
	public Producto guardar(Producto producto);
	public void BorrarById(Long idProducto);
	public Producto editarObjeto(Producto producto, Long id);
	
}
