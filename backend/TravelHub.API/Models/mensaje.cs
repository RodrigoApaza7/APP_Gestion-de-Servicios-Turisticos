using System;
using System.Collections.Generic;

namespace TravelHub.API.Models;

public partial class mensaje
{
    public int id_mensaje { get; set; }

    public int id_conversacion { get; set; }

    public int id_emisor { get; set; }

    public string? mensaje1 { get; set; }

    public string? tipo { get; set; }

    public string? archivo { get; set; }

    public bool? leido { get; set; }

    public DateTime? fecha_envio { get; set; }

    public DateTime? fecha_lectura { get; set; }

    public bool? eliminado { get; set; }

    public virtual conversacione id_conversacionNavigation { get; set; } = null!;

    public virtual usuario id_emisorNavigation { get; set; } = null!;

    public virtual ICollection<multimedia_chat> multimedia_chats { get; set; } = new List<multimedia_chat>();
}
