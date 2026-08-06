using System;
using System.Collections.Generic;

namespace TravelHub.API.Models;

public partial class usuario
{
    public int id_usuario { get; set; }

    public int id_rol { get; set; }

    public string nombre { get; set; } = null!;

    public string apellido { get; set; } = null!;

    public string correo { get; set; } = null!;

    public string password_hash { get; set; } = null!;

    public string? telefono { get; set; }

    public string? foto_perfil { get; set; }

    public DateOnly? fecha_nacimiento { get; set; }

    public string? nacionalidad { get; set; }

    public string? ciudad { get; set; }

    public string? idioma { get; set; }

    public bool correo_verificado { get; set; }

    public string? token_recuperacion { get; set; }

    public DateTime? expiracion_token { get; set; }

    public DateTime? ultimo_login { get; set; }

    public bool activo { get; set; }

    public DateTime fecha_creacion { get; set; }

    public DateTime? fecha_actualizacion { get; set; }

    public virtual ICollection<auditorium> auditoria { get; set; } = new List<auditorium>();

    public virtual ICollection<calificacione> calificaciones { get; set; } = new List<calificacione>();

    public virtual ICollection<conversacione> conversacioneid_usuario1Navigations { get; set; } = new List<conversacione>();

    public virtual ICollection<conversacione> conversacioneid_usuario2Navigations { get; set; } = new List<conversacione>();

    public virtual ICollection<favorito> favoritos { get; set; } = new List<favorito>();

    public virtual ICollection<favoritos_itinerario> favoritos_itinerarios { get; set; } = new List<favoritos_itinerario>();

    public virtual role id_rolNavigation { get; set; } = null!;

    public virtual ICollection<imagenes_usuario> imagenes_usuarios { get; set; } = new List<imagenes_usuario>();

    public virtual ICollection<itinerario> itinerarios { get; set; } = new List<itinerario>();

    public virtual ICollection<mensaje> mensajes { get; set; } = new List<mensaje>();

    public virtual ICollection<notificacione> notificaciones { get; set; } = new List<notificacione>();

    public virtual prestadore? prestadore { get; set; }

    public virtual ICollection<reporte> reportes { get; set; } = new List<reporte>();

    public virtual ICollection<reserva> reservas { get; set; } = new List<reserva>();

    public virtual ICollection<tokens_dispositivo> tokens_dispositivos { get; set; } = new List<tokens_dispositivo>();
}
