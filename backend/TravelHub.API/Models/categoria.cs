using System;
using System.Collections.Generic;

namespace TravelHub.API.Models;

public partial class categoria
{
    public int id_categoria { get; set; }

    public string nombre { get; set; } = null!;

    public string? descripcion { get; set; }

    public virtual ICollection<servicio> servicios { get; set; } = new List<servicio>();
}
