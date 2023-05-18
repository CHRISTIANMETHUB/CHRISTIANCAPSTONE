--
-- PostgreSQL database dump
--

-- Dumped from database version 15.2
-- Dumped by pg_dump version 15.2

-- Started on 2023-05-18 16:35:15

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
-- TOC entry 3358 (class 1262 OID 16398)
-- Name: sport; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE sport WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1252';


ALTER DATABASE sport OWNER TO postgres;

\connect sport

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
-- TOC entry 3359 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 215 (class 1259 OID 16599)
-- Name: field; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.field (
    field_id integer NOT NULL,
    name character varying(255) NOT NULL,
    address character varying(255) NOT NULL,
    capacity integer NOT NULL
);


ALTER TABLE public.field OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 16598)
-- Name: field_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.field_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.field_id_seq OWNER TO postgres;

--
-- TOC entry 3360 (class 0 OID 0)
-- Dependencies: 214
-- Name: field_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.field_id_seq OWNED BY public.field.field_id;


--
-- TOC entry 219 (class 1259 OID 16618)
-- Name: match; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.match (
    match_id integer NOT NULL,
    field_id integer NOT NULL,
    tournament_id integer NOT NULL,
    teams_id character varying,
    date_time timestamp without time zone NOT NULL
);


ALTER TABLE public.match OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 16617)
-- Name: match_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.match_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.match_id_seq OWNER TO postgres;

--
-- TOC entry 3361 (class 0 OID 0)
-- Dependencies: 218
-- Name: match_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.match_id_seq OWNED BY public.match.match_id;


--
-- TOC entry 221 (class 1259 OID 16637)
-- Name: tickets; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tickets (
    id integer NOT NULL,
    customer_name character varying(255) NOT NULL,
    match_id integer NOT NULL,
    ticket_price numeric(10,2) NOT NULL
);


ALTER TABLE public.tickets OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 16636)
-- Name: tickets_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tickets_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tickets_id_seq OWNER TO postgres;

--
-- TOC entry 3362 (class 0 OID 0)
-- Dependencies: 220
-- Name: tickets_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tickets_id_seq OWNED BY public.tickets.id;


--
-- TOC entry 217 (class 1259 OID 16609)
-- Name: tournament; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tournament (
    tournament_id integer NOT NULL,
    name character varying(255) NOT NULL,
    category character varying(255) NOT NULL,
    style character varying(255) NOT NULL
);


ALTER TABLE public.tournament OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 16608)
-- Name: tournament_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tournament_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tournament_id_seq OWNER TO postgres;

--
-- TOC entry 3363 (class 0 OID 0)
-- Dependencies: 216
-- Name: tournament_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tournament_id_seq OWNED BY public.tournament.tournament_id;


--
-- TOC entry 3188 (class 2604 OID 16602)
-- Name: field field_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.field ALTER COLUMN field_id SET DEFAULT nextval('public.field_id_seq'::regclass);


--
-- TOC entry 3190 (class 2604 OID 16621)
-- Name: match match_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.match ALTER COLUMN match_id SET DEFAULT nextval('public.match_id_seq'::regclass);


--
-- TOC entry 3191 (class 2604 OID 16640)
-- Name: tickets id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tickets ALTER COLUMN id SET DEFAULT nextval('public.tickets_id_seq'::regclass);


--
-- TOC entry 3189 (class 2604 OID 16612)
-- Name: tournament tournament_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tournament ALTER COLUMN tournament_id SET DEFAULT nextval('public.tournament_id_seq'::regclass);


