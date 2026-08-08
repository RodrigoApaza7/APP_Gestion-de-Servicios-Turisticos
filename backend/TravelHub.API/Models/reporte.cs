using System;
using System.Collections.Generic;

namespace TravelHub.API.Models;

public partial class reporte
{
    public int id_reporte { get; set; }

    public int id_usuario { get; set; }

    public int? id_servicio { get; set; }

    public string? motivo { get; set; }

    public string? descripcion { get; set; }

    public string? estado { get; set; }

    public DateTime? fecha_creacion { get; set; }

    public virtual servicio? id_servicioNavigation { get; set; }

    public virtual usuario id_usuarioNavigation { get; set; } = null!;
}
