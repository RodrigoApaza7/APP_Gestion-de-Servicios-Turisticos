using Microsoft.AspNetCore.Mvc;
using TravelHub.API.Common.Responses;
using TravelHub.API.Modules.Usuarios.DTOs;
using TravelHub.API.Modules.Usuarios.Interfaces;

namespace TravelHub.API.Modules.Usuarios.Controllers;

[ApiController]
[Route("api/[controller]")]
public class UsuariosController : ControllerBase
{
    private readonly IUsuarioService _service;

    public UsuariosController(IUsuarioService service)
    {
        _service = service;
    }

    [HttpGet]
    public async Task<IActionResult> ObtenerTodos()
    {
        var usuarios = await _service.ObtenerTodosAsync();

        return Ok(
            ApiResponse<IEnumerable<UsuarioRespuestaDto>>.Ok(
                usuarios,
                "Usuarios obtenidos correctamente."
            )
        );
    }

    [HttpPost]
    public async Task<IActionResult> Crear([FromBody] CrearUsuarioDto dto)
    {
        var usuario = await _service.CrearAsync(dto);

        return Created(
            $"api/usuarios/{usuario.IdUsuario}",
            ApiResponse<UsuarioRespuestaDto>.Created(
                usuario,
                "Usuario creado correctamente."
            )
        );
    }

    [HttpGet("{id:int}")]
    public async Task<IActionResult> ObtenerPorId(int id)
    {
        var usuario = await _service.ObtenerPorIdAsync(id);

        if (usuario == null)
        {
            return NotFound(
                ApiResponse<UsuarioRespuestaDto>.Fail(
                    "Usuario no encontrado."
                )
            );
        }

        return Ok(
            ApiResponse<UsuarioRespuestaDto>.Ok(
                usuario,
                "Usuario encontrado."
            )
        );
    }
}