--
-- PostgreSQL database dump
--

-- Dumped from database version 15.3
-- Dumped by pg_dump version 15.3

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
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
-- Name: critica; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.critica (
    critica_id bigint NOT NULL,
    calificacion double precision,
    comentario character varying(255),
    usuario character varying(255),
    libro_id bigint
);


ALTER TABLE public.critica OWNER TO postgres;

--
-- Name: critica_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.critica_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.critica_seq OWNER TO postgres;

--
-- Name: genero; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.genero (
    codigo bigint NOT NULL,
    descripcion character varying(255),
    nombre character varying(255)
);


ALTER TABLE public.genero OWNER TO postgres;

--
-- Name: genero_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.genero_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.genero_seq OWNER TO postgres;

--
-- Name: libro; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.libro (
    libro_id bigint NOT NULL,
    anio_publicacion date,
    autor character varying(255),
    cantidad integer,
    editorial character varying(255),
    imagen character varying(255),
    isbn10 character varying(10),
    isbn13 character varying(13),
    sinopsis character varying(500),
    titulo character varying(255)
);


ALTER TABLE public.libro OWNER TO postgres;

--
-- Name: libro_genero; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.libro_genero (
    libro_id bigint NOT NULL,
    codigo bigint NOT NULL
);


ALTER TABLE public.libro_genero OWNER TO postgres;

--
-- Name: libro_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.libro_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.libro_seq OWNER TO postgres;


--
-- Name: critica_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.critica_seq', 1, false);


--
-- Name: genero_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.genero_seq', 1, false);


--
-- Name: libro_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.libro_seq', 1, false);


--
-- Name: critica critica_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.critica
    ADD CONSTRAINT critica_pkey PRIMARY KEY (critica_id);


--
-- Name: genero genero_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.genero
    ADD CONSTRAINT genero_pkey PRIMARY KEY (codigo);


--
-- Name: libro_genero libro_genero_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.libro_genero
    ADD CONSTRAINT libro_genero_pkey PRIMARY KEY (libro_id, codigo);


--
-- Name: libro libro_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.libro
    ADD CONSTRAINT libro_pkey PRIMARY KEY (libro_id);


--
-- Name: libro uk_7xpy6palqx64igktskfn8cn9u; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.libro
    ADD CONSTRAINT uk_7xpy6palqx64igktskfn8cn9u UNIQUE (isbn10);


--
-- Name: libro uk_iryuuuwuaak77rrkxkaoitfec; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.libro
    ADD CONSTRAINT uk_iryuuuwuaak77rrkxkaoitfec UNIQUE (isbn13);


--
-- Name: libro uk_qn9pauuvgqh324jmfru6wp6kj; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.libro
    ADD CONSTRAINT uk_qn9pauuvgqh324jmfru6wp6kj UNIQUE (titulo);


--
-- Name: libro_genero fk5prqkuhwgevdkt1dj5459t4k6; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.libro_genero
    ADD CONSTRAINT fk5prqkuhwgevdkt1dj5459t4k6 FOREIGN KEY (codigo) REFERENCES public.genero(codigo);


--
-- Name: libro_genero fk7875n3phxfpu8rnbxjvucl5k4; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.libro_genero
    ADD CONSTRAINT fk7875n3phxfpu8rnbxjvucl5k4 FOREIGN KEY (libro_id) REFERENCES public.libro(libro_id);


--
-- Name: critica fklhyfbbl8vx73rurb1mlwh1vet; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.critica
    ADD CONSTRAINT fklhyfbbl8vx73rurb1mlwh1vet FOREIGN KEY (libro_id) REFERENCES public.libro(libro_id);


--
-- PostgreSQL database dump complete
--

