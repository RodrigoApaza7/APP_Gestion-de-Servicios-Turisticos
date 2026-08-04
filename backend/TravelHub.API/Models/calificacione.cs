using System;
using System.Collections.Generic;

namespace TravelHub.API.Models;

public partial class calificacione
{
    public int id_calificacion { get; set; }

    public int id_usuario { get; set; }

    public int id_servicio { get; set; }

    public int puntuacion { get; set; }

    public string? comentario { get; set; }

    public DateTime? fecha { get; set; }

    public string? respuesta_prestador { get; set; }

    public virtual servicio id_servicioNavigation { get; set; } = null!;

    public virtual usuario id_usuarioNavigation { get; set; } = null!;
}
