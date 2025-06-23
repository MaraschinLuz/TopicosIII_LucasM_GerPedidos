package br.upf.projectapp.controller;

import br.upf.projectapp.entity.PedidoEntity;
import br.upf.projectapp.entity.ClienteEntity;
import br.upf.projectapp.facade.PedidoFacade;
import br.upf.projectapp.facade.ClienteFacade;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Named("pedidoController")
@SessionScoped
public class PedidoController implements Serializable {

    @EJB
    private PedidoFacade pedidoFacade;

    @EJB
    private ClienteFacade clienteFacade;

    private PedidoEntity pedido = new PedidoEntity();
    private PedidoEntity selected;

    public void prepararNovoPedido() {
        pedido = new PedidoEntity();
        pedido.setDataPedido(new Date());
    }

    public void salvar() {
        try {
            pedido.setDataPedido(new Date());
            pedidoFacade.create(pedido);
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Pedido cadastrado com sucesso."));
            pedido = new PedidoEntity();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao salvar o pedido."));
        }
    }

    public void prepararEdicao() {
        if (selected != null) {
            this.pedido = selected;
        }
    }

    public void editar() {
        try {
            pedidoFacade.edit(selected);
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualizado", "Pedido atualizado com sucesso."));
            selected = null;
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao atualizar pedido."));
        }
    }

    public void deletar() {
        try {
            pedidoFacade.remove(selected);
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Removido", "Pedido excluído com sucesso."));
            selected = null;
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao excluir pedido."));
        }
    }

    public List<PedidoEntity> getLista() {
        return pedidoFacade.findAll();
    }

    public List<ClienteEntity> getClientes() {
        return clienteFacade.buscarTodos();
    }

    public PedidoEntity getPedido() { return pedido; }
    public void setPedido(PedidoEntity pedido) { this.pedido = pedido; }

    public PedidoEntity getSelected() { return selected; }
    public void setSelected(PedidoEntity selected) { this.selected = selected; }
}
