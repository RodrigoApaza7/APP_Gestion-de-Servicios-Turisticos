using System;
using System.Collections.Generic;

namespace TravelHub.API.Models;

public partial class pago
{
    public int id_pago { get; set; }

    public int id_reserva { get; set; }

    public decimal monto { get; set; }

    public string metodo { get; set; } = null!;

    public string? estado { get; set; }

    public string? referencia { get; set; }

    public DateTime? fecha_pago { get; set; }

    public string? comprobante { get; set; }

    public string? observacion { get; set; }

    public virtual reserva id_reservaNavigation { get; set; } = null!;
}
