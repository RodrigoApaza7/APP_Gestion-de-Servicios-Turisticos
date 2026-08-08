using System;
using System.Collections.Generic;

namespace TravelHub.API.Models;

public partial class favorito
{
    public int id_favorito { get; set; }

    public int id_usuario { get; set; }

    public int id_servicio { get; set; }

    public DateTime? fecha_agregado { get; set; }

    public virtual servicio id_servicioNavigation { get; set; } = null!;

    public virtual usuario id_usuarioNavigation { get; set; } = null!;
}
