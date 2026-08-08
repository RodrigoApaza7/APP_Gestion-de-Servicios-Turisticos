using TravelHub.API.Models;
using TravelHub.API.Modules.Itinerarios.DTOs;
using TravelHub.API.Modules.Itinerarios.Interfaces;

namespace TravelHub.API.Modules.Itinerarios.Services;

public class ItinerarioService : IItinerarioService
{
    private readonly IItinerarioRepository _repository;

    public ItinerarioService(IItinerarioRepository repository)
    {
        _repository = repository;
    }

    public async Task<List<ItinerarioRespuestaDto>> ObtenerPorUsuarioAsync(int idUsuario)
    {
        var itinerarios = await _repository.ObtenerPorUsuarioAsync(idUsuario);

        return itinerarios.Select(MapearRespuesta).ToList();
    }

    public async Task<ItinerarioRespuestaDto?> ObtenerPorIdAsync(int idItinerario)
    {
        var itinerario = await _repository.ObtenerPorIdAsync(idItinerario);

        if (itinerario == null)
            return null;

        return MapearRespuesta(itinerario);
    }

    public async Task<ItinerarioRespuestaDto> CrearAsync(CrearItinerarioDto dto)
    {
        var itinerario = new itinerario
        {
            id_usuario = dto.IdUsuario,
            nombre = dto.Nombre,
            descripcion = dto.Descripcion,
            imagen_portada = dto.ImagenPortada,
            fecha_inicio = dto.FechaInicio,
            fecha_fin = dto.FechaFin,
            compartido = dto.Compartido ?? false,
            publico = dto.Publico ?? false
        };

        var creado = await _repository.CrearAsync(itinerario);

        return MapearRespuesta(creado);
    }

    public async Task<bool> ActualizarAsync(
        int idItinerario,
        ActualizarItinerarioDto dto)
    {
        var itinerario = await _repository.ObtenerPorIdAsync(idItinerario);

        if (itinerario == null)
            return false;

        itinerario.nombre = dto.Nombre;
        itinerario.descripcion = dto.Descripcion;
        itinerario.imagen_portada = dto.ImagenPortada;
        itinerario.fecha_inicio = dto.FechaInicio;
        itinerario.fecha_fin = dto.FechaFin;
        itinerario.compartido = dto.Compartido ?? false;
        itinerario.publico = dto.Publico ?? false;

        await _repository.ActualizarAsync(itinerario);

        return true;
    }

    public async Task<bool> EliminarAsync(int idItinerario)
    {
        var itinerario = await _repository.ObtenerPorIdAsync(idItinerario);

        if (itinerario == null)
            return false;

        await _repository.EliminarAsync(itinerario);

        return true;
    }

    private static ItinerarioRespuestaDto MapearRespuesta(itinerario itinerario)
    {
        return new ItinerarioRespuestaDto
        {
            IdItinerario = itinerario.id_itinerario,
            IdUsuario = itinerario.id_usuario,
            Nombre = itinerario.nombre,
            Descripcion = itinerario.descripcion,
            ImagenPortada = itinerario.imagen_portada,
            FechaInicio = itinerario.fecha_inicio,
            FechaFin = itinerario.fecha_fin,
            Compartido = itinerario.compartido,
            Publico = itinerario.publico,
            FechaCreacion = itinerario.fecha_creacion,
            FechaActualizacion = itinerario.fecha_actualizacion
        };
    }
}