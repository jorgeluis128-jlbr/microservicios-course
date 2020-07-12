package com.maverick.springboot.app.productos.models.entity.dao;

import org.springframework.data.repository.CrudRepository;
import com.maverick.springboot.commons.models.entity.Producto;

public interface ProductoDAO extends CrudRepository<Producto, Long>{
 
}
