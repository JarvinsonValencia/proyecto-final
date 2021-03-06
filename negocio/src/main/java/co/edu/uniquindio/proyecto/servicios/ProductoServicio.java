package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.*;

import java.util.ArrayList;
import java.util.List;

public interface ProductoServicio {
    Producto publicarProducto(Producto producto) throws Exception;

    Producto actualizarProducto(Producto p) throws Exception;

    void eliminarProducto(Integer codigo) throws Exception;

    Producto obtenerProducto(Integer codigo) throws Exception;

    String obtenerCategoria(Integer codigo) throws Exception;

    List<Producto> listarProductos(Categoria categoria);

    List<Producto> listarTodosProductos();

    void comentarProducto(Comentario comentario) throws Exception;

    void guardarProductoEnFavoritos(Producto producto, Usuario usuario) throws Exception;

    List<Producto> listarProductosFavoritos(Integer codigo);

    void eliminarProductoEnFavoritos(Producto producto, Usuario usuario) throws Exception;

    List<Producto> buscarProductos(String nombreProducto, String[] filtros) ;

    List<Producto> buscarProductoCategoria(String nombreProducto) ;

    List<Producto> productosPorDescuento(double precio);

    List<Producto> buscarProductosPrecio(double precio1, double precio2);

    List<Producto> listarProductosUsuario(Integer codigo) throws Exception;

    List<Producto> productosPorCiudad(String nombre);

    Compra realizarCompra(Usuario usuario, ArrayList<ProductoCarrito> productos, String medioPago) throws Exception;

}
