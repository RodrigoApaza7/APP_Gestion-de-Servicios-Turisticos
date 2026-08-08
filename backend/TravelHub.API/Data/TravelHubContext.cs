using System;
using System.Collections.Generic;
using Microsoft.EntityFrameworkCore;
using TravelHub.API.Models;

namespace TravelHub.API.Data;

public partial class TravelHubContext : DbContext
{
    public TravelHubContext(DbContextOptions<TravelHubContext> options)
        : base(options)
    {
    }

    public virtual DbSet<auditorium> auditoria { get; set; }

    public virtual DbSet<calificacione> calificaciones { get; set; }

    public virtual DbSet<categoria> categorias { get; set; }

    public virtual DbSet<conversacione> conversaciones { get; set; }

    public virtual DbSet<detalle_itinerario> detalle_itinerarios { get; set; }

    public virtual DbSet<favorito> favoritos { get; set; }

    public virtual DbSet<favoritos_itinerario> favoritos_itinerarios { get; set; }

    public virtual DbSet<historial_estados_reserva> historial_estados_reservas { get; set; }

    public virtual DbSet<horarios_servicio> horarios_servicios { get; set; }

    public virtual DbSet<imagenes_servicio> imagenes_servicios { get; set; }

    public virtual DbSet<imagenes_usuario> imagenes_usuarios { get; set; }

    public virtual DbSet<itinerario> itinerarios { get; set; }

    public virtual DbSet<mensaje> mensajes { get; set; }

    public virtual DbSet<multimedia_chat> multimedia_chats { get; set; }

    public virtual DbSet<notificacione> notificaciones { get; set; }

    public virtual DbSet<pago> pagos { get; set; }

    public virtual DbSet<permiso> permisos { get; set; }

    public virtual DbSet<prestadore> prestadores { get; set; }

    public virtual DbSet<reporte> reportes { get; set; }

    public virtual DbSet<reserva> reservas { get; set; }

    public virtual DbSet<role> roles { get; set; }

    public virtual DbSet<servicio> servicios { get; set; }

    public virtual DbSet<tokens_dispositivo> tokens_dispositivos { get; set; }

    public virtual DbSet<ubicacione> ubicaciones { get; set; }

    public virtual DbSet<usuario> usuarios { get; set; }

    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        modelBuilder.Entity<auditorium>(entity =>
        {
            entity.HasKey(e => e.id_auditoria).HasName("pk_auditoria");

            entity.HasIndex(e => e.id_usuario, "idx_auditoria_usuario");

            entity.Property(e => e.id_auditoria).UseIdentityAlwaysColumn();
            entity.Property(e => e.accion).HasMaxLength(20);
            entity.Property(e => e.fecha)
                .HasDefaultValueSql("CURRENT_TIMESTAMP")
                .HasColumnType("timestamp without time zone");
            entity.Property(e => e.ip).HasMaxLength(50);
            entity.Property(e => e.tabla).HasMaxLength(80);

            entity.HasOne(d => d.id_usuarioNavigation).WithMany(p => p.auditoria)
                .HasForeignKey(d => d.id_usuario)
                .OnDelete(DeleteBehavior.SetNull)
                .HasConstraintName("fk_auditoria_usuario");
        });

        modelBuilder.Entity<calificacione>(entity =>
        {
            entity.HasKey(e => e.id_calificacion).HasName("pk_calificaciones");

            entity.HasIndex(e => e.id_servicio, "idx_calificacion_servicio");

            entity.HasIndex(e => new { e.id_usuario, e.id_servicio }, "uq_calificacion").IsUnique();

            entity.Property(e => e.id_calificacion).UseIdentityAlwaysColumn();
            entity.Property(e => e.editado).HasDefaultValue(false);
            entity.Property(e => e.fecha_creacion)
                .HasDefaultValueSql("CURRENT_TIMESTAMP")
                .HasColumnType("timestamp without time zone");

            entity.HasOne(d => d.id_servicioNavigation).WithMany(p => p.calificaciones)
                .HasForeignKey(d => d.id_servicio)
                .HasConstraintName("fk_calificacion_servicio");

            entity.HasOne(d => d.id_usuarioNavigation).WithMany(p => p.calificaciones)
                .HasForeignKey(d => d.id_usuario)
                .HasConstraintName("fk_calificacion_usuario");
        });

