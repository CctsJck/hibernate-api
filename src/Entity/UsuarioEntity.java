package Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import modelo.Usuario;

@Entity
@Table(name="usuarios")
public class UsuarioEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idUsuario;
	private String rol;
	private String contraseña;
	
	public UsuarioEntity(Integer idUsuario,String rol,String contraseña) {
		this.idUsuario = idUsuario;
		this.contraseña = contraseña;
		this.rol = rol;
	}
	public UsuarioEntity() {}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	
	public Usuario toModelo() {
		return new Usuario(this.idUsuario,this.rol,this.contraseña);
	}
	
	
}
