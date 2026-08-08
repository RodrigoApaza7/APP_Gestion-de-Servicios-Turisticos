using Microsoft.AspNetCore.Mvc;
using TravelHub.API.Modules.Itinerarios.DTOs;
using TravelHub.API.Modules.Itinerarios.Interfaces;

namespace TravelHub.API.Modules.Itinerarios.Controllers;

[ApiController]
[Route("api/[controller]")]
public class ItinerariosController : ControllerBase
{
    private readonly IItinerarioService _service;
    private readonly IDetalleItinerarioService _detalleService;

    public ItinerariosController(
        IItinerarioService service,
        IDetalleItinerarioService detalleService)
    {
        _service = service;
        _detalleService = detalleService;
    }

    // GET: api/Itinerarios/usuario/2
    [HttpGet("usuario/{idUsuario}")]
    public async Task<ActionResult<List<ItinerarioRespuestaDto>>> ObtenerPorUsuario(
        int idUsuario)
    {
        var itinerarios = await _service.ObtenerPorUsuarioAsync(idUsuario);

        return Ok(itinerarios);
    }

    // GET: api/Itinerarios/1
    [HttpGet("{idItinerario}")]
    public async Task<ActionResult<ItinerarioRespuestaDto>> ObtenerPorId(
        int idItinerario)
    {
        var itinerario = await _service.ObtenerPorIdAsync(idItinerario);

        if (itinerario == null)
            return NotFound(new
            {
                mensaje = "Itinerario no encontrado"
            });

        return Ok(itinerario);
    }

    // POST: api/Itinerarios
    [HttpPost]
    public async Task<ActionResult<ItinerarioRespuestaDto>> Crear(
        [FromBody] CrearItinerarioDto dto)
    {
        var itinerario = await _service.CrearAsync(dto);

        return CreatedAtAction(
            nameof(ObtenerPorId),
            new { idItinerario = itinerario.IdItinerario },
            itinerario
        );
    }

    // PUT: api/Itinerarios/1
    [HttpPut("{idItinerario}")]
    public async Task<IActionResult> Actualizar(
        int idItinerario,
        [FromBody] ActualizarItinerarioDto dto)
    {
        var actualizado = await _service.ActualizarAsync(
            idItinerario,
            dto
        );

        if (!actualizado)
            return NotFound(new
            {
                mensaje = "Itinerario no encontrado"
            });

        return NoContent();
    }

    // DELETE: api/Itinerarios/1
    [HttpDelete("{idItinerario}")]
    public async Task<IActionResult> Eliminar(int idItinerario)
    {
        var eliminado = await _service.EliminarAsync(idItinerario);

        if (!eliminado)
            return NotFound(new
            {
                mensaje = "Itinerario no encontrado"
            });

        return NoContent();
    }

        // GET: api/Itinerarios/1/detalles
    [HttpGet("{idItinerario}/detalles")]
    public async Task<ActionResult<List<DetalleItinerarioRespuestaDto>>> ObtenerDetalles(
        int idItinerario)
    {
        var detalles = await _detalleService.ObtenerPorItinerarioAsync(
            idItinerario
        );

        return Ok(detalles);
    }

    // GET: api/Itinerarios/detalles/1
    [HttpGet("detalles/{idDetalle}")]
    public async Task<ActionResult<DetalleItinerarioRespuestaDto>> ObtenerDetalle(
        int idDetalle)
    {
        var detalle = await _detalleService.ObtenerPorIdAsync(
            idDetalle
        );

        if (detalle == null)
        {
            return NotFound(new
            {
                mensaje = "Detalle de itinerario no encontrado"
            });
        }

        return Ok(detalle);
    }

    // POST: api/Itinerarios/1/detalles
    [HttpPost("{idItinerario}/detalles")]
    public async Task<ActionResult<DetalleItinerarioRespuestaDto>> CrearDetalle(
        int idItinerario,
        [FromBody] CrearDetalleItinerarioDto dto)
    {
        var itinerario = await _service.ObtenerPorIdAsync(idItinerario);

        if (itinerario == null)
        {
            return NotFound(new
            {
                mensaje = "Itinerario no encontrado"
            });
        }

        var detalle = await _detalleService.CrearAsync(
            idItinerario,
            dto
        );

        return Ok(detalle);
    }

    // PUT: api/Itinerarios/detalles/1
    [HttpPut("detalles/{idDetalle}")]
    public async Task<IActionResult> ActualizarDetalle(
        int idDetalle,
        [FromBody] ActualizarDetalleItinerarioDto dto)
    {
        var actualizado = await _detalleService.ActualizarAsync(
            idDetalle,
            dto
        );

        if (!actualizado)
        {
            return NotFound(new
            {
                mensaje = "Detalle de itinerario no encontrado"
            });
        }

        return NoContent();
    }

    // DELETE: api/Itinerarios/detalles/1
    [HttpDelete("detalles/{idDetalle}")]
    public async Task<IActionResult> EliminarDetalle(
        int idDetalle)
    {
        var eliminado = await _detalleService.EliminarAsync(
            idDetalle
        );

        if (!eliminado)
        {
            return NotFound(new
            {
                mensaje = "Detalle de itinerario no encontrado"
            });
        }

        return NoContent();
    }
}