        modelBuilder.Entity<categoria>(entity =>
        {
            entity.HasKey(e => e.id_categoria).HasName("pk_categorias");

            entity.HasIndex(e => e.nombre, "idx_categoria_nombre");

            entity.HasIndex(e => e.nombre, "uq_categoria_nombre").IsUnique();

            entity.Property(e => e.id_categoria).UseIdentityAlwaysColumn();
            entity.Property(e => e.activo).HasDefaultValue(true);
            entity.Property(e => e.color).HasMaxLength(20);
            entity.Property(e => e.fecha_creacion)
                .HasDefaultValueSql("CURRENT_TIMESTAMP")
                .HasColumnType("timestamp without time zone");
            entity.Property(e => e.icono).HasMaxLength(100);
            entity.Property(e => e.nombre).HasMaxLength(80);
        });

        modelBuilder.Entity<conversacione>(entity =>
        {
            entity.HasKey(e => e.id_conversacion).HasName("pk_conversaciones");

            entity.HasIndex(e => e.id_usuario1, "idx_conversacion_usuario1");

            entity.HasIndex(e => e.id_usuario2, "idx_conversacion_usuario2");

            entity.HasIndex(e => new { e.id_usuario1, e.id_usuario2 }, "uq_conversacion").IsUnique();

            entity.Property(e => e.id_conversacion).UseIdentityAlwaysColumn();
            entity.Property(e => e.activa).HasDefaultValue(true);
            entity.Property(e => e.fecha_creacion)
                .HasDefaultValueSql("CURRENT_TIMESTAMP")
                .HasColumnType("timestamp without time zone");
            entity.Property(e => e.fecha_ultimo_mensaje).HasColumnType("timestamp without time zone");

            entity.HasOne(d => d.id_usuario1Navigation).WithMany(p => p.conversacioneid_usuario1Navigations)
                .HasForeignKey(d => d.id_usuario1)
                .HasConstraintName("fk_conversacion_usuario1");

            entity.HasOne(d => d.id_usuario2Navigation).WithMany(p => p.conversacioneid_usuario2Navigations)
                .HasForeignKey(d => d.id_usuario2)
                .HasConstraintName("fk_conversacion_usuario2");
        });

        modelBuilder.Entity<detalle_itinerario>(entity =>
        {
            entity.HasKey(e => e.id_detalle).HasName("pk_detalle");

            entity.ToTable("detalle_itinerario");

            entity.HasIndex(e => e.id_itinerario, "idx_detalle_itinerario");

            entity.HasIndex(e => e.id_servicio, "idx_detalle_servicio");

            entity.HasIndex(e => new { e.id_itinerario, e.orden }, "uq_detalle").IsUnique();

            entity.Property(e => e.id_detalle).UseIdentityAlwaysColumn();

            entity.HasOne(d => d.id_itinerarioNavigation).WithMany(p => p.detalle_itinerarios)
                .HasForeignKey(d => d.id_itinerario)
                .HasConstraintName("fk_detalle_itinerario");

            entity.HasOne(d => d.id_servicioNavigation).WithMany(p => p.detalle_itinerarios)
                .HasForeignKey(d => d.id_servicio)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("fk_detalle_servicio");
        });

        modelBuilder.Entity<favorito>(entity =>
        {
            entity.HasKey(e => e.id_favorito).HasName("pk_favoritos");

            entity.HasIndex(e => e.id_servicio, "idx_favorito_servicio");

            entity.HasIndex(e => e.id_usuario, "idx_favorito_usuario");

            entity.HasIndex(e => new { e.id_usuario, e.id_servicio }, "uq_favorito").IsUnique();

            entity.Property(e => e.id_favorito).UseIdentityAlwaysColumn();
            entity.Property(e => e.fecha_agregado)
                .HasDefaultValueSql("CURRENT_TIMESTAMP")
                .HasColumnType("timestamp without time zone");

            entity.HasOne(d => d.id_servicioNavigation).WithMany(p => p.favoritos)
                .HasForeignKey(d => d.id_servicio)
                .HasConstraintName("fk_favorito_servicio");

            entity.HasOne(d => d.id_usuarioNavigation).WithMany(p => p.favoritos)
                .HasForeignKey(d => d.id_usuario)
                .HasConstraintName("fk_favorito_usuario");
        });

