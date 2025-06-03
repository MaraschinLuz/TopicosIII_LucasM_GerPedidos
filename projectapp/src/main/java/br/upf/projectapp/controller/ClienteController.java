/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package br.upf.projectapp.controller;

import br.upf.projectapp.entity.ClienteEntity;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 193897
 */
@Named(value = "clienteController")
@SessionScoped
public class ClienteController implements Serializable {

    /**
     * Creates a new instance of ClienteController
     */
    public ClienteController() {
    }
    
    private int gerarId(){
        int id = 1;
        if (!clienteList.isEmpty()) {
            id = clienteList.size() + 1;
        }
        return id;
    }
    
    private void exibirMensagem(){
        String msg = "Cliente adicionado: " + cliente.getNome();
        FacesMessage fm = new FacesMessage(msg);
        FacesContext.getCurrentInstance().addMessage(msg, fm);
    }
    
    public void adicionarCliente(){
        cliente.setId(gerarId());
        clienteList.add(cliente);
        exibirMensagem();
        cliente = new ClienteEntity();
    }
    private ClienteEntity cliente = new ClienteEntity();
    
    private List<ClienteEntity> clienteList = new ArrayList<>();

    
    private ClienteEntity selected;
    
    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    public List<ClienteEntity> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<ClienteEntity> clienteList) {
        this.clienteList = clienteList;
    }

    public ClienteEntity getSelected() {
        return selected;
    }

    public void setSelected(ClienteEntity selected) {
        this.selected = selected;
    }
    
    public void editarCliente(){
        int index = clienteList.indexOf(selected);
        clienteList.set(index, selected);
        selected = null;
        FacesMessage fm = new FacesMessage(
        FacesMessage.SEVERITY_INFO,
                "Sucesso!",
                "Registro alterado com sucesso.");
        FacesContext.getCurrentInstance().addMessage(null, fm);
                
    }
    
    public void deletarCliente(){
        int index = clienteList.indexOf(selected);
        clienteList.remove(index);
        selected = null;
        FacesMessage fm = new FacesMessage(
                       FacesMessage.SEVERITY_INFO,
                       "Sucesso!",
                       "Registro excluido com sucesso");
        FacesContext.getCurrentInstance().addMessage(null, fm);
    }
    
}
