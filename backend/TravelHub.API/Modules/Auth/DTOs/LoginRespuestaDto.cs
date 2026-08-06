namespace TravelHub.API.Modules.Auth.DTOs;

public class LoginRespuestaDto
{
    public int IdUsuario { get; set; }

    public string Nombre { get; set; } = string.Empty;

    public string Correo { get; set; } = string.Empty;

    public int IdRol { get; set; }

    public string Token { get; set; } = string.Empty;
}