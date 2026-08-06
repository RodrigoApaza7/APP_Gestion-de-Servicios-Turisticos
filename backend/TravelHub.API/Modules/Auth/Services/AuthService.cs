using TravelHub.API.Common.Security;
using TravelHub.API.Modules.Auth.DTOs;
using TravelHub.API.Modules.Auth.Interfaces;
using TravelHub.API.Modules.Usuarios.Interfaces;

namespace TravelHub.API.Modules.Auth.Services;

public class AuthService : IAuthService
{
    private readonly IUsuarioRepository _usuarioRepository;

    private readonly JwtService _jwtService;

    public AuthService(
        IUsuarioRepository usuarioRepository,
        JwtService jwtService)
    {
        _usuarioRepository = usuarioRepository;
        _jwtService = jwtService;
    }

    public async Task<LoginRespuestaDto?> LoginAsync(LoginDto dto)
    {
        var usuario = await _usuarioRepository.ObtenerPorCorreoAsync(dto.Correo);

        if (usuario == null)
            return null;

        if (!PasswordHasher.Verify(dto.Password, usuario.password_hash))
            return null;

        return new LoginRespuestaDto
        {
            IdUsuario = usuario.id_usuario,
            Nombre = usuario.nombre,
            Correo = usuario.correo,
            IdRol = usuario.id_rol,

            // JWT en el siguiente paso
            Token = _jwtService.GenerarToken(usuario)
        };
    }
}