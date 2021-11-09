package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;

import java.util.List;

public interface ProductoServicio {
    Producto publicarProducto(Producto producto) throws Exception;

    void actualizarProducto(Producto p) throws Exception;

    void eliminarProducto(Integer codigo) throws Exception;

    Producto obtenerProducto(Integer codigo) throws Exception;

    List<Producto> listarProductos(Categoria categoria);

    void comentarProducto(String mensaje, Integer calificacion, Usuario usuario, Producto producto) throws Exception;

    void guardarProductoEnFavoritos(Producto producto, Usuario usuario) throws Exception;

    void eliminarProductoEnFavoritos(Producto producto, Usuario usuario) throws Exception;

    void comprarProducto(Compra compra) throws Exception;

    List<Producto> buscarProductos(String nombreProducto, String[] filtros);

    List<Producto> listarProductosUsuario(Integer codigo) throws Exception;
}