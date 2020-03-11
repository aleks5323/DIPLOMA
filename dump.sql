--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.16
-- Dumped by pg_dump version 12.2

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

--
-- Name: users; Type: TABLE; Schema: public; Owner: diplom
--

CREATE TABLE public.users (
    uid integer NOT NULL,
    uname character varying(16) NOT NULL,
    uemail character varying(64) NOT NULL,
    upassword character varying(32) NOT NULL
);


ALTER TABLE public.users OWNER TO diplom;

--
-- Name: users_uid_seq; Type: SEQUENCE; Schema: public; Owner: diplom
--

CREATE SEQUENCE public.users_uid_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_uid_seq OWNER TO diplom;

--
-- Name: users_uid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: diplom
--

ALTER SEQUENCE public.users_uid_seq OWNED BY public.users.uid;


--
-- Name: users uid; Type: DEFAULT; Schema: public; Owner: diplom
--

ALTER TABLE ONLY public.users ALTER COLUMN uid SET DEFAULT nextval('public.users_uid_seq'::regclass);


--
-- Name: users login; Type: CONSTRAINT; Schema: public; Owner: diplom
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT login PRIMARY KEY (uname);


--
-- Name: users uemail; Type: CONSTRAINT; Schema: public; Owner: diplom
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT uemail UNIQUE (uemail);


--
-- Name: users uid; Type: CONSTRAINT; Schema: public; Owner: diplom
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT uid UNIQUE (uid);


--
-- Name: users uname; Type: CONSTRAINT; Schema: public; Owner: diplom
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT uname UNIQUE (uname);


--
-- PostgreSQL database dump complete
--

