package com.tcc.lolanalise.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.tcc.lolanalise.domain.Role;
import com.tcc.lolanalise.domain.RoleName;
import com.tcc.lolanalise.domain.Usuario;
import com.tcc.lolanalise.dto.JwtAuthenticationDTO;
import com.tcc.lolanalise.exception.CustomException;
import com.tcc.lolanalise.repository.UsuarioRepository;
import com.tcc.lolanalise.security.JwtTokenProvider;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UsuarioService {
	@NonNull
	private UsuarioRepository userRepository;

	@NonNull
	private PasswordEncoder passwordEncoder;

	@NonNull
	private JwtTokenProvider tokenProvider;

	@NonNull
	private AuthenticationManager authenticationManager;

	public ResponseEntity<?> login(Usuario usuario) {
		try {
			Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(	
	                		usuario.getUsername(),
	                		usuario.getPassword()
	                )
	        );

	        SecurityContextHolder.getContext().setAuthentication(authentication);

	        String jwt = tokenProvider.generateToken(authentication);
	        return ResponseEntity.ok(new JwtAuthenticationDTO(jwt));
		} catch (AuthenticationException e) {
			throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	public ResponseEntity<?> cadastroUsuario(Usuario usuario) {
		if (!userRepository.existsByUsername(usuario.getUsername())) {
			usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
			List<Role> roles = new ArrayList<Role>();
			Role role = new Role();
			role.setName(RoleName.ROLE_USER);
			roles.add(role);
			usuario.setRoles(roles);
			usuario = userRepository.save(usuario);
			ResponseEntity<?> response = login(usuario);
			return response;
		} else {
			throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
}
