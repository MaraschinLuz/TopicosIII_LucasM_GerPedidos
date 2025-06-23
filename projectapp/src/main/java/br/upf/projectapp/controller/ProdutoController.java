package br.upf.projectapp.controller;

import br.upf.projectapp.entity.ProdutoEntity;
import br.upf.projectapp.facade.ProdutoFacade;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;

@Named(value = "produtoController")
@SessionScoped
public class ProdutoController implements Serializable {

    @EJB
    private ProdutoFacade produtoFacade;

    private ProdutoEntity produto = new ProdutoEntity();
    private ProdutoEntity selected;
    private List<ProdutoEntity> produtoList;

    public ProdutoEntity getProduto() {
        return produto;
    }

    public void setProduto(ProdutoEntity produto) {
        this.produto = produto;
    }

    public ProdutoEntity getSelected() {
        return selected;
    }

    public void setSelected(ProdutoEntity selected) {
        this.selected = selected;
    }

    public List<ProdutoEntity> getProdutoList() {
        if (produtoList == null) {
            produtoList = produtoFacade.buscarTodos();
        }
        return produtoList;
    }

    public void adicionarProduto() {
        try {
            produtoFacade.create(produto);
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Produto adicionado com sucesso!", null));
            produtoList = null; // força recarregar lista
            produto = new ProdutoEntity();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao adicionar produto", e.getMessage()));
        }
    }

    public void editarProduto() {
        try {
            produtoFacade.edit(selected);
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Produto atualizado com sucesso!", null));
            produtoList = null; // força recarregar lista
            selected = null;
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao atualizar produto", e.getMessage()));
        }
    }

    public void deletarProduto() {
        try {
            produtoFacade.excluir(selected);
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Produto excluído com sucesso!", null));
            produtoList = null; // força recarregar lista
            selected = null;
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao excluir produto", e.getMessage()));
        }
    }
}
