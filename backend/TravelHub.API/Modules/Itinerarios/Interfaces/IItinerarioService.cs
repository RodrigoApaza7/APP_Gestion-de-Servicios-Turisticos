using TravelHub.API.Modules.Itinerarios.DTOs;

namespace TravelHub.API.Modules.Itinerarios.Interfaces;

public interface IItinerarioService
{
    Task<List<ItinerarioRespuestaDto>> ObtenerPorUsuarioAsync(int idUsuario);

    Task<ItinerarioRespuestaDto?> ObtenerPorIdAsync(int idItinerario);

    Task<ItinerarioRespuestaDto> CrearAsync(CrearItinerarioDto dto);

    Task<bool> ActualizarAsync(
        int idItinerario,
        ActualizarItinerarioDto dto
    );

    Task<bool> EliminarAsync(int idItinerario);
}