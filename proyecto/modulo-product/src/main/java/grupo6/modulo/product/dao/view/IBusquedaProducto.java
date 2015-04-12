package grupo6.modulo.product.dao.view;

import grupo6.persistencia.entidades.Producto;

import java.util.List;

public interface IBusquedaProducto {

	public List<Producto> buscar(Object... parametros);
}
