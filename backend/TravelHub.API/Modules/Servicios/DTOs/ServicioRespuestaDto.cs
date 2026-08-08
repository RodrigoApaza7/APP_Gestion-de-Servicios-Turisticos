namespace TravelHub.API.Modules.Servicios.DTOs;

public class ServicioRespuestaDto
{
    public int IdServicio { get; set; }

    public int IdPrestador { get; set; }

    public int IdCategoria { get; set; }

    public int IdUbicacion { get; set; }

    public string Nombre { get; set; } = string.Empty;

    public string? Descripcion { get; set; }

    public decimal Precio { get; set; }

    public string? Moneda { get; set; }

    public string UnidadCobro { get; set; } = string.Empty;

    public string? DuracionEstimada { get; set; }

    public bool? Activo { get; set; }

    public bool? Destacado { get; set; }

    public decimal? CalificacionPromedio { get; set; }
}