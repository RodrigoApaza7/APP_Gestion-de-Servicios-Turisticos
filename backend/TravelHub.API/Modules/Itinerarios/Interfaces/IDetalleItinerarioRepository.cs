using TravelHub.API.Models;

namespace TravelHub.API.Modules.Itinerarios.Interfaces;

public interface IDetalleItinerarioRepository
{
    Task<List<detalle_itinerario>> ObtenerPorItinerarioAsync(int idItinerario);

    Task<detalle_itinerario?> ObtenerPorIdAsync(int idDetalle);

    Task<detalle_itinerario> CrearAsync(detalle_itinerario detalle);

    Task ActualizarAsync(detalle_itinerario detalle);

    Task EliminarAsync(detalle_itinerario detalle);
}