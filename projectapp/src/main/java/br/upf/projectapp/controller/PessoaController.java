/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package br.upf.projectapp.controller;

import br.upf.projectapp.entity.PessoaEntity;
import jakarta.ejb.EJB;
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
@Named(value = "pessoaController")
@SessionScoped
public class PessoaController implements Serializable {

    /**
     * Creates a new instance of PessoaController
     */
    @EJB
    private br.upf.projectapp.facade.PessoaFacade ejbFacade;
    
    private int gerarId(){
        int id = 1;
        if (!pessoaList.isEmpty()) {
            id = pessoaList.size() + 1;
        }
        return id;
    }
    
    private void exibirMensagem(){
        String msg = "Pessoa adicionado: " + pessoa.getNome();
        FacesMessage fm = new FacesMessage(msg);
        FacesContext.getCurrentInstance().addMessage(msg, fm);
    }
    
    public void adicionarPessoa(){
        pessoa.setId(gerarId());
        pessoaList.add(pessoa);
        exibirMensagem();
        pessoa = new PessoaEntity();
    }
    private PessoaEntity pessoa = new PessoaEntity();
    
    private List<PessoaEntity> pessoaList = new ArrayList<>();

    
    private PessoaEntity selected;
    
    public PessoaEntity getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaEntity pessoa) {
        this.pessoa = pessoa;
    }

    public List<PessoaEntity> getPessoaList() {
        return ejbFacade.buscarTodos();
    }

    public void setPessoaList(List<PessoaEntity> pessoaList) {
        this.pessoaList = pessoaList;
    }

    public PessoaEntity getSelected() {
        return selected;
    }

    public void setSelected(PessoaEntity selected) {
        this.selected = selected;
    }
    
    public void editarPessoa(){
        int index = pessoaList.indexOf(selected);
        pessoaList.set(index, selected);
        selected = null;
        FacesMessage fm = new FacesMessage(
        FacesMessage.SEVERITY_INFO,
                "Sucesso!",
                "Registro alterado com sucesso.");
        FacesContext.getCurrentInstance().addMessage(null, fm);
                
    }
    
    public void deletarPessoa(){
        int index = pessoaList.indexOf(selected);
        pessoaList.remove(index);
        selected = null;
        FacesMessage fm = new FacesMessage(
                       FacesMessage.SEVERITY_INFO,
                       "Sucesso!",
                       "Registro excluido com sucesso");
        FacesContext.getCurrentInstance().addMessage(null, fm);
    }
    
}
