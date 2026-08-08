using System;
using System.Collections.Generic;

namespace TravelHub.API.Models;

public partial class multimedia_chat
{
    public int id_multimedia { get; set; }

    public int id_mensaje { get; set; }

    public string url_archivo { get; set; } = null!;

    public string? tipo { get; set; }

    public int? peso_kb { get; set; }

    public int? ancho { get; set; }

    public int? alto { get; set; }

    public int? duracion { get; set; }

    public DateTime? fecha_subida { get; set; }

    public virtual mensaje id_mensajeNavigation { get; set; } = null!;
}
