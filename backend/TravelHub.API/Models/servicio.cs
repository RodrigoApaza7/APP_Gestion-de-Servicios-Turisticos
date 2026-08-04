using System;
using System.Collections.Generic;

namespace TravelHub.API.Models;

public partial class servicio
{
    public int id_servicio { get; set; }

    public int id_prestador { get; set; }

    public int id_categoria { get; set; }

    public string nombre { get; set; } = null!;

    public string? descripcion { get; set; }

    public decimal precio { get; set; }

    public int? capacidad { get; set; }

    public decimal? calificacion_promedio { get; set; }

    public bool? activo { get; set; }

    public int? id_ubicacion { get; set; }

    public string? moneda { get; set; }

    public string? tipo_reserva { get; set; }

    public string? estado { get; set; }

    public bool? destacado { get; set; }

    public string? unidad_cobro { get; set; }

    public virtual ICollection<calificacione> calificaciones { get; set; } = new List<calificacione>();

    public virtual ICollection<detalle_itinerario> detalle_itinerarios { get; set; } = new List<detalle_itinerario>();

    public virtual ICollection<disponibilidad> disponibilidads { get; set; } = new List<disponibilidad>();

    public virtual ICollection<favorito> favoritos { get; set; } = new List<favorito>();

    public virtual ICollection<horarios_servicio> horarios_servicios { get; set; } = new List<horarios_servicio>();

    public virtual categoria id_categoriaNavigation { get; set; } = null!;

    public virtual prestadore id_prestadorNavigation { get; set; } = null!;

    public virtual ubicacione? id_ubicacionNavigation { get; set; }

    public virtual ICollection<imagenes_servicio> imagenes_servicios { get; set; } = new List<imagenes_servicio>();

    public virtual ICollection<reserva> reservas { get; set; } = new List<reserva>();
}
