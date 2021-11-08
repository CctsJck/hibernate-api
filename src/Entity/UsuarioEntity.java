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
	private String contrase�a;
	
	public UsuarioEntity(Integer idUsuario,String rol,String contrase�a) {
		this.idUsuario = idUsuario;
		this.contrase�a = contrase�a;
		this.rol = rol;
	}
	public UsuarioEntity() {}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getContrase�a() {
		return contrase�a;
	}
	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	
	public Usuario toModelo() {
		return new Usuario(this.idUsuario,this.rol,this.contrase�a);
	}
	
	
}
