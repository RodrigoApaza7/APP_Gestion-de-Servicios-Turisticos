using System;
using System.Collections.Generic;

namespace TravelHub.API.Models;

public partial class reserva
{
    public int id_reserva { get; set; }

    public int id_usuario { get; set; }

    public int id_servicio { get; set; }

    public DateTime? fecha_reserva { get; set; }

    public DateOnly fecha_servicio { get; set; }

    public int? cantidad_personas { get; set; }

    public decimal precio_total { get; set; }

    public string? estado { get; set; }

    public string? metodo_pago { get; set; }

    public string? observaciones { get; set; }

    public virtual servicio id_servicioNavigation { get; set; } = null!;

    public virtual usuario id_usuarioNavigation { get; set; } = null!;
}