        modelBuilder.Entity<favoritos_itinerario>(entity =>
        {
            entity.HasKey(e => e.id_favorito_itinerario).HasName("pk_favoritos_itinerarios");

            entity.HasIndex(e => e.id_itinerario, "idx_favorito_itinerario");

            entity.HasIndex(e => e.id_usuario, "idx_favorito_itinerario_usuario");

            entity.HasIndex(e => new { e.id_usuario, e.id_itinerario }, "uq_favorito_itinerario").IsUnique();

            entity.Property(e => e.id_favorito_itinerario).UseIdentityAlwaysColumn();
            entity.Property(e => e.fecha_agregado)
                .HasDefaultValueSql("CURRENT_TIMESTAMP")
                .HasColumnType("timestamp without time zone");

            entity.HasOne(d => d.id_itinerarioNavigation).WithMany(p => p.favoritos_itinerarios)
                .HasForeignKey(d => d.id_itinerario)
                .HasConstraintName("fk_favorito_itinerario");

            entity.HasOne(d => d.id_usuarioNavigation).WithMany(p => p.favoritos_itinerarios)
                .HasForeignKey(d => d.id_usuario)
                .HasConstraintName("fk_favorito_itinerario_usuario");
        });

        modelBuilder.Entity<historial_estados_reserva>(entity =>
        {
            entity.HasKey(e => e.id_historial).HasName("pk_historial");

            entity.ToTable("historial_estados_reserva");

            entity.Property(e => e.id_historial).UseIdentityAlwaysColumn();
            entity.Property(e => e.estado_anterior).HasMaxLength(20);
            entity.Property(e => e.estado_nuevo).HasMaxLength(20);
            entity.Property(e => e.fecha)
                .HasDefaultValueSql("CURRENT_TIMESTAMP")
                .HasColumnType("timestamp without time zone");

            entity.HasOne(d => d.id_reservaNavigation).WithMany(p => p.historial_estados_reservas)
                .HasForeignKey(d => d.id_reserva)
                .HasConstraintName("fk_historial_reserva");
        });

        modelBuilder.Entity<horarios_servicio>(entity =>
        {
            entity.HasKey(e => e.id_horario).HasName("pk_horarios");

            entity.ToTable("horarios_servicio");

            entity.HasIndex(e => e.id_servicio, "idx_horario_servicio");

            entity.Property(e => e.id_horario).UseIdentityAlwaysColumn();
            entity.Property(e => e.abierto).HasDefaultValue(true);
            entity.Property(e => e.fecha_creacion)
                .HasDefaultValueSql("CURRENT_TIMESTAMP")
                .HasColumnType("timestamp without time zone");
            entity.Property(e => e.observaciones).HasMaxLength(300);

            entity.HasOne(d => d.id_servicioNavigation).WithMany(p => p.horarios_servicios)
                .HasForeignKey(d => d.id_servicio)
                .HasConstraintName("fk_horario_servicio");
        });

        modelBuilder.Entity<imagenes_servicio>(entity =>
        {
            entity.HasKey(e => e.id_imagen).HasName("pk_imagenes");

            entity.ToTable("imagenes_servicio");

            entity.HasIndex(e => e.id_servicio, "idx_imagen_servicio");

            entity.Property(e => e.id_imagen).UseIdentityAlwaysColumn();
            entity.Property(e => e.descripcion).HasMaxLength(250);
            entity.Property(e => e.fecha_subida)
                .HasDefaultValueSql("CURRENT_TIMESTAMP")
                .HasColumnType("timestamp without time zone");
            entity.Property(e => e.orden).HasDefaultValue((short)1);
            entity.Property(e => e.principal).HasDefaultValue(false);
            entity.Property(e => e.tipo)
                .HasMaxLength(20)
                .HasDefaultValueSql("'JPG'::character varying");

            entity.HasOne(d => d.id_servicioNavigation).WithMany(p => p.imagenes_servicios)
                .HasForeignKey(d => d.id_servicio)
                .HasConstraintName("fk_imagen_servicio");
        });

