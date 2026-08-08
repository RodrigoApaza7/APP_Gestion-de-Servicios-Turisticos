using System;
using System.Collections.Generic;

namespace TravelHub.API.Models;

public partial class favoritos_itinerario
{
    public int id_favorito_itinerario { get; set; }

    public int id_usuario { get; set; }

    public int id_itinerario { get; set; }

    public DateTime? fecha_agregado { get; set; }

    public virtual itinerario id_itinerarioNavigation { get; set; } = null!;

    public virtual usuario id_usuarioNavigation { get; set; } = null!;
}
