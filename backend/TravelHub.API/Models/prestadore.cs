using System;
using System.Collections.Generic;

namespace TravelHub.API.Models;

public partial class prestadore
{
    public int id_prestador { get; set; }

    public int id_usuario { get; set; }

    public string? descripcion { get; set; }

    public string? documento_identidad { get; set; }

    public string? certificado { get; set; }

    public bool? aprobado { get; set; }

    public string? razon_social { get; set; }

    public string? ruc { get; set; }

    public int? anios_experiencia { get; set; }

    public bool? verificado { get; set; }

    public string? telefono { get; set; }

    public string? correo { get; set; }

    public string? pagina_web { get; set; }

    public string? facebook { get; set; }

    public string? instagram { get; set; }

    public virtual usuario id_usuarioNavigation { get; set; } = null!;

    public virtual ICollection<servicio> servicios { get; set; } = new List<servicio>();
}