--
-- TOC entry 3346 (class 0 OID 16599)
-- Dependencies: 215
-- Data for Name: field; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.field VALUES (3, 'Field1', 'asd', 10);
INSERT INTO public.field VALUES (4, 'Field1', 'asd', 10);
INSERT INTO public.field VALUES (5, 'Field1', 'asd', 10);
INSERT INTO public.field VALUES (6, 'Field1', 'asd', 10);
INSERT INTO public.field VALUES (7, 'Field1', 'asd', 10);
INSERT INTO public.field VALUES (8, 'Field1', 'asd', 10);
INSERT INTO public.field VALUES (9, 'Field1', 'asd', 10);
INSERT INTO public.field VALUES (10, 'Field1', 'asd', 10);
INSERT INTO public.field VALUES (11, 'Example Venue', '123 Main Street, Anytown USA', 250);
INSERT INTO public.field VALUES (12, 'Example Venue', '123 Main Street, Anytown USA', 100);
INSERT INTO public.field VALUES (2, 'Example Venue', '123 Main Street, Anytown USA', 250);
INSERT INTO public.field VALUES (13, 'Example Venue', '123 Main Street, Anytown USA', 250);
INSERT INTO public.field VALUES (14, 'Field Name', 'Field Address', 100);


--
-- TOC entry 3350 (class 0 OID 16618)
-- Dependencies: 219
-- Data for Name: match; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.match VALUES (12, 3, 1, '789,1011', '2023-05-01 10:00:00');


--
-- TOC entry 3352 (class 0 OID 16637)
-- Dependencies: 221
-- Data for Name: tickets; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.tickets VALUES (2, 'John Doe', 12, 19.99);
INSERT INTO public.tickets VALUES (1, 'John Doe', 12, 29.99);
INSERT INTO public.tickets VALUES (3, 'John Doe', 12, 19.99);
INSERT INTO public.tickets VALUES (4, 'John Doe', 12, 19.99);


--
-- TOC entry 3348 (class 0 OID 16609)
-- Dependencies: 217
-- Data for Name: tournament; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.tournament VALUES (1, 'tournament', 'Basketball', '3 on 3');


--
-- TOC entry 3364 (class 0 OID 0)
-- Dependencies: 214
-- Name: field_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.field_id_seq', 14, true);


--
-- TOC entry 3365 (class 0 OID 0)
-- Dependencies: 218
-- Name: match_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.match_id_seq', 12, true);


--
-- TOC entry 3366 (class 0 OID 0)
-- Dependencies: 220
-- Name: tickets_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tickets_id_seq', 4, true);


--
-- TOC entry 3367 (class 0 OID 0)
-- Dependencies: 216
-- Name: tournament_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tournament_id_seq', 1, true);


--
-- TOC entry 3193 (class 2606 OID 16606)
-- Name: field field_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.field
    ADD CONSTRAINT field_pkey PRIMARY KEY (field_id);


--
-- TOC entry 3197 (class 2606 OID 16625)
-- Name: match match_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.match
    ADD CONSTRAINT match_pkey PRIMARY KEY (match_id);


--
-- TOC entry 3199 (class 2606 OID 16642)
-- Name: tickets tickets_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tickets
    ADD CONSTRAINT tickets_pkey PRIMARY KEY (id);


--
-- TOC entry 3195 (class 2606 OID 16616)
-- Name: tournament tournament_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tournament
    ADD CONSTRAINT tournament_pkey PRIMARY KEY (tournament_id);


--
-- TOC entry 3200 (class 2606 OID 16726)
-- Name: match match_field_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.match
    ADD CONSTRAINT match_field_id_fkey FOREIGN KEY (field_id) REFERENCES public.field(field_id) ON DELETE CASCADE;


--
-- TOC entry 3201 (class 2606 OID 16731)
-- Name: match match_tournament_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.match
    ADD CONSTRAINT match_tournament_id_fkey FOREIGN KEY (tournament_id) REFERENCES public.tournament(tournament_id) ON DELETE CASCADE;


--
-- TOC entry 3202 (class 2606 OID 16736)
-- Name: tickets tickets_match_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tickets
    ADD CONSTRAINT tickets_match_id_fkey FOREIGN KEY (match_id) REFERENCES public.match(match_id) ON DELETE CASCADE;


-- Completed on 2023-05-18 16:35:16

--
-- PostgreSQL database dump complete
--

