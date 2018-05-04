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

import br.fk.projeto.entity.Documento;
import br.fk.projeto.entity.Usuario;

public class DocumentoTest {

	@Before
	public void criacaoDocumento() {
		Usuario usuarioTeste = mock(Usuario.class);

		Documento documento = new Documento();
		documento.setDescricao("Documento Teste");
		documento.setDestinatario(usuarioTeste);
		documento.setDtEnvio(new Date(Calendar.getInstance().getTimeInMillis()));
		documento.setRemetente(usuarioTeste);
		documento.setStatus("STATUS TESTE");
		documento.setTipo("TIPO TESTE");
		documento.setUrl("URL TESTE");

		assertTrue(documento instanceof Documento);
		assertTrue(documento.getDescricao() instanceof String);
		assertTrue(documento.getDestinatario() instanceof Usuario);
		assertTrue(documento.getDtEnvio() instanceof Date);
		assertTrue(documento.getRemetente() instanceof Usuario);
		assertTrue(documento.getStatus() instanceof String);
		assertTrue(documento.getTipo() instanceof String);
		assertTrue(documento.getUrl() instanceof String);

	}

	@Test
	public void listaDocumentos() {
		List<Documento> documentos = listaDeDocumentos();

		documentos.forEach(documento -> {
			assertNotNull(documento);

			assertTrue(documento instanceof Documento);
			assertTrue(documento.getDescricao() instanceof String);
			assertTrue(documento.getDestinatario() instanceof Usuario);
			assertTrue(documento.getDtEnvio() instanceof Date);
			assertTrue(documento.getRemetente() instanceof Usuario);
			assertTrue(documento.getStatus() instanceof String);
			assertTrue(documento.getTipo() instanceof String);
			assertTrue(documento.getUrl() instanceof String);
		});
	}

	private List<Documento> listaDeDocumentos() {
		List<Documento> lista = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			Usuario usuarioTeste = mock(Usuario.class);

			Documento documento = new Documento();
			documento.setDescricao("Documento " + (int) Math.random());
			documento.setDestinatario(usuarioTeste);
			documento.setDtEnvio(new Date(Calendar.getInstance().getTimeInMillis()));
			documento.setRemetente(usuarioTeste);
			documento.setStatus("STATUS " + (int) Math.random());
			documento.setTipo("TIPO " + (int) Math.random());
			documento.setUrl("URL " + (int) Math.random());

			lista.add(documento);
		}
		return lista;
	}
}
