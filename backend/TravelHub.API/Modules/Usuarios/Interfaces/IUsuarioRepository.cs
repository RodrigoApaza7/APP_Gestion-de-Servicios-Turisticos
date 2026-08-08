///Cualquier repositorio de usuarios debe ser capaz de hacer estas operaciones.
using TravelHub.API.Models;

namespace TravelHub.API.Modules.Usuarios.Interfaces;

public interface IUsuarioRepository
{
    Task<IEnumerable<usuario>> ObtenerTodosAsync();

    Task<usuario?> ObtenerPorIdAsync(int id);

    Task<usuario?> ObtenerPorCorreoAsync(string correo);

    Task CrearAsync(usuario usuario);

    Task ActualizarAsync(usuario usuario);

    Task EliminarAsync(usuario usuario);

    Task GuardarCambiosAsync();
}