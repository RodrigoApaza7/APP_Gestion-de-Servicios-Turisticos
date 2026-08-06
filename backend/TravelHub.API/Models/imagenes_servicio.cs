using System;
using System.Collections.Generic;

namespace TravelHub.API.Models;

public partial class imagenes_servicio
{
    public int id_imagen { get; set; }

    public int id_servicio { get; set; }

    public string url_imagen { get; set; } = null!;

    public string? descripcion { get; set; }

    public bool? principal { get; set; }

    public int? orden { get; set; }

    public virtual servicio id_servicioNavigation { get; set; } = null!;
}
