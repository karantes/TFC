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

import br.fk.projeto.entity.Evento;
import br.fk.projeto.entity.Usuario;

public class EventoTest {

	@Before
	public void criacaoEvento() {

		Usuario participante = mock(Usuario.class);

		Evento evento = new Evento();
		evento.setNome("Evento Teste");
		evento.setDescricao("Evento Teste");
		evento.setDtEvento(new Date(Calendar.getInstance().getTimeInMillis()));
		evento.setLocal("Local teste");
		evento.setStatus("STATUS TESTE");
		evento.setParticipante(participante);

		assertTrue(evento instanceof Evento);
		assertTrue(evento.getNome() instanceof String);
		assertTrue(evento.getDescricao() instanceof String);
		assertTrue(evento.getDtEvento() instanceof Date);
		assertTrue(evento.getLocal() instanceof String);
		assertTrue(evento.getStatus() instanceof String);
		assertTrue(evento.getParticipante() instanceof Usuario);

	}

	@Test
	public void listaEventos() {
		List<Evento> eventos = listaDeEventos();

		eventos.forEach(evento -> {
			assertNotNull(evento);

			assertTrue(evento instanceof Evento);
			assertTrue(evento.getNome() instanceof String);
			assertTrue(evento.getDescricao() instanceof String);
			assertTrue(evento.getDtEvento() instanceof Date);
			assertTrue(evento.getLocal() instanceof String);
			assertTrue(evento.getStatus() instanceof String);
			assertTrue(evento.getParticipante() instanceof Usuario);
		});
	}

	private List<Evento> listaDeEventos() {
		List<Evento> lista = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			Usuario participante = mock(Usuario.class);

			Evento evento = new Evento();
			evento.setNome("Evento Teste" + (int) Math.random());
			evento.setDescricao("Evento Teste" + (int) Math.random());
			evento.setDtEvento(new Date(Calendar.getInstance().getTimeInMillis()));
			evento.setLocal("Local teste" + (int) Math.random());
			evento.setStatus("STATUS TESTE" + (int) Math.random());
			evento.setParticipante(participante);

			lista.add(evento);
		}
		return lista;
	}
}
