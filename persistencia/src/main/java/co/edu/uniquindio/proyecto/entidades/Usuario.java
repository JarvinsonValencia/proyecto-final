package co.edu.uniquindio.proyecto.entidades;

import com.fasterxml.jackson.annotation.JacksonAnnotation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Usuario extends Persona implements Serializable {

    @NotBlank(message = "Debe ingresar un username")
    @Column(nullable = false, unique = true)
    private String username;

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    @JsonIgnore
    private List<Comentario> comentarioList;

    @OneToMany(mappedBy = "vendedor")
    @ToString.Exclude
    @JsonIgnore
    private List<Producto>productoList;

    @ManyToMany
    @ToString.Exclude
    @JsonIgnore
    private List<Producto>productosFavoritos;

    @ManyToOne
    @JoinColumn(name = "codigoCiudad")
    private Ciudad ciudad;

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    @JsonIgnore
    private List<Compra> compra;

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    @JsonIgnore
    private List<SubastaUsuario> subastaUsuario;

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    @JsonIgnore
    private List<Chat> chats;

    @ElementCollection //Etiqueta que permite la normalizacion del atributo telefono
    @JsonIgnore
    private List<String> numTelefonos;

    // Este es el constructor de la clase Usuario
    public Usuario(Integer codigo, String nombre, String email, String password, String username, Ciudad ciudad) {
        super(codigo, nombre, email, password);
        this.username = username;
        this.ciudad = ciudad;
    }
}
