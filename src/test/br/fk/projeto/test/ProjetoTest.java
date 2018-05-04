package br.fk.projeto.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.fk.projeto.entity.Projeto;
import br.fk.projeto.entity.Semestre;
import br.fk.projeto.entity.Usuario;

@SuppressWarnings("unchecked")
public class ProjetoTest {

	@Before
	public void criacaoProjeto() {
		Semestre semestre = mock(Semestre.class);
		List<Usuario> usuarios = mock(List.class);

		Projeto projeto = new Projeto();
		projeto.setNome("Nome teste");
		projeto.setDescricao("Projeto teste");
		projeto.setAtivo(true);
		projeto.setSemestre(semestre);
		projeto.setUsuarios(usuarios);

		assertTrue(projeto instanceof Projeto);
		assertTrue(projeto.getNome() instanceof String);
		assertTrue(projeto.getDescricao() instanceof String);
		assertTrue(projeto.getAtivo() instanceof Boolean);
		assertTrue(projeto.getSemestre() instanceof Semestre);
		assertTrue(projeto.getUsuarios() instanceof List);

	}

	@Test
	public void listaProjetos() {
		List<Projeto> projetos = listaDeProjetos();

		projetos.forEach(projeto -> {
			assertNotNull(projeto);

			assertTrue(projeto instanceof Projeto);
			assertTrue(projeto.getNome() instanceof String);
			assertTrue(projeto.getDescricao() instanceof String);
			assertTrue(projeto.getAtivo() instanceof Boolean);
			assertTrue(projeto.getSemestre() instanceof Semestre);
			assertTrue(projeto.getUsuarios() instanceof List);

		});
	}

	private List<Projeto> listaDeProjetos() {
		List<Projeto> lista = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			Semestre semestre = mock(Semestre.class);
			List<Usuario> usuarios = mock(List.class);

			Projeto projeto = new Projeto();
			projeto.setNome("Nome teste" + (int) Math.random());
			projeto.setDescricao("Projeto teste" + (int) Math.random());
			projeto.setAtivo(true);
			projeto.setSemestre(semestre);
			projeto.setUsuarios(usuarios);

			lista.add(projeto);
		}
		return lista;
	}
}
