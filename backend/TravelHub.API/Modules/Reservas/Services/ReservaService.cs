using TravelHub.API.Models;
using TravelHub.API.Modules.Reservas.DTOs;
using TravelHub.API.Modules.Reservas.Interfaces;

namespace TravelHub.API.Modules.Reservas.Services;

public class ReservaService : IReservaService
{
    private readonly IReservaRepository _repository;

    public ReservaService(IReservaRepository repository)
    {
        _repository = repository;
    }

    public async Task<IEnumerable<ReservaRespuestaDto>> ObtenerTodosAsync()
    {
        var reservas = await _repository.ObtenerTodosAsync();

        return reservas.Select(r => new ReservaRespuestaDto
        {
            IdReserva = r.id_reserva,
            CodigoReserva = r.codigo_reserva,
            IdUsuario = r.id_usuario,
            IdServicio = r.id_servicio,
            FechaReserva = r.fecha_reserva,
            HoraReserva = r.hora_reserva,
            CantidadPersonas = r.cantidad_personas,
            PrecioUnitario = r.precio_unitario,
            PrecioTotal = r.precio_total,
            MetodoPago = r.metodo_pago,
            EstadoPago = r.estado_pago,
            ComprobantePago = r.comprobante_pago,
            Estado = r.estado,
            Observaciones = r.observaciones,
            FechaCreacion = r.fecha_creacion,
            FechaActualizacion = r.fecha_actualizacion
        });
    }

    public async Task<ReservaRespuestaDto?> ObtenerPorIdAsync(int id)
    {
        var r = await _repository.ObtenerPorIdAsync(id);

        if (r == null)
            return null;

        return new ReservaRespuestaDto
        {
            IdReserva = r.id_reserva,
            CodigoReserva = r.codigo_reserva,
            IdUsuario = r.id_usuario,
            IdServicio = r.id_servicio,
            FechaReserva = r.fecha_reserva,
            HoraReserva = r.hora_reserva,
            CantidadPersonas = r.cantidad_personas,
            PrecioUnitario = r.precio_unitario,
            PrecioTotal = r.precio_total,
            MetodoPago = r.metodo_pago,
            EstadoPago = r.estado_pago,
            ComprobantePago = r.comprobante_pago,
            Estado = r.estado,
            Observaciones = r.observaciones,
            FechaCreacion = r.fecha_creacion,
            FechaActualizacion = r.fecha_actualizacion
        };
    }

    public async Task<ReservaRespuestaDto> CrearAsync(CrearReservaDto dto)
    {
        var reserva = new reserva
        {
            codigo_reserva = dto.CodigoReserva,
            id_usuario = dto.IdUsuario,
            id_servicio = dto.IdServicio,
            fecha_reserva = dto.FechaReserva,
            hora_reserva = dto.HoraReserva,
            cantidad_personas = dto.CantidadPersonas,
            precio_unitario = dto.PrecioUnitario,
            precio_total = dto.PrecioTotal,
            metodo_pago = dto.MetodoPago,
            estado_pago = dto.EstadoPago,
            comprobante_pago = dto.ComprobantePago,
            estado = dto.Estado ?? "PENDIENTE",
            observaciones = dto.Observaciones,
            fecha_creacion = DateTime.Now
        };

        await _repository.CrearAsync(reserva);
        await _repository.GuardarCambiosAsync();

        return await ObtenerPorIdAsync(reserva.id_reserva)
               ?? throw new Exception("No se pudo recuperar la reserva creada.");
    }

    public async Task<bool> ActualizarAsync(int id, ActualizarReservaDto dto)
    {
        var reserva = await _repository.ObtenerPorIdAsync(id);

        if (reserva == null)
            return false;

        reserva.codigo_reserva = dto.CodigoReserva;
        reserva.id_usuario = dto.IdUsuario;
        reserva.id_servicio = dto.IdServicio;
        reserva.fecha_reserva = dto.FechaReserva;
        reserva.hora_reserva = dto.HoraReserva;
        reserva.cantidad_personas = dto.CantidadPersonas;
        reserva.precio_unitario = dto.PrecioUnitario;
        reserva.precio_total = dto.PrecioTotal;
        reserva.metodo_pago = dto.MetodoPago;
        reserva.estado_pago = dto.EstadoPago;
        reserva.comprobante_pago = dto.ComprobantePago;
        reserva.estado = dto.Estado;
        reserva.observaciones = dto.Observaciones;
        reserva.fecha_actualizacion = DateTime.Now;

        await _repository.ActualizarAsync(reserva);
        await _repository.GuardarCambiosAsync();

        return true;
    }

    public async Task<bool> EliminarAsync(int id)
    {
        var reserva = await _repository.ObtenerPorIdAsync(id);

        if (reserva == null)
            return false;

        await _repository.EliminarAsync(reserva);
        await _repository.GuardarCambiosAsync();

        return true;
    }
}