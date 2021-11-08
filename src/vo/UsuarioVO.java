package vo;

import java.io.Serializable;

import modelo.Usuario;

public class UsuarioVO implements Serializable{
	
	private static final long serialVersionUID = 0;
	
	private Integer idUsuario;
	private String rol;
	private String contrase�a;
	
	public UsuarioVO() {}
	
	public UsuarioVO(Integer idUsuario,String rol,String contrase�a) {
		this.idUsuario = idUsuario;
		this.contrase�a = contrase�a;
		this.rol = rol;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
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
        return new Usuario(this.idUsuario, this.rol, this.contrase�a);
    }
}
