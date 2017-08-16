--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

--
-- Name: bankaccount; Type: DATABASE; Schema: -; Owner: -
--

CREATE DATABASE bankaccount WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'en_US.UTF-8' LC_CTYPE = 'en_US.UTF-8';


\connect bankaccount

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: accounts; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE accounts (
    acc_number integer NOT NULL,
    acc_name text NOT NULL,
    created_on timestamp without time zone DEFAULT now() NOT NULL,
    active_balance integer DEFAULT 0 NOT NULL
);


--
-- Name: accounts_acc_number_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE accounts_acc_number_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


--
-- Name: accounts_acc_number_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE accounts_acc_number_seq OWNED BY accounts.acc_number;


--
-- Name: accounts_acc_number_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('accounts_acc_number_seq', 1, true);


--
-- Name: transactions; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE transactions (
    rec_id integer NOT NULL,
    acc_number integer NOT NULL,
    amount integer NOT NULL,
    trans_date timestamp without time zone DEFAULT now() NOT NULL,
    trans_type text NOT NULL
);


--
-- Name: transactions_rec_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE transactions_rec_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


--
-- Name: transactions_rec_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE transactions_rec_id_seq OWNED BY transactions.rec_id;


--
-- Name: transactions_rec_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('transactions_rec_id_seq', 87, true);


--
-- Name: acc_number; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY accounts ALTER COLUMN acc_number SET DEFAULT nextval('accounts_acc_number_seq'::regclass);


--
-- Name: rec_id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY transactions ALTER COLUMN rec_id SET DEFAULT nextval('transactions_rec_id_seq'::regclass);


--
-- Data for Name: accounts; Type: TABLE DATA; Schema: public; Owner: -
--

COPY accounts (acc_number, acc_name, created_on, active_balance) FROM stdin;
1	Anonymous	2017-07-02 11:26:28.097873	590
\.


--
-- Data for Name: transactions; Type: TABLE DATA; Schema: public; Owner: -
--

COPY transactions (rec_id, acc_number, amount, trans_date, trans_type) FROM stdin;
\.


--
-- Name: accounts_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY accounts
    ADD CONSTRAINT accounts_pkey PRIMARY KEY (acc_number);


--
-- Name: transactions_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY transactions
    ADD CONSTRAINT transactions_pkey PRIMARY KEY (rec_id);


--
-- Name: accounts_idx; Type: INDEX; Schema: public; Owner: -; Tablespace: 
--

CREATE UNIQUE INDEX accounts_idx ON accounts USING btree (acc_number);


--
-- Name: transactions_idx; Type: INDEX; Schema: public; Owner: -; Tablespace: 
--

CREATE INDEX transactions_idx ON transactions USING btree (rec_id, acc_number);


--
-- Name: transactions_acc_number_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY transactions
    ADD CONSTRAINT transactions_acc_number_fkey FOREIGN KEY (acc_number) REFERENCES accounts(acc_number);


--
-- Name: public; Type: ACL; Schema: -; Owner: -
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

