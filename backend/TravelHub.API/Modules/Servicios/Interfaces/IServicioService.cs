using TravelHub.API.Modules.Servicios.DTOs;

namespace TravelHub.API.Modules.Servicios.Interfaces;

public interface IServicioService
{
    Task<IEnumerable<ServicioRespuestaDto>> ObtenerTodosAsync();

    Task<ServicioRespuestaDto?> ObtenerPorIdAsync(int id);

    Task<ServicioRespuestaDto> CrearAsync(CrearServicioDto dto);

    Task<bool> ActualizarAsync(int id, ActualizarServicioDto dto);

    Task<bool> EliminarAsync(int id);
}