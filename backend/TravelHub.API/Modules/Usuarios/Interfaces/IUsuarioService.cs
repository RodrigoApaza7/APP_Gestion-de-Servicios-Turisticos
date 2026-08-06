using TravelHub.API.Modules.Usuarios.DTOs;

namespace TravelHub.API.Modules.Usuarios.Interfaces;

public interface IUsuarioService
{
    Task<IEnumerable<UsuarioRespuestaDto>> ObtenerTodosAsync();

    Task<UsuarioRespuestaDto?> ObtenerPorIdAsync(int id);

    Task<UsuarioRespuestaDto> CrearAsync(CrearUsuarioDto dto);

    Task<bool> ActualizarAsync(int id, ActualizarUsuarioDto dto);

    Task<bool> EliminarAsync(int id);
}