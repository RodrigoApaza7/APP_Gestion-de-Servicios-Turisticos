using TravelHub.API.Models;
using TravelHub.API.Modules.Usuarios.DTOs;
using TravelHub.API.Modules.Usuarios.Interfaces;
using TravelHub.API.Common.Security;

namespace TravelHub.API.Modules.Usuarios.Services;

public class UsuarioService : IUsuarioService
{
    private readonly IUsuarioRepository _repository;

    public UsuarioService(IUsuarioRepository repository)
    {
        _repository = repository;
    }

    public async Task<IEnumerable<UsuarioRespuestaDto>> ObtenerTodosAsync()
    {
        var usuarios = await _repository.ObtenerTodosAsync();

        return usuarios.Select(u => new UsuarioRespuestaDto
        {
            IdUsuario = u.id_usuario,
            IdRol = u.id_rol,
            Nombre = u.nombre,
            Apellido = u.apellido,
            Correo = u.correo,
            Telefono = u.telefono,
            FotoPerfil = u.foto_perfil,
            FechaNacimiento = u.fecha_nacimiento,
            Nacionalidad = u.nacionalidad,
            Ciudad = u.ciudad,
            Idioma = u.idioma,
            Activo = u.activo,
            CorreoVerificado = u.correo_verificado,
            FechaCreacion = u.fecha_creacion
        });
    }

    public async Task<UsuarioRespuestaDto?> ObtenerPorIdAsync(int id)
    {
        var u = await _repository.ObtenerPorIdAsync(id);

        if (u == null)
            return null;

        return new UsuarioRespuestaDto
        {
            IdUsuario = u.id_usuario,
            IdRol = u.id_rol,
            Nombre = u.nombre,
            Apellido = u.apellido,
            Correo = u.correo,
            Telefono = u.telefono,
            FotoPerfil = u.foto_perfil,
            FechaNacimiento = u.fecha_nacimiento,
            Nacionalidad = u.nacionalidad,
            Ciudad = u.ciudad,
            Idioma = u.idioma,
            Activo = u.activo,
            CorreoVerificado = u.correo_verificado,
            FechaCreacion = u.fecha_creacion
        };
    }

    public async Task<UsuarioRespuestaDto> CrearAsync(CrearUsuarioDto dto)
    {
        var existe = await _repository.ObtenerPorCorreoAsync(dto.Correo);

        if (existe != null)
            throw new Exception("El correo ya está registrado.");

        var usuario = new usuario
        {
            nombre = dto.Nombre,
            apellido = dto.Apellido,
            correo = dto.Correo,

            // Más adelante aquí irá BCrypt
            password_hash = PasswordHasher.Hash(dto.Password),

            telefono = dto.Telefono,
            foto_perfil = null,

            fecha_nacimiento = dto.FechaNacimiento,
            nacionalidad = dto.Nacionalidad,
            ciudad = dto.Ciudad,
            idioma = dto.Idioma,

            id_rol = 2,

            activo = true,

            correo_verificado = false,

            fecha_creacion = DateTime.Now
        };

        await _repository.CrearAsync(usuario);

        await _repository.GuardarCambiosAsync();

        return new UsuarioRespuestaDto
        {
            IdUsuario = usuario.id_usuario,
            IdRol = usuario.id_rol,
            Nombre = usuario.nombre,
            Apellido = usuario.apellido,
            Correo = usuario.correo,
            Telefono = usuario.telefono,
            FotoPerfil = usuario.foto_perfil,
            FechaNacimiento = usuario.fecha_nacimiento,
            Nacionalidad = usuario.nacionalidad,
            Ciudad = usuario.ciudad,
            Idioma = usuario.idioma,
            Activo = usuario.activo,
            CorreoVerificado = usuario.correo_verificado,
            FechaCreacion = usuario.fecha_creacion
        };
    }

    public async Task<bool> ActualizarAsync(int id, ActualizarUsuarioDto dto)
    {
        var usuario = await _repository.ObtenerPorIdAsync(id);

        if (usuario == null)
            return false;

        usuario.nombre = dto.Nombre;
        usuario.apellido = dto.Apellido;
        usuario.telefono = dto.Telefono;
        usuario.foto_perfil = dto.FotoPerfil;
        usuario.fecha_nacimiento = dto.FechaNacimiento;
        usuario.nacionalidad = dto.Nacionalidad;
        usuario.ciudad = dto.Ciudad;
        usuario.idioma = dto.Idioma;
        usuario.activo = dto.Activo;

        usuario.fecha_actualizacion = DateTime.Now;

        await _repository.ActualizarAsync(usuario);
        await _repository.GuardarCambiosAsync();

        return true;
    }

    public async Task<bool> EliminarAsync(int id)
    {
        var usuario = await _repository.ObtenerPorIdAsync(id);

        if (usuario == null)
            return false;

        await _repository.EliminarAsync(usuario);

        await _repository.GuardarCambiosAsync();

        return true;
    }
}