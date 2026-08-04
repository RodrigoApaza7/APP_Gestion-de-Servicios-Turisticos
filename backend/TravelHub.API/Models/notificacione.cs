using System;
using System.Collections.Generic;

namespace TravelHub.API.Models;

public partial class notificacione
{
    public int id_notificacion { get; set; }

    public int id_usuario { get; set; }

    public string titulo { get; set; } = null!;

    public string? descripcion { get; set; }

    public bool? leida { get; set; }

    public DateTime? fecha { get; set; }

    public string? tipo { get; set; }

    public string? url_destino { get; set; }

    public virtual usuario id_usuarioNavigation { get; set; } = null!;
}
