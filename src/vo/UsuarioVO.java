package vo;

import java.io.Serializable;

import modelo.Usuario;

public class UsuarioVO implements Serializable{
	
	private static final long serialVersionUID = 0;
	
	private Integer idUsuario;
	private String rol;
	private String password;
	
	public UsuarioVO() {}
	
	public UsuarioVO(Integer idUsuario,String rol,String password) {
		this.idUsuario = idUsuario;
		this.password = password;
		this.rol = rol;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
	
	public Usuario toModelo() {
        return new Usuario(this.idUsuario, this.rol, this.password);
    }
}
