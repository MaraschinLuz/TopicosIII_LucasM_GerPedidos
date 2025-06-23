package br.upf.projectapp.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import jakarta.validation.constraints.Email;

@Entity
@Table(name = "pessoa")
public class PessoaEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Basic(optional = false)
    @NotNull(message = "Nome é obrigatório")
    @Size(min = 1, max = 500, message = "Nome deve ter entre 1 e 500 caracteres")
    @Column(name = "nome")
    private String nome;

    @Basic(optional = false)
    @NotNull(message = "Email é obrigatório")
    @Size(min = 1, max = 250, message = "Email deve ter entre 1 e 250 caracteres")
    @Email(message = "Email inválido")
    @Column(name = "email")
    private String email;

    @Basic(optional = false)
    @NotNull(message = "Senha é obrigatória")
    @Column(name = "senha")
    private String senha;

    // Getters e Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    // hashCode e equals baseados no id

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof PessoaEntity)) return false;
        PessoaEntity other = (PessoaEntity) obj;
        if (id == null || other.id == null) return false;
        return id.equals(other.id);
    }
}
