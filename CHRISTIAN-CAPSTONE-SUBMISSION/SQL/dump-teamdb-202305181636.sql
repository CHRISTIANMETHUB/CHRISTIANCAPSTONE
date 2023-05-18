--
-- PostgreSQL database dump
--

-- Dumped from database version 15.2
-- Dumped by pg_dump version 15.2

-- Started on 2023-05-18 16:36:17

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

--
-- TOC entry 3336 (class 1262 OID 16741)
-- Name: teamdb; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE teamdb WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1252';


ALTER DATABASE teamdb OWNER TO postgres;

\connect teamdb

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

--
-- TOC entry 4 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: pg_database_owner
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO pg_database_owner;

--
-- TOC entry 3337 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 217 (class 1259 OID 16771)
-- Name: player; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.player (
    id integer NOT NULL,
    first_name character varying(255) NOT NULL,
    last_name character varying(255) NOT NULL,
    country character varying(255) NOT NULL,
    team_id bigint NOT NULL
);


ALTER TABLE public.player OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 16770)
-- Name: player_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.player_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.player_id_seq OWNER TO postgres;

--
-- TOC entry 3338 (class 0 OID 0)
-- Dependencies: 216
-- Name: player_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.player_id_seq OWNED BY public.player.id;


--
-- TOC entry 215 (class 1259 OID 16764)
-- Name: team; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.team (
    id integer NOT NULL,
    team_name character varying(255) NOT NULL
);


ALTER TABLE public.team OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 16763)
-- Name: team_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.team_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.team_id_seq OWNER TO postgres;

--
-- TOC entry 3339 (class 0 OID 0)
-- Dependencies: 214
-- Name: team_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.team_id_seq OWNED BY public.team.id;


--
-- TOC entry 3179 (class 2604 OID 16774)
-- Name: player id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.player ALTER COLUMN id SET DEFAULT nextval('public.player_id_seq'::regclass);


--
-- TOC entry 3178 (class 2604 OID 16767)
-- Name: team id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.team ALTER COLUMN id SET DEFAULT nextval('public.team_id_seq'::regclass);


--
-- TOC entry 3330 (class 0 OID 16771)
-- Dependencies: 217
-- Data for Name: player; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.player VALUES (1, 'John', 'Doe', 'USA', 1);
INSERT INTO public.player VALUES (2, 'John', 'Doe', 'USA', 1);
INSERT INTO public.player VALUES (3, 'John', 'Doe', 'USA', 1);


--
-- TOC entry 3328 (class 0 OID 16764)
-- Dependencies: 215
-- Data for Name: team; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.team VALUES (1, 'Team A');
INSERT INTO public.team VALUES (2, 'Sample Team');
INSERT INTO public.team VALUES (3, 'teamName');
INSERT INTO public.team VALUES (4, 'teamName 2');
INSERT INTO public.team VALUES (5, 'Sample Team');


--
-- TOC entry 3340 (class 0 OID 0)
-- Dependencies: 216
-- Name: player_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.player_id_seq', 3, true);


--
-- TOC entry 3341 (class 0 OID 0)
-- Dependencies: 214
-- Name: team_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.team_id_seq', 5, true);


--
-- TOC entry 3183 (class 2606 OID 16778)
-- Name: player player_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.player
    ADD CONSTRAINT player_pkey PRIMARY KEY (id);


--
-- TOC entry 3181 (class 2606 OID 16769)
-- Name: team team_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.team
    ADD CONSTRAINT team_pkey PRIMARY KEY (id);


--
-- TOC entry 3184 (class 2606 OID 16779)
-- Name: player fk_team; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.player
    ADD CONSTRAINT fk_team FOREIGN KEY (team_id) REFERENCES public.team(id) ON DELETE CASCADE;


-- Completed on 2023-05-18 16:36:18

--
-- PostgreSQL database dump complete
--

