package br.upf.projectapp.controller;

import br.upf.projectapp.entity.ClienteEntity;
import br.upf.projectapp.facade.ClienteFacade;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named(value = "clienteController")
@SessionScoped
public class ClienteController implements Serializable {

    @EJB
    private ClienteFacade clienteFacade;

    private ClienteEntity cliente = new ClienteEntity();
    private ClienteEntity selected;

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    public ClienteEntity getSelected() {
        return selected;
    }

    public void setSelected(ClienteEntity selected) {
        this.selected = selected;
    }

    public List<ClienteEntity> getClienteList() {
        return clienteFacade.buscarTodos();
    }

    public void adicionarCliente() {
        try {
            clienteFacade.create(cliente);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Sucesso!", "Cliente cadastrado com sucesso.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            cliente = new ClienteEntity();
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro!", "Erro ao cadastrar cliente.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void editarCliente() {
        try {
            clienteFacade.edit(selected);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Sucesso!", "Cliente editado com sucesso.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            selected = null;
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro!", "Erro ao editar cliente.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void deletarCliente() {
        try {
            clienteFacade.excluir(selected);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Sucesso!", "Cliente excluído com sucesso.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            selected = null;
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro!", "Erro ao excluir cliente.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
}