        modelBuilder.Entity<imagenes_usuario>(entity =>
        {
            entity.HasKey(e => e.id_imagen_usuario).HasName("pk_imagen_usuario");

            entity.HasIndex(e => e.id_usuario, "idx_imagen_usuario");

            entity.Property(e => e.id_imagen_usuario).UseIdentityAlwaysColumn();
            entity.Property(e => e.fecha_subida)
                .HasDefaultValueSql("CURRENT_TIMESTAMP")
                .HasColumnType("timestamp without time zone");
            entity.Property(e => e.principal).HasDefaultValue(true);

            entity.HasOne(d => d.id_usuarioNavigation).WithMany(p => p.imagenes_usuarios)
                .HasForeignKey(d => d.id_usuario)
                .HasConstraintName("fk_imagen_usuario");
        });

        modelBuilder.Entity<itinerario>(entity =>
        {
            entity.HasKey(e => e.id_itinerario).HasName("pk_itinerarios");

            entity.HasIndex(e => e.id_usuario, "idx_itinerario_usuario");

            entity.Property(e => e.id_itinerario).UseIdentityAlwaysColumn();
            entity.Property(e => e.compartido).HasDefaultValue(false);
            entity.Property(e => e.fecha_actualizacion).HasColumnType("timestamp without time zone");
            entity.Property(e => e.fecha_creacion)
                .HasDefaultValueSql("CURRENT_TIMESTAMP")
                .HasColumnType("timestamp without time zone");
            entity.Property(e => e.nombre).HasMaxLength(150);
            entity.Property(e => e.publico).HasDefaultValue(false);

            entity.HasOne(d => d.id_usuarioNavigation).WithMany(p => p.itinerarios)
                .HasForeignKey(d => d.id_usuario)
                .HasConstraintName("fk_itinerario_usuario");
        });

        modelBuilder.Entity<mensaje>(entity =>
        {
            entity.HasKey(e => e.id_mensaje).HasName("pk_mensajes");

            entity.HasIndex(e => e.id_conversacion, "idx_mensaje_conversacion");

            entity.HasIndex(e => e.fecha_envio, "idx_mensaje_fecha");

            entity.Property(e => e.id_mensaje).UseIdentityAlwaysColumn();
            entity.Property(e => e.eliminado).HasDefaultValue(false);
            entity.Property(e => e.fecha_envio)
                .HasDefaultValueSql("CURRENT_TIMESTAMP")
                .HasColumnType("timestamp without time zone");
            entity.Property(e => e.fecha_lectura).HasColumnType("timestamp without time zone");
            entity.Property(e => e.leido).HasDefaultValue(false);
            entity.Property(e => e.mensaje1).HasColumnName("mensaje");
            entity.Property(e => e.tipo)
                .HasMaxLength(20)
                .HasDefaultValueSql("'TEXTO'::character varying");

            entity.HasOne(d => d.id_conversacionNavigation).WithMany(p => p.mensajes)
                .HasForeignKey(d => d.id_conversacion)
                .HasConstraintName("fk_mensaje_conversacion");

            entity.HasOne(d => d.id_emisorNavigation).WithMany(p => p.mensajes)
                .HasForeignKey(d => d.id_emisor)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("fk_mensaje_emisor");
        });

