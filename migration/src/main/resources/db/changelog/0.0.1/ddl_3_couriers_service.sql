CREATE SEQUENCE IF NOT EXISTS couriers_seq;

CREATE TABLE IF NOT EXISTS couriers
(
    id          bigint       NOT NULL DEFAULT nextval('couriers_seq'),
    phone  varchar(255) NOT NULL UNIQUE,
    status varchar(255) NOT NULL DEFAULT ('FREE')
        CHECK ( status IN ('ON_ORDER', 'FREE', 'FIRED', 'NOT_WORKING_NOW')),

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS courier_coordinate
(
    courier_id bigint REFERENCES couriers (id) ON DELETE CASCADE ON UPDATE NO ACTION,
    latitude   float8 NULL DEFAULT (NULL),
    longitude  float8 NULL DEFAULT (NULL),

    PRIMARY KEY (courier_id)
);

comment on table couriers is 'Таблица курьеров';
comment on column couriers.id is 'Идентификатор';
comment on column couriers.phone is 'Номер телефона';
comment on column couriers.status is 'Статус курьера';

comment on table courier_coordinate is 'Таблица координат курьера';
comment on column courier_coordinate.courier_id is 'Идентификатор курьера';
comment on column courier_coordinate.latitude is 'Широта';
comment on column courier_coordinate.longitude is 'Долгота';



