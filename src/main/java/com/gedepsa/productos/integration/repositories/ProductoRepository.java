package com.gedepsa.productos.integration.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.gedepsa.productos.business.services.model.Categoria;
import com.gedepsa.productos.business.services.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>{
	
	List<Producto> findByPrecioBetween(double min, double max);
	
	List<Producto> findByFechaAltaBetweenOrderByFechaAltaDesc(Date desde, Date hasta);
	
	List<Producto> findByDescatalogadoTrue();
	
	List<Producto> findByCategoria(Categoria categoria);
	
	@Query("UPDATE Producto p SET p.precio = p.precio + (p.precio * :porcentaje / 100) WHERE p IN :productos")
	@Modifying
	void variarPrecio(List<Producto> productos, double porcentaje);
	
	@Query("UPDATE Producto p SET p.precio = p.precio + (p.precio * :porcentaje / 100) WHERE p.codigo IN :codigos")
	@Modifying
	void variarPrecio(long[] codigos, double porcentaje);
	
	/*
	 
	  SELECT C.ID,
           C.NAME,
           COUNT(P.CODIGO) 
      FROM CATEGORIAS C LEFT JOIN PRODUCTOS P ON P.ID_CATEGORIA = C.ID
  GROUP BY P.ID_CATEGORIA, C.NAME
  ORDER BY C.ID
	
	 */
	
	@Query("SELECT c.nombre, COUNT(p) FROM Categoria c LEFT JOIN Producto p ON c = p.categoria GROUP BY c")
	List<Object[]> getEstadisticaNumeroProductoPorCategoria();
}