        modelBuilder.Entity<multimedia_chat>(entity =>
        {
            entity.HasKey(e => e.id_multimedia).HasName("pk_multimedia");

            entity.ToTable("multimedia_chat");

            entity.HasIndex(e => e.id_mensaje, "idx_multimedia_mensaje");

            entity.Property(e => e.id_multimedia).UseIdentityAlwaysColumn();
            entity.Property(e => e.fecha_subida)
                .HasDefaultValueSql("CURRENT_TIMESTAMP")
                .HasColumnType("timestamp without time zone");
            entity.Property(e => e.tipo).HasMaxLength(20);

            entity.HasOne(d => d.id_mensajeNavigation).WithMany(p => p.multimedia_chats)
                .HasForeignKey(d => d.id_mensaje)
                .HasConstraintName("fk_multimedia_mensaje");
        });

        modelBuilder.Entity<notificacione>(entity =>
        {
            entity.HasKey(e => e.id_notificacion).HasName("pk_notificaciones");

            entity.HasIndex(e => e.id_usuario, "idx_notificacion_usuario");

            entity.Property(e => e.id_notificacion).UseIdentityAlwaysColumn();
            entity.Property(e => e.fecha_creacion)
                .HasDefaultValueSql("CURRENT_TIMESTAMP")
                .HasColumnType("timestamp without time zone");
            entity.Property(e => e.icono).HasMaxLength(100);
            entity.Property(e => e.leida).HasDefaultValue(false);
            entity.Property(e => e.prioridad).HasDefaultValue((short)2);
            entity.Property(e => e.tipo).HasMaxLength(30);
            entity.Property(e => e.titulo).HasMaxLength(150);

            entity.HasOne(d => d.id_usuarioNavigation).WithMany(p => p.notificaciones)
                .HasForeignKey(d => d.id_usuario)
                .HasConstraintName("fk_notificacion_usuario");
        });

        modelBuilder.Entity<pago>(entity =>
        {
            entity.HasKey(e => e.id_pago).HasName("pk_pagos");

            entity.HasIndex(e => e.id_reserva, "idx_pago_reserva");

            entity.Property(e => e.id_pago).UseIdentityAlwaysColumn();
            entity.Property(e => e.estado)
                .HasMaxLength(20)
                .HasDefaultValueSql("'PENDIENTE'::character varying");
            entity.Property(e => e.fecha_pago).HasColumnType("timestamp without time zone");
            entity.Property(e => e.metodo).HasMaxLength(30);
            entity.Property(e => e.monto).HasPrecision(10, 2);
            entity.Property(e => e.referencia).HasMaxLength(200);

            entity.HasOne(d => d.id_reservaNavigation).WithMany(p => p.pagos)
                .HasForeignKey(d => d.id_reserva)
                .HasConstraintName("fk_pago_reserva");
        });

        modelBuilder.Entity<permiso>(entity =>
        {
            entity.HasKey(e => e.id_permiso).HasName("pk_permisos");

            entity.HasIndex(e => e.nombre, "uq_permiso").IsUnique();

            entity.Property(e => e.id_permiso).UseIdentityAlwaysColumn();
            entity.Property(e => e.nombre).HasMaxLength(80);
        });

        modelBuilder.Entity<prestadore>(entity =>
        {
            entity.HasKey(e => e.id_prestador).HasName("pk_prestadores");

            entity.HasIndex(e => e.id_usuario, "idx_prestador_usuario");

            entity.HasIndex(e => e.ruc, "uq_prestador_ruc").IsUnique();

            entity.HasIndex(e => e.id_usuario, "uq_prestador_usuario").IsUnique();

            entity.Property(e => e.id_prestador).UseIdentityAlwaysColumn();
            entity.Property(e => e.anios_experiencia).HasDefaultValue(0);
            entity.Property(e => e.aprobado).HasDefaultValue(false);
            entity.Property(e => e.cantidad_reservas).HasDefaultValue(0);
            entity.Property(e => e.cantidad_servicios).HasDefaultValue(0);
            entity.Property(e => e.correo).HasMaxLength(150);
            entity.Property(e => e.documento_identidad).HasMaxLength(20);
            entity.Property(e => e.facebook).HasMaxLength(255);
            entity.Property(e => e.fecha_registro)
                .HasDefaultValueSql("CURRENT_TIMESTAMP")
                .HasColumnType("timestamp without time zone");
            entity.Property(e => e.instagram).HasMaxLength(255);
            entity.Property(e => e.nombre_comercial).HasMaxLength(200);
            entity.Property(e => e.pagina_web).HasMaxLength(255);
            entity.Property(e => e.promedio_calificacion)
                .HasPrecision(3, 2)
                .HasDefaultValueSql("0");
            entity.Property(e => e.razon_social).HasMaxLength(200);
            entity.Property(e => e.ruc).HasMaxLength(20);
            entity.Property(e => e.telefono).HasMaxLength(20);
            entity.Property(e => e.verificado).HasDefaultValue(false);

            entity.HasOne(d => d.id_usuarioNavigation).WithOne(p => p.prestadore)
                .HasForeignKey<prestadore>(d => d.id_usuario)
                .HasConstraintName("fk_prestador_usuario");
        });

