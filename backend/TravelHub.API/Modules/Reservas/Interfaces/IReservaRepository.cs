using TravelHub.API.Models;

namespace TravelHub.API.Modules.Reservas.Interfaces;

public interface IReservaRepository
{
    Task<IEnumerable<reserva>> ObtenerTodosAsync();

    Task<reserva?> ObtenerPorIdAsync(int id);

    Task CrearAsync(reserva reserva);

    Task ActualizarAsync(reserva reserva);

    Task EliminarAsync(reserva reserva);

    Task GuardarCambiosAsync();
}