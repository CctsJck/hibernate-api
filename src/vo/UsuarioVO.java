package vo;

import java.io.Serializable;

import modelo.Usuario;

public class UsuarioVO implements Serializable{
	
	private static final long serialVersionUID = 0;
	
	private Integer idUsuario;
	private String rol;
	private String contraseña;
	
	public UsuarioVO() {}
	
	public UsuarioVO(Integer idUsuario,String rol,String contraseña) {
		this.idUsuario = idUsuario;
		this.contraseña = contraseña;
		this.rol = rol;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
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
        return new Usuario(this.idUsuario, this.rol, this.contraseña);
    }
}
