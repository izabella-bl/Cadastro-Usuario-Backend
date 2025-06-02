package com.cadastro.usuario.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Objeto de resposta contendo os dados do usuário")
public record UsuarioResponseDTO(

        @Schema(description = "ID do usuário", example = "1")
        Long id,

        @Schema(description = "Nome do usuário", example = "Izabella Barros")
        String nome,

        @Schema(description = "Email do usuário", example = "izabella@email.com")
        String email
) {}