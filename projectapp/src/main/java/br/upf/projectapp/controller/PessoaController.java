package br.upf.projectapp.controller;

import br.upf.projectapp.entity.PessoaEntity;
import br.upf.projectapp.facade.PessoaFacade;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named(value = "pessoaController")
@SessionScoped
public class PessoaController implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private PessoaFacade ejbFacade;

    private PessoaEntity pessoa = new PessoaEntity();
    private PessoaEntity selected;

    private List<PessoaEntity> pessoaList;

    public PessoaEntity getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaEntity pessoa) {
        this.pessoa = pessoa;
    }

    public PessoaEntity getSelected() {
        return selected;
    }

    public void setSelected(PessoaEntity selected) {
        this.selected = selected;
    }

    public List<PessoaEntity> getPessoaList() {
        if (pessoaList == null) {
            pessoaList = ejbFacade.buscarTodos();
        }
        return pessoaList;
    }

    public void setPessoaList(List<PessoaEntity> pessoaList) {
        this.pessoaList = pessoaList;
    }

    public void adicionarPessoa() {
        try {
            ejbFacade.salvar(pessoa);
            pessoaList = null; // força recarregar a lista no próximo get
            pessoa = new PessoaEntity();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Pessoa adicionada com sucesso."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao adicionar pessoa", e.getMessage()));
        }
    }

    public void editarPessoa() {
        if (selected == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_WARN, "Seleção inválida", "Selecione uma pessoa para editar."));
            return;
        }
        try {
            ejbFacade.atualizar(selected);
            pessoaList = null; // força recarregar
            selected = null;
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Pessoa atualizada com sucesso."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao atualizar pessoa", e.getMessage()));
        }
    }

    public void deletarPessoa() {
        if (selected == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_WARN, "Seleção inválida", "Selecione uma pessoa para excluir."));
            return;
        }
        try {
            ejbFacade.excluir(selected);
            pessoaList = null; // força recarregar
            selected = null;
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Pessoa excluída com sucesso."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao excluir pessoa", e.getMessage()));
        }
    }
}
