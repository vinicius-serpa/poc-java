package controller;

import javax.faces.bean.ManagedBean;

import model.Client;

@ManagedBean(name = "register")
public class RegisterClientBean {

	private Client client = new Client();
	 
	public void cadastrar() {
		System.out.println("Nome: " + this.client.getFirstName());
		System.out.println("Sobrenome: " + this.client.getLastName());
	}
 
	public Client getClient() {
		return client;
	}
	
}