        modelBuilder.Entity<reporte>(entity =>
        {
            entity.HasKey(e => e.id_reporte).HasName("pk_reportes");

            entity.Property(e => e.id_reporte).UseIdentityAlwaysColumn();
            entity.Property(e => e.estado)
                .HasMaxLength(20)
                .HasDefaultValueSql("'PENDIENTE'::character varying");
            entity.Property(e => e.fecha_creacion)
                .HasDefaultValueSql("CURRENT_TIMESTAMP")
                .HasColumnType("timestamp without time zone");
            entity.Property(e => e.motivo).HasMaxLength(100);

            entity.HasOne(d => d.id_servicioNavigation).WithMany(p => p.reportes)
                .HasForeignKey(d => d.id_servicio)
                .OnDelete(DeleteBehavior.Cascade)
                .HasConstraintName("fk_reporte_servicio");

            entity.HasOne(d => d.id_usuarioNavigation).WithMany(p => p.reportes)
                .HasForeignKey(d => d.id_usuario)
                .HasConstraintName("fk_reporte_usuario");
        });

        modelBuilder.Entity<reserva>(entity =>
        {
            entity.HasKey(e => e.id_reserva).HasName("pk_reservas");

            entity.HasIndex(e => e.estado, "idx_reserva_estado");

            entity.HasIndex(e => e.fecha_reserva, "idx_reserva_fecha");

            entity.HasIndex(e => e.id_servicio, "idx_reserva_servicio");

            entity.HasIndex(e => e.id_usuario, "idx_reserva_usuario");

            entity.HasIndex(e => e.codigo_reserva, "uq_codigo_reserva").IsUnique();

            entity.Property(e => e.id_reserva).UseIdentityAlwaysColumn();
            entity.Property(e => e.codigo_reserva).HasMaxLength(30);
            entity.Property(e => e.estado)
                .HasMaxLength(20)
                .HasDefaultValueSql("'PENDIENTE'::character varying");
            entity.Property(e => e.estado_pago)
                .HasMaxLength(20)
                .HasDefaultValueSql("'PENDIENTE'::character varying");
            entity.Property(e => e.fecha_actualizacion).HasColumnType("timestamp without time zone");
            entity.Property(e => e.fecha_cancelacion).HasColumnType("timestamp without time zone");
            entity.Property(e => e.fecha_creacion)
                .HasDefaultValueSql("CURRENT_TIMESTAMP")
                .HasColumnType("timestamp without time zone");
            entity.Property(e => e.metodo_pago).HasMaxLength(30);
            entity.Property(e => e.precio_total).HasPrecision(10, 2);
            entity.Property(e => e.precio_unitario).HasPrecision(10, 2);

            entity.HasOne(d => d.id_servicioNavigation).WithMany(p => p.reservas)
                .HasForeignKey(d => d.id_servicio)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("fk_reserva_servicio");

            entity.HasOne(d => d.id_usuarioNavigation).WithMany(p => p.reservas)
                .HasForeignKey(d => d.id_usuario)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("fk_reserva_usuario");
        });

