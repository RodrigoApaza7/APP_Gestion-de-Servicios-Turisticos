using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using TravelHub.API.Common.Responses;
using TravelHub.API.Modules.Servicios.DTOs;
using TravelHub.API.Modules.Servicios.Interfaces;

namespace TravelHub.API.Modules.Servicios.Controllers;

[ApiController]
[Route("api/[controller]")]
[Authorize]
public class ServiciosController : ControllerBase
{
    private readonly IServicioService _service;

    public ServiciosController(IServicioService service)
    {
        _service = service;
    }

    [HttpGet]
    public async Task<IActionResult> ObtenerTodos()
    {
        var servicios = await _service.ObtenerTodosAsync();

        return Ok(ApiResponse<IEnumerable<object>>.Ok(
            servicios.Cast<object>(),
            "Servicios obtenidos correctamente."
        ));
    }

    [HttpGet("{id}")]
    public async Task<IActionResult> ObtenerPorId(int id)
    {
        var servicio = await _service.ObtenerPorIdAsync(id);

        if (servicio == null)
            return NotFound(ApiResponse<object>.Fail("Servicio no encontrado."));

        return Ok(ApiResponse<ServicioRespuestaDto>.Ok(servicio));
    }

    [HttpPost]
    public async Task<IActionResult> Crear(CrearServicioDto dto)
    {
        var servicio = await _service.CrearAsync(dto);

        return Created(
            $"api/Servicios/{servicio.IdServicio}",
            ApiResponse<ServicioRespuestaDto>.Created(
                servicio,
                "Servicio creado correctamente."
            )
        );
    }

    [HttpPut("{id}")]
    public async Task<IActionResult> Actualizar(int id, ActualizarServicioDto dto)
    {
        var actualizado = await _service.ActualizarAsync(id, dto);

        if (!actualizado)
            return NotFound(ApiResponse<object>.Fail("Servicio no encontrado."));

        return Ok(ApiResponse<object>.Ok(null, "Servicio actualizado correctamente."));
    }

    [HttpDelete("{id}")]
    public async Task<IActionResult> Eliminar(int id)
    {
        var eliminado = await _service.EliminarAsync(id);

        if (!eliminado)
            return NotFound(ApiResponse<object>.Fail("Servicio no encontrado."));

        return Ok(ApiResponse<object>.Ok(null, "Servicio eliminado correctamente."));
    }
}