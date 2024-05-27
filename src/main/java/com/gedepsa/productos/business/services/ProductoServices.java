package com.gedepsa.productos.business.services;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.gedepsa.productos.business.services.model.Categoria;
import com.gedepsa.productos.business.services.model.Producto;

public interface ProductoServices {

	/**
	 * Devuelve el codigo que le ha otorgado el sistema.
	 * 
	 * Si el codigo del producto NO es null lanza IllegalStateException
	 * 
	 */
	Long create(Producto producto);
	
	Optional<Producto> read(Long codigo);
	
	/**
	 * 
	 * Si el codigo NO es null o el producto no existe lanza IllegalStateException
	 * 
	 */
	void update(Producto producto);
	
	/**
	 * 
	 * Si el codigo es null o el producto no existe lanza IllegalStateException
	 * 
	 */
	void delete(Long codigo);
	
	List<Producto> getAll();

	/**
	 * 
	 * Incluye los extremos
	 * 
	 */
	List<Producto> getBetweenPriceRange(double min, double max);
	
	List<Producto> getBetweenDates(Date desde, Date hasta);
	List<Producto> getDescatalogados();
	List<Producto> getByCategoria(Categoria categoria);
	
	int getNumeroTotalProductos();
	
	/**
	 * Incrementa un porcentaje el precio de los productos que se pasan.
	 * 
	 * Por ejemplo:
	 * 
	 * si porcentaje = 2.0 ---> Los incrementa un 2.0%
	 * 
	 */
	void variarPrecio(List<Producto> productos, double porcentaje);
	
	/**
	 * Incrementa un porcentaje el precio de los productos que se pasan (por codigo)
	 * 
	 * Por ejemplo:
	 * 
	 * si porcentaje = 2.0 ---> Los incrementa un 2.0%
	 * 
	 */
	void variarPrecio(long[] codigos, double porcentaje);
	
	/**
	 * Nos devuelve un mapa con la informacion del numero de productos que hay para cada categoria
	 * 
	 * Categoria   Numero de Productos
	 * =========   ===================
	 * TAPA                         12
	 * REFRESCOS                     7
	 * LICORES                       5
	 * ...
	 */
	Map<Categoria, Integer> getEstadisticaNumeroProductoPorCategoria();
	
	/**
	 * Nos devuelve un mapa con la informacion del precio medio de los productos de cada categoria
	 * 
	 * Categoria   Precio medio
	 * =========   ============
	 * TAPA                12.9
	 * REFRESCOS            2.8
	 * LICORES             20.5
	 * ...
	 */
	Map<Categoria, Double> getEstadisticaPrecioMedioProductosPorCategoria();

/*
 * 
	List<ProductoDTO1> getAllDTO1();
	List<ProductoDTO2> getAllDTO2();
	
	Pagina<Producto> getPagina(String nombre, int pageNumber, int pageSize);

*/
}
