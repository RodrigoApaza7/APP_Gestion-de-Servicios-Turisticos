using System;
using System.Collections.Generic;

namespace TravelHub.API.Models;

public partial class imagenes_usuario
{
    public int id_imagen_usuario { get; set; }

    public int id_usuario { get; set; }

    public string url_imagen { get; set; } = null!;

    public bool? principal { get; set; }

    public DateTime? fecha_subida { get; set; }

    public virtual usuario id_usuarioNavigation { get; set; } = null!;
}
