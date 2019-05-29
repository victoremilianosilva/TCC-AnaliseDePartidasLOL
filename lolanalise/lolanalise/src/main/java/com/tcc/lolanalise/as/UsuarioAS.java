package com.tcc.lolanalise.as;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tcc.lolanalise.comum.ConverterMapper;
import com.tcc.lolanalise.domain.Usuario;
import com.tcc.lolanalise.dto.UsuarioDTO;
import com.tcc.lolanalise.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioAS {
	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ConverterMapper mapper;
	
	public ResponseEntity<?> login(UsuarioDTO usuario) {
		return usuarioService.login(mapper.map(usuario, Usuario.class));
	}
	
	public ResponseEntity<?> cadastroUsuario(UsuarioDTO usuario) {
		return usuarioService.cadastroUsuario(mapper.map(usuario, Usuario.class));
	}
}
