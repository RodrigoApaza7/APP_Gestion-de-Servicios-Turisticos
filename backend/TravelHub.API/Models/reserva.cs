using System;
using System.Collections.Generic;

namespace TravelHub.API.Models;

public partial class reserva
{
    public int id_reserva { get; set; }

    public string codigo_reserva { get; set; } = null!;

    public int id_usuario { get; set; }

    public int id_servicio { get; set; }

    public DateOnly fecha_reserva { get; set; }

    public TimeOnly? hora_reserva { get; set; }

    public int cantidad_personas { get; set; }

    public decimal precio_unitario { get; set; }

    public decimal precio_total { get; set; }

    public string? metodo_pago { get; set; }

    public string? estado_pago { get; set; }

    public string? comprobante_pago { get; set; }

    public string? estado { get; set; }

    public string? observaciones { get; set; }

    public string? motivo_cancelacion { get; set; }

    public DateTime? fecha_cancelacion { get; set; }

    public DateTime? fecha_creacion { get; set; }

    public DateTime? fecha_actualizacion { get; set; }

    public virtual ICollection<historial_estados_reserva> historial_estados_reservas { get; set; } = new List<historial_estados_reserva>();

    public virtual servicio id_servicioNavigation { get; set; } = null!;

    public virtual usuario id_usuarioNavigation { get; set; } = null!;

    public virtual ICollection<pago> pagos { get; set; } = new List<pago>();
}
