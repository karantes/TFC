package br.fk.projeto.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.fk.projeto.entity.Usuario;

public class UsuarioTest {

	@Before
	public void criacaoUsuario() {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		Usuario usuario = new Usuario();
		usuario.setNome("Nome Teste");
		usuario.setSenha(encoder.encode("Senha Teste"));
		usuario.setEmail("Email teste");
		usuario.setTipoUsuario(1);
		usuario.setDtCadastro(new Date(Calendar.getInstance().getTimeInMillis()));
		usuario.setDtAlteracao(new Date(Calendar.getInstance().getTimeInMillis()));
		usuario.setAtivo(true);

		assertTrue(usuario instanceof Usuario);
		assertTrue(usuario.getNome() instanceof String);
		assertTrue(usuario.getSenha() instanceof String);
		assertTrue(usuario.getEmail() instanceof String);
		assertTrue(usuario.getTipoUsuario() instanceof Integer);
		assertTrue(usuario.getDtCadastro() instanceof Date);
		assertTrue(usuario.getDtAlteracao() instanceof Date);
		assertTrue(usuario.getAtivo() instanceof Boolean);
	}

	@Test
	public void listaUsuarios() {
		List<Usuario> usuarios = listaDeUsuarios();

		usuarios.forEach(usuario -> {
			assertNotNull(usuario);

			assertTrue(usuario instanceof Usuario);
			assertTrue(usuario.getNome() instanceof String);
			assertTrue(usuario.getSenha() instanceof String);
			assertTrue(usuario.getEmail() instanceof String);
			assertTrue(usuario.getTipoUsuario() instanceof Integer);
			assertTrue(usuario.getDtCadastro() instanceof Date);
			assertTrue(usuario.getDtAlteracao() instanceof Date);
			assertTrue(usuario.getAtivo() instanceof Boolean);

		});
	}

	private List<Usuario> listaDeUsuarios() {
		List<Usuario> lista = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

			Usuario usuario = new Usuario();
			usuario.setNome("Nome Teste" + (int) Math.random());
			usuario.setSenha(encoder.encode("Senha Teste" + (int) Math.random()));
			usuario.setEmail("Email teste" + (int) Math.random());
			usuario.setTipoUsuario(1);
			usuario.setDtCadastro(new Date(Calendar.getInstance().getTimeInMillis()));
			usuario.setDtAlteracao(new Date(Calendar.getInstance().getTimeInMillis()));
			usuario.setAtivo(true);

			lista.add(usuario);
		}
		return lista;
	}
}
