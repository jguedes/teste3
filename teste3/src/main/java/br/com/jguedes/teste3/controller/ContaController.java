package br.com.jguedes.teste3.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.jguedes.teste3.model.Conta;

@Path("contas")
@RequestScoped
public class ContaController {

	@Resource
	private UserTransaction ut;

	@PersistenceContext
	private EntityManager em;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("listar/{tipo}/")
	public List<Conta> listContas(@PathParam("tipo") int tipo) {
		System.out.println("Listar contas tipo "+(tipo==0?"ENTRADA":"SAÍDA")+" invocado.");
		List<Conta> lista = em.createQuery("Select c from Conta c where c.tipo = :tipo", Conta.class)
				.setParameter("tipo", tipo).getResultList();
		return lista;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/")
	public Conta getConta(@PathParam("id") int id) {
		System.out.println("getConta invocado.");
		Conta conta = em.createQuery("Select c from Conta c where c.id = :id", Conta.class).setParameter("id", id)
				.getSingleResult();
		return conta;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response create(Conta conta) {

		try {
			ut.begin();
			em.persist(conta);
			ut.commit();
			return Response.status(Response.Status.OK).build();
		} catch (SecurityException | IllegalStateException | NotSupportedException | SystemException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}

	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response update(Conta conta) {
		System.out.println("update invocado.");
		try {
			ut.begin();
			em.merge(conta);
			ut.commit();
			return Response.status(Response.Status.OK).build();
		} catch (SecurityException | IllegalStateException | NotSupportedException | SystemException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DELETE
	@Path("{id}/")
	public Response delete(@PathParam("id") int id) {
		System.out.println("delete invocado.");
		try {
			ut.begin();
			em.remove(getConta(id));
			ut.commit();
			return Response.status(Response.Status.OK).build();
		} catch (SecurityException | IllegalStateException | NotSupportedException | SystemException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

}
