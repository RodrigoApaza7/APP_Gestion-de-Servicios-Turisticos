using System;
using System.Collections.Generic;

namespace TravelHub.API.Models;

public partial class role
{
    public int id_rol { get; set; }

    public string nombre { get; set; } = null!;

    public string? descripcion { get; set; }

    public bool? activo { get; set; }

    public DateTime? fecha_creacion { get; set; }

    public virtual ICollection<usuario> usuarios { get; set; } = new List<usuario>();

    public virtual ICollection<permiso> id_permisos { get; set; } = new List<permiso>();
}
