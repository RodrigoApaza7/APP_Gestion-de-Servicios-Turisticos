--
-- PostgreSQL database dump
--

\restrict hoZKn1qHuLMzS2fOvxU6djJVn0dd3ZMi67DX0s8MfMFaYxajtGCC4NNzP91veIS

-- Dumped from database version 18.4
-- Dumped by pg_dump version 18.4

-- Started on 2026-08-04 16:36:24

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 267 (class 1259 OID 25686)
-- Name: auditoria; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.auditoria (
    id_auditoria integer NOT NULL,
    id_usuario integer,
    tabla character varying(80),
    accion character varying(20),
    descripcion text,
    ip character varying(50),
    fecha timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.auditoria OWNER TO postgres;

--
-- TOC entry 266 (class 1259 OID 25685)
-- Name: auditoria_id_auditoria_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.auditoria ALTER COLUMN id_auditoria ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.auditoria_id_auditoria_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 244 (class 1259 OID 25458)
-- Name: calificaciones; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.calificaciones (
    id_calificacion integer NOT NULL,
    id_usuario integer NOT NULL,
    id_servicio integer NOT NULL,
    puntuacion smallint NOT NULL,
    comentario text,
    fecha_creacion timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    editado boolean DEFAULT false,
    CONSTRAINT chk_puntuacion CHECK (((puntuacion >= 1) AND (puntuacion <= 5)))
);


ALTER TABLE public.calificaciones OWNER TO postgres;

--
-- TOC entry 243 (class 1259 OID 25457)
-- Name: calificaciones_id_calificacion_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.calificaciones ALTER COLUMN id_calificacion ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.calificaciones_id_calificacion_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 220 (class 1259 OID 25138)
-- Name: categorias; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.categorias (
    id_categoria integer NOT NULL,
    nombre character varying(80) NOT NULL,
    descripcion text,
    icono character varying(100),
    color character varying(20),
    activo boolean DEFAULT true NOT NULL,
    fecha_creacion timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.categorias OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 25137)
-- Name: categorias_id_categoria_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.categorias ALTER COLUMN id_categoria ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.categorias_id_categoria_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 240 (class 1259 OID 25404)
-- Name: conversaciones; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.conversaciones (
    id_conversacion integer NOT NULL,
    id_usuario1 integer NOT NULL,
    id_usuario2 integer NOT NULL,
    activa boolean DEFAULT true,
    fecha_creacion timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    fecha_ultimo_mensaje timestamp without time zone,
    CONSTRAINT chk_conversacion CHECK ((id_usuario1 <> id_usuario2))
);


ALTER TABLE public.conversaciones OWNER TO postgres;

--
-- TOC entry 239 (class 1259 OID 25403)
-- Name: conversaciones_id_conversacion_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.conversaciones ALTER COLUMN id_conversacion ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.conversaciones_id_conversacion_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 238 (class 1259 OID 25377)
-- Name: detalle_itinerario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.detalle_itinerario (
    id_detalle integer NOT NULL,
    id_itinerario integer NOT NULL,
    id_servicio integer NOT NULL,
    fecha date,
    hora time without time zone,
    orden smallint NOT NULL,
    notas text
);


ALTER TABLE public.detalle_itinerario OWNER TO postgres;

--
-- TOC entry 237 (class 1259 OID 25376)
-- Name: detalle_itinerario_id_detalle_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.detalle_itinerario ALTER COLUMN id_detalle ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.detalle_itinerario_id_detalle_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 232 (class 1259 OID 25294)
-- Name: favoritos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.favoritos (
    id_favorito integer NOT NULL,
    id_usuario integer NOT NULL,
    id_servicio integer NOT NULL,
    fecha_agregado timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.favoritos OWNER TO postgres;

--
-- TOC entry 231 (class 1259 OID 25293)
-- Name: favoritos_id_favorito_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.favoritos ALTER COLUMN id_favorito ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.favoritos_id_favorito_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 256 (class 1259 OID 25583)
-- Name: favoritos_itinerarios; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.favoritos_itinerarios (
    id_favorito_itinerario integer NOT NULL,
    id_usuario integer NOT NULL,
    id_itinerario integer NOT NULL,
    fecha_agregado timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.favoritos_itinerarios OWNER TO postgres;

--
-- TOC entry 255 (class 1259 OID 25582)
-- Name: favoritos_itinerarios_id_favorito_itinerario_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.favoritos_itinerarios ALTER COLUMN id_favorito_itinerario ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.favoritos_itinerarios_id_favorito_itinerario_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 250 (class 1259 OID 25529)
-- Name: historial_estados_reserva; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.historial_estados_reserva (
    id_historial integer NOT NULL,
    id_reserva integer NOT NULL,
    estado_anterior character varying(20),
    estado_nuevo character varying(20),
    fecha timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    observacion text
);


ALTER TABLE public.historial_estados_reserva OWNER TO postgres;

--
-- TOC entry 249 (class 1259 OID 25528)
-- Name: historial_estados_reserva_id_historial_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.historial_estados_reserva ALTER COLUMN id_historial ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.historial_estados_reserva_id_historial_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 228 (class 1259 OID 25251)
-- Name: horarios_servicio; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.horarios_servicio (
    id_horario integer NOT NULL,
    id_servicio integer NOT NULL,
    dia_semana smallint NOT NULL,
    hora_apertura time without time zone NOT NULL,
    hora_cierre time without time zone NOT NULL,
    abierto boolean DEFAULT true NOT NULL,
    observaciones character varying(300),
    fecha_creacion timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT chk_dia CHECK (((dia_semana >= 1) AND (dia_semana <= 7))),
    CONSTRAINT chk_horas CHECK ((hora_apertura < hora_cierre))
);


ALTER TABLE public.horarios_servicio OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 25250)
-- Name: horarios_servicio_id_horario_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.horarios_servicio ALTER COLUMN id_horario ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.horarios_servicio_id_horario_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 230 (class 1259 OID 25273)
-- Name: imagenes_servicio; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.imagenes_servicio (
    id_imagen integer NOT NULL,
    id_servicio integer NOT NULL,
    url_imagen text NOT NULL,
    descripcion character varying(250),
    principal boolean DEFAULT false,
    orden smallint DEFAULT 1,
    tipo character varying(20) DEFAULT 'JPG'::character varying,
    peso_kb integer,
    fecha_subida timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.imagenes_servicio OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 25272)
-- Name: imagenes_servicio_id_imagen_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.imagenes_servicio ALTER COLUMN id_imagen ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.imagenes_servicio_id_imagen_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 254 (class 1259 OID 25564)
-- Name: imagenes_usuarios; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.imagenes_usuarios (
    id_imagen_usuario integer NOT NULL,
    id_usuario integer NOT NULL,
    url_imagen text NOT NULL,
    principal boolean DEFAULT true,
    fecha_subida timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.imagenes_usuarios OWNER TO postgres;

--
-- TOC entry 253 (class 1259 OID 25563)
-- Name: imagenes_usuarios_id_imagen_usuario_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.imagenes_usuarios ALTER COLUMN id_imagen_usuario ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.imagenes_usuarios_id_imagen_usuario_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 236 (class 1259 OID 25357)
-- Name: itinerarios; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.itinerarios (
    id_itinerario integer NOT NULL,
    id_usuario integer NOT NULL,
    nombre character varying(150) NOT NULL,
    descripcion text,
    imagen_portada text,
    fecha_inicio date,
    fecha_fin date,
    compartido boolean DEFAULT false,
    publico boolean DEFAULT false,
    fecha_creacion timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion timestamp without time zone
);


ALTER TABLE public.itinerarios OWNER TO postgres;

--
-- TOC entry 235 (class 1259 OID 25356)
-- Name: itinerarios_id_itinerario_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.itinerarios ALTER COLUMN id_itinerario ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.itinerarios_id_itinerario_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 242 (class 1259 OID 25430)
-- Name: mensajes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.mensajes (
    id_mensaje integer NOT NULL,
    id_conversacion integer NOT NULL,
    id_emisor integer NOT NULL,
    mensaje text,
    tipo character varying(20) DEFAULT 'TEXTO'::character varying,
    archivo text,
    leido boolean DEFAULT false,
    fecha_envio timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    fecha_lectura timestamp without time zone,
    eliminado boolean DEFAULT false,
    CONSTRAINT chk_tipo_mensaje CHECK (((tipo)::text = ANY ((ARRAY['TEXTO'::character varying, 'IMAGEN'::character varying, 'VIDEO'::character varying, 'AUDIO'::character varying, 'DOCUMENTO'::character varying, 'UBICACION'::character varying])::text[])))
);


ALTER TABLE public.mensajes OWNER TO postgres;

--
-- TOC entry 241 (class 1259 OID 25429)
-- Name: mensajes_id_mensaje_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.mensajes ALTER COLUMN id_mensaje ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.mensajes_id_mensaje_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 265 (class 1259 OID 25668)
-- Name: multimedia_chat; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.multimedia_chat (
    id_multimedia integer NOT NULL,
    id_mensaje integer NOT NULL,
    url_archivo text NOT NULL,
    tipo character varying(20),
    peso_kb integer,
    ancho integer,
    alto integer,
    duracion integer,
    fecha_subida timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.multimedia_chat OWNER TO postgres;

--
-- TOC entry 264 (class 1259 OID 25667)
-- Name: multimedia_chat_id_multimedia_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.multimedia_chat ALTER COLUMN id_multimedia ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.multimedia_chat_id_multimedia_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 246 (class 1259 OID 25486)
-- Name: notificaciones; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.notificaciones (
    id_notificacion integer NOT NULL,
    id_usuario integer NOT NULL,
    titulo character varying(150) NOT NULL,
    mensaje text NOT NULL,
    tipo character varying(30),
    url_destino text,
    icono character varying(100),
    prioridad smallint DEFAULT 2,
    leida boolean DEFAULT false,
    fecha_creacion timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.notificaciones OWNER TO postgres;

--
-- TOC entry 245 (class 1259 OID 25485)
-- Name: notificaciones_id_notificacion_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.notificaciones ALTER COLUMN id_notificacion ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.notificaciones_id_notificacion_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 263 (class 1259 OID 25649)
-- Name: pagos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pagos (
    id_pago integer NOT NULL,
    id_reserva integer NOT NULL,
    monto numeric(10,2) NOT NULL,
    metodo character varying(30) NOT NULL,
    estado character varying(20) DEFAULT 'PENDIENTE'::character varying,
    referencia character varying(200),
    fecha_pago timestamp without time zone,
    comprobante text,
    observacion text
);


ALTER TABLE public.pagos OWNER TO postgres;

--
-- TOC entry 262 (class 1259 OID 25648)
-- Name: pagos_id_pago_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.pagos ALTER COLUMN id_pago ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.pagos_id_pago_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 260 (class 1259 OID 25620)
-- Name: permisos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.permisos (
    id_permiso integer NOT NULL,
    nombre character varying(80) NOT NULL,
    descripcion text
);


ALTER TABLE public.permisos OWNER TO postgres;

--
-- TOC entry 259 (class 1259 OID 25619)
-- Name: permisos_id_permiso_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.permisos ALTER COLUMN id_permiso ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.permisos_id_permiso_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 224 (class 1259 OID 25177)
-- Name: prestadores; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.prestadores (
    id_prestador integer NOT NULL,
    id_usuario integer NOT NULL,
    nombre_comercial character varying(200),
    descripcion text,
    documento_identidad character varying(20),
    razon_social character varying(200),
    ruc character varying(20),
    telefono character varying(20),
    correo character varying(150),
    pagina_web character varying(255),
    facebook character varying(255),
    instagram character varying(255),
    logo text,
    certificado text,
    anios_experiencia integer DEFAULT 0,
    promedio_calificacion numeric(3,2) DEFAULT 0,
    cantidad_servicios integer DEFAULT 0,
    cantidad_reservas integer DEFAULT 0,
    aprobado boolean DEFAULT false,
    verificado boolean DEFAULT false,
    fecha_registro timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.prestadores OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 25176)
-- Name: prestadores_id_prestador_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.prestadores ALTER COLUMN id_prestador ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.prestadores_id_prestador_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 248 (class 1259 OID 25507)
-- Name: reportes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.reportes (
    id_reporte integer NOT NULL,
    id_usuario integer NOT NULL,
    id_servicio integer,
    motivo character varying(100),
    descripcion text,
    estado character varying(20) DEFAULT 'PENDIENTE'::character varying,
    fecha_creacion timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.reportes OWNER TO postgres;

--
-- TOC entry 247 (class 1259 OID 25506)
-- Name: reportes_id_reporte_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.reportes ALTER COLUMN id_reporte ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.reportes_id_reporte_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 234 (class 1259 OID 25318)
-- Name: reservas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.reservas (
    id_reserva integer NOT NULL,
    codigo_reserva character varying(30) NOT NULL,
    id_usuario integer NOT NULL,
    id_servicio integer NOT NULL,
    fecha_reserva date NOT NULL,
    hora_reserva time without time zone,
    cantidad_personas integer NOT NULL,
    precio_unitario numeric(10,2) NOT NULL,
    precio_total numeric(10,2) NOT NULL,
    metodo_pago character varying(30),
    estado_pago character varying(20) DEFAULT 'PENDIENTE'::character varying,
    comprobante_pago text,
    estado character varying(20) DEFAULT 'PENDIENTE'::character varying,
    observaciones text,
    motivo_cancelacion text,
    fecha_cancelacion timestamp without time zone,
    fecha_creacion timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion timestamp without time zone,
    CONSTRAINT chk_estado_pago CHECK (((estado_pago)::text = ANY ((ARRAY['PENDIENTE'::character varying, 'PAGADO'::character varying, 'REEMBOLSADO'::character varying])::text[]))),
    CONSTRAINT chk_estado_reserva CHECK (((estado)::text = ANY ((ARRAY['PENDIENTE'::character varying, 'CONFIRMADA'::character varying, 'CANCELADA'::character varying, 'FINALIZADA'::character varying])::text[]))),
    CONSTRAINT chk_personas CHECK ((cantidad_personas > 0)),
    CONSTRAINT chk_precio CHECK ((precio_total >= (0)::numeric))
);


ALTER TABLE public.reservas OWNER TO postgres;

--
-- TOC entry 233 (class 1259 OID 25317)
-- Name: reservas_id_reserva_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.reservas ALTER COLUMN id_reserva ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.reservas_id_reserva_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 261 (class 1259 OID 25631)
-- Name: rol_permisos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.rol_permisos (
    id_rol integer NOT NULL,
    id_permiso integer NOT NULL
);


ALTER TABLE public.rol_permisos OWNER TO postgres;

--
-- TOC entry 258 (class 1259 OID 25608)
-- Name: roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.roles (
    id_rol integer NOT NULL,
    nombre character varying(50) NOT NULL,
    descripcion character varying(200),
    activo boolean DEFAULT true,
    fecha_creacion timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.roles OWNER TO postgres;

--
-- TOC entry 257 (class 1259 OID 25607)
-- Name: roles_id_rol_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.roles ALTER COLUMN id_rol ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.roles_id_rol_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 226 (class 1259 OID 25204)
-- Name: servicios; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.servicios (
    id_servicio integer NOT NULL,
    id_prestador integer NOT NULL,
    id_categoria integer NOT NULL,
    id_ubicacion integer NOT NULL,
    nombre character varying(150) NOT NULL,
    descripcion text,
    precio numeric(10,2) NOT NULL,
    moneda character varying(10) DEFAULT 'PEN'::character varying,
    unidad_cobro character varying(20) NOT NULL,
    duracion_estimada character varying(50),
    capacidad integer,
    aforo_maximo integer,
    requiere_reserva boolean DEFAULT true,
    cancelacion_gratuita boolean DEFAULT false,
    edad_minima integer DEFAULT 0,
    incluye text,
    no_incluye text,
    politicas text,
    estado character varying(20) DEFAULT 'ACTIVO'::character varying,
    destacado boolean DEFAULT false,
    activo boolean DEFAULT true,
    calificacion_promedio numeric(3,2) DEFAULT 0,
    fecha_creacion timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion timestamp without time zone,
    CONSTRAINT chk_edad CHECK ((edad_minima >= 0)),
    CONSTRAINT chk_estado_servicio CHECK (((estado)::text = ANY ((ARRAY['ACTIVO'::character varying, 'PAUSADO'::character varying, 'OCULTO'::character varying, 'SUSPENDIDO'::character varying])::text[]))),
    CONSTRAINT chk_precio CHECK ((precio >= (0)::numeric))
);


ALTER TABLE public.servicios OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 25203)
-- Name: servicios_id_servicio_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.servicios ALTER COLUMN id_servicio ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.servicios_id_servicio_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 252 (class 1259 OID 25545)
-- Name: tokens_dispositivos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tokens_dispositivos (
    id_token integer NOT NULL,
    id_usuario integer NOT NULL,
    token text NOT NULL,
    plataforma character varying(20),
    activo boolean DEFAULT true,
    fecha_registro timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.tokens_dispositivos OWNER TO postgres;

--
-- TOC entry 251 (class 1259 OID 25544)
-- Name: tokens_dispositivos_id_token_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.tokens_dispositivos ALTER COLUMN id_token ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.tokens_dispositivos_id_token_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 222 (class 1259 OID 25154)
-- Name: ubicaciones; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ubicaciones (
    id_ubicacion integer NOT NULL,
    nombre_lugar character varying(150),
    direccion character varying(250) NOT NULL,
    referencia character varying(250),
    distrito character varying(100) NOT NULL,
    provincia character varying(100) NOT NULL,
    departamento character varying(100) NOT NULL,
    pais character varying(100) DEFAULT 'Perú'::character varying NOT NULL,
    latitud numeric(10,8) NOT NULL,
    longitud numeric(11,8) NOT NULL,
    url_google_maps text,
    plus_code character varying(50),
    radio_cobertura_km numeric(5,2),
    fecha_creacion timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT chk_latitud CHECK (((latitud >= ('-90'::integer)::numeric) AND (latitud <= (90)::numeric))),
    CONSTRAINT chk_longitud CHECK (((longitud >= ('-180'::integer)::numeric) AND (longitud <= (180)::numeric)))
);


ALTER TABLE public.ubicaciones OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 25153)
-- Name: ubicaciones_id_ubicacion_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.ubicaciones ALTER COLUMN id_ubicacion ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.ubicaciones_id_ubicacion_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 269 (class 1259 OID 25702)
-- Name: usuarios; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuarios (
    id_usuario integer NOT NULL,
    id_rol integer NOT NULL,
    nombre character varying(100) NOT NULL,
    apellido character varying(100) NOT NULL,
    correo character varying(150) NOT NULL,
    password_hash character varying(255) NOT NULL,
    telefono character varying(20),
    foto_perfil text,
    fecha_nacimiento date,
    nacionalidad character varying(100),
    ciudad character varying(100),
    idioma character varying(50) DEFAULT 'Español'::character varying,
    correo_verificado boolean DEFAULT false NOT NULL,
    token_recuperacion character varying(255),
    expiracion_token timestamp without time zone,
    ultimo_login timestamp without time zone,
    activo boolean DEFAULT true NOT NULL,
    fecha_creacion timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    fecha_actualizacion timestamp without time zone
);


ALTER TABLE public.usuarios OWNER TO postgres;

--
-- TOC entry 268 (class 1259 OID 25701)
-- Name: usuarios_id_usuario_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.usuarios ALTER COLUMN id_usuario ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.usuarios_id_usuario_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 5396 (class 0 OID 25686)
-- Dependencies: 267
-- Data for Name: auditoria; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.auditoria (id_auditoria, id_usuario, tabla, accion, descripcion, ip, fecha) FROM stdin;
\.


--
-- TOC entry 5373 (class 0 OID 25458)
-- Dependencies: 244
-- Data for Name: calificaciones; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.calificaciones (id_calificacion, id_usuario, id_servicio, puntuacion, comentario, fecha_creacion, editado) FROM stdin;
\.


--
-- TOC entry 5349 (class 0 OID 25138)
-- Dependencies: 220
-- Data for Name: categorias; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.categorias (id_categoria, nombre, descripcion, icono, color, activo, fecha_creacion) FROM stdin;
1	Hoteles	Hospedajes y hoteles	hotel	#1565C0	t	2026-08-04 16:14:43.86727
2	Restaurantes	Restaurantes y cafeterías	restaurant	#EF6C00	t	2026-08-04 16:14:43.86727
3	Guías Turísticos	Guías certificados	person	#2E7D32	t	2026-08-04 16:14:43.86727
4	Transporte	Servicios de transporte	directions_bus	#6A1B9A	t	2026-08-04 16:14:43.86727
5	Tours	Excursiones y tours	map	#00897B	t	2026-08-04 16:14:43.86727
6	Artesanía	Tiendas artesanales	shopping_bag	#5D4037	t	2026-08-04 16:14:43.86727
7	Aventura	Turismo de aventura	terrain	#D84315	t	2026-08-04 16:14:43.86727
8	Museos	Museos y centros culturales	museum	#3949AB	t	2026-08-04 16:14:43.86727
9	Eventos	Eventos turísticos	event	#C62828	t	2026-08-04 16:14:43.86727
10	Fotografía	Fotografía profesional	photo_camera	#546E7A	t	2026-08-04 16:14:43.86727
11	Camping	Campamentos	park	#558B2F	t	2026-08-04 16:14:43.86727
12	Otros	Otros servicios	apps	#616161	t	2026-08-04 16:14:43.86727
\.


--
-- TOC entry 5369 (class 0 OID 25404)
-- Dependencies: 240
-- Data for Name: conversaciones; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.conversaciones (id_conversacion, id_usuario1, id_usuario2, activa, fecha_creacion, fecha_ultimo_mensaje) FROM stdin;
\.


--
-- TOC entry 5367 (class 0 OID 25377)
-- Dependencies: 238
-- Data for Name: detalle_itinerario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.detalle_itinerario (id_detalle, id_itinerario, id_servicio, fecha, hora, orden, notas) FROM stdin;
\.


--
-- TOC entry 5361 (class 0 OID 25294)
-- Dependencies: 232
-- Data for Name: favoritos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.favoritos (id_favorito, id_usuario, id_servicio, fecha_agregado) FROM stdin;
\.


--
-- TOC entry 5385 (class 0 OID 25583)
-- Dependencies: 256
-- Data for Name: favoritos_itinerarios; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.favoritos_itinerarios (id_favorito_itinerario, id_usuario, id_itinerario, fecha_agregado) FROM stdin;
\.


--
-- TOC entry 5379 (class 0 OID 25529)
-- Dependencies: 250
-- Data for Name: historial_estados_reserva; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.historial_estados_reserva (id_historial, id_reserva, estado_anterior, estado_nuevo, fecha, observacion) FROM stdin;
\.


--
-- TOC entry 5357 (class 0 OID 25251)
-- Dependencies: 228
-- Data for Name: horarios_servicio; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.horarios_servicio (id_horario, id_servicio, dia_semana, hora_apertura, hora_cierre, abierto, observaciones, fecha_creacion) FROM stdin;
\.


--
-- TOC entry 5359 (class 0 OID 25273)
-- Dependencies: 230
-- Data for Name: imagenes_servicio; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.imagenes_servicio (id_imagen, id_servicio, url_imagen, descripcion, principal, orden, tipo, peso_kb, fecha_subida) FROM stdin;
\.


--
-- TOC entry 5383 (class 0 OID 25564)
-- Dependencies: 254
-- Data for Name: imagenes_usuarios; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.imagenes_usuarios (id_imagen_usuario, id_usuario, url_imagen, principal, fecha_subida) FROM stdin;
\.


--
-- TOC entry 5365 (class 0 OID 25357)
-- Dependencies: 236
-- Data for Name: itinerarios; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.itinerarios (id_itinerario, id_usuario, nombre, descripcion, imagen_portada, fecha_inicio, fecha_fin, compartido, publico, fecha_creacion, fecha_actualizacion) FROM stdin;
\.


--
-- TOC entry 5371 (class 0 OID 25430)
-- Dependencies: 242
-- Data for Name: mensajes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.mensajes (id_mensaje, id_conversacion, id_emisor, mensaje, tipo, archivo, leido, fecha_envio, fecha_lectura, eliminado) FROM stdin;
\.


--
-- TOC entry 5394 (class 0 OID 25668)
-- Dependencies: 265
-- Data for Name: multimedia_chat; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.multimedia_chat (id_multimedia, id_mensaje, url_archivo, tipo, peso_kb, ancho, alto, duracion, fecha_subida) FROM stdin;
\.


--
-- TOC entry 5375 (class 0 OID 25486)
-- Dependencies: 246
-- Data for Name: notificaciones; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.notificaciones (id_notificacion, id_usuario, titulo, mensaje, tipo, url_destino, icono, prioridad, leida, fecha_creacion) FROM stdin;
\.


--
-- TOC entry 5392 (class 0 OID 25649)
-- Dependencies: 263
-- Data for Name: pagos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pagos (id_pago, id_reserva, monto, metodo, estado, referencia, fecha_pago, comprobante, observacion) FROM stdin;
\.


--
-- TOC entry 5389 (class 0 OID 25620)
-- Dependencies: 260
-- Data for Name: permisos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.permisos (id_permiso, nombre, descripcion) FROM stdin;
\.


--
-- TOC entry 5353 (class 0 OID 25177)
-- Dependencies: 224
-- Data for Name: prestadores; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.prestadores (id_prestador, id_usuario, nombre_comercial, descripcion, documento_identidad, razon_social, ruc, telefono, correo, pagina_web, facebook, instagram, logo, certificado, anios_experiencia, promedio_calificacion, cantidad_servicios, cantidad_reservas, aprobado, verificado, fecha_registro) FROM stdin;
\.


--
-- TOC entry 5377 (class 0 OID 25507)
-- Dependencies: 248
-- Data for Name: reportes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.reportes (id_reporte, id_usuario, id_servicio, motivo, descripcion, estado, fecha_creacion) FROM stdin;
\.


--
-- TOC entry 5363 (class 0 OID 25318)
-- Dependencies: 234
-- Data for Name: reservas; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.reservas (id_reserva, codigo_reserva, id_usuario, id_servicio, fecha_reserva, hora_reserva, cantidad_personas, precio_unitario, precio_total, metodo_pago, estado_pago, comprobante_pago, estado, observaciones, motivo_cancelacion, fecha_cancelacion, fecha_creacion, fecha_actualizacion) FROM stdin;
\.


--
-- TOC entry 5390 (class 0 OID 25631)
-- Dependencies: 261
-- Data for Name: rol_permisos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.rol_permisos (id_rol, id_permiso) FROM stdin;
\.


--
-- TOC entry 5387 (class 0 OID 25608)
-- Dependencies: 258
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.roles (id_rol, nombre, descripcion, activo, fecha_creacion) FROM stdin;
1	ADMIN	Administrador del sistema	t	2026-08-04 16:19:25.352092
2	PRESTADOR	Prestador de servicios	t	2026-08-04 16:19:25.352092
3	TURISTA	Usuario turista	t	2026-08-04 16:19:25.352092
\.


--
-- TOC entry 5355 (class 0 OID 25204)
-- Dependencies: 226
-- Data for Name: servicios; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.servicios (id_servicio, id_prestador, id_categoria, id_ubicacion, nombre, descripcion, precio, moneda, unidad_cobro, duracion_estimada, capacidad, aforo_maximo, requiere_reserva, cancelacion_gratuita, edad_minima, incluye, no_incluye, politicas, estado, destacado, activo, calificacion_promedio, fecha_creacion, fecha_actualizacion) FROM stdin;
\.


--
-- TOC entry 5381 (class 0 OID 25545)
-- Dependencies: 252
-- Data for Name: tokens_dispositivos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tokens_dispositivos (id_token, id_usuario, token, plataforma, activo, fecha_registro) FROM stdin;
\.


--
-- TOC entry 5351 (class 0 OID 25154)
-- Dependencies: 222
-- Data for Name: ubicaciones; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ubicaciones (id_ubicacion, nombre_lugar, direccion, referencia, distrito, provincia, departamento, pais, latitud, longitud, url_google_maps, plus_code, radio_cobertura_km, fecha_creacion) FROM stdin;
\.


--
-- TOC entry 5398 (class 0 OID 25702)
-- Dependencies: 269
-- Data for Name: usuarios; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.usuarios (id_usuario, id_rol, nombre, apellido, correo, password_hash, telefono, foto_perfil, fecha_nacimiento, nacionalidad, ciudad, idioma, correo_verificado, token_recuperacion, expiracion_token, ultimo_login, activo, fecha_creacion, fecha_actualizacion) FROM stdin;
\.


--
-- TOC entry 5404 (class 0 OID 0)
-- Dependencies: 266
-- Name: auditoria_id_auditoria_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.auditoria_id_auditoria_seq', 1, false);


--
-- TOC entry 5405 (class 0 OID 0)
-- Dependencies: 243
-- Name: calificaciones_id_calificacion_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.calificaciones_id_calificacion_seq', 1, false);


--
-- TOC entry 5406 (class 0 OID 0)
-- Dependencies: 219
-- Name: categorias_id_categoria_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.categorias_id_categoria_seq', 12, true);


--
-- TOC entry 5407 (class 0 OID 0)
-- Dependencies: 239
-- Name: conversaciones_id_conversacion_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.conversaciones_id_conversacion_seq', 1, false);


--
-- TOC entry 5408 (class 0 OID 0)
-- Dependencies: 237
-- Name: detalle_itinerario_id_detalle_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.detalle_itinerario_id_detalle_seq', 1, false);


--
-- TOC entry 5409 (class 0 OID 0)
-- Dependencies: 231
-- Name: favoritos_id_favorito_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.favoritos_id_favorito_seq', 1, false);


--
-- TOC entry 5410 (class 0 OID 0)
-- Dependencies: 255
-- Name: favoritos_itinerarios_id_favorito_itinerario_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.favoritos_itinerarios_id_favorito_itinerario_seq', 1, false);


--
-- TOC entry 5411 (class 0 OID 0)
-- Dependencies: 249
-- Name: historial_estados_reserva_id_historial_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.historial_estados_reserva_id_historial_seq', 1, false);


--
-- TOC entry 5412 (class 0 OID 0)
-- Dependencies: 227
-- Name: horarios_servicio_id_horario_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.horarios_servicio_id_horario_seq', 1, false);


--
-- TOC entry 5413 (class 0 OID 0)
-- Dependencies: 229
-- Name: imagenes_servicio_id_imagen_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.imagenes_servicio_id_imagen_seq', 1, false);


--
-- TOC entry 5414 (class 0 OID 0)
-- Dependencies: 253
-- Name: imagenes_usuarios_id_imagen_usuario_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.imagenes_usuarios_id_imagen_usuario_seq', 1, false);


--
-- TOC entry 5415 (class 0 OID 0)
-- Dependencies: 235
-- Name: itinerarios_id_itinerario_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.itinerarios_id_itinerario_seq', 1, false);


--
-- TOC entry 5416 (class 0 OID 0)
-- Dependencies: 241
-- Name: mensajes_id_mensaje_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.mensajes_id_mensaje_seq', 1, false);


--
-- TOC entry 5417 (class 0 OID 0)
-- Dependencies: 264
-- Name: multimedia_chat_id_multimedia_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.multimedia_chat_id_multimedia_seq', 1, false);


--
-- TOC entry 5418 (class 0 OID 0)
-- Dependencies: 245
-- Name: notificaciones_id_notificacion_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.notificaciones_id_notificacion_seq', 1, false);


--
-- TOC entry 5419 (class 0 OID 0)
-- Dependencies: 262
-- Name: pagos_id_pago_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pagos_id_pago_seq', 1, false);


--
-- TOC entry 5420 (class 0 OID 0)
-- Dependencies: 259
-- Name: permisos_id_permiso_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.permisos_id_permiso_seq', 1, false);


--
-- TOC entry 5421 (class 0 OID 0)
-- Dependencies: 223
-- Name: prestadores_id_prestador_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.prestadores_id_prestador_seq', 1, false);


--
-- TOC entry 5422 (class 0 OID 0)
-- Dependencies: 247
-- Name: reportes_id_reporte_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.reportes_id_reporte_seq', 1, false);


--
-- TOC entry 5423 (class 0 OID 0)
-- Dependencies: 233
-- Name: reservas_id_reserva_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.reservas_id_reserva_seq', 1, false);


--
-- TOC entry 5424 (class 0 OID 0)
-- Dependencies: 257
-- Name: roles_id_rol_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.roles_id_rol_seq', 3, true);


--
-- TOC entry 5425 (class 0 OID 0)
-- Dependencies: 225
-- Name: servicios_id_servicio_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.servicios_id_servicio_seq', 1, false);


--
-- TOC entry 5426 (class 0 OID 0)
-- Dependencies: 251
-- Name: tokens_dispositivos_id_token_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tokens_dispositivos_id_token_seq', 1, false);


--
-- TOC entry 5427 (class 0 OID 0)
-- Dependencies: 221
-- Name: ubicaciones_id_ubicacion_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ubicaciones_id_ubicacion_seq', 1, false);


--
-- TOC entry 5428 (class 0 OID 0)
-- Dependencies: 268
-- Name: usuarios_id_usuario_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.usuarios_id_usuario_seq', 1, false);


--
-- TOC entry 5160 (class 2606 OID 25694)
-- Name: auditoria pk_auditoria; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.auditoria
    ADD CONSTRAINT pk_auditoria PRIMARY KEY (id_auditoria);


--
-- TOC entry 5120 (class 2606 OID 25471)
-- Name: calificaciones pk_calificaciones; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.calificaciones
    ADD CONSTRAINT pk_calificaciones PRIMARY KEY (id_calificacion);


--
-- TOC entry 5057 (class 2606 OID 25149)
-- Name: categorias pk_categorias; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categorias
    ADD CONSTRAINT pk_categorias PRIMARY KEY (id_categoria);


--
-- TOC entry 5111 (class 2606 OID 25414)
-- Name: conversaciones pk_conversaciones; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.conversaciones
    ADD CONSTRAINT pk_conversaciones PRIMARY KEY (id_conversacion);


--
-- TOC entry 5105 (class 2606 OID 25387)
-- Name: detalle_itinerario pk_detalle; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.detalle_itinerario
    ADD CONSTRAINT pk_detalle PRIMARY KEY (id_detalle);


--
-- TOC entry 5088 (class 2606 OID 25302)
-- Name: favoritos pk_favoritos; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.favoritos
    ADD CONSTRAINT pk_favoritos PRIMARY KEY (id_favorito);


--
-- TOC entry 5139 (class 2606 OID 25591)
-- Name: favoritos_itinerarios pk_favoritos_itinerarios; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.favoritos_itinerarios
    ADD CONSTRAINT pk_favoritos_itinerarios PRIMARY KEY (id_favorito_itinerario);


--
-- TOC entry 5129 (class 2606 OID 25538)
-- Name: historial_estados_reserva pk_historial; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.historial_estados_reserva
    ADD CONSTRAINT pk_historial PRIMARY KEY (id_historial);


--
-- TOC entry 5081 (class 2606 OID 25265)
-- Name: horarios_servicio pk_horarios; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.horarios_servicio
    ADD CONSTRAINT pk_horarios PRIMARY KEY (id_horario);


--
-- TOC entry 5135 (class 2606 OID 25575)
-- Name: imagenes_usuarios pk_imagen_usuario; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.imagenes_usuarios
    ADD CONSTRAINT pk_imagen_usuario PRIMARY KEY (id_imagen_usuario);


--
-- TOC entry 5084 (class 2606 OID 25286)
-- Name: imagenes_servicio pk_imagenes; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.imagenes_servicio
    ADD CONSTRAINT pk_imagenes PRIMARY KEY (id_imagen);


--
-- TOC entry 5101 (class 2606 OID 25369)
-- Name: itinerarios pk_itinerarios; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.itinerarios
    ADD CONSTRAINT pk_itinerarios PRIMARY KEY (id_itinerario);


--
-- TOC entry 5117 (class 2606 OID 25444)
-- Name: mensajes pk_mensajes; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mensajes
    ADD CONSTRAINT pk_mensajes PRIMARY KEY (id_mensaje);


--
-- TOC entry 5157 (class 2606 OID 25678)
-- Name: multimedia_chat pk_multimedia; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.multimedia_chat
    ADD CONSTRAINT pk_multimedia PRIMARY KEY (id_multimedia);


--
-- TOC entry 5125 (class 2606 OID 25499)
-- Name: notificaciones pk_notificaciones; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.notificaciones
    ADD CONSTRAINT pk_notificaciones PRIMARY KEY (id_notificacion);


--
-- TOC entry 5154 (class 2606 OID 25660)
-- Name: pagos pk_pagos; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pagos
    ADD CONSTRAINT pk_pagos PRIMARY KEY (id_pago);


--
-- TOC entry 5147 (class 2606 OID 25628)
-- Name: permisos pk_permisos; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.permisos
    ADD CONSTRAINT pk_permisos PRIMARY KEY (id_permiso);


--
-- TOC entry 5067 (class 2606 OID 25192)
-- Name: prestadores pk_prestadores; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prestadores
    ADD CONSTRAINT pk_prestadores PRIMARY KEY (id_prestador);


--
-- TOC entry 5127 (class 2606 OID 25517)
-- Name: reportes pk_reportes; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reportes
    ADD CONSTRAINT pk_reportes PRIMARY KEY (id_reporte);


--
-- TOC entry 5096 (class 2606 OID 25339)
-- Name: reservas pk_reservas; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reservas
    ADD CONSTRAINT pk_reservas PRIMARY KEY (id_reserva);


--
-- TOC entry 5151 (class 2606 OID 25637)
-- Name: rol_permisos pk_rol_permiso; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rol_permisos
    ADD CONSTRAINT pk_rol_permiso PRIMARY KEY (id_rol, id_permiso);


--
-- TOC entry 5143 (class 2606 OID 25616)
-- Name: roles pk_roles; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT pk_roles PRIMARY KEY (id_rol);


--
-- TOC entry 5078 (class 2606 OID 25229)
-- Name: servicios pk_servicios; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.servicios
    ADD CONSTRAINT pk_servicios PRIMARY KEY (id_servicio);


--
-- TOC entry 5132 (class 2606 OID 25556)
-- Name: tokens_dispositivos pk_tokens; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tokens_dispositivos
    ADD CONSTRAINT pk_tokens PRIMARY KEY (id_token);


--
-- TOC entry 5064 (class 2606 OID 25172)
-- Name: ubicaciones pk_ubicaciones; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ubicaciones
    ADD CONSTRAINT pk_ubicaciones PRIMARY KEY (id_ubicacion);


--
-- TOC entry 5165 (class 2606 OID 25721)
-- Name: usuarios pk_usuarios; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT pk_usuarios PRIMARY KEY (id_usuario);


--
-- TOC entry 5122 (class 2606 OID 25473)
-- Name: calificaciones uq_calificacion; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.calificaciones
    ADD CONSTRAINT uq_calificacion UNIQUE (id_usuario, id_servicio);


--
-- TOC entry 5059 (class 2606 OID 25151)
-- Name: categorias uq_categoria_nombre; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categorias
    ADD CONSTRAINT uq_categoria_nombre UNIQUE (nombre);


--
-- TOC entry 5098 (class 2606 OID 25341)
-- Name: reservas uq_codigo_reserva; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reservas
    ADD CONSTRAINT uq_codigo_reserva UNIQUE (codigo_reserva);


--
-- TOC entry 5113 (class 2606 OID 25416)
-- Name: conversaciones uq_conversacion; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.conversaciones
    ADD CONSTRAINT uq_conversacion UNIQUE (id_usuario1, id_usuario2);


--
-- TOC entry 5107 (class 2606 OID 25389)
-- Name: detalle_itinerario uq_detalle; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.detalle_itinerario
    ADD CONSTRAINT uq_detalle UNIQUE (id_itinerario, orden);


--
-- TOC entry 5090 (class 2606 OID 25304)
-- Name: favoritos uq_favorito; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.favoritos
    ADD CONSTRAINT uq_favorito UNIQUE (id_usuario, id_servicio);


--
-- TOC entry 5141 (class 2606 OID 25593)
-- Name: favoritos_itinerarios uq_favorito_itinerario; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.favoritos_itinerarios
    ADD CONSTRAINT uq_favorito_itinerario UNIQUE (id_usuario, id_itinerario);


--
-- TOC entry 5149 (class 2606 OID 25630)
-- Name: permisos uq_permiso; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.permisos
    ADD CONSTRAINT uq_permiso UNIQUE (nombre);


--
-- TOC entry 5069 (class 2606 OID 25196)
-- Name: prestadores uq_prestador_ruc; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prestadores
    ADD CONSTRAINT uq_prestador_ruc UNIQUE (ruc);


--
-- TOC entry 5071 (class 2606 OID 25194)
-- Name: prestadores uq_prestador_usuario; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prestadores
    ADD CONSTRAINT uq_prestador_usuario UNIQUE (id_usuario);


--
-- TOC entry 5145 (class 2606 OID 25618)
-- Name: roles uq_rol; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT uq_rol UNIQUE (nombre);


--
-- TOC entry 5167 (class 2606 OID 25723)
-- Name: usuarios uq_usuario_correo; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT uq_usuario_correo UNIQUE (correo);


--
-- TOC entry 5158 (class 1259 OID 25700)
-- Name: idx_auditoria_usuario; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_auditoria_usuario ON public.auditoria USING btree (id_usuario);


--
-- TOC entry 5118 (class 1259 OID 25484)
-- Name: idx_calificacion_servicio; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_calificacion_servicio ON public.calificaciones USING btree (id_servicio);


--
-- TOC entry 5055 (class 1259 OID 25152)
-- Name: idx_categoria_nombre; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_categoria_nombre ON public.categorias USING btree (nombre);


--
-- TOC entry 5108 (class 1259 OID 25427)
-- Name: idx_conversacion_usuario1; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_conversacion_usuario1 ON public.conversaciones USING btree (id_usuario1);


--
-- TOC entry 5109 (class 1259 OID 25428)
-- Name: idx_conversacion_usuario2; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_conversacion_usuario2 ON public.conversaciones USING btree (id_usuario2);


--
-- TOC entry 5102 (class 1259 OID 25400)
-- Name: idx_detalle_itinerario; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_detalle_itinerario ON public.detalle_itinerario USING btree (id_itinerario);


--
-- TOC entry 5103 (class 1259 OID 25401)
-- Name: idx_detalle_servicio; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_detalle_servicio ON public.detalle_itinerario USING btree (id_servicio);


--
-- TOC entry 5136 (class 1259 OID 25605)
-- Name: idx_favorito_itinerario; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_favorito_itinerario ON public.favoritos_itinerarios USING btree (id_itinerario);


--
-- TOC entry 5137 (class 1259 OID 25604)
-- Name: idx_favorito_itinerario_usuario; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_favorito_itinerario_usuario ON public.favoritos_itinerarios USING btree (id_usuario);


--
-- TOC entry 5085 (class 1259 OID 25316)
-- Name: idx_favorito_servicio; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_favorito_servicio ON public.favoritos USING btree (id_servicio);


--
-- TOC entry 5086 (class 1259 OID 25315)
-- Name: idx_favorito_usuario; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_favorito_usuario ON public.favoritos USING btree (id_usuario);


--
-- TOC entry 5079 (class 1259 OID 25271)
-- Name: idx_horario_servicio; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_horario_servicio ON public.horarios_servicio USING btree (id_servicio);


--
-- TOC entry 5082 (class 1259 OID 25292)
-- Name: idx_imagen_servicio; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_imagen_servicio ON public.imagenes_servicio USING btree (id_servicio);


--
-- TOC entry 5133 (class 1259 OID 25581)
-- Name: idx_imagen_usuario; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_imagen_usuario ON public.imagenes_usuarios USING btree (id_usuario);


--
-- TOC entry 5099 (class 1259 OID 25375)
-- Name: idx_itinerario_usuario; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_itinerario_usuario ON public.itinerarios USING btree (id_usuario);


--
-- TOC entry 5114 (class 1259 OID 25455)
-- Name: idx_mensaje_conversacion; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_mensaje_conversacion ON public.mensajes USING btree (id_conversacion);


--
-- TOC entry 5115 (class 1259 OID 25456)
-- Name: idx_mensaje_fecha; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_mensaje_fecha ON public.mensajes USING btree (fecha_envio);


--
-- TOC entry 5155 (class 1259 OID 25684)
-- Name: idx_multimedia_mensaje; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_multimedia_mensaje ON public.multimedia_chat USING btree (id_mensaje);


--
-- TOC entry 5123 (class 1259 OID 25505)
-- Name: idx_notificacion_usuario; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_notificacion_usuario ON public.notificaciones USING btree (id_usuario);


--
-- TOC entry 5152 (class 1259 OID 25666)
-- Name: idx_pago_reserva; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_pago_reserva ON public.pagos USING btree (id_reserva);


--
-- TOC entry 5065 (class 1259 OID 25202)
-- Name: idx_prestador_usuario; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_prestador_usuario ON public.prestadores USING btree (id_usuario);


--
-- TOC entry 5091 (class 1259 OID 25354)
-- Name: idx_reserva_estado; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_reserva_estado ON public.reservas USING btree (estado);


--
-- TOC entry 5092 (class 1259 OID 25355)
-- Name: idx_reserva_fecha; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_reserva_fecha ON public.reservas USING btree (fecha_reserva);


--
-- TOC entry 5093 (class 1259 OID 25353)
-- Name: idx_reserva_servicio; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_reserva_servicio ON public.reservas USING btree (id_servicio);


--
-- TOC entry 5094 (class 1259 OID 25352)
-- Name: idx_reserva_usuario; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_reserva_usuario ON public.reservas USING btree (id_usuario);


--
-- TOC entry 5072 (class 1259 OID 25249)
-- Name: idx_servicio_activo; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_servicio_activo ON public.servicios USING btree (activo);


--
-- TOC entry 5073 (class 1259 OID 25245)
-- Name: idx_servicio_categoria; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_servicio_categoria ON public.servicios USING btree (id_categoria);


--
-- TOC entry 5074 (class 1259 OID 25248)
-- Name: idx_servicio_estado; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_servicio_estado ON public.servicios USING btree (estado);


--
-- TOC entry 5075 (class 1259 OID 25246)
-- Name: idx_servicio_prestador; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_servicio_prestador ON public.servicios USING btree (id_prestador);


--
-- TOC entry 5076 (class 1259 OID 25247)
-- Name: idx_servicio_ubicacion; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_servicio_ubicacion ON public.servicios USING btree (id_ubicacion);


--
-- TOC entry 5130 (class 1259 OID 25562)
-- Name: idx_token_usuario; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_token_usuario ON public.tokens_dispositivos USING btree (id_usuario);


--
-- TOC entry 5060 (class 1259 OID 25173)
-- Name: idx_ubicacion_departamento; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_ubicacion_departamento ON public.ubicaciones USING btree (departamento);


--
-- TOC entry 5061 (class 1259 OID 25175)
-- Name: idx_ubicacion_distrito; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_ubicacion_distrito ON public.ubicaciones USING btree (distrito);


--
-- TOC entry 5062 (class 1259 OID 25174)
-- Name: idx_ubicacion_provincia; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_ubicacion_provincia ON public.ubicaciones USING btree (provincia);


--
-- TOC entry 5161 (class 1259 OID 25731)
-- Name: idx_usuario_activo; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_usuario_activo ON public.usuarios USING btree (activo);


--
-- TOC entry 5162 (class 1259 OID 25729)
-- Name: idx_usuario_correo; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_usuario_correo ON public.usuarios USING btree (correo);


--
-- TOC entry 5163 (class 1259 OID 25730)
-- Name: idx_usuario_rol; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_usuario_rol ON public.usuarios USING btree (id_rol);


--
-- TOC entry 5199 (class 2606 OID 25787)
-- Name: auditoria fk_auditoria_usuario; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.auditoria
    ADD CONSTRAINT fk_auditoria_usuario FOREIGN KEY (id_usuario) REFERENCES public.usuarios(id_usuario) ON DELETE SET NULL;


--
-- TOC entry 5185 (class 2606 OID 25479)
-- Name: calificaciones fk_calificacion_servicio; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.calificaciones
    ADD CONSTRAINT fk_calificacion_servicio FOREIGN KEY (id_servicio) REFERENCES public.servicios(id_servicio) ON DELETE CASCADE;


--
-- TOC entry 5186 (class 2606 OID 25767)
-- Name: calificaciones fk_calificacion_usuario; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.calificaciones
    ADD CONSTRAINT fk_calificacion_usuario FOREIGN KEY (id_usuario) REFERENCES public.usuarios(id_usuario) ON DELETE CASCADE;


--
-- TOC entry 5181 (class 2606 OID 25752)
-- Name: conversaciones fk_conversacion_usuario1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.conversaciones
    ADD CONSTRAINT fk_conversacion_usuario1 FOREIGN KEY (id_usuario1) REFERENCES public.usuarios(id_usuario) ON DELETE CASCADE;


--
-- TOC entry 5182 (class 2606 OID 25757)
-- Name: conversaciones fk_conversacion_usuario2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.conversaciones
    ADD CONSTRAINT fk_conversacion_usuario2 FOREIGN KEY (id_usuario2) REFERENCES public.usuarios(id_usuario) ON DELETE CASCADE;


--
-- TOC entry 5179 (class 2606 OID 25390)
-- Name: detalle_itinerario fk_detalle_itinerario; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.detalle_itinerario
    ADD CONSTRAINT fk_detalle_itinerario FOREIGN KEY (id_itinerario) REFERENCES public.itinerarios(id_itinerario) ON DELETE CASCADE;


--
-- TOC entry 5180 (class 2606 OID 25395)
-- Name: detalle_itinerario fk_detalle_servicio; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.detalle_itinerario
    ADD CONSTRAINT fk_detalle_servicio FOREIGN KEY (id_servicio) REFERENCES public.servicios(id_servicio);


--
-- TOC entry 5193 (class 2606 OID 25599)
-- Name: favoritos_itinerarios fk_favorito_itinerario; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.favoritos_itinerarios
    ADD CONSTRAINT fk_favorito_itinerario FOREIGN KEY (id_itinerario) REFERENCES public.itinerarios(id_itinerario) ON DELETE CASCADE;


--
-- TOC entry 5194 (class 2606 OID 25797)
-- Name: favoritos_itinerarios fk_favorito_itinerario_usuario; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.favoritos_itinerarios
    ADD CONSTRAINT fk_favorito_itinerario_usuario FOREIGN KEY (id_usuario) REFERENCES public.usuarios(id_usuario) ON DELETE CASCADE;


--
-- TOC entry 5174 (class 2606 OID 25310)
-- Name: favoritos fk_favorito_servicio; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.favoritos
    ADD CONSTRAINT fk_favorito_servicio FOREIGN KEY (id_servicio) REFERENCES public.servicios(id_servicio) ON DELETE CASCADE;


--
-- TOC entry 5175 (class 2606 OID 25737)
-- Name: favoritos fk_favorito_usuario; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.favoritos
    ADD CONSTRAINT fk_favorito_usuario FOREIGN KEY (id_usuario) REFERENCES public.usuarios(id_usuario) ON DELETE CASCADE;


--
-- TOC entry 5190 (class 2606 OID 25539)
-- Name: historial_estados_reserva fk_historial_reserva; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.historial_estados_reserva
    ADD CONSTRAINT fk_historial_reserva FOREIGN KEY (id_reserva) REFERENCES public.reservas(id_reserva) ON DELETE CASCADE;


--
-- TOC entry 5172 (class 2606 OID 25266)
-- Name: horarios_servicio fk_horario_servicio; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.horarios_servicio
    ADD CONSTRAINT fk_horario_servicio FOREIGN KEY (id_servicio) REFERENCES public.servicios(id_servicio) ON DELETE CASCADE;


--
-- TOC entry 5173 (class 2606 OID 25287)
-- Name: imagenes_servicio fk_imagen_servicio; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.imagenes_servicio
    ADD CONSTRAINT fk_imagen_servicio FOREIGN KEY (id_servicio) REFERENCES public.servicios(id_servicio) ON DELETE CASCADE;


--
-- TOC entry 5192 (class 2606 OID 25777)
-- Name: imagenes_usuarios fk_imagen_usuario; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.imagenes_usuarios
    ADD CONSTRAINT fk_imagen_usuario FOREIGN KEY (id_usuario) REFERENCES public.usuarios(id_usuario) ON DELETE CASCADE;


--
-- TOC entry 5178 (class 2606 OID 25747)
-- Name: itinerarios fk_itinerario_usuario; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.itinerarios
    ADD CONSTRAINT fk_itinerario_usuario FOREIGN KEY (id_usuario) REFERENCES public.usuarios(id_usuario) ON DELETE CASCADE;


--
-- TOC entry 5183 (class 2606 OID 25445)
-- Name: mensajes fk_mensaje_conversacion; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mensajes
    ADD CONSTRAINT fk_mensaje_conversacion FOREIGN KEY (id_conversacion) REFERENCES public.conversaciones(id_conversacion) ON DELETE CASCADE;


--
-- TOC entry 5184 (class 2606 OID 25762)
-- Name: mensajes fk_mensaje_emisor; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mensajes
    ADD CONSTRAINT fk_mensaje_emisor FOREIGN KEY (id_emisor) REFERENCES public.usuarios(id_usuario);


--
-- TOC entry 5198 (class 2606 OID 25679)
-- Name: multimedia_chat fk_multimedia_mensaje; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.multimedia_chat
    ADD CONSTRAINT fk_multimedia_mensaje FOREIGN KEY (id_mensaje) REFERENCES public.mensajes(id_mensaje) ON DELETE CASCADE;


--
-- TOC entry 5187 (class 2606 OID 25772)
-- Name: notificaciones fk_notificacion_usuario; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.notificaciones
    ADD CONSTRAINT fk_notificacion_usuario FOREIGN KEY (id_usuario) REFERENCES public.usuarios(id_usuario) ON DELETE CASCADE;


--
-- TOC entry 5197 (class 2606 OID 25661)
-- Name: pagos fk_pago_reserva; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pagos
    ADD CONSTRAINT fk_pago_reserva FOREIGN KEY (id_reserva) REFERENCES public.reservas(id_reserva) ON DELETE CASCADE;


--
-- TOC entry 5168 (class 2606 OID 25732)
-- Name: prestadores fk_prestador_usuario; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prestadores
    ADD CONSTRAINT fk_prestador_usuario FOREIGN KEY (id_usuario) REFERENCES public.usuarios(id_usuario) ON DELETE CASCADE;


--
-- TOC entry 5188 (class 2606 OID 25523)
-- Name: reportes fk_reporte_servicio; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reportes
    ADD CONSTRAINT fk_reporte_servicio FOREIGN KEY (id_servicio) REFERENCES public.servicios(id_servicio) ON DELETE CASCADE;


--
-- TOC entry 5189 (class 2606 OID 25792)
-- Name: reportes fk_reporte_usuario; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reportes
    ADD CONSTRAINT fk_reporte_usuario FOREIGN KEY (id_usuario) REFERENCES public.usuarios(id_usuario) ON DELETE CASCADE;


--
-- TOC entry 5176 (class 2606 OID 25347)
-- Name: reservas fk_reserva_servicio; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reservas
    ADD CONSTRAINT fk_reserva_servicio FOREIGN KEY (id_servicio) REFERENCES public.servicios(id_servicio);


--
-- TOC entry 5177 (class 2606 OID 25742)
-- Name: reservas fk_reserva_usuario; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reservas
    ADD CONSTRAINT fk_reserva_usuario FOREIGN KEY (id_usuario) REFERENCES public.usuarios(id_usuario);


--
-- TOC entry 5195 (class 2606 OID 25643)
-- Name: rol_permisos fk_rp_permiso; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rol_permisos
    ADD CONSTRAINT fk_rp_permiso FOREIGN KEY (id_permiso) REFERENCES public.permisos(id_permiso) ON DELETE CASCADE;


--
-- TOC entry 5196 (class 2606 OID 25638)
-- Name: rol_permisos fk_rp_rol; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rol_permisos
    ADD CONSTRAINT fk_rp_rol FOREIGN KEY (id_rol) REFERENCES public.roles(id_rol) ON DELETE CASCADE;


--
-- TOC entry 5169 (class 2606 OID 25235)
-- Name: servicios fk_servicio_categoria; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.servicios
    ADD CONSTRAINT fk_servicio_categoria FOREIGN KEY (id_categoria) REFERENCES public.categorias(id_categoria);


--
-- TOC entry 5170 (class 2606 OID 25230)
-- Name: servicios fk_servicio_prestador; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.servicios
    ADD CONSTRAINT fk_servicio_prestador FOREIGN KEY (id_prestador) REFERENCES public.prestadores(id_prestador);


--
-- TOC entry 5171 (class 2606 OID 25240)
-- Name: servicios fk_servicio_ubicacion; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.servicios
    ADD CONSTRAINT fk_servicio_ubicacion FOREIGN KEY (id_ubicacion) REFERENCES public.ubicaciones(id_ubicacion);


--
-- TOC entry 5191 (class 2606 OID 25782)
-- Name: tokens_dispositivos fk_token_usuario; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tokens_dispositivos
    ADD CONSTRAINT fk_token_usuario FOREIGN KEY (id_usuario) REFERENCES public.usuarios(id_usuario) ON DELETE CASCADE;


--
-- TOC entry 5200 (class 2606 OID 25724)
-- Name: usuarios fk_usuario_rol; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT fk_usuario_rol FOREIGN KEY (id_rol) REFERENCES public.roles(id_rol);


-- Completed on 2026-08-04 16:36:24

--
-- PostgreSQL database dump complete
--

\unrestrict hoZKn1qHuLMzS2fOvxU6djJVn0dd3ZMi67DX0s8MfMFaYxajtGCC4NNzP91veIS

