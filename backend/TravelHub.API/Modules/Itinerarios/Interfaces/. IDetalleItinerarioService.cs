using TravelHub.API.Modules.Itinerarios.DTOs;

namespace TravelHub.API.Modules.Itinerarios.Interfaces;

public interface IDetalleItinerarioService
{
    Task<List<DetalleItinerarioRespuestaDto>> ObtenerPorItinerarioAsync(
        int idItinerario
    );

    Task<DetalleItinerarioRespuestaDto?> ObtenerPorIdAsync(
        int idDetalle
    );

    Task<DetalleItinerarioRespuestaDto> CrearAsync(
        int idItinerario,
        CrearDetalleItinerarioDto dto
    );

    Task<bool> ActualizarAsync(
        int idDetalle,
        ActualizarDetalleItinerarioDto dto
    );

    Task<bool> EliminarAsync(
        int idDetalle
    );
}