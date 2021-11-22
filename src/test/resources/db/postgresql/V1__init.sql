DROP SCHEMA IF EXISTS sales CASCADE;

CREATE SCHEMA sales;

-- GRANT ALL PRIVILEGES ON DATABASE sales TO postgresql;
GRANT ALL PRIVILEGES ON SCHEMA sales TO postgresql;

CREATE TABLE IF NOT EXISTS sales.tb_customer (
	customer_id uuid NOT NULL,
	first_name text NOT NULL,
	last_name text NOT NULL,
	identification text NOT NULL,
	CONSTRAINT tb_customer_identification_key UNIQUE (identification),
	CONSTRAINT tb_customer_customer_id_pkey PRIMARY KEY (customer_id)
);
-- Permissions
-- ALTER TABLE sales.tb_customer OWNER TO postgresql;
GRANT ALL ON TABLE sales.tb_customer TO postgresql;

CREATE TABLE IF NOT EXISTS sales.tb_product (
	product_id uuid NOT NULL,
	name text NOT NULL,
	value float8 NULL,
	CONSTRAINT tb_product_product_id_pkey PRIMARY KEY (product_id)
);
-- Permissions
-- ALTER TABLE sales.tb_product OWNER TO postgresql;
GRANT ALL ON TABLE sales.tb_product TO postgresql;

CREATE TABLE IF NOT EXISTS sales.tb_order (
	order_id uuid NOT NULL,
	date_ts timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP,
	total float8 NULL,
	customer_id uuid NOT NULL,
	CONSTRAINT tb_order_order_id_pkey PRIMARY KEY (order_id),
	CONSTRAINT tb_customer_customer_id_fkey FOREIGN KEY (customer_id) REFERENCES sales.tb_customer(customer_id) 
);
-- Permissions
-- ALTER TABLE sales.tb_order OWNER TO postgresql;
GRANT ALL ON TABLE sales.tb_order TO postgresql;

CREATE TABLE IF NOT EXISTS sales.tb_item (
	item_id uuid NOT NULL,
	product_id uuid NOT NULL,
	quantity int4 NOT NULL,
	order_id uuid NOT NULL,
	CONSTRAINT tb_item_item_id_pkey PRIMARY KEY (item_id),
	CONSTRAINT tb_product_product_id_fkey FOREIGN KEY (product_id) REFERENCES sales.tb_product(product_id), 
	CONSTRAINT tb_order_order_id_fkey FOREIGN KEY (order_id) REFERENCES sales.tb_order(order_id) 
);
-- Permissions
-- ALTER TABLE rules_engine.rule_libraries OWNER TO postgresql;
GRANT ALL ON TABLE sales.tb_item TO postgresql;


-- Basic tests (Package, Rule, Version)
-- Package Rules
INSERT INTO sales.tb_customer
	(customer_id, first_name, last_name, identification)
VALUES
	('f85321a9-bc00-4f1c-a916-b8639bb90371', 'Orlei', 'Bicheski', 'DL-9907654');

INSERT INTO sales.tb_product
	(product_id, name, value)
VALUES
	('916f424b-08e3-4c58-ad71-6f7d27d1ecbf', 'Book', 29.97);
INSERT INTO sales.tb_product
	(product_id, name, value)
VALUES
	('4170a34c-4707-45cc-989c-9678aaf63d96', 'Notebook', 1.97);
INSERT INTO sales.tb_product
	(product_id, name, value)
VALUES
	('9aa5e2b1-0e5e-4d51-a1b8-dc6e6f7a1d4a', 'CD', 11.89);

COMMIT;
