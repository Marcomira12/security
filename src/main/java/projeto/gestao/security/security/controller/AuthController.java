package projeto.gestao.security.security.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.RequiredArgsConstructor;
import projeto.gestao.security.security.dto.LoginRequest;
import projeto.gestao.security.security.service.TokenService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final TokenService tokenService;

	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody LoginRequest dto) {

		try {

			Authentication autenticacao = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(dto.cpf(), dto.senha()));

			String token = tokenService.gerarToken(autenticacao);

			return ResponseEntity.ok(Map.of("token", token));

		} catch (AuthenticationException e) {

			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("CPF ou senha inválidos");
		}
	}

}