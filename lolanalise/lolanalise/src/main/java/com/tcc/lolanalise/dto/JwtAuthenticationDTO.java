package com.tcc.lolanalise.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(of = "accessToken")
@ToString
public class JwtAuthenticationDTO {
	private String accessToken;
    private String tokenType;
    
    public JwtAuthenticationDTO(String accessToken) {
        this.accessToken = accessToken;
        this.tokenType = "Bearer";
    }
}
