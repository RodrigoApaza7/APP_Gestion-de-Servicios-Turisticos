using TravelHub.API.Models;

namespace TravelHub.API.Modules.Itinerarios.Interfaces;

public interface IItinerarioRepository
{
    Task<List<itinerario>> ObtenerPorUsuarioAsync(int idUsuario);

    Task<itinerario?> ObtenerPorIdAsync(int idItinerario);

    Task<itinerario> CrearAsync(itinerario itinerario);

    Task ActualizarAsync(itinerario itinerario);

    Task EliminarAsync(itinerario itinerario);
}