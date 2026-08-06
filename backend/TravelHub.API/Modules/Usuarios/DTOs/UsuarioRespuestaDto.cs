namespace TravelHub.API.Modules.Usuarios.DTOs;

public class UsuarioRespuestaDto
{
    public int IdUsuario { get; set; }

    public int IdRol { get; set; }

    public string Nombre { get; set; } = string.Empty;

    public string Apellido { get; set; } = string.Empty;

    public string Correo { get; set; } = string.Empty;

    public string? Telefono { get; set; }

    public string? FotoPerfil { get; set; }

    public DateOnly? FechaNacimiento { get; set; }

    public string? Nacionalidad { get; set; }

    public string? Ciudad { get; set; }

    public string? Idioma { get; set; }

    public bool Activo { get; set; }

    public bool CorreoVerificado { get; set; }

    public DateTime FechaCreacion { get; set; }
}