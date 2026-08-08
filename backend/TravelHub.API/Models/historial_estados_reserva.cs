using System;
using System.Collections.Generic;

namespace TravelHub.API.Models;

public partial class historial_estados_reserva
{
    public int id_historial { get; set; }

    public int id_reserva { get; set; }

    public string? estado_anterior { get; set; }

    public string? estado_nuevo { get; set; }

    public DateTime? fecha { get; set; }

    public string? observacion { get; set; }

    public virtual reserva id_reservaNavigation { get; set; } = null!;
}
