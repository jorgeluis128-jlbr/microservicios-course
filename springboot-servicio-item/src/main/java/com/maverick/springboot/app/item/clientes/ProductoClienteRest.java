package com.maverick.springboot.app.item.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.maverick.springboot.commons.models.entity.Producto;

@FeignClient(name="servicio-productos")
public interface ProductoClienteRest {
	
	@GetMapping("/listar")
	public List<Producto> getListaProductos();
	
	@GetMapping("/ver/{id}")
	public Producto verDetalle(@PathVariable Long id);
	
	@PostMapping("/crear")
	public Producto crearProducto(@RequestBody Producto producto);
	
	@PostMapping("/editar/{id}")
	public Producto editarProduct(@RequestBody Producto producto, @PathVariable Long id);
	
	@DeleteMapping("/eliminar/{id}")
	public void eliminar(@PathVariable Long id);
	

}
