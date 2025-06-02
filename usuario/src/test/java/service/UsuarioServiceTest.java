package service;

import com.cadastro.usuario.dtos.UsuarioDTO;
import com.cadastro.usuario.dtos.UsuarioLoginDTO;
import com.cadastro.usuario.dtos.UsuarioResponseDTO;
import com.cadastro.usuario.model.Usuario;
import com.cadastro.usuario.repository.UsuarioRepository;
import com.cadastro.usuario.security.JwtService;
import com.cadastro.usuario.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    private UsuarioService usuarioService;
    private UsuarioRepository usuarioRepository;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;

    @BeforeEach
    void setup() {
        usuarioRepository = mock(UsuarioRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        jwtService = mock(JwtService.class);
        usuarioService = new UsuarioService(usuarioRepository, passwordEncoder,jwtService);
    }

    @Test
    void deveCriarUsuarioComSucesso() {
        UsuarioDTO dto = new UsuarioDTO("Maria", "maria@email.com", "123456", "123456");
        Usuario usuarioSalvo = new Usuario(dto.nome(), dto.email(), "senhaCriptografada");
        usuarioSalvo.setId(1L);

        Mockito.when(usuarioRepository.save(Mockito.any(Usuario.class))).thenReturn(usuarioSalvo);
        UsuarioResponseDTO response = usuarioService.criar(dto);
        assertEquals("Maria", response.nome());
    }

    @Test
    void naoDeveCriarUsuarioQuandoSenhasDiferentes() {
        UsuarioDTO dto = new UsuarioDTO("Maria", "maria@email.com", "123456", "123");

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.criar(dto);
        });

        assertEquals("Senhas não coincidem", thrown.getMessage());
    }

    @Test
    void naoDeveCriarUsuarioQuandoEmailExistente() {
        UsuarioDTO dto = new UsuarioDTO("Maria", "maria@email.com", "123456", "123456");

        when(usuarioRepository.existsByEmail(dto.email())).thenReturn(true);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.criar(dto);
        });

        assertEquals("Email já cadastrado", thrown.getMessage());
    }

    @Test
    void deveLancarErroNoLoginQuandoEmailNaoExistir() {
        UsuarioLoginDTO dto = new UsuarioLoginDTO("email@naoexiste.com", "123456");

        when(usuarioRepository.findByEmail(dto.email())).thenReturn(Optional.empty());

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            usuarioService.autenticar(dto);
        });

        assertEquals("Email ou senha inválidos", thrown.getMessage());
    }

    @Test
    void deveBuscarUsuarioPorId() {
        Usuario usuario = new Usuario("Maria", "maria@email.com", "senhaCriptografada");
        usuario.setId(1L);

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        UsuarioResponseDTO response = usuarioService.buscarPorId(1L);

        assertEquals(1L, response.id());
        assertEquals(usuario.getNome(), response.nome());
    }

    @Test
    void deveLancarErroQuandoBuscarUsuarioPorIdInexistente() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            usuarioService.buscarPorId(1L);
        });

        assertEquals("Usuário não encontrado", thrown.getMessage());
    }

    @Test
    void deveListarTodosUsuarios() {
        Usuario u1 = new Usuario("Maria", "maria@email.com", "senha1");
        u1.setId(1L);
        Usuario u2 = new Usuario("João", "joao@email.com", "senha2");
        u2.setId(2L);

        when(usuarioRepository.findAll()).thenReturn(List.of(u1, u2));

        List<UsuarioResponseDTO> lista = usuarioService.listarTodos();

        assertEquals(2, lista.size());
        assertEquals("Maria", lista.get(0).nome());
        assertEquals("João", lista.get(1).nome());
    }

    @Test
    void deveDeletarUsuarioComSucesso() {
        when(usuarioRepository.existsById(1L)).thenReturn(true);

        usuarioService.deletar(1L);

        verify(usuarioRepository, times(1)).deleteById(1L);
    }

}