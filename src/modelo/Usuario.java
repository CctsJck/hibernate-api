package modelo;

import Entity.UsuarioEntity;
import vo.UsuarioVO;

public class Usuario {
	
	private Integer idUsuario;
	private String rol;
	private String password;
	
	
	public Usuario(String rol,String password) {
		this.rol = rol;
		this.password = password;
	}
	
	public Usuario(Integer idUsuario,String rol,String password) {
		this.idUsuario = idUsuario;
		this.rol = rol;
		this.password = password;
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


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	public UsuarioVO toVO() {
        return new UsuarioVO(this.idUsuario, this.rol, this.password);
    }
	
	public UsuarioEntity toEntity() {
		return new UsuarioEntity(this.idUsuario,this.rol,this.password);
		
	}
	

}
