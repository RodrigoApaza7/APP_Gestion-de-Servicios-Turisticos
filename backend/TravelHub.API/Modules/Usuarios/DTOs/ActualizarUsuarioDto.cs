namespace TravelHub.API.Modules.Usuarios.DTOs;

public class ActualizarUsuarioDto
{
    public string Nombre { get; set; } = string.Empty;

    public string Apellido { get; set; } = string.Empty;

    public string? Telefono { get; set; }

    public string? FotoPerfil { get; set; }

    public DateOnly? FechaNacimiento { get; set; }

    public string? Nacionalidad { get; set; }

    public string? Ciudad { get; set; }

    public string? Idioma { get; set; }

    public bool Activo { get; set; }
}