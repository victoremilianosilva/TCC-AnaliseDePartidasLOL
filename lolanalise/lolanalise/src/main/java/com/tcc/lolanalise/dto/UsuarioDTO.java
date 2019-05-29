package com.tcc.lolanalise.dto;

import java.util.List;

import com.tcc.lolanalise.domain.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class UsuarioDTO {
  private Long id;
 
  private String username;

  private String email;
 
  private String password;
  
  private List<Role> roles;
}
