package com.maverick.springboot.app.productos.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.maverick.springboot.app.productos.models.entity.service.ProductosService;
import com.maverick.springboot.commons.models.entity.Producto;

@RestController
public class ProductoController {
	
	@Autowired 
	private Environment env;
	
	@Value("${server.port}")
	private Integer puerto;
	
	@Autowired
	private ProductosService productoService;
	
	@GetMapping("/listar")
	public List<Producto> ListaPersonas(){
		// Integer puerto = Integer.parseInt(env.getProperty("local.server.port"));
		return productoService.buscarTodo().stream().map(producto ->{
			producto.setPort(puerto);
			return producto;
		}).collect(Collectors.toList());
	}
	
	@GetMapping("/ver/{id}")
	public Producto verDetalle(@PathVariable Long id){
		Producto producto = productoService.findById(id);
		producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		/*try {
			Thread.sleep(2000l);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return producto;
	}
	
	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto crearProducto(@RequestBody Producto producto) {
		return productoService.guardar(producto);
	}
	
	@PutMapping("/editar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto editarProduct(@RequestBody Producto producto, @PathVariable Long id) {
		return productoService.editarObjeto(producto, id);
	};
	
	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		productoService.BorrarById(id);
	}
	
	
	

}
