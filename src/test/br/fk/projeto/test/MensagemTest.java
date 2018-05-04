package br.fk.projeto.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.fk.projeto.entity.Mensagem;
import br.fk.projeto.entity.Usuario;

public class MensagemTest {

	@Before
	public void criacaoMensagem() {

		Usuario remetente = mock(Usuario.class);
		Usuario destinatario = mock(Usuario.class);

		Mensagem mensagem = new Mensagem();
		mensagem.setAssunto("Assunto teste");
		mensagem.setMensagem("Mensagem teste");
		mensagem.setTipo("Tipo teste");
		mensagem.setDtEnvio(new Date(Calendar.getInstance().getTimeInMillis()));
		mensagem.setRemetente(remetente);
		mensagem.setDestinatario(destinatario);

		assertTrue(mensagem instanceof Mensagem);
		assertTrue(mensagem.getAssunto() instanceof String);
		assertTrue(mensagem.getMensagem() instanceof String);
		assertTrue(mensagem.getTipo() instanceof String);
		assertTrue(mensagem.getDtEnvio() instanceof Date);
		assertTrue(mensagem.getRemetente() instanceof Usuario);
		assertTrue(mensagem.getDestinatario() instanceof Usuario);

	}

	@Test
	public void listaMensagems() {
		List<Mensagem> mensagems = listaDeMensagems();

		mensagems.forEach(mensagem -> {
			assertNotNull(mensagem);

			assertTrue(mensagem instanceof Mensagem);
			assertTrue(mensagem.getAssunto() instanceof String);
			assertTrue(mensagem.getMensagem() instanceof String);
			assertTrue(mensagem.getTipo() instanceof String);
			assertTrue(mensagem.getDtEnvio() instanceof Date);
			assertTrue(mensagem.getRemetente() instanceof Usuario);
			assertTrue(mensagem.getDestinatario() instanceof Usuario);
		});
	}

	private List<Mensagem> listaDeMensagems() {
		List<Mensagem> lista = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			Usuario remetente = mock(Usuario.class);
			Usuario destinatario = mock(Usuario.class);

			Mensagem mensagem = new Mensagem();
			mensagem.setAssunto("Assunto teste" + (int) Math.random());
			mensagem.setMensagem("Mensagem teste" + (int) Math.random());
			mensagem.setTipo("Tipo teste" + (int) Math.random());
			mensagem.setDtEnvio(new Date(Calendar.getInstance().getTimeInMillis()));
			mensagem.setRemetente(remetente);
			mensagem.setDestinatario(destinatario);

			lista.add(mensagem);
		}
		return lista;
	}
}
