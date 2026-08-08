using Microsoft.EntityFrameworkCore;
using TravelHub.API.Data;
using TravelHub.API.Models;
using TravelHub.API.Modules.Reservas.Interfaces;

namespace TravelHub.API.Modules.Reservas.Repositories;

public class ReservaRepository : IReservaRepository
{
    private readonly TravelHubContext _context;

    public ReservaRepository(TravelHubContext context)
    {
        _context = context;
    }

    public async Task<IEnumerable<reserva>> ObtenerTodosAsync()
    {
        return await _context.reservas.ToListAsync();
    }

    public async Task<reserva?> ObtenerPorIdAsync(int id)
    {
        return await _context.reservas.FindAsync(id);
    }

    public async Task CrearAsync(reserva reserva)
    {
        await _context.reservas.AddAsync(reserva);
    }

    public Task ActualizarAsync(reserva reserva)
    {
        _context.reservas.Update(reserva);
        return Task.CompletedTask;
    }

    public Task EliminarAsync(reserva reserva)
    {
        _context.reservas.Remove(reserva);
        return Task.CompletedTask;
    }

    public async Task GuardarCambiosAsync()
    {
        await _context.SaveChangesAsync();
    }
}
