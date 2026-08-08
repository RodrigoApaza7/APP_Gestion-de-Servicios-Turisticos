using TravelHub.API.Models;

namespace TravelHub.API.Modules.Servicios.Interfaces;

public interface IServicioRepository
{
    Task<IEnumerable<servicio>> ObtenerTodosAsync();

    Task<servicio?> ObtenerPorIdAsync(int id);

    Task CrearAsync(servicio servicio);

    Task ActualizarAsync(servicio servicio);

    Task EliminarAsync(servicio servicio);

    Task GuardarCambiosAsync();
}