namespace TravelHub.API.Modules.Usuarios.DTOs;

public class CrearUsuarioDto
{
    public string Nombre { get; set; } = string.Empty;

    public string Apellido { get; set; } = string.Empty;

    public string Correo { get; set; } = string.Empty;

    public string Password { get; set; } = string.Empty;

    public string? Telefono { get; set; }

    public DateOnly? FechaNacimiento { get; set; }

    public string? Nacionalidad { get; set; }

    public string? Ciudad { get; set; }

    public string? Idioma { get; set; }
}