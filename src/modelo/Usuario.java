package modelo;

import Entity.UsuarioEntity;
import vo.UsuarioVO;

public class Usuario {
	
	private Integer idUsuario;
	private String rol;
	private String contrase�a;
	
	
	public Usuario(String rol,String contrase�a) {
		this.rol = rol;
		this.contrase�a = contrase�a;
	}
	
	public Usuario(Integer idUsuario,String rol,String contrase�a) {
		this.idUsuario = idUsuario;
		this.rol = rol;
		this.contrase�a = contrase�a;
	}
	
	public Usuario () {}


	public Integer getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}


	public String getRol() {
		return rol;
	}


	public void setRol(String rol) {
		this.rol = rol;
	}


	public String getContrase�a() {
		return contrase�a;
	}


	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}
	
	public UsuarioVO toVO() {
        return new UsuarioVO(this.idUsuario, this.rol, this.contrase�a);
    }
	
	public UsuarioEntity toEntity() {
		return new UsuarioEntity(this.idUsuario,this.rol,this.contrase�a);
		
	}
	

}
