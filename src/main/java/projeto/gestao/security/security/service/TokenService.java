package projeto.gestao.security.security.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenService {

	private final JwtEncoder jwtEncoder;

	public String gerarToken(Authentication authentication) {

		Instant agora = Instant.now();

		List<String> perfis = authentication.getAuthorities().stream().map(authority -> authority.getAuthority())
				.toList();

		JwtClaimsSet claims = JwtClaimsSet.builder().subject(authentication.getName()).issuedAt(agora)
				.expiresAt(agora.plus(15, ChronoUnit.MINUTES)).claim("perfis", perfis).build();

		JwsHeader header = JwsHeader.with(MacAlgorithm.HS256).build();

		return jwtEncoder.encode(JwtEncoderParameters.from(header, claims)).getTokenValue();
	}
}