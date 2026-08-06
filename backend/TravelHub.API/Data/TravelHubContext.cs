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

    public virtual DbSet<calificacione> calificaciones { get; set; }

    public virtual DbSet<categoria> categorias { get; set; }

    public virtual DbSet<conversacione> conversaciones { get; set; }

    public virtual DbSet<detalle_itinerario> detalle_itinerarios { get; set; }

    public virtual DbSet<disponibilidad> disponibilidads { get; set; }

    public virtual DbSet<favorito> favoritos { get; set; }

    public virtual DbSet<horarios_servicio> horarios_servicios { get; set; }

    public virtual DbSet<imagenes_servicio> imagenes_servicios { get; set; }

    public virtual DbSet<itinerario> itinerarios { get; set; }

    public virtual DbSet<mensaje> mensajes { get; set; }

    public virtual DbSet<notificacione> notificaciones { get; set; }

    public virtual DbSet<prestadore> prestadores { get; set; }

    public virtual DbSet<reserva> reservas { get; set; }

    public virtual DbSet<servicio> servicios { get; set; }

    public virtual DbSet<ubicacione> ubicaciones { get; set; }

    public virtual DbSet<usuario> usuarios { get; set; }

    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        modelBuilder.Entity<calificacione>(entity =>
        {
            entity.HasKey(e => e.id_calificacion).HasName("calificaciones_pkey");

            entity.Property(e => e.fecha)
                .HasDefaultValueSql("CURRENT_TIMESTAMP")
                .HasColumnType("timestamp without time zone");

            entity.HasOne(d => d.id_servicioNavigation).WithMany(p => p.calificaciones)
                .HasForeignKey(d => d.id_servicio)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("calificaciones_id_servicio_fkey");

            entity.HasOne(d => d.id_usuarioNavigation).WithMany(p => p.calificaciones)
                .HasForeignKey(d => d.id_usuario)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("calificaciones_id_usuario_fkey");
        });

        modelBuilder.Entity<categoria>(entity =>
        {
            entity.HasKey(e => e.id_categoria).HasName("categorias_pkey");

            entity.HasIndex(e => e.nombre, "categorias_nombre_key").IsUnique();

            entity.Property(e => e.nombre).HasMaxLength(50);
        });

        modelBuilder.Entity<conversacione>(entity =>
        {
            entity.HasKey(e => e.id_conversacion).HasName("conversaciones_pkey");

            entity.Property(e => e.fecha_creacion)
                .HasDefaultValueSql("CURRENT_TIMESTAMP")
                .HasColumnType("timestamp without time zone");

            entity.HasOne(d => d.id_usuario1Navigation).WithMany(p => p.conversacioneid_usuario1Navigations)
                .HasForeignKey(d => d.id_usuario1)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("fk_conversacion_usuario1");

            entity.HasOne(d => d.id_usuario2Navigation).WithMany(p => p.conversacioneid_usuario2Navigations)
                .HasForeignKey(d => d.id_usuario2)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("fk_conversacion_usuario2");
        });

        modelBuilder.Entity<detalle_itinerario>(entity =>
        {
            entity.HasKey(e => e.id_detalle).HasName("detalle_itinerario_pkey");

            entity.ToTable("detalle_itinerario");

            entity.HasOne(d => d.id_itinerarioNavigation).WithMany(p => p.detalle_itinerarios)
                .HasForeignKey(d => d.id_itinerario)
                .HasConstraintName("detalle_itinerario_id_itinerario_fkey");

            entity.HasOne(d => d.id_servicioNavigation).WithMany(p => p.detalle_itinerarios)
                .HasForeignKey(d => d.id_servicio)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("detalle_itinerario_id_servicio_fkey");
        });

        modelBuilder.Entity<disponibilidad>(entity =>
        {
            entity.HasKey(e => e.id_disponibilidad).HasName("disponibilidad_pkey");

            entity.ToTable("disponibilidad");

            entity.Property(e => e.disponible).HasDefaultValue(true);

            entity.HasOne(d => d.id_servicioNavigation).WithMany(p => p.disponibilidads)
                .HasForeignKey(d => d.id_servicio)
                .HasConstraintName("disponibilidad_id_servicio_fkey");
        });

        modelBuilder.Entity<favorito>(entity =>
        {
            entity.HasKey(e => e.id_favorito).HasName("favoritos_pkey");

            entity.HasIndex(e => new { e.id_usuario, e.id_servicio }, "favoritos_id_usuario_id_servicio_key").IsUnique();

            entity.Property(e => e.fecha)
                .HasDefaultValueSql("CURRENT_TIMESTAMP")
                .HasColumnType("timestamp without time zone");

            entity.HasOne(d => d.id_servicioNavigation).WithMany(p => p.favoritos)
                .HasForeignKey(d => d.id_servicio)
                .HasConstraintName("favoritos_id_servicio_fkey");

            entity.HasOne(d => d.id_usuarioNavigation).WithMany(p => p.favoritos)
                .HasForeignKey(d => d.id_usuario)
                .HasConstraintName("favoritos_id_usuario_fkey");
        });

        modelBuilder.Entity<horarios_servicio>(entity =>
        {
            entity.HasKey(e => e.id_horario).HasName("horarios_servicio_pkey");

            entity.ToTable("horarios_servicio");

            entity.Property(e => e.abierto).HasDefaultValue(true);
            entity.Property(e => e.dia_semana).HasMaxLength(15);
            entity.Property(e => e.observaciones).HasMaxLength(200);

            entity.HasOne(d => d.id_servicioNavigation).WithMany(p => p.horarios_servicios)
                .HasForeignKey(d => d.id_servicio)
                .HasConstraintName("horarios_servicio_id_servicio_fkey");
        });

        modelBuilder.Entity<imagenes_servicio>(entity =>
        {
            entity.HasKey(e => e.id_imagen).HasName("imagenes_servicio_pkey");

            entity.ToTable("imagenes_servicio");

            entity.Property(e => e.descripcion).HasMaxLength(100);
            entity.Property(e => e.orden).HasDefaultValue(1);
            entity.Property(e => e.principal).HasDefaultValue(false);

            entity.HasOne(d => d.id_servicioNavigation).WithMany(p => p.imagenes_servicios)
                .HasForeignKey(d => d.id_servicio)
                .HasConstraintName("imagenes_servicio_id_servicio_fkey");
        });

        modelBuilder.Entity<itinerario>(entity =>
        {
            entity.HasKey(e => e.id_itinerario).HasName("itinerarios_pkey");

            entity.Property(e => e.nombre).HasMaxLength(100);

            entity.HasOne(d => d.id_usuarioNavigation).WithMany(p => p.itinerarios)
                .HasForeignKey(d => d.id_usuario)
                .HasConstraintName("itinerarios_id_usuario_fkey");
        });

        modelBuilder.Entity<mensaje>(entity =>
        {
            entity.HasKey(e => e.id_mensaje).HasName("mensajes_pkey");

            entity.Property(e => e.fecha_envio)
                .HasDefaultValueSql("CURRENT_TIMESTAMP")
                .HasColumnType("timestamp without time zone");
            entity.Property(e => e.leido).HasDefaultValue(false);
            entity.Property(e => e.mensaje1).HasColumnName("mensaje");

            entity.HasOne(d => d.id_conversacionNavigation).WithMany(p => p.mensajes)
                .HasForeignKey(d => d.id_conversacion)
                .HasConstraintName("mensajes_id_conversacion_fkey");

            entity.HasOne(d => d.id_emisorNavigation).WithMany(p => p.mensajes)
                .HasForeignKey(d => d.id_emisor)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("mensajes_id_emisor_fkey");
        });

        modelBuilder.Entity<notificacione>(entity =>
        {
            entity.HasKey(e => e.id_notificacion).HasName("notificaciones_pkey");

            entity.Property(e => e.fecha)
                .HasDefaultValueSql("CURRENT_TIMESTAMP")
                .HasColumnType("timestamp without time zone");
            entity.Property(e => e.leida).HasDefaultValue(false);
            entity.Property(e => e.tipo).HasMaxLength(30);
            entity.Property(e => e.titulo).HasMaxLength(150);

            entity.HasOne(d => d.id_usuarioNavigation).WithMany(p => p.notificaciones)
                .HasForeignKey(d => d.id_usuario)
                .HasConstraintName("notificaciones_id_usuario_fkey");
        });

        modelBuilder.Entity<prestadore>(entity =>
        {
            entity.HasKey(e => e.id_prestador).HasName("prestadores_pkey");

            entity.HasIndex(e => e.id_usuario, "prestadores_id_usuario_key").IsUnique();

            entity.Property(e => e.anios_experiencia).HasDefaultValue(0);
            entity.Property(e => e.aprobado).HasDefaultValue(false);
            entity.Property(e => e.correo).HasMaxLength(150);
            entity.Property(e => e.documento_identidad).HasMaxLength(20);
            entity.Property(e => e.facebook).HasMaxLength(255);
            entity.Property(e => e.instagram).HasMaxLength(255);
            entity.Property(e => e.pagina_web).HasMaxLength(255);
            entity.Property(e => e.razon_social).HasMaxLength(200);
            entity.Property(e => e.ruc).HasMaxLength(20);
            entity.Property(e => e.telefono).HasMaxLength(20);
            entity.Property(e => e.verificado).HasDefaultValue(false);

            entity.HasOne(d => d.id_usuarioNavigation).WithOne(p => p.prestadore)
                .HasForeignKey<prestadore>(d => d.id_usuario)
                .HasConstraintName("prestadores_id_usuario_fkey");
        });

        modelBuilder.Entity<reserva>(entity =>
        {
            entity.HasKey(e => e.id_reserva).HasName("reservas_pkey");

            entity.Property(e => e.cantidad_personas).HasDefaultValue(1);
            entity.Property(e => e.estado)
                .HasMaxLength(20)
                .HasDefaultValueSql("'PENDIENTE'::character varying");
            entity.Property(e => e.fecha_reserva)
                .HasDefaultValueSql("CURRENT_TIMESTAMP")
                .HasColumnType("timestamp without time zone");
            entity.Property(e => e.metodo_pago).HasMaxLength(30);
            entity.Property(e => e.precio_total).HasPrecision(10, 2);

            entity.HasOne(d => d.id_servicioNavigation).WithMany(p => p.reservas)
                .HasForeignKey(d => d.id_servicio)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("reservas_id_servicio_fkey");

            entity.HasOne(d => d.id_usuarioNavigation).WithMany(p => p.reservas)
                .HasForeignKey(d => d.id_usuario)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("reservas_id_usuario_fkey");
        });

        modelBuilder.Entity<servicio>(entity =>
        {
            entity.HasKey(e => e.id_servicio).HasName("servicios_pkey");

            entity.Property(e => e.activo).HasDefaultValue(true);
            entity.Property(e => e.calificacion_promedio)
                .HasPrecision(3, 2)
                .HasDefaultValueSql("0");
            entity.Property(e => e.destacado).HasDefaultValue(false);
            entity.Property(e => e.estado)
                .HasMaxLength(20)
                .HasDefaultValueSql("'ACTIVO'::character varying");
            entity.Property(e => e.moneda)
                .HasMaxLength(10)
                .HasDefaultValueSql("'PEN'::character varying");
            entity.Property(e => e.nombre).HasMaxLength(150);
            entity.Property(e => e.precio).HasPrecision(10, 2);
            entity.Property(e => e.tipo_reserva).HasMaxLength(20);
            entity.Property(e => e.unidad_cobro).HasMaxLength(30);

            entity.HasOne(d => d.id_categoriaNavigation).WithMany(p => p.servicios)
                .HasForeignKey(d => d.id_categoria)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("servicios_id_categoria_fkey");

            entity.HasOne(d => d.id_prestadorNavigation).WithMany(p => p.servicios)
                .HasForeignKey(d => d.id_prestador)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("servicios_id_prestador_fkey");

            entity.HasOne(d => d.id_ubicacionNavigation).WithMany(p => p.servicios)
                .HasForeignKey(d => d.id_ubicacion)
                .HasConstraintName("fk_servicio_ubicacion");
        });

        modelBuilder.Entity<ubicacione>(entity =>
        {
            entity.HasKey(e => e.id_ubicacion).HasName("ubicaciones_pkey");

            entity.Property(e => e.departamento).HasMaxLength(100);
            entity.Property(e => e.direccion).HasMaxLength(250);
            entity.Property(e => e.distrito).HasMaxLength(100);
            entity.Property(e => e.latitud).HasPrecision(10, 8);
            entity.Property(e => e.longitud).HasPrecision(11, 8);
            entity.Property(e => e.nombre_lugar).HasMaxLength(150);
            entity.Property(e => e.pais)
                .HasMaxLength(100)
                .HasDefaultValueSql("'Perú'::character varying");
            entity.Property(e => e.provincia).HasMaxLength(100);
            entity.Property(e => e.referencia).HasMaxLength(250);
        });

        modelBuilder.Entity<usuario>(entity =>
        {
            entity.HasKey(e => e.id_usuario).HasName("usuarios_pkey");

            entity.HasIndex(e => e.correo, "usuarios_correo_key").IsUnique();

            entity.Property(e => e.activo).HasDefaultValue(true);
            entity.Property(e => e.apellido).HasMaxLength(100);
            entity.Property(e => e.ciudad).HasMaxLength(100);
            entity.Property(e => e.correo).HasMaxLength(150);
            entity.Property(e => e.fecha_registro)
                .HasDefaultValueSql("CURRENT_TIMESTAMP")
                .HasColumnType("timestamp without time zone");
            entity.Property(e => e.idioma)
                .HasMaxLength(50)
                .HasDefaultValueSql("'Español'::character varying");
            entity.Property(e => e.nacionalidad).HasMaxLength(100);
            entity.Property(e => e.nombre).HasMaxLength(100);
            entity.Property(e => e.password).HasMaxLength(255);
            entity.Property(e => e.rol).HasMaxLength(20);
            entity.Property(e => e.telefono).HasMaxLength(20);
        });

        OnModelCreatingPartial(modelBuilder);
    }

    partial void OnModelCreatingPartial(ModelBuilder modelBuilder);
}
