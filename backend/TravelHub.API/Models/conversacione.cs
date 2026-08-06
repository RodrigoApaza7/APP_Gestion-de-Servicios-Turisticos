using System;
using System.Collections.Generic;

namespace TravelHub.API.Models;

public partial class conversacione
{
    public int id_conversacion { get; set; }

    public int id_usuario1 { get; set; }

    public int id_usuario2 { get; set; }

    public bool? activa { get; set; }

    public DateTime? fecha_creacion { get; set; }

    public DateTime? fecha_ultimo_mensaje { get; set; }

    public virtual usuario id_usuario1Navigation { get; set; } = null!;

    public virtual usuario id_usuario2Navigation { get; set; } = null!;

    public virtual ICollection<mensaje> mensajes { get; set; } = new List<mensaje>();
}
