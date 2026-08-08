namespace TravelHub.API.Modules.Servicios.DTOs;

public class CrearServicioDto
{
    public int IdPrestador { get; set; }

    public int IdCategoria { get; set; }

    public int IdUbicacion { get; set; }

    public string Nombre { get; set; } = string.Empty;

    public string? Descripcion { get; set; }

    public decimal Precio { get; set; }

    public string? Moneda { get; set; }

    public string UnidadCobro { get; set; } = string.Empty;

    public string? DuracionEstimada { get; set; }

    public int? Capacidad { get; set; }

    public int? AforoMaximo { get; set; }

    public bool? RequiereReserva { get; set; }

    public bool? CancelacionGratuita { get; set; }

    public int? EdadMinima { get; set; }

    public string? Incluye { get; set; }

    public string? NoIncluye { get; set; }

    public string? Politicas { get; set; }
}