package usuario;

public class Usuario {
	protected String loginUsuario;
	protected String senhaUsuario;


public Usuario(String loginUsuario, String senhaUsuario) {
	super();
	this.loginUsuario = loginUsuario;
	this.senhaUsuario = senhaUsuario;
}


public Usuario(String loginUsuario) {
	this.loginUsuario = loginUsuario;
}


public String getLoginUsuario() {
	return loginUsuario;
}


public void setLoginUsuario(String loginUsuario) {
	this.loginUsuario = loginUsuario;
}


public String getSenhaUsuario() {
	return senhaUsuario;
}


public void setSenhaUsuario(String senhaUsuario) {
	this.senhaUsuario = senhaUsuario;
}
}