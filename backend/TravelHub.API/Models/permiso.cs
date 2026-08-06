using System;
using System.Collections.Generic;

namespace TravelHub.API.Models;

public partial class permiso
{
    public int id_permiso { get; set; }

    public string nombre { get; set; } = null!;

    public string? descripcion { get; set; }

    public virtual ICollection<role> id_rols { get; set; } = new List<role>();
}
