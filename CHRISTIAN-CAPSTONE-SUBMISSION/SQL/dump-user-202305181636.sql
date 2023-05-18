--
-- PostgreSQL database dump
--

-- Dumped from database version 15.2
-- Dumped by pg_dump version 15.2

-- Started on 2023-05-18 16:36:30

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
-- TOC entry 3330 (class 1262 OID 16648)
-- Name: user; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "user" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1252';


ALTER DATABASE "user" OWNER TO postgres;

\connect "user"

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
-- TOC entry 3331 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 215 (class 1259 OID 16650)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    user_id bigint NOT NULL,
    username character varying(255),
    password character varying(255),
    first_name character varying(255),
    middle_name character varying(255),
    last_name character varying(255),
    email character varying(255),
    phone_number character varying(255),
    account_non_locked boolean DEFAULT true NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 16649)
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO postgres;

--
-- TOC entry 3332 (class 0 OID 0)
-- Dependencies: 214
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.user_id;


--
-- TOC entry 3173 (class 2604 OID 16653)
-- Name: users user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN user_id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- TOC entry 3324 (class 0 OID 16650)
-- Dependencies: 215
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.users VALUES (1, 'admin', '$2a$12$ujzVGiQsVX04/iH7WYjMpO2fzpu4dTT4gWeuUzQXLsjFv.3pkuene', NULL, NULL, NULL, 'admin@email.com', NULL, true);
INSERT INTO public.users VALUES (2, 'client', '$2a$12$UVoWFFEILE2h3X1DUXQC5u7m3mRubPQ54256QWsSqLZZ22wkFB79e', NULL, NULL, NULL, 'client@email.com', NULL, true);
INSERT INTO public.users VALUES (3, 'john_doe', '$2a$12$ZttlX93mZnug/.GNe9QUGui/d0o9no4nboc7.aKShn/4erKqcwoZq', NULL, NULL, NULL, 'john.doe@example.com', NULL, true);
INSERT INTO public.users VALUES (4, 'christ', '$2a$10$/lEwsfIweTeCHGqpxJjWGOP3.Zp6uNDfX7Z9qbTTVA5abotESDl9.', 'John', 'J.', 'Doe', 'christianz@example.com', '1234567890', true);
INSERT INTO public.users VALUES (6, NULL, '$2a$10$8uq9IMuuofqc5JiwgSqBguScS75O6Vmz4KSVl/URpJCtLXb9TPXJe', 'JOHN', 'WILLIAM', 'DOE', 'example@email.com', '09564115461', true);


--
-- TOC entry 3333 (class 0 OID 0)
-- Dependencies: 214
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 6, true);


--
-- TOC entry 3176 (class 2606 OID 16662)
-- Name: users users_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_email_key UNIQUE (email);


--
-- TOC entry 3178 (class 2606 OID 16658)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);


--
-- TOC entry 3180 (class 2606 OID 16660)
-- Name: users users_username_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_username_key UNIQUE (username);


-- Completed on 2023-05-18 16:36:31

--
-- PostgreSQL database dump complete
--

