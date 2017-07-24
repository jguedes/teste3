package br.com.jguedes.teste3.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.Response;

import br.com.jguedes.teste3.model.Movimentacao;
import br.com.jguedes.teste3.model.MovimentacaoPK;

@Path("movimentacoes")
@RequestScoped
public class MovimentacaoController {

	@Resource
	private UserTransaction ut;

	@PersistenceContext
	private EntityManager em;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("listar/{tipo}/")
	public List<Movimentacao> listMovimentacoes(@PathParam("tipo") int tipo) {
		System.out.println("Listar movimentacoes tipo " + (tipo == 0 ? "ENTRADA" : "SAÍDA") + " invocado.");
		List<Movimentacao> lista = em
				.createQuery("Select m from Movimentacao m where m.tipo = :tipo", Movimentacao.class)
				.setParameter("tipo", tipo).getResultList();
		return lista;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{tipo}/{ano}/{mes}/{num}/{sequecia}/")
	public Movimentacao getMovimentacao(@PathParam("tipo") String tipo, @PathParam("ano") int ano, @PathParam("mes") String mes, @PathParam("num") String num, @PathParam("sequecia") String seq) {
		System.out.println("getMovimentacao invocado.");
		MovimentacaoPK id = new MovimentacaoPK();
		id.setTipo(tipo);
		id.setAno(ano);
		id.setMes(mes);
		id.setNum(num);
		id.setSeq(seq);
		
		return getMovimentacao(id);
	}
	
	public Movimentacao getMovimentacao(MovimentacaoPK id) {
		Movimentacao movimentacao = em.createQuery("Select m from Movimentacao m where m.id = :id", Movimentacao.class)
				.setParameter("id", id).getSingleResult();
		return movimentacao;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response create(Movimentacao movimentacao) {

		try {
			ut.begin();
			em.persist(movimentacao);
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
	public Response update(Movimentacao movimentacao) {
		System.out.println("update invocado.");
		try {
			ut.begin();
			em.merge(movimentacao);
			ut.commit();
			return Response.status(Response.Status.OK).build();
		} catch (SecurityException | IllegalStateException | NotSupportedException | SystemException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DELETE
	@Path("{tipo}/{ano}/{mes}/{num}/{sequecia}/")
	public Response delete(@PathParam("tipo") String tipo, @PathParam("ano") int ano, @PathParam("mes") String mes, @PathParam("num") String num, @PathParam("sequecia") String seq) {
		System.out.println("delete invocado.");
		MovimentacaoPK id = new MovimentacaoPK();
		id.setTipo(tipo);
		id.setAno(ano);
		id.setMes(mes);
		id.setNum(num);
		id.setSeq(seq);
		try {
			ut.begin();
			em.remove(getMovimentacao(id));
			ut.commit();
			return Response.status(Response.Status.OK).build();
		} catch (SecurityException | IllegalStateException | NotSupportedException | SystemException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

}
