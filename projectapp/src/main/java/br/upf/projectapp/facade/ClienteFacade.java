package br.upf.projectapp.facade;

import br.upf.projectapp.entity.ClienteEntity;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.List;

@Stateless
public class ClienteFacade extends AbstractFacade<ClienteEntity> {

    @PersistenceContext(unitName = "ProjetojfprimefacesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClienteFacade() {
        super(ClienteEntity.class);
    }

    private List<ClienteEntity> entityList;

    /**
     * Buscar todos os clientes ordenados por nome
     */
    public List<ClienteEntity> buscarTodos() {
        entityList = new ArrayList<>();
        try {
            Query query = getEntityManager().createQuery("SELECT c FROM ClienteEntity c ORDER BY c.nome");
            entityList = query.getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao buscar todos os clientes: " + e.getMessage());
        }
        return entityList;
    }

    /**
     * Buscar cliente por telefone
     */
    public ClienteEntity buscarPorTelefone(String telefone) {
        ClienteEntity cliente = null;
        try {
            Query query = getEntityManager().createQuery("SELECT c FROM ClienteEntity c WHERE c.telefone = :telefone");
            query.setParameter("telefone", telefone);

            cliente = (ClienteEntity) query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Erro ao buscar cliente por telefone: " + e.getMessage());
        }
        return cliente;
    }

    /**
     * Excluir cliente
     */
    public void excluir(ClienteEntity cliente) {
        if (cliente != null) {
            ClienteEntity managedCliente = getEntityManager().find(ClienteEntity.class, cliente.getId());
            if (managedCliente != null) {
                getEntityManager().remove(managedCliente);
            } else {
                System.out.println("Cliente para exclusão não encontrado.");
            }
        } else {
            System.out.println("Cliente ou ID nulo para exclusão.");
        }
    }
}
