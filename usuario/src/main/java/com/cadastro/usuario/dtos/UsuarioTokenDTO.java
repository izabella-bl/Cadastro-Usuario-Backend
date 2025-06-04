package com.cadastro.usuario.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO de resposta contendo o token JWT do usuário autenticado")
public record UsuarioTokenDTO(

        @Schema(description = "Token JWT gerado após login", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6...")
        String token,

        @Schema(description = "Id do usuário", example = "1")
        Long id

) {}