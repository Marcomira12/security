package projeto.gestao.security.security.Aspect;

import java.util.Arrays;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import projeto.gestao.security.security.annotation.ExigirPerfil;


@Aspect
@Component
public class AutorizacaoAspect {

	@Before("@annotation(exigirPerfil)")
	public void verificarPermissao(ExigirPerfil exigirPerfil) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null || !authentication.isAuthenticated()) {

			throw new AccessDeniedException("USUARIO_NAO_AUTENTICADO");
		}

		boolean autorizado = Arrays.stream(exigirPerfil.value()).anyMatch(perfil -> authentication.getAuthorities()
				.stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_" + perfil.name())));

		if (!autorizado) {
			throw new AccessDeniedException("SEM_PERMISSAO");
		}
	}
}
