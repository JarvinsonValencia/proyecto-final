package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.dto.ProductoValido;
import co.edu.uniquindio.proyecto.dto.ProductosPorUsuario;
import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.hibernate.sql.Select;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
// Repositorio de la clase Producto
public interface ProductoRepo extends JpaRepository<Producto, Integer> {

    List<Producto> findAllByNombreContains(String nombre);

    Page<Producto> findAll(Pageable paginador);

    @Query("select p.vendedor.nombre from Producto p where p.codigo = :codigo")
    String obtenerNombreVendedor(Integer codigo);

    @Query("select p, c from Producto  p left join p.comentarioList c")
    List<Object[]> listarProductosYComentarios();

    @Query("select distinct c.usuario from Producto  p join p.comentarioList c where p.codigo = :id")
    List<Usuario> listarUsuariosComentarios(Integer id);

    @Query("select new co.edu.uniquindio.proyecto.dto.ProductoValido (p.nombre, p.descripcion, p.precio, p.ciudad)  from Producto p where :fechaActual < p.fechaLimite ")
    List<ProductoValido> listarProductosValidos (LocalDateTime fechaActual);

    @Query("select c.nombre, count(p) from Producto p join p.categoria c group by c")
    List<Object[]> obtenerTotalProductosPorCategoria();

    @Query("select p from Producto p where p.comentarioList is empty ")
    List<Producto> obtenerProductosSinComentarios();

    List<Producto> findByNombreContains(String nombre);

    @Query("select p from Producto p join p.categoria c where p.nombre like concat('%', :nombre, '%') or c.nombre like concat('%', :nombre, '%') ")
    List<Producto> buscarProductoNombre(String nombre);

    @Query("select p from Producto p join p.categoria c where c.nombre like concat('%', :nombre, '%') ")
    List<Producto> buscarProductoCategoria(String nombre);

    @Query("select p from Producto p where p.precio between :precio1 and :precio2")
    List<Producto> buscarProductosPrecio(double precio1, double precio2);

    @Query("select p from Producto p where p.descuento = :descuento")
    List<Producto> productoPorDescuento(double descuento);

    @Query("select p from Producto p join p.ciudad c where c.nombre like :nombre")
    List<Producto> productosPorCiudad(String nombre);

    @Query("select new co.edu.uniquindio.proyecto.dto.ProductosPorUsuario (p.vendedor.codigo, p.vendedor.email, count(p)) from Producto p group by p.vendedor")
    List<ProductosPorUsuario> obtenerProductoEnVenta();

    @Query("select avg(c.calificacion) from Producto p join p.comentarioList c where p.codigo = :codigo ")
    Float obtnerPromedioCalificaciones(Integer codigo);

    @Query("select p from Producto p where :categoria member of p.categoria")
    List<Producto> listarPorCategoria(Categoria categoria);

    @Query("select u.productoList from Producto p join p.usuarios u where u.codigo = :id")
    List<Producto> listarProductosUsuario(Integer id);

    @Query("select u.productosFavoritos from Producto p join p.usuarios u where u.codigo = :codigo")
    List<Producto> listarProductosFavoritos(Integer codigo);

    @Query("delete from Usuario u where u.productosFavoritos in (select p from Producto p where p.codigo = :id and u.codigo = :codigo)")
    void eliminarFavoritos(Integer id, Integer codigo);

    @Query("Select c.nombre from Producto p join p.categoria c where p.codigo = :codigo" )
    String obtenerCatgoria(Integer codigo);
}
