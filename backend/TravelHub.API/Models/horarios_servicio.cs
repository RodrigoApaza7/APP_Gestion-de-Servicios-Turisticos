using System;
using System.Collections.Generic;

namespace TravelHub.API.Models;

public partial class horarios_servicio
{
    public int id_horario { get; set; }

    public int id_servicio { get; set; }

    public short dia_semana { get; set; }

    public TimeOnly hora_apertura { get; set; }

    public TimeOnly hora_cierre { get; set; }

    public bool abierto { get; set; }

    public string? observaciones { get; set; }

    public DateTime? fecha_creacion { get; set; }

    public virtual servicio id_servicioNavigation { get; set; } = null!;
}
