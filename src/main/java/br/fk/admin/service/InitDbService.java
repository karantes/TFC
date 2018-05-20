package br.fk.admin.service;

import java.util.Calendar;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.fk.projeto.entity.Usuario;
import br.fk.projeto.repository.UsuarioRepository;

//import br.lb.admin.repository.UserRepository;

@Transactional
@Service
public class InitDbService {

	@Autowired
	private UsuarioRepository userRepository;

	@PostConstruct
	public void init() {

		if (userRepository.findAll().isEmpty()) {
			usersTest();
		}
	}

	private void usersTest() {
		Usuario sysAdmin = new Usuario();
		sysAdmin.setAtivo(true);
		sysAdmin.setNome("João Dionisio Paraiba");
		sysAdmin.setEmail("jdparaiba@hotmail.com");
		sysAdmin.setTipoUsuario(1);
		sysAdmin.setDtCadastro(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		sysAdmin.setSenha(encoder.encode("12345678"));

		userRepository.save(sysAdmin);

	}

}
