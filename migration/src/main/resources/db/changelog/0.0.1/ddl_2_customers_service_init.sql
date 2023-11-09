CREATE SEQUENCE IF NOT EXISTS customers_seq;

CREATE TABLE IF NOT EXISTS customers
(
    id          bigint       NOT NULL DEFAULT nextval('customers_seq'),
    phone       varchar(255) NOT NULL UNIQUE,
    email       varchar(255) NOT NULL UNIQUE,
    address     varchar(255) NOT NULL,
    create_date timestamptz  NOT NULL DEFAULT now(),

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS customer_coordinate
(
    customer_id bigint REFERENCES customers (id) ON DELETE CASCADE ON UPDATE NO ACTION,
    latitude    float8 NULL DEFAULT (NULL),
    longitude   float8 NULL DEFAULT (NULL),

    PRIMARY KEY (customer_id)
);

comment on table customers is 'Таблица клиентов';
comment on column customers.id is 'Идентификатор';
comment on column customers.phone is 'Номер телефона';
comment on column customers.email is 'Электронная почта';
comment on column customers.address is 'Адрес';
comment on column customers.create_date is 'Дата создания';

comment on table customer_coordinate is 'Таблица координат адреса клиента';
comment on column customer_coordinate.customer_id is 'Идентификатор клиента';
comment on column customer_coordinate.latitude is 'Широта';
comment on column customer_coordinate.longitude is 'Долгота';