package com.maverick.springboot.app.item.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.maverick.springboot.app.item.models.Item;
import com.maverick.springboot.commons.models.entity.Producto;
import com.maverick.springboot.app.item.models.service.ItemService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RefreshScope
@RestController
public class ItemController {
	
	@Value("${configuracion.texto}")
	private String texto;
	
	@Autowired
	private Environment environment;
	
	@Autowired
	@Qualifier("serviceFeign") // Utilizando feing para consumir el servicio
	//@Qualifier("serviceRestClient") /// Usando RestCliente
	private ItemService itemService;
	
	
	@GetMapping("/listar")
	public List<Item> getListaItems(){
		return itemService.buscarTodo();
	}
	
	@HystrixCommand(fallbackMethod="manejoErroBuscarPorId")
	@GetMapping("/ver/{id}/cantidad/{cantidad}")
	public Item buscarPorId(@PathVariable Long id, @PathVariable Integer cantidad) {
		return itemService.findById(id, cantidad);
	}
	
	public Item manejoErroBuscarPorId(Long id, Integer cantidad) {
		Item item = new Item();
		Producto producto = new Producto();
		producto.setId(id);
		producto.setNombre("desde manejor error");
		producto.setPrecio(00.00);
		item.setProducto(producto);
		item.setCantidad(cantidad);
		return item;
	}
	
	
	@GetMapping("/obtener-config")
	public ResponseEntity<?> configuracion(@Value("${server.port}") String puerto){
		Map<String, String> json = new HashMap<>();
		json.put("texto", texto);
		json.put("puerto", puerto);
		
		if(environment.getActiveProfiles().length>0) {
			if(environment.getActiveProfiles()[0].equals("dev")) {
				json.put("autor.nombre", environment.getProperty("configuracion.autor.nombre"));
				json.put("autor.email", environment.getProperty("configuracion.autor.email"));
			}	
		}
		return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
	};
	
	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto crear(@RequestBody Producto producto) {
		return itemService.guardar(producto);
	}
	
	@PutMapping("/editar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto editarProducto(@RequestBody Producto producto, @PathVariable Long id) {
		return itemService.actualizar(producto, id);
	}
	
	@DeleteMapping("/eliminar/{idProducto}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminarProducto(@PathVariable Long idProducto) {
		itemService.eliminar(idProducto);
	}
	
	

}
