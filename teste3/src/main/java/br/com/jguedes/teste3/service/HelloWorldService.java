package br.com.jguedes.teste3.service;

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
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import br.com.jguedes.teste3.model.Conta;

@Path("/helloworld")
@RequestScoped
public class HelloWorldService {

	@Resource
	private UserTransaction ut;
	
	@PersistenceContext
	private EntityManager em;
	
	@GET
	@Path("/sayhello")
	public String sayHello() {
		return "<h1>Hello World!</h1>";
	}
	
	@GET
	@Path("/criarconta")
	public String criarConta() {
		Conta c = new Conta();
		c.setTipo(0);
		c.setTitulo("conta 1");
		try {
			ut.begin();
			em.persist(c);
			ut.commit();
			return "Conta criada com sucesso: " + c.imprimir();
		} catch (NotSupportedException | SystemException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (RollbackException e) {
			e.printStackTrace();
		} catch (HeuristicMixedException e) {
			e.printStackTrace();
		} catch (HeuristicRollbackException e) {
			e.printStackTrace();
		}
		return "Não foi possível criar a conta!";
	}
}
