using TravelHub.API.Modules.Reservas.DTOs;

namespace TravelHub.API.Modules.Reservas.Interfaces;

public interface IReservaService
{
    Task<IEnumerable<ReservaRespuestaDto>> ObtenerTodosAsync();

    Task<ReservaRespuestaDto?> ObtenerPorIdAsync(int id);

    Task<ReservaRespuestaDto> CrearAsync(CrearReservaDto dto);

    Task<bool> ActualizarAsync(int id, ActualizarReservaDto dto);

    Task<bool> EliminarAsync(int id);
}