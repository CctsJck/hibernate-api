package modelo;

import Entity.UsuarioEntity;
import vo.UsuarioVO;

public class Usuario {
	
	private Integer idUsuario;
	private String rol;
	private String contraseña;
	
	
	public Usuario(String rol,String contraseña) {
		this.rol = rol;
		this.contraseña = contraseña;
	}
	
	public Usuario(Integer idUsuario,String rol,String contraseña) {
		this.idUsuario = idUsuario;
		this.rol = rol;
		this.contraseña = contraseña;
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


	public String getContraseña() {
		return contraseña;
	}


	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	
	public UsuarioVO toVO() {
        return new UsuarioVO(this.idUsuario, this.rol, this.contraseña);
    }
	
	public UsuarioEntity toEntity() {
		return new UsuarioEntity(this.idUsuario,this.rol,this.contraseña);
		
	}
	

}
