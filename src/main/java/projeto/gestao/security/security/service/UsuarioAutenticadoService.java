	package projeto.gestao.security.security.service;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
/*
@Service
@RequiredArgsConstructor
public class UsuarioAutenticadoService {

	private final UsuarioRepository usuarioRepository;

	public Usuario obterUsuario() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth == null || !auth.isAuthenticated() || auth instanceof AnonymousAuthenticationToken) {

			throw new AuthenticationCredentialsNotFoundException("Usuário não autenticado");
		}

		// o subject do JWT é o CPF (ver TokenService / UsuarioServiceSecurity)
		return usuarioRepository.findByCpfUsuario(auth.getName()).orElseThrow(
				() -> new AuthenticationCredentialsNotFoundException("Usuário autenticado não encontrado"));
	}
}*/
