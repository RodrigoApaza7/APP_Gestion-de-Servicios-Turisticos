namespace TravelHub.API.Modules.Itinerarios.DTOs;

public class ItinerarioRespuestaDto
{
    public int IdItinerario { get; set; }

    public int IdUsuario { get; set; }

    public string Nombre { get; set; } = null!;

    public string? Descripcion { get; set; }

    public string? ImagenPortada { get; set; }

    public DateOnly? FechaInicio { get; set; }

    public DateOnly? FechaFin { get; set; }

    public bool? Compartido { get; set; }

    public bool? Publico { get; set; }

    public DateTime? FechaCreacion { get; set; }

    public DateTime? FechaActualizacion { get; set; }
}