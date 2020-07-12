package com.maverick.springboot.app.item.models.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.maverick.springboot.app.item.models.Item;
import com.maverick.springboot.commons.models.entity.Producto;

@Service("serviceRestClient")
public class ItemServiceImpl implements ItemService {

	@Autowired 
	private RestTemplate clienteRestItem;
	
	@Override
	public List<Item> buscarTodo() {
		List<Producto> listaProductos = Arrays.asList(clienteRestItem.getForObject("http://servicio-productos/listar",
													  Producto[].class));
		return listaProductos.stream().map(producto -> new Item(producto,1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());		
		Producto producto = clienteRestItem.getForObject("http://servicio-productos/ver/{id}", Producto.class, pathVariables);
		return new Item(producto, cantidad);
	}

	@Override
	public Producto guardar(Producto producto) {
		HttpEntity<Producto> cuerpo = new HttpEntity<Producto>(producto);
		ResponseEntity<Producto> response = clienteRestItem.exchange("http://servicio-productos/crear", HttpMethod.POST, cuerpo, Producto.class);
		return response.getBody();
	}

	@Override
	public Producto actualizar(Producto producto, Long id) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());
		
		HttpEntity<Producto> body = new HttpEntity<Producto>(producto);
		ResponseEntity<Producto> response = clienteRestItem.exchange("http://servicio-productos/editar/{id}", HttpMethod.PUT, body, Producto.class, pathVariables);
		return response.getBody();
	}

	@Override
	public void eliminar(Long idProducto) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", idProducto.toString());
		clienteRestItem.delete("http://servicio-productos/eliminar/{id}", pathVariables);
	}

}
