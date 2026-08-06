using TravelHub.API.Models;
using TravelHub.API.Modules.Servicios.DTOs;
using TravelHub.API.Modules.Servicios.Interfaces;

namespace TravelHub.API.Modules.Servicios.Services;

public class ServicioService : IServicioService
{
    private readonly IServicioRepository _repository;

    public ServicioService(IServicioRepository repository)
    {
        _repository = repository;
    }

    public async Task<IEnumerable<ServicioRespuestaDto>> ObtenerTodosAsync()
    {
        var servicios = await _repository.ObtenerTodosAsync();

        return servicios.Select(s => new ServicioRespuestaDto
        {
            IdServicio = s.id_servicio,
            IdPrestador = s.id_prestador,
            IdCategoria = s.id_categoria,
            IdUbicacion = s.id_ubicacion,
            Nombre = s.nombre,
            Descripcion = s.descripcion,
            Precio = s.precio,
            Moneda = s.moneda,
            UnidadCobro = s.unidad_cobro,
            DuracionEstimada = s.duracion_estimada,
            Activo = s.activo,
            Destacado = s.destacado,
            CalificacionPromedio = s.calificacion_promedio
        });
    }

    public async Task<ServicioRespuestaDto?> ObtenerPorIdAsync(int id)
    {
        var s = await _repository.ObtenerPorIdAsync(id);

        if (s == null)
            return null;

        return new ServicioRespuestaDto
        {
            IdServicio = s.id_servicio,
            IdPrestador = s.id_prestador,
            IdCategoria = s.id_categoria,
            IdUbicacion = s.id_ubicacion,
            Nombre = s.nombre,
            Descripcion = s.descripcion,
            Precio = s.precio,
            Moneda = s.moneda,
            UnidadCobro = s.unidad_cobro,
            DuracionEstimada = s.duracion_estimada,
            Activo = s.activo,
            Destacado = s.destacado,
            CalificacionPromedio = s.calificacion_promedio
        };
    }

    public async Task<ServicioRespuestaDto> CrearAsync(CrearServicioDto dto)
    {
        var servicio = new servicio
        {
            id_prestador = dto.IdPrestador,
            id_categoria = dto.IdCategoria,
            id_ubicacion = dto.IdUbicacion,

            nombre = dto.Nombre,
            descripcion = dto.Descripcion,
            precio = dto.Precio,
            moneda = dto.Moneda,
            unidad_cobro = dto.UnidadCobro,
            duracion_estimada = dto.DuracionEstimada,

            capacidad = dto.Capacidad,
            aforo_maximo = dto.AforoMaximo,
            requiere_reserva = dto.RequiereReserva,
            cancelacion_gratuita = dto.CancelacionGratuita,
            edad_minima = dto.EdadMinima,

            incluye = dto.Incluye,
            no_incluye = dto.NoIncluye,
            politicas = dto.Politicas,

            estado = "ACTIVO",
            activo = true,
            destacado = false,
            calificacion_promedio = 0,

            fecha_creacion = DateTime.Now
        };

        await _repository.CrearAsync(servicio);
        await _repository.GuardarCambiosAsync();

        return new ServicioRespuestaDto
        {
            IdServicio = servicio.id_servicio,
            IdPrestador = servicio.id_prestador,
            IdCategoria = servicio.id_categoria,
            IdUbicacion = servicio.id_ubicacion,
            Nombre = servicio.nombre,
            Descripcion = servicio.descripcion,
            Precio = servicio.precio,
            Moneda = servicio.moneda,
            UnidadCobro = servicio.unidad_cobro,
            DuracionEstimada = servicio.duracion_estimada,
            Activo = servicio.activo,
            Destacado = servicio.destacado,
            CalificacionPromedio = servicio.calificacion_promedio
        };
    }

    public async Task<bool> ActualizarAsync(int id, ActualizarServicioDto dto)
    {
        var servicio = await _repository.ObtenerPorIdAsync(id);

        if (servicio == null)
            return false;

        servicio.id_categoria = dto.IdCategoria;
        servicio.id_prestador = dto.IdPrestador;
        servicio.id_ubicacion = dto.IdUbicacion;

        servicio.nombre = dto.Nombre;
        servicio.descripcion = dto.Descripcion;
        servicio.precio = dto.Precio;
        servicio.moneda = dto.Moneda;
        servicio.unidad_cobro = dto.UnidadCobro;
        servicio.duracion_estimada = dto.DuracionEstimada;

        servicio.capacidad = dto.Capacidad;
        servicio.aforo_maximo = dto.AforoMaximo;
        servicio.requiere_reserva = dto.RequiereReserva;
        servicio.cancelacion_gratuita = dto.CancelacionGratuita;
        servicio.edad_minima = dto.EdadMinima;

        servicio.incluye = dto.Incluye;
        servicio.no_incluye = dto.NoIncluye;
        servicio.politicas = dto.Politicas;

        servicio.activo = dto.Activo;
        servicio.destacado = dto.Destacado;
        servicio.fecha_actualizacion = DateTime.Now;

        await _repository.ActualizarAsync(servicio);
        await _repository.GuardarCambiosAsync();

        return true;
    }

    public async Task<bool> EliminarAsync(int id)
    {
        var servicio = await _repository.ObtenerPorIdAsync(id);

        if (servicio == null)
            return false;

        await _repository.EliminarAsync(servicio);
        await _repository.GuardarCambiosAsync();

        return true;
    }
    
}