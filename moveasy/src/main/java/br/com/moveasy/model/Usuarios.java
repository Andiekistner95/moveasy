package br.com.moveasy.model;

public class Usuarios {

	int cod_usuario;
	String login;
	String senha;
	int status;

	public Usuarios(int cod_usuario, String login, String senha, int status) {
		setCod_usuario(cod_usuario);
		setLogin(login);
		setSenha(senha);
		setStatus(status);

	}
	
	public Usuarios(String login, String senha) {
		setCod_usuario(cod_usuario);
		setLogin(login);
		setSenha(senha);
	
	}

	public int getCod_usuario() {
		return cod_usuario;
	}

	public void setCod_usuario(int cod_usuario) {
		this.cod_usuario = cod_usuario;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
