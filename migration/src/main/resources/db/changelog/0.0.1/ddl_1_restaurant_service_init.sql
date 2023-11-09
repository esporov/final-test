CREATE SEQUENCE IF NOT EXISTS restaurants_seq;

CREATE TABLE IF NOT EXISTS restaurants
(
    id          bigint              NOT NULL DEFAULT nextval('restaurants_seq'),
    address     varchar(255) UNIQUE NOT NULL,
    work_status varchar(255)        NOT NULL DEFAULT ('ACTIVE') CHECK ( work_status IN ('ACTIVE', 'INACTIVE')),

    PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS restaurant_coordinate
(
    restaurant_id bigint REFERENCES restaurants (id) ON DELETE CASCADE ON UPDATE NO ACTION,
    latitude      float8 NULL DEFAULT (NULL),
    longitude     float8 NULL DEFAULT (NULL),

    PRIMARY KEY (restaurant_id)
);

CREATE SEQUENCE IF NOT EXISTS menu_items_seq;

CREATE TABLE IF NOT EXISTS menu_items
(
    id            bigint         NOT NULL DEFAULT nextval('menu_items_seq'),
    restaurant_id bigint REFERENCES restaurants (id) ON DELETE CASCADE ON UPDATE NO ACTION,
    name          varchar(255)   NOT NULL,
    price         numeric(12, 2) NOT NULL CHECK ( price > 0 ),
    image         varchar(255)   NOT NULL,
    description   varchar(255)   NOT NULL,
    status        varchar(255)   NOT NULL DEFAULT ('AVAILABLE') CHECK (status IN ('AVAILABLE', 'NOT_AVAILABLE')),

    PRIMARY KEY (id)
);

comment on table restaurants is 'Таблица ресторанов';
comment on column restaurants.id is 'Идентификатор';
comment on column restaurants.address is 'Адрес';

comment on table restaurant_coordinate is 'Таблица координат ресторана';
comment on column restaurant_coordinate.restaurant_id is 'Идентификатор ресторана';
comment on column restaurant_coordinate.latitude is 'Широта';
comment on column restaurant_coordinate.longitude is 'Долгота';

comment on table menu_items is 'Таблица позиций меню ресторана';
comment on column menu_items.id is 'Идентификатор позиции из меню';
comment on column menu_items.restaurant_id is 'Идентификатор ресторана';
comment on column menu_items.name is 'Название позиции из меню';
comment on column menu_items.price is 'Цена позиции из меню';
comment on column menu_items.image is 'Ссылка на изображение позиции из меню';
comment on column menu_items.description is 'Описание позиции из меню';
comment on column menu_items.status is 'Стоп лист позиции из меню';





