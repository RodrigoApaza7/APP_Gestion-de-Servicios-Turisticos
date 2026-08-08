using Microsoft.EntityFrameworkCore;
using TravelHub.API.Data;
using TravelHub.API.Models;
using TravelHub.API.Modules.Servicios.Interfaces;

namespace TravelHub.API.Modules.Servicios.Repositories;

public class ServicioRepository : IServicioRepository
{
    private readonly TravelHubContext _context;

    public ServicioRepository(TravelHubContext context)
    {
        _context = context;
    }

    public async Task<IEnumerable<servicio>> ObtenerTodosAsync()
    {
        return await _context.servicios.ToListAsync();
    }

    public async Task<servicio?> ObtenerPorIdAsync(int id)
    {
        return await _context.servicios.FindAsync(id);
    }

    public async Task CrearAsync(servicio servicio)
    {
        await _context.servicios.AddAsync(servicio);
    }

    public Task ActualizarAsync(servicio servicio)
    {
        _context.servicios.Update(servicio);
        return Task.CompletedTask;
    }

    public Task EliminarAsync(servicio servicio)
    {
        _context.servicios.Remove(servicio);
        return Task.CompletedTask;
    }

    public async Task GuardarCambiosAsync()
    {
        await _context.SaveChangesAsync();
    }
}