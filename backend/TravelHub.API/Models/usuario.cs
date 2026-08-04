using System;
using System.Collections.Generic;

namespace TravelHub.API.Models;

public partial class usuario
{
    public int id_usuario { get; set; }

    public string nombre { get; set; } = null!;

    public string apellido { get; set; } = null!;

    public string correo { get; set; } = null!;

    public string password { get; set; } = null!;

    public string? telefono { get; set; }

    public string? foto_perfil { get; set; }

    public string rol { get; set; } = null!;

    public DateTime? fecha_registro { get; set; }

    public bool? activo { get; set; }

    public DateOnly? fecha_nacimiento { get; set; }

    public string? nacionalidad { get; set; }

    public string? ciudad { get; set; }

    public string? idioma { get; set; }

    public virtual ICollection<calificacione> calificaciones { get; set; } = new List<calificacione>();

    public virtual ICollection<conversacione> conversacioneid_usuario1Navigations { get; set; } = new List<conversacione>();

    public virtual ICollection<conversacione> conversacioneid_usuario2Navigations { get; set; } = new List<conversacione>();

    public virtual ICollection<favorito> favoritos { get; set; } = new List<favorito>();

    public virtual ICollection<itinerario> itinerarios { get; set; } = new List<itinerario>();

    public virtual ICollection<mensaje> mensajes { get; set; } = new List<mensaje>();

    public virtual ICollection<notificacione> notificaciones { get; set; } = new List<notificacione>();

    public virtual prestadore? prestadore { get; set; }

    public virtual ICollection<reserva> reservas { get; set; } = new List<reserva>();
}
