/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package br.upf.projectapp.controller;

import br.upf.projectapp.entity.ClienteEntity;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
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
    
    
    private ClienteEntity cliente = new ClienteEntity();
    
    private List<ClienteEntity> clienteList = new ArrayList<>();

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
    
}
