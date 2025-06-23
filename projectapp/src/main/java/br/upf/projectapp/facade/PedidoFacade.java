package br.upf.projectapp.facade;

import br.upf.projectapp.entity.PedidoEntity;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class PedidoFacade extends AbstractFacade<PedidoEntity> {

    @PersistenceContext(unitName = "ProjetojfprimefacesPU")
    private EntityManager em;

    public PedidoFacade() {
        super(PedidoEntity.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
