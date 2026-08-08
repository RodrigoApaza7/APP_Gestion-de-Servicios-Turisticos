using Microsoft.EntityFrameworkCore;
using TravelHub.API.Data;
using TravelHub.API.Models;
using TravelHub.API.Modules.Itinerarios.Interfaces;

namespace TravelHub.API.Modules.Itinerarios.Repositories;

public class DetalleItinerarioRepository : IDetalleItinerarioRepository
{
    private readonly TravelHubContext _context;

    public DetalleItinerarioRepository(TravelHubContext context)
    {
        _context = context;
    }

    public async Task<List<detalle_itinerario>> ObtenerPorItinerarioAsync(
        int idItinerario)
    {
        return await _context.detalle_itinerarios
            .Where(d => d.id_itinerario == idItinerario)
            .OrderBy(d => d.orden)
            .ThenBy(d => d.fecha)
            .ThenBy(d => d.hora)
            .ToListAsync();
    }

    public async Task<detalle_itinerario?> ObtenerPorIdAsync(
        int idDetalle)
    {
        return await _context.detalle_itinerarios
            .FirstOrDefaultAsync(
                d => d.id_detalle == idDetalle
            );
    }

    public async Task<detalle_itinerario> CrearAsync(
        detalle_itinerario detalle)
    {
        _context.detalle_itinerarios.Add(detalle);

        await _context.SaveChangesAsync();

        return detalle;
    }

    public async Task ActualizarAsync(
        detalle_itinerario detalle)
    {
        _context.detalle_itinerarios.Update(detalle);

        await _context.SaveChangesAsync();
    }

    public async Task EliminarAsync(
        detalle_itinerario detalle)
    {
        _context.detalle_itinerarios.Remove(detalle);

        await _context.SaveChangesAsync();
    }
}