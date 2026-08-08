using System;
using System.Collections.Generic;

namespace TravelHub.API.Models;

public partial class calificacione
{
    public int id_calificacion { get; set; }

    public int id_usuario { get; set; }

    public int id_servicio { get; set; }

    public short puntuacion { get; set; }

    public string? comentario { get; set; }

    public DateTime? fecha_creacion { get; set; }

    public bool? editado { get; set; }

    public virtual servicio id_servicioNavigation { get; set; } = null!;

    public virtual usuario id_usuarioNavigation { get; set; } = null!;
}
