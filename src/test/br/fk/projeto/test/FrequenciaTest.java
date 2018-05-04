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

import br.fk.projeto.entity.Frequencia;
import br.fk.projeto.entity.Projeto;
import br.fk.projeto.entity.Usuario;

public class FrequenciaTest {

	@Before
	public void criacaoFrequencia() {

		Usuario orientador = mock(Usuario.class);
		Projeto projeto = mock(Projeto.class);

		Frequencia frequencia = new Frequencia();
		frequencia.setAtividades("Atividades teste");
		frequencia.setDtFrequencia(new Date(Calendar.getInstance().getTimeInMillis()));
		frequencia.setCompareceu(true);
		frequencia.setProjeto(projeto);
		frequencia.setOrientador(orientador);

		assertTrue(frequencia instanceof Frequencia);
		assertTrue(frequencia.getAtividades() instanceof String);
		assertTrue(frequencia.getDtFrequencia() instanceof Date);
		assertTrue(frequencia.getCompareceu() instanceof Boolean);
		assertTrue(frequencia.getProjeto() instanceof Projeto);
		assertTrue(frequencia.getOrientador() instanceof Usuario);

	}

	@Test
	public void listaFrequencias() {
		List<Frequencia> frequencias = listaDeFrequencias();

		frequencias.forEach(frequencia -> {
			assertNotNull(frequencia);

			assertTrue(frequencia instanceof Frequencia);
			assertTrue(frequencia.getAtividades() instanceof String);
			assertTrue(frequencia.getDtFrequencia() instanceof Date);
			assertTrue(frequencia.getCompareceu() instanceof Boolean);
			assertTrue(frequencia.getProjeto() instanceof Projeto);
			assertTrue(frequencia.getOrientador() instanceof Usuario);
		});
	}

	private List<Frequencia> listaDeFrequencias() {
		List<Frequencia> lista = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			Usuario orientador = mock(Usuario.class);
			Projeto projeto = mock(Projeto.class);

			Frequencia frequencia = new Frequencia();
			frequencia.setAtividades("Atividades teste" + (int) Math.random());
			frequencia.setDtFrequencia(new Date(Calendar.getInstance().getTimeInMillis()));
			frequencia.setCompareceu(true);
			frequencia.setProjeto(projeto);
			frequencia.setOrientador(orientador);

			lista.add(frequencia);
		}
		return lista;
	}
}
