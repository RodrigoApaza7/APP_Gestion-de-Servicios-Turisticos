namespace TravelHub.API.Modules.Itinerarios.DTOs;

public class CrearDetalleItinerarioDto
{
    public int IdServicio { get; set; }

    public DateOnly? Fecha { get; set; }

    public TimeOnly? Hora { get; set; }

    public short? Orden { get; set; }

    public string? Notas { get; set; }
}