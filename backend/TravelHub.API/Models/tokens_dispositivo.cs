using System;
using System.Collections.Generic;

namespace TravelHub.API.Models;

public partial class tokens_dispositivo
{
    public int id_token { get; set; }

    public int id_usuario { get; set; }

    public string token { get; set; } = null!;

    public string? plataforma { get; set; }

    public bool? activo { get; set; }

    public DateTime? fecha_registro { get; set; }

    public virtual usuario id_usuarioNavigation { get; set; } = null!;
}
