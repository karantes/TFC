package br.fk.projeto.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.fk.projeto.entity.Semestre;

public class SemestreTest {

	@Before
	public void criacaoSemestre() {

		Semestre semestre = new Semestre();
		semestre.setAno(Calendar.YEAR);
		semestre.setSemestre(1);

		assertTrue(semestre instanceof Semestre);
		assertTrue(semestre.getAno() instanceof Integer);
		assertTrue(semestre.getSemestre() instanceof Integer);
	}

	@Test
	public void listaSemestres() {
		List<Semestre> semestres = listaDeSemestres();

		semestres.forEach(semestre -> {
			assertNotNull(semestre);

			assertTrue(semestre instanceof Semestre);
			assertTrue(semestre.getAno() instanceof Integer);
			assertTrue(semestre.getSemestre() instanceof Integer);

		});
	}

	private List<Semestre> listaDeSemestres() {
		List<Semestre> lista = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			Semestre semestre = new Semestre();
			semestre.setAno((int) Math.random());
			semestre.setSemestre((int) Math.random());

			lista.add(semestre);
		}
		return lista;
	}
}
