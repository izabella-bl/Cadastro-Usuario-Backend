package com.cadastro.usuario.dtos;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Objeto para registrar um novo usuário")
public record UsuarioDTO(

        @Schema(description = "Nome completo do usuário", example = "João Silva")
        @NotBlank @Size(min = 3, max = 50)
        String nome,

        @Schema(description = "Email válido do usuário", example = "joao@email.com")
        @NotBlank @Email
        String email,

        @Schema(description = "Senha de 6 a 20 caracteres", example = "123456")
        @NotBlank @Size(min = 6, max = 20)
        String senha,

        @Schema(description = "Confirmação da senha", example = "123456")
        @NotBlank
        String confirmacaoSenha
) {
    public boolean senhaValida() {
        return senha != null && senha.equals(confirmacaoSenha);
    }
}