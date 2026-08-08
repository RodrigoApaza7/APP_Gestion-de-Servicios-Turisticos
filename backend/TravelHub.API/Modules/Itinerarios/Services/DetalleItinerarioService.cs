using TravelHub.API.Models;
using TravelHub.API.Modules.Itinerarios.DTOs;
using TravelHub.API.Modules.Itinerarios.Interfaces;

namespace TravelHub.API.Modules.Itinerarios.Services;

public class DetalleItinerarioService : IDetalleItinerarioService
{
    private readonly IDetalleItinerarioRepository _repository;

    public DetalleItinerarioService(
        IDetalleItinerarioRepository repository)
    {
        _repository = repository;
    }

    public async Task<List<DetalleItinerarioRespuestaDto>> ObtenerPorItinerarioAsync(
        int idItinerario)
    {
        var detalles = await _repository.ObtenerPorItinerarioAsync(
            idItinerario
        );

        return detalles.Select(MapearRespuesta).ToList();
    }

    public async Task<DetalleItinerarioRespuestaDto?> ObtenerPorIdAsync(
        int idDetalle)
    {
        var detalle = await _repository.ObtenerPorIdAsync(
            idDetalle
        );

        if (detalle == null)
            return null;

        return MapearRespuesta(detalle);
    }

    public async Task<DetalleItinerarioRespuestaDto> CrearAsync(
        int idItinerario,
        CrearDetalleItinerarioDto dto)
    {
        var detalle = new detalle_itinerario
        {
            id_itinerario = idItinerario,
            id_servicio = dto.IdServicio,
            fecha = dto.Fecha,
            hora = dto.Hora,
            orden = dto.Orden ?? 0,
            notas = dto.Notas
        };

        var creado = await _repository.CrearAsync(detalle);

        return MapearRespuesta(creado);
    }

    public async Task<bool> ActualizarAsync(
        int idDetalle,
        ActualizarDetalleItinerarioDto dto)
    {
        var detalle = await _repository.ObtenerPorIdAsync(
            idDetalle
        );

        if (detalle == null)
            return false;

        detalle.fecha = dto.Fecha;
        detalle.hora = dto.Hora;
        detalle.orden = dto.Orden??0;
        detalle.notas = dto.Notas;

        await _repository.ActualizarAsync(detalle);

        return true;
    }

    public async Task<bool> EliminarAsync(
        int idDetalle)
    {
        var detalle = await _repository.ObtenerPorIdAsync(
            idDetalle
        );

        if (detalle == null)
            return false;

        await _repository.EliminarAsync(detalle);

        return true;
    }

    private static DetalleItinerarioRespuestaDto MapearRespuesta(
        detalle_itinerario detalle)
    {
        return new DetalleItinerarioRespuestaDto
        {
            IdDetalle = detalle.id_detalle,
            IdItinerario = detalle.id_itinerario,
            IdServicio = detalle.id_servicio,
            Fecha = detalle.fecha,
            Hora = detalle.hora,
            Orden = detalle.orden,
            Notas = detalle.notas
        };
    }
}