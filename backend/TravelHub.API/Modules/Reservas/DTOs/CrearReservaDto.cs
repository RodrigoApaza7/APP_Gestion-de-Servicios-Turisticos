namespace TravelHub.API.Modules.Reservas.DTOs;

public class CrearReservaDto
{
    public string CodigoReserva { get; set; } = string.Empty;

    public int IdUsuario { get; set; }

    public int IdServicio { get; set; }

    public DateOnly FechaReserva { get; set; }

    public TimeOnly? HoraReserva { get; set; }

    public int CantidadPersonas { get; set; }

    public decimal PrecioUnitario { get; set; }

    public decimal PrecioTotal { get; set; }

    public string? MetodoPago { get; set; }

    public string? EstadoPago { get; set; }

    public string? ComprobantePago { get; set; }

    public string? Estado { get; set; }

    public string? Observaciones { get; set; }
}