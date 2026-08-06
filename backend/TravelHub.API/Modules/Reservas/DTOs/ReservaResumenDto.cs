namespace TravelHub.API.Modules.Reservas.DTOs;

public class ReservaResumenDto
{
    public int IdReserva { get; set; }

    public string CodigoReserva { get; set; } = string.Empty;

    public int IdUsuario { get; set; }

    public int IdServicio { get; set; }

    public decimal PrecioTotal { get; set; }

    public string? Estado { get; set; }
}