        modelBuilder.Entity<role>(entity =>
        {
            entity.HasKey(e => e.id_rol).HasName("pk_roles");

            entity.HasIndex(e => e.nombre, "uq_rol").IsUnique();

            entity.Property(e => e.id_rol).UseIdentityAlwaysColumn();
            entity.Property(e => e.activo).HasDefaultValue(true);
            entity.Property(e => e.descripcion).HasMaxLength(200);
            entity.Property(e => e.fecha_creacion)
                .HasDefaultValueSql("CURRENT_TIMESTAMP")
                .HasColumnType("timestamp without time zone");
            entity.Property(e => e.nombre).HasMaxLength(50);

            entity.HasMany(d => d.id_permisos).WithMany(p => p.id_rols)
                .UsingEntity<Dictionary<string, object>>(
                    "rol_permiso",
                    r => r.HasOne<permiso>().WithMany()
                        .HasForeignKey("id_permiso")
                        .HasConstraintName("fk_rp_permiso"),
                    l => l.HasOne<role>().WithMany()
                        .HasForeignKey("id_rol")
                        .HasConstraintName("fk_rp_rol"),
                    j =>
                    {
                        j.HasKey("id_rol", "id_permiso").HasName("pk_rol_permiso");
                        j.ToTable("rol_permisos");
                    });
        });

        modelBuilder.Entity<servicio>(entity =>
        {
            entity.HasKey(e => e.id_servicio).HasName("pk_servicios");

            entity.HasIndex(e => e.activo, "idx_servicio_activo");

            entity.HasIndex(e => e.id_categoria, "idx_servicio_categoria");

            entity.HasIndex(e => e.estado, "idx_servicio_estado");

            entity.HasIndex(e => e.id_prestador, "idx_servicio_prestador");

            entity.HasIndex(e => e.id_ubicacion, "idx_servicio_ubicacion");

            entity.Property(e => e.id_servicio).UseIdentityAlwaysColumn();
            entity.Property(e => e.activo).HasDefaultValue(true);
            entity.Property(e => e.calificacion_promedio)
                .HasPrecision(3, 2)
                .HasDefaultValueSql("0");
            entity.Property(e => e.cancelacion_gratuita).HasDefaultValue(false);
            entity.Property(e => e.destacado).HasDefaultValue(false);
            entity.Property(e => e.duracion_estimada).HasMaxLength(50);
            entity.Property(e => e.edad_minima).HasDefaultValue(0);
            entity.Property(e => e.estado)
                .HasMaxLength(20)
                .HasDefaultValueSql("'ACTIVO'::character varying");
            entity.Property(e => e.fecha_actualizacion).HasColumnType("timestamp without time zone");
            entity.Property(e => e.fecha_creacion)
                .HasDefaultValueSql("CURRENT_TIMESTAMP")
                .HasColumnType("timestamp without time zone");
            entity.Property(e => e.moneda)
                .HasMaxLength(10)
                .HasDefaultValueSql("'PEN'::character varying");
            entity.Property(e => e.nombre).HasMaxLength(150);
            entity.Property(e => e.precio).HasPrecision(10, 2);
            entity.Property(e => e.requiere_reserva).HasDefaultValue(true);
            entity.Property(e => e.unidad_cobro).HasMaxLength(20);

            entity.HasOne(d => d.id_categoriaNavigation).WithMany(p => p.servicios)
                .HasForeignKey(d => d.id_categoria)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("fk_servicio_categoria");

            entity.HasOne(d => d.id_prestadorNavigation).WithMany(p => p.servicios)
                .HasForeignKey(d => d.id_prestador)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("fk_servicio_prestador");

            entity.HasOne(d => d.id_ubicacionNavigation).WithMany(p => p.servicios)
                .HasForeignKey(d => d.id_ubicacion)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("fk_servicio_ubicacion");
        });

        modelBuilder.Entity<tokens_dispositivo>(entity =>
        {
            entity.HasKey(e => e.id_token).HasName("pk_tokens");

            entity.HasIndex(e => e.id_usuario, "idx_token_usuario");

            entity.Property(e => e.id_token).UseIdentityAlwaysColumn();
            entity.Property(e => e.activo).HasDefaultValue(true);
            entity.Property(e => e.fecha_registro)
                .HasDefaultValueSql("CURRENT_TIMESTAMP")
                .HasColumnType("timestamp without time zone");
            entity.Property(e => e.plataforma).HasMaxLength(20);

            entity.HasOne(d => d.id_usuarioNavigation).WithMany(p => p.tokens_dispositivos)
                .HasForeignKey(d => d.id_usuario)
                .HasConstraintName("fk_token_usuario");
        });

        modelBuilder.Entity<ubicacione>(entity =>
        {
            entity.HasKey(e => e.id_ubicacion).HasName("pk_ubicaciones");

            entity.HasIndex(e => e.departamento, "idx_ubicacion_departamento");

            entity.HasIndex(e => e.distrito, "idx_ubicacion_distrito");

            entity.HasIndex(e => e.provincia, "idx_ubicacion_provincia");

            entity.Property(e => e.id_ubicacion).UseIdentityAlwaysColumn();
            entity.Property(e => e.departamento).HasMaxLength(100);
            entity.Property(e => e.direccion).HasMaxLength(250);
            entity.Property(e => e.distrito).HasMaxLength(100);
            entity.Property(e => e.fecha_creacion)
                .HasDefaultValueSql("CURRENT_TIMESTAMP")
                .HasColumnType("timestamp without time zone");
            entity.Property(e => e.latitud).HasPrecision(10, 8);
            entity.Property(e => e.longitud).HasPrecision(11, 8);
            entity.Property(e => e.nombre_lugar).HasMaxLength(150);
            entity.Property(e => e.pais)
                .HasMaxLength(100)
                .HasDefaultValueSql("'Perú'::character varying");
            entity.Property(e => e.plus_code).HasMaxLength(50);
            entity.Property(e => e.provincia).HasMaxLength(100);
            entity.Property(e => e.radio_cobertura_km).HasPrecision(5, 2);
            entity.Property(e => e.referencia).HasMaxLength(250);
        });

        modelBuilder.Entity<usuario>(entity =>
        {
            entity.HasKey(e => e.id_usuario).HasName("pk_usuarios");

            entity.HasIndex(e => e.activo, "idx_usuario_activo");

            entity.HasIndex(e => e.correo, "idx_usuario_correo");

            entity.HasIndex(e => e.id_rol, "idx_usuario_rol");

            entity.HasIndex(e => e.correo, "uq_usuario_correo").IsUnique();

            entity.Property(e => e.id_usuario).UseIdentityAlwaysColumn();
            entity.Property(e => e.activo).HasDefaultValue(true);
            entity.Property(e => e.apellido).HasMaxLength(100);
            entity.Property(e => e.ciudad).HasMaxLength(100);
            entity.Property(e => e.correo).HasMaxLength(150);
            entity.Property(e => e.correo_verificado).HasDefaultValue(false);
            entity.Property(e => e.expiracion_token).HasColumnType("timestamp without time zone");
            entity.Property(e => e.fecha_actualizacion).HasColumnType("timestamp without time zone");
            entity.Property(e => e.fecha_creacion)
                .HasDefaultValueSql("CURRENT_TIMESTAMP")
                .HasColumnType("timestamp without time zone");
            entity.Property(e => e.idioma)
                .HasMaxLength(50)
                .HasDefaultValueSql("'Español'::character varying");
            entity.Property(e => e.nacionalidad).HasMaxLength(100);
            entity.Property(e => e.nombre).HasMaxLength(100);
            entity.Property(e => e.password_hash).HasMaxLength(255);
            entity.Property(e => e.telefono).HasMaxLength(20);
            entity.Property(e => e.token_recuperacion).HasMaxLength(255);
            entity.Property(e => e.ultimo_login).HasColumnType("timestamp without time zone");

            entity.HasOne(d => d.id_rolNavigation).WithMany(p => p.usuarios)
                .HasForeignKey(d => d.id_rol)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("fk_usuario_rol");
        });

        OnModelCreatingPartial(modelBuilder);
    }

    partial void OnModelCreatingPartial(ModelBuilder modelBuilder);
}
