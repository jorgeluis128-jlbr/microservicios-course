package com.maverick.springboot.app.productos.models.entity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maverick.springboot.app.productos.models.entity.dao.ProductoDAO;
import com.maverick.springboot.commons.models.entity.Producto;


@Service
public class ProductosServiceImpl implements ProductosService{

	@Autowired
	private ProductoDAO productoDAO;
	
	@Override
	@Transactional(readOnly=true)
	public List<Producto> buscarTodo() {
		return (List<Producto>) productoDAO.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Producto findById(Long id) {
		return productoDAO.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public Producto guardar(Producto producto) {
		return productoDAO.save(producto);
	}

	@Transactional
	@Override
	public void BorrarById(Long idProducto) {
		productoDAO.deleteById(idProducto);
	}

	@Override
	public Producto editarObjeto(Producto producto, Long id) {
		Producto respuesta = productoDAO.findById(id).orElse(null);
		if(respuesta!=null) {
			respuesta.setNombre(producto.getNombre());
			respuesta.setPrecio(producto.getPrecio());
			productoDAO.save(respuesta);
		}
		return respuesta;
	}
	

}
