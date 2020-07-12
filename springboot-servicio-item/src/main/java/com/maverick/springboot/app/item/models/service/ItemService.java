package com.maverick.springboot.app.item.models.service;

import java.util.List;

import com.maverick.springboot.app.item.models.Item;
import com.maverick.springboot.commons.models.entity.Producto;

public interface ItemService {
	
	public List<Item> buscarTodo();
	public Item findById(Long id, Integer cantidad);
	
	public Producto guardar(Producto producto);
	
	public Producto actualizar(Producto producto, Long id);
	
	public void eliminar(Long idProducto);
	

}
