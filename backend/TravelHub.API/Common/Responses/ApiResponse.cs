namespace TravelHub.API.Common.Responses;

/// <summary>
/// Respuesta estándar para toda la API.
/// </summary>
public class ApiResponse<T>
{
    public bool Success { get; init; }

    public string Message { get; init; } = string.Empty;

    public T? Data { get; init; }

    public List<string> Errors { get; init; } = new();

    public DateTime Timestamp { get; init; } = DateTime.UtcNow;

    private ApiResponse() { }

    public static ApiResponse<T> Ok(T? data, string message = "Operación realizada correctamente.")
    {
        return new ApiResponse<T>
        {
            Success = true,
            Message = message,
            Data = data
        };
    }

    public static ApiResponse<T> Created(T? data, string message = "Recurso creado correctamente.")
    {
        return new ApiResponse<T>
        {
            Success = true,
            Message = message,
            Data = data
        };
    }

    public static ApiResponse<T> Fail(string message, List<string>? errors = null)
    {
        return new ApiResponse<T>
        {
            Success = false,
            Message = message,
            Errors = errors ?? new List<string>()
        };
    }
}