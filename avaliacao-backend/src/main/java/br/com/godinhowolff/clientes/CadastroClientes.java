package br.com.godinhowolff.clientes;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.godinhowolff.application.ClienteRepository;
import br.com.godinhowolff.model.Cliente;

/***
 * Classe responsável pelos requests do REST
 * 
 * @author marce
 *
 */

@Path("/clientes")
public class CadastroClientes {

	public CadastroClientes(ClienteRepository userRepository) {
		this.clienteRepository = userRepository;
	}

	private ClienteRepository clienteRepository;

	/***
	 * Método que retorna o cadastro de um cliente do banco de dados
	 * 
	 * @return
	 */
	@GET
	@Consumes("application/json")
	@Produces("application/json")
	@Path("cliente/{id}")
	public Response getCliente(@PathParam(value = "id") Long id) {

		Cliente cliente = clienteRepository.find(Cliente.class, id);

		if (cliente == null) {
			return getResponse(Status.NOT_FOUND, null);
		}

		return getResponse(Status.ACCEPTED, cliente);
	}

	/***
	 * Método que retorna o cadastro de todos os cliente do banco de dados
	 * 
	 * @return
	 */
	@GET
	@Produces("application/json")
	@Path("cliente")
	public Response getClientes() {
		System.out.println("AAAA");
		List<Cliente> cliente = clienteRepository.retornaTodosOsClientes();

		if (cliente == null | cliente.size() == 0) {
			return Response.status(404).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Headers", "Origin, X-Request-Width, Content-Type, Accept").build();
		}

		return getResponse(Status.ACCEPTED, cliente);
	}

	/***
	 * Método que insere o cadastro do cliente do banco de dados
	 * 
	 * @return
	 */
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	@Path("cliente")
	public Response criaCliente(Cliente cliente) {
		try {
			clienteRepository.find(Cliente.class, cliente);
			return getResponse(Status.CONFLICT, null);
		} catch (NoResultException noresult) {
			clienteRepository.criarDados(cliente);
			return getResponse(Status.CREATED, null);
		} catch (NonUniqueResultException notUnique) {
			notUnique.printStackTrace();
			return Response.serverError().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	/***
	 * Método que atualiza o cadastro do cliente do banco de dados
	 * 
	 * @return
	 */
	@PUT
	@Consumes("application/json")
	@Produces("application/json")
	@Path("cliente/{id}")
	public Response atualizaCliente(@PathParam(value = "id") Integer id, Cliente cliente) {
		try {
			cliente.setId(id);
			clienteRepository.atualizarDados(cliente);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();

		}
		return getResponse(Status.CREATED, null);
	}

	/***
	 * Método que exclui o cadastro do cliente do banco de dados
	 * 
	 * @return
	 */
	@DELETE
	@Consumes("application/json")
	@Produces("application/json")
	@Path("cliente/{id}")
	public Response excluiCliente(@PathParam(value = "id") Long id) {
		try {
			clienteRepository.removerDados(id);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();

		}
		return getResponse(Status.ACCEPTED, null);
	}

	private Response getResponse(Status status, Object object) {
		if (object != null) {
			return Response.status(status).entity(object).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Headers", "Origin, X-Request-Width, Content-Type, Accept").build();
		}
		return Response.status(status).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Headers", "Origin, X-Request-Width, Content-Type, Accept").build();

	}

	/***
	 * Workaround. O angular faz um request no método options antes do post e do
	 * delete e para resolver o problema de forma rápida, adicionei esse método.
	 * TODO Realizar a correção deste workarount
	 * 
	 * @return
	 */
	@OPTIONS
	@Consumes("application/json")
	@Produces("application/json")
	@Path("cliente")

	public Response options() {
		return this.getOptionsGeneric();
	}

	/***
	 * Workaround. O angular faz um request no método options antes do post e do
	 * delete e para resolver o problema de forma rápida, adicionei esse método.
	 * TODO Realizar a correção deste workarount
	 * 
	 * @return
	 */
	@OPTIONS
	@Consumes("application/json")
	@Produces("application/json")
	@Path("cliente/{id}")
	public Response optionsId() {
		return this.getOptionsGeneric();
	}

	/***
	 * Método genérico para retornar o objeto para os métodos optionsId e options
	 * 
	 * @return
	 */
	private Response getOptionsGeneric() {
		return Response.status(Status.ACCEPTED).header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE")
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Headers", "Origin, X-Request-Width, Content-Type, Accept").build();
	}
}
