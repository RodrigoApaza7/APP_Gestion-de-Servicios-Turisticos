using System;
using System.Collections.Generic;

namespace TravelHub.API.Models;

public partial class detalle_itinerario
{
    public int id_detalle { get; set; }

    public int id_itinerario { get; set; }

    public int id_servicio { get; set; }

    public DateOnly? fecha { get; set; }

    public TimeOnly? hora { get; set; }

    public short orden { get; set; }

    public string? notas { get; set; }

    public virtual itinerario id_itinerarioNavigation { get; set; } = null!;

    public virtual servicio id_servicioNavigation { get; set; } = null!;
}
