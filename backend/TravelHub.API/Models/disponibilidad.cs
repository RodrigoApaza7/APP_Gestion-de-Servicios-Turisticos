using System;
using System.Collections.Generic;

namespace TravelHub.API.Models;

public partial class disponibilidad
{
    public int id_disponibilidad { get; set; }

    public int id_servicio { get; set; }

    public DateOnly fecha { get; set; }

    public TimeOnly? hora_inicio { get; set; }

    public TimeOnly? hora_fin { get; set; }

    public bool? disponible { get; set; }

    public virtual servicio id_servicioNavigation { get; set; } = null!;
}
