package com.maverick.springboot.app.item.models.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maverick.springboot.app.item.clientes.ProductoClienteRest;
import com.maverick.springboot.app.item.models.Item;
import com.maverick.springboot.commons.models.entity.Producto;



@Service("serviceFeign")
public class ItemServiceFeign implements ItemService {
	
	@Autowired
	private ProductoClienteRest clienteFeign;

	@Override
	public List<Item> buscarTodo() {
		return clienteFeign.getListaProductos().stream().map(producto -> new Item(producto, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		return new Item(clienteFeign.verDetalle(id), cantidad);
	}

	@Override
	public void eliminar(Long idProducto) {
		clienteFeign.eliminar(idProducto);
	}

	@Override
	public Producto guardar(Producto producto) {
		return clienteFeign.crearProducto(producto);
	}

	@Override
	public Producto actualizar(Producto producto, Long id) {
		return clienteFeign.editarProduct(producto, id);
	}

}
