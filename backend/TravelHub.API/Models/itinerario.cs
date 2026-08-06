using System;
using System.Collections.Generic;

namespace TravelHub.API.Models;

public partial class itinerario
{
    public int id_itinerario { get; set; }

    public int id_usuario { get; set; }

    public string nombre { get; set; } = null!;

    public string? descripcion { get; set; }

    public DateOnly? fecha_inicio { get; set; }

    public DateOnly? fecha_fin { get; set; }

    public virtual ICollection<detalle_itinerario> detalle_itinerarios { get; set; } = new List<detalle_itinerario>();

    public virtual usuario id_usuarioNavigation { get; set; } = null!;
}
