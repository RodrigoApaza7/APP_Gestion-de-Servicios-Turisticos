using Microsoft.AspNetCore.Mvc;
using TravelHub.API.Common.Responses;
using TravelHub.API.Modules.Auth.DTOs;
using TravelHub.API.Modules.Auth.Interfaces;

namespace TravelHub.API.Modules.Auth.Controllers;

[ApiController]
[Route("api/[controller]")]
public class AuthController : ControllerBase
{
    private readonly IAuthService _service;

    public AuthController(IAuthService service)
    {
        _service = service;
    }

    [HttpPost("login")]
    public async Task<IActionResult> Login([FromBody] LoginDto dto)
    {
        var usuario = await _service.LoginAsync(dto);

        if (usuario == null)
        {
            return Unauthorized(
                ApiResponse<string>.Fail(
                    "Correo o contraseña incorrectos."
                )
            );
        }

        return Ok(
            ApiResponse<LoginRespuestaDto>.Ok(
                usuario,
                "Inicio de sesión correcto."
            )
        );
    }
}