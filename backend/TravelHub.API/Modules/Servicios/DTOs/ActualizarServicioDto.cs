namespace TravelHub.API.Modules.Servicios.DTOs;

public class ActualizarServicioDto : CrearServicioDto
{
    public bool Activo { get; set; }

    public bool Destacado { get; set; }
}