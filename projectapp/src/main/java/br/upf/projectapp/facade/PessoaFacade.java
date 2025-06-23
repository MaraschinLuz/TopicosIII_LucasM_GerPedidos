package br.upf.projectapp.facade;

import br.upf.projectapp.entity.PessoaEntity;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class PessoaFacade extends AbstractFacade<PessoaEntity> {

    @PersistenceContext(unitName = "ProjetojfprimefacesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PessoaFacade() {
        super(PessoaEntity.class);
    }

    private List<PessoaEntity> entityList;

    public List<PessoaEntity> buscarTodos() {
        entityList = new ArrayList<>();
        try {
            Query query = getEntityManager().createQuery("SELECT p FROM PessoaEntity p ORDER BY p.nome");
            entityList = query.getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao buscar todos: " + e);
        }
        return entityList;
    }

    public PessoaEntity buscarPorEmail(String email, String senha) {
        PessoaEntity pessoa = null;
        try {
            Query query = getEntityManager()
                    .createQuery("SELECT p FROM PessoaEntity p WHERE p.email = :email AND p.senha = :senha");
            query.setParameter("email", email);
            query.setParameter("senha", senha);

            pessoa = (PessoaEntity) query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Erro ao buscar por email e senha: " + e);
        }
        return pessoa;
    }

    // Método para salvar PessoaEntity
    public void salvar(PessoaEntity pessoa) {
        try {
            getEntityManager().persist(pessoa);
        } catch (Exception e) {
            System.out.println("Erro ao salvar pessoa: " + e);
            throw e;
        }
    }

    // Método para atualizar PessoaEntity
    public void atualizar(PessoaEntity pessoa) {
        try {
            getEntityManager().merge(pessoa);
        } catch (Exception e) {
            System.out.println("Erro ao atualizar pessoa: " + e);
            throw e;
        }
    }

    // Método para excluir PessoaEntity
    public void excluir(PessoaEntity pessoa) {
        if (pessoa != null && pessoa.getId() != 0) {
            PessoaEntity managedPessoa = getEntityManager().find(PessoaEntity.class, pessoa.getId());
            if (managedPessoa != null) {
                try {
                    getEntityManager().remove(managedPessoa);
                } catch (Exception e) {
                    System.out.println("Erro ao excluir pessoa: " + e);
                    throw e;
                }
            } else {
                System.out.println("Pessoa para exclusão não encontrada no banco.");
            }
        } else {
            System.out.println("Pessoa ou ID nulo para exclusão.");
        }
    }
}
