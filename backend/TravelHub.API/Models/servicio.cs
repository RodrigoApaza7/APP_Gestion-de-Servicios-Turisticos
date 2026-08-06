using System;
using System.Collections.Generic;

namespace TravelHub.API.Models;

public partial class servicio
{
    public int id_servicio { get; set; }

    public int id_prestador { get; set; }

    public int id_categoria { get; set; }

    public int id_ubicacion { get; set; }

    public string nombre { get; set; } = null!;

    public string? descripcion { get; set; }

    public decimal precio { get; set; }

    public string? moneda { get; set; }

    public string unidad_cobro { get; set; } = null!;

    public string? duracion_estimada { get; set; }

    public int? capacidad { get; set; }

    public int? aforo_maximo { get; set; }

    public bool? requiere_reserva { get; set; }

    public bool? cancelacion_gratuita { get; set; }

    public int? edad_minima { get; set; }

    public string? incluye { get; set; }

    public string? no_incluye { get; set; }

    public string? politicas { get; set; }

    public string? estado { get; set; }

    public bool? destacado { get; set; }

    public bool? activo { get; set; }

    public decimal? calificacion_promedio { get; set; }

    public DateTime? fecha_creacion { get; set; }

    public DateTime? fecha_actualizacion { get; set; }

    public virtual ICollection<calificacione> calificaciones { get; set; } = new List<calificacione>();

    public virtual ICollection<detalle_itinerario> detalle_itinerarios { get; set; } = new List<detalle_itinerario>();

    public virtual ICollection<favorito> favoritos { get; set; } = new List<favorito>();

    public virtual ICollection<horarios_servicio> horarios_servicios { get; set; } = new List<horarios_servicio>();

    public virtual categoria id_categoriaNavigation { get; set; } = null!;

    public virtual prestadore id_prestadorNavigation { get; set; } = null!;

    public virtual ubicacione id_ubicacionNavigation { get; set; } = null!;

    public virtual ICollection<imagenes_servicio> imagenes_servicios { get; set; } = new List<imagenes_servicio>();

    public virtual ICollection<reporte> reportes { get; set; } = new List<reporte>();

    public virtual ICollection<reserva> reservas { get; set; } = new List<reserva>();
}
