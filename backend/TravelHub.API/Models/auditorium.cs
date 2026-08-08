using System;
using System.Collections.Generic;

namespace TravelHub.API.Models;

public partial class auditorium
{
    public int id_auditoria { get; set; }

    public int? id_usuario { get; set; }

    public string? tabla { get; set; }

    public string? accion { get; set; }

    public string? descripcion { get; set; }

    public string? ip { get; set; }

    public DateTime? fecha { get; set; }

    public virtual usuario? id_usuarioNavigation { get; set; }
}
