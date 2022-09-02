--
-- PostgreSQL database dump
--

-- Dumped from database version 14.2
-- Dumped by pg_dump version 14.2

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
-- Name: shopping; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA shopping;


ALTER SCHEMA shopping OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: buyers; Type: TABLE; Schema: shopping; Owner: postgres
--

CREATE TABLE shopping.buyers (
    id bigint NOT NULL,
    first_name character varying(255),
    last_name character varying(255)
);


ALTER TABLE shopping.buyers OWNER TO postgres;

--
-- Name: items; Type: TABLE; Schema: shopping; Owner: postgres
--

CREATE TABLE shopping.items (
    id bigint NOT NULL,
    price bigint,
    name character varying(255)
);


ALTER TABLE shopping.items OWNER TO postgres;

--
-- Name: purchases; Type: TABLE; Schema: shopping; Owner: postgres
--

CREATE TABLE shopping.purchases (
    id bigint NOT NULL,
    buyer_id bigint,
    date date,
    item_id bigint
);


ALTER TABLE shopping.purchases OWNER TO postgres;

--
-- Data for Name: buyers; Type: TABLE DATA; Schema: shopping; Owner: postgres
--

COPY shopping.buyers (id, first_name, last_name) FROM stdin;
3	Ivan	Shatalov
1	Artur	Sorento
2	John	Wolf
4	Maria	Types
5	Anna	Yerr
\.


--
-- Data for Name: items; Type: TABLE DATA; Schema: shopping; Owner: postgres
--

COPY shopping.items (id, price, name) FROM stdin;
1	123	Spoon
2	50	Tea
3	1000	Oreo
4	5000	Headphones
\.


--
-- Data for Name: purchases; Type: TABLE DATA; Schema: shopping; Owner: postgres
--

COPY shopping.purchases (id, buyer_id, date, item_id) FROM stdin;
1	1	2022-08-30	1
2	2	2021-08-26	2
3	3	2020-02-12	4
4	1	2022-09-01	1
5	2	2022-09-06	4
6	1	2000-01-01	4
7	5	2022-06-15	3
\.


--
-- Name: buyers buyers_pkey; Type: CONSTRAINT; Schema: shopping; Owner: postgres
--

ALTER TABLE ONLY shopping.buyers
    ADD CONSTRAINT buyers_pkey PRIMARY KEY (id);


--
-- Name: items items_pkey; Type: CONSTRAINT; Schema: shopping; Owner: postgres
--

ALTER TABLE ONLY shopping.items
    ADD CONSTRAINT items_pkey PRIMARY KEY (id);


--
-- Name: purchases purchases_pkey; Type: CONSTRAINT; Schema: shopping; Owner: postgres
--

ALTER TABLE ONLY shopping.purchases
    ADD CONSTRAINT purchases_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

