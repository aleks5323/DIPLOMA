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
-- Name: conversations; Type: TABLE; Schema: public; Owner: diplom
--

CREATE TABLE public.conversations (
    cid uuid NOT NULL,
    request text NOT NULL,
    req_date timestamp without time zone DEFAULT (now())::timestamp without time zone,
    response text,
    res_date timestamp without time zone,
    performed_by integer NOT NULL,
    authorname character varying(255),
    cstatus character varying(255)
);


ALTER TABLE public.conversations OWNER TO diplom;

--
-- Name: users_uid_seq; Type: SEQUENCE; Schema: public; Owner: diplom
--

CREATE SEQUENCE public.users_uid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
    CYCLE;


ALTER TABLE public.users_uid_seq OWNER TO diplom;

--
-- Name: users; Type: TABLE; Schema: public; Owner: diplom
--

CREATE TABLE public.users (
    uid integer DEFAULT nextval('public.users_uid_seq'::regclass) NOT NULL,
    uname character varying(16) NOT NULL,
    uemail character varying(64) NOT NULL,
    upassword character varying(32) NOT NULL
);


ALTER TABLE public.users OWNER TO diplom;

--
-- Name: conversations cid_pk; Type: CONSTRAINT; Schema: public; Owner: diplom
--

ALTER TABLE ONLY public.conversations
    ADD CONSTRAINT cid_pk PRIMARY KEY (cid);


--
-- Name: users uemail_uk; Type: CONSTRAINT; Schema: public; Owner: diplom
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT uemail_uk UNIQUE (uemail);


--
-- Name: users uid_pk; Type: CONSTRAINT; Schema: public; Owner: diplom
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT uid_pk PRIMARY KEY (uid);


--
-- Name: users uid_uk; Type: CONSTRAINT; Schema: public; Owner: diplom
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT uid_uk UNIQUE (uid);


--
-- Name: users uname_uk; Type: CONSTRAINT; Schema: public; Owner: diplom
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT uname_uk UNIQUE (uname);


--
-- PostgreSQL database dump complete
--

