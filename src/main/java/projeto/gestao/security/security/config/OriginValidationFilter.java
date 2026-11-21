package projeto.gestao.security.security.config;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OriginValidationFilter extends OncePerRequestFilter {

	private final List<String> origensPermitidas;

	public OriginValidationFilter(
			@Value("${cors.origens-permitidas:http://localhost:4200}")
			List<String> origensPermitidas) {

		this.origensPermitidas = origensPermitidas;
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		// O preflight CORS é tratado pelo CorsFilter do Spring.
		return "OPTIONS".equalsIgnoreCase(request.getMethod());
	}

	@Override
	protected void doFilterInternal(
			HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain)
			throws ServletException, IOException {

		String origin = request.getHeader("Origin");

		boolean origemPermitida =
				origin != null &&
						origensPermitidas.stream()
								.anyMatch(origem -> origem.equalsIgnoreCase(origin));

		if (!origemPermitida) {
			// Evita um novo despacho para /error antes da autenticação do JWT,
			// que converteria o bloqueio de origem em um 401 de sessão inválida.
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write("{\"status\":403,\"mensagem\":\"Origem nao autorizada\"}");
			return;
		}

		filterChain.doFilter(request, response);
	}
}
