using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using TravelHub.API.Common.Responses;
using TravelHub.API.Modules.Reservas.DTOs;
using TravelHub.API.Modules.Reservas.Interfaces;

namespace TravelHub.API.Modules.Reservas.Controllers;

[ApiController]
[Route("api/[controller]")]
[Authorize]
public class ReservasController : ControllerBase
{
    private readonly IReservaService _service;

    public ReservasController(IReservaService service)
    {
        _service = service;
    }

    [HttpGet]
    public async Task<IActionResult> ObtenerTodos()
    {
        var reservas = await _service.ObtenerTodosAsync();

        return Ok(
            ApiResponse<IEnumerable<object>>.Ok(
                reservas.Cast<object>(),
                "Reservas obtenidas correctamente."
            )
        );
    }

    [HttpGet("{id}")]
    public async Task<IActionResult> ObtenerPorId(int id)
    {
        var reserva = await _service.ObtenerPorIdAsync(id);

        if (reserva == null)
            return NotFound(ApiResponse<object>.Fail("Reserva no encontrada."));

        return Ok(ApiResponse<ReservaRespuestaDto>.Ok(reserva));
    }

    [HttpPost]
    public async Task<IActionResult> Crear(CrearReservaDto dto)
    {
        var reserva = await _service.CrearAsync(dto);

        return Created(
            $"api/Reservas/{reserva.IdReserva}",
            ApiResponse<ReservaRespuestaDto>.Created(
                reserva,
                "Reserva creada correctamente."
            )
        );
    }

    [HttpPut("{id}")]
    public async Task<IActionResult> Actualizar(int id, ActualizarReservaDto dto)
    {
        var actualizado = await _service.ActualizarAsync(id, dto);

        if (!actualizado)
            return NotFound(ApiResponse<object>.Fail("Reserva no encontrada."));

        return Ok(ApiResponse<object>.Ok(null, "Reserva actualizada correctamente."));
    }

    [HttpDelete("{id}")]
    public async Task<IActionResult> Eliminar(int id)
    {
        var eliminado = await _service.EliminarAsync(id);

        if (!eliminado)
            return NotFound(ApiResponse<object>.Fail("Reserva no encontrada."));

        return Ok(ApiResponse<object>.Ok(null, "Reserva eliminada correctamente."));
    }
}