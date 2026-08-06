using Microsoft.EntityFrameworkCore;
using TravelHub.API.Data;
using TravelHub.API.Models;
using TravelHub.API.Modules.Usuarios.Interfaces;

namespace TravelHub.API.Modules.Usuarios.Repositories;

public class UsuarioRepository : IUsuarioRepository
{
    private readonly TravelHubContext _context;

    public UsuarioRepository(TravelHubContext context)
    {
        _context = context;
    }

    public async Task<IEnumerable<usuario>> ObtenerTodosAsync()
    {
        return await _context.usuarios
            .AsNoTracking()
            .OrderBy(u => u.nombre)
            .ToListAsync();
    }

    public async Task<usuario?> ObtenerPorIdAsync(int id)
    {
        return await _context.usuarios
            .FirstOrDefaultAsync(u => u.id_usuario == id);
    }

    public async Task<usuario?> ObtenerPorCorreoAsync(string correo)
    {
        return await _context.usuarios
            .FirstOrDefaultAsync(u => u.correo == correo);
    }

    public async Task CrearAsync(usuario usuario)
    {
        await _context.usuarios.AddAsync(usuario);
    }

    public Task ActualizarAsync(usuario usuario)
    {
        _context.usuarios.Update(usuario);
        return Task.CompletedTask;
    }

    public Task EliminarAsync(usuario usuario)
    {
        _context.usuarios.Remove(usuario);
        return Task.CompletedTask;
    }

    public async Task GuardarCambiosAsync()
    {
        await _context.SaveChangesAsync();
    }
}