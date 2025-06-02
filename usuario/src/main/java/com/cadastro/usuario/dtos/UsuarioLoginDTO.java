package com.cadastro.usuario.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(description = "Objeto de entrada para login do usuário")
public record UsuarioLoginDTO(

        @Schema(description = "Email válido do usuário", example = "usuario@email.com")
        @NotBlank @Email
        String email,

        @Schema(description = "Senha do usuário", example = "minhaSenha123")
        @NotBlank
        String senha
) {}