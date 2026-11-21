package projeto.gestao.security.security.service;

import java.util.List;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServiceSecurity implements UserDetailsService {@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	/*
	private final UsuarioService usuarioService;

	@Override
	@org.springframework.transaction.annotation.Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {

		Usuario usuario = usuarioService.buscarViaCPF(cpf);

		List<String> permissoes = usuario.getPerfis().stream().map(perfil -> "ROLE_" + perfil.getNomePerfil()).toList();

		return User.builder().username(usuario.getCpfUsuario()).password(usuario.getSenhaArmazenada())
				.disabled(!Boolean.TRUE.equals(usuario.getStatusUsuario()))
				.authorities(permissoes.toArray(new String[0])).build();
	}*/

}
