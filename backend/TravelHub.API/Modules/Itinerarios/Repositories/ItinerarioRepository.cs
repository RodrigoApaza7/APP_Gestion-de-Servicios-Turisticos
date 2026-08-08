using Microsoft.EntityFrameworkCore;
using TravelHub.API.Data;
using TravelHub.API.Models;
using TravelHub.API.Modules.Itinerarios.Interfaces;

namespace TravelHub.API.Modules.Itinerarios.Repositories;

public class ItinerarioRepository : IItinerarioRepository
{
    private readonly TravelHubContext _context;

    public ItinerarioRepository(TravelHubContext context)
    {
        _context = context;
    }

    public async Task<List<itinerario>> ObtenerPorUsuarioAsync(int idUsuario)
    {
        return await _context.itinerarios
            .Where(i => i.id_usuario == idUsuario)
            .OrderByDescending(i => i.fecha_creacion)
            .ToListAsync();
    }

    public async Task<itinerario?> ObtenerPorIdAsync(int idItinerario)
    {
        return await _context.itinerarios
            .FirstOrDefaultAsync(i => i.id_itinerario == idItinerario);
    }

    public async Task<itinerario> CrearAsync(itinerario itinerario)
    {
        _context.itinerarios.Add(itinerario);
        await _context.SaveChangesAsync();

        return itinerario;
    }

    public async Task ActualizarAsync(itinerario itinerario)
    {
        _context.itinerarios.Update(itinerario);
        await _context.SaveChangesAsync();
    }

    public async Task EliminarAsync(itinerario itinerario)
    {
        _context.itinerarios.Remove(itinerario);
        await _context.SaveChangesAsync();
    }
}