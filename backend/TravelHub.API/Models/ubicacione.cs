using System;
using System.Collections.Generic;

namespace TravelHub.API.Models;

public partial class ubicacione
{
    public int id_ubicacion { get; set; }

    public string? nombre_lugar { get; set; }

    public string direccion { get; set; } = null!;

    public string? referencia { get; set; }

    public string distrito { get; set; } = null!;

    public string provincia { get; set; } = null!;

    public string departamento { get; set; } = null!;

    public string pais { get; set; } = null!;

    public decimal latitud { get; set; }

    public decimal longitud { get; set; }

    public string? url_google_maps { get; set; }

    public string? plus_code { get; set; }

    public decimal? radio_cobertura_km { get; set; }

    public DateTime? fecha_creacion { get; set; }

    public virtual ICollection<servicio> servicios { get; set; } = new List<servicio>();
}
