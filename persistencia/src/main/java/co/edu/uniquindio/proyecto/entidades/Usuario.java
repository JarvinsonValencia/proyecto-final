package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Usuario extends Persona implements Serializable {


    @OneToMany(mappedBy = "usuario")
    private List<Comentario> comentarioList;

    @OneToMany(mappedBy = "vendedor")
    private List<Producto>productoList;

    @ManyToMany(mappedBy = "usuario")
    private List<Producto>producto;

    @ManyToOne
    @JoinColumn(name = "codigoCiudad", nullable = false)
    private Ciudad ciudad;

    @OneToMany(mappedBy = "usuario")
    private List<Compra> compra;

    @OneToMany(mappedBy = "usuario")
    private List<SubastaUsuario> subastaUsuario;

    @OneToMany(mappedBy = "usuario")
    private List<Chat> chats;


    @ElementCollection
    @Column(nullable = false)
    private Map<String, String> numTelefonos;




}
