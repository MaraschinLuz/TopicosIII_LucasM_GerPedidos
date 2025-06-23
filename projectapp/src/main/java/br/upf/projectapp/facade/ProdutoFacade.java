package br.upf.projectapp.facade;

import br.upf.projectapp.entity.ProdutoEntity;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;

@Stateless
public class ProdutoFacade extends AbstractFacade<ProdutoEntity> {

    @PersistenceContext(unitName = "ProjetojfprimefacesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProdutoFacade() {
        super(ProdutoEntity.class);
    }

    public List<ProdutoEntity> buscarTodos() {
        Query query = getEntityManager().createQuery("SELECT p FROM ProdutoEntity p ORDER BY p.nome");
        return query.getResultList();
    }

    public void excluir(ProdutoEntity produto) {
        ProdutoEntity managed = getEntityManager().find(ProdutoEntity.class, produto.getId());
        if (managed != null) {
            getEntityManager().remove(managed);
        }
    }
}
