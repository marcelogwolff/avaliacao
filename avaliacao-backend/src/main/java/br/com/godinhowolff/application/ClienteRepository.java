package br.com.godinhowolff.application;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import br.com.godinhowolff.model.Cliente;


public class ClienteRepository extends AbstractRepository<Cliente> {

	  public ClienteRepository(EntityManagerFactory emf) {
	        super(emf);
	    }

	    public void criarDados(Cliente user) {
	        create(user);
	    }

	    public void atualizarDados(Cliente user) {
	        update(user);
	    }
	    
	    public void removerDados(Long id) {
	    	removeById(Cliente.class, id);
	    }

	    public List<Cliente> retornaTodosOsClientes() {
	        return findAll(Cliente.class);
	    }
	    
	    public Cliente find(Class<Cliente> clazz, Cliente cliente) {
	    	TypedQuery<Cliente> query = getEntityManager().createQuery("SELECT c FROM Cliente c where c.cpf =:cpf", Cliente.class);
	        return query.setParameter("cpf", cliente.getCpf()).getSingleResult();
	    }
}
