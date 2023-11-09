CREATE TABLE IF NOT EXISTS orders
(
    id              uuid NOT NULL DEFAULT uuid_generate_v4(),
    customer_id     bigint REFERENCES customers (id) ON DELETE CASCADE ON UPDATE NO ACTION,
    restaurant_id   bigint         NOT NULL,
    kitchen_status  varchar(255)   NOT NULL DEFAULT 'ACCEPTED'
        CHECK ( kitchen_status IN ('ACCEPTED', 'IN_WORK', 'DONE', 'DENIED')),
    delivery_status varchar(255)   NOT NULL DEFAULT 'COURIER_SEARCH'
        CHECK ( delivery_status IN ('COURIER_SEARCH','HURRIES_TO_ORDER', 'PICKED_UP_THE_ORDER', 'DELIVERED', 'DENIED')),
    create_date     timestamptz    NOT NULL DEFAULT now(),
    price           numeric(12, 2) NOT NULL CHECK ( price > 0 ),
    courier_id      bigint         NULL REFERENCES couriers (id) ON DELETE CASCADE ON UPDATE NO ACTION,

    PRIMARY KEY (id)
);

CREATE SEQUENCE IF NOT EXISTS order_items_seq;

CREATE TABLE IF NOT EXISTS order_items
(
    id       bigint NOT NULL DEFAULT nextval('order_items_seq'),
    order_id uuid REFERENCES orders (id) ON DELETE CASCADE ON UPDATE NO ACTION,
    item_id  bigint REFERENCES menu_items (id) ON DELETE CASCADE ON UPDATE NO ACTION,
    quantity integer        NOT NULL CHECK ( quantity > 0 ),
    price    numeric(12, 2) NOT NULL CHECK ( price > 0 ),

    PRIMARY KEY (id)
);

comment on table orders is 'Таблица заказов';
comment on column orders.id is 'Идентификатор';
comment on column orders.customer_id is 'Идентификатор клиента';
comment on column orders.restaurant_id is 'Идентификатор ресторана';
comment on column orders.kitchen_status is 'Статус заказа по кухне';
comment on column orders.delivery_status is 'Статус заказа по доставке';
comment on column orders.create_date is 'Дата создания заказа';
comment on column orders.price is 'Суммарная стоимость заказа';
comment on column orders.courier_id is 'Идентификатор курьера';

comment on table order_items is 'Таблица позиций заказа';
comment on column order_items.id is 'Идентификатор';
comment on column order_items.order_id is 'Идентификатор заказа';
comment on column order_items.item_id is 'Идентификатор блюда ресторана';
comment on column order_items.quantity is 'Количество';
comment on column order_items.price is 'Цена';






