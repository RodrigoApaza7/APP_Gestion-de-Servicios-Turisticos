using TravelHub.API.Modules.Auth.DTOs;

namespace TravelHub.API.Modules.Auth.Interfaces;

public interface IAuthService
{
    Task<LoginRespuestaDto?> LoginAsync(LoginDto dto);
}