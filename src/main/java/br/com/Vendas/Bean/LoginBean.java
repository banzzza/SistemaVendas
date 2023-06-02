package br.com.Vendas.Bean;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.com.Vendas.util.JSFUtil;

@ManagedBean(name = "MBLogin")
@ViewScoped
public class LoginBean {
	
	private String usuario;
	private String senha;
	
	public void loginUsuario() {
		if (usuario.equals("admin") && senha.equals("12345")) {
		try {
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            externalContext.redirect("vendaCadastro.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    } else {
    	JSFUtil.adicionarMensagemErro("Usuário ou senha incorretos!");
	}
	}
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
}
