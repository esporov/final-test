--################# Restaurants ################################
INSERT INTO restaurants (address)
VALUES ('ул. Керченская, д. 13А'),
       ('ул. Бетанкура, д. 1'),
       ('пл. Революции, д. 5А');

insert into restaurant_coordinate (restaurant_id,latitude, longitude)
values (1,56.334108, 43.959903),
       (2,56.339402, 43.956211),
       (3,56.321476, 43.947398);

--#### c 1 по 16
INSERT INTO menu_items (restaurant_id, name, price, image, description)
VALUES (1, 'БигХит', 182, 'https://images.restaurant/?id=1&item-id=1', 'БигХит'),
       (1, 'Град', 193, 'https://images.restaurant/?id=1&item-id=2', 'Град'),
       (1, 'Гранд Де Люкс', 219, 'https://images.restaurant/?id=1&item-id=3', 'Гранд Де Люкс'),
       (1, 'Биг Спешиал', 299, 'https://images.restaurant/?id=1&item-id=4', 'Биг Спешиал'),
       (1, 'Чизбургер', 78, 'https://images.restaurant/?id=1&item-id=5', 'Чизбургер'),
       (1, 'Гамбургер', 45, 'https://images.restaurant/?id=1&item-id=6', 'Гамбургер'),
       (1, 'Чикенбургер', 59, 'https://images.restaurant/?id=1&item-id=7', 'Чикенбургер'),
       (1, 'Чикенбургер Двойной', 139, 'https://images.restaurant/?id=1&item-id=8', 'Чикенбургер Двойной'),
       (1, 'Картофель Фри мал.', 65, 'https://images.restaurant/?id=1&item-id=9', 'Картофель Фри мал.'),
       (1, 'Картофель Фри ср.', 89, 'https://images.restaurant/?id=1&item-id=10', 'Картофель Фри ср.'),
       (1, 'Картофель Фри бол.', 150, 'https://images.restaurant/?id=1&item-id=11', 'Картофель Фри бол.'),
       (1, 'Картофель Фри бол.', 150, 'https://images.restaurant/?id=1&item-id=12', 'Картофель Фри бол.'),
       (1, 'Соус', 45, 'https://images.restaurant/?id=1&item-id=13', 'Соус'),
       (1, 'Кола мал.', 89, 'https://images.restaurant/?id=1&item-id=14', 'Кола мал.'),
       (1, 'Кола ср.', 99, 'https://images.restaurant/?id=1&item-id=15', 'Кола ср.'),
       (1, 'Кола бол.', 109, 'https://images.restaurant/?id=1&item-id=16', 'Кола бол.');



--#### c 17 по 33
INSERT INTO menu_items (restaurant_id, name, price, image, description)
VALUES (2, 'Ангус Трюфель', 455, 'https://images.restaurant/?id=2&item-id=17', 'Ангус Трюфель'),
       (2, 'Ангус Трюфель Двойной', 605, 'https://images.restaurant/?id=2&item-id=18', 'Ангус Трюфель Двойной'),
       (2, 'Чикенбургер', 90, 'https://images.restaurant/?id=2&item-id=19', 'Чикенбургер'),
       (2, 'Чикер Тар-Тар', 220, 'https://images.restaurant/?id=2&item-id=20', 'Чикер Тар-Тар'),
       (2, 'Цезарь Кинг', 130, 'https://images.restaurant/?id=2&item-id=21', 'Цезарь Кинг'),
       (2, 'Фиш Бургер', 160, 'https://images.restaurant/?id=2&item-id=22', 'Фиш Бургер'),
       (2, 'Шримп Ролл', 290, 'https://images.restaurant/?id=2&item-id=23', 'Шримп Ролл'),
       (2, 'Кинг Букет "Креветки"', 730, 'https://images.restaurant/?id=2&item-id=24', 'Кинг Букет "Креветки"'),
       (2, 'Кинг Фри мал.', 90, 'https://images.restaurant/?id=2&item-id=25', 'Кинг Фри мал.'),
       (2, 'Кинг Фри ср.', 105, 'https://images.restaurant/?id=2&item-id=26', 'Кинг Фри ср.'),
       (2, 'Кинг Фри бол.', 140, 'https://images.restaurant/?id=2&item-id=27', 'Кинг Фри бол.'),
       (2, 'Воппер', 255, 'https://images.restaurant/?id=2&item-id=28', 'Воппер'),
       (2, 'Соус', 50, 'https://images.restaurant/?id=2&item-id=29', 'Соус'),
       (2, 'Наггесты 9 шт.', 155, 'https://images.restaurant/?id=2&item-id=30', 'Наггесты 9 шт.'),
       (2, 'Кола мал.', 109, 'https://images.restaurant/?id=2&item-id=31', 'Кола мал.'),
       (2, 'Кола ср.', 140, 'https://images.restaurant/?id=2&item-id=32', 'Кола ср.'),
       (2, 'Кола бол.', 180, 'https://images.restaurant/?id=2&item-id=33', 'Кола бол.');


--#### c 34 по 50
INSERT INTO menu_items (restaurant_id, name, price, image, description)
VALUES (3, 'Баскет L 24 Острых Крылышка', 709, 'https://images.restaurant/?id=3&item-id=34',
        'Баскет L 24 Острых Крылышка'),
       (3, 'Баскет "12 Острых Крылышек + 12 Наггетсов"', 574, 'https://images.restaurant/?id=3&item-id=35',
        'Баскет "12 Острых Крылышек + 12 Наггетсов"'),
       (3, 'Баскет Дуэт Оригинальный', 494, 'https://images.restaurant/?id=3&item-id=36', 'Баскет Дуэт Оригинальный'),
       (3, 'Шеф Баскет Острый', 254, 'https://images.restaurant/?id=3&item-id=37', 'Шеф Баскет Острый'),
       (3, 'Всё Сразу Баскет', 209, 'https://images.restaurant/?id=3&item-id=38', 'Всё Сразу Баскет'),
       (3, 'Биг Маэстро Бургер Оригинальный', 284, 'https://images.restaurant/?id=3&item-id=39',
        'Биг Маэстро Бургер Оригинальный'),
       (3, 'Чизбургер', 99, 'https://images.restaurant/?id=3&item-id=40', 'Чизбургер'),
       (3, 'АйТвистер Чиз"', 79, 'https://images.restaurant/?id=3&item-id=41', 'АйТвистер Чиз"'),
       (3, 'Кинг Фри мал.', 74, 'https://images.restaurant/?id=3&item-id=42', 'Кинг Фри мал.'),
       (3, 'Кинг Фри ср.', 94, 'https://images.restaurant/?id=3&item-id=43', 'Кинг Фри ср.'),
       (3, 'Кинг Фри бол.', 174, 'https://images.restaurant/?id=3&item-id=44', 'Кинг Фри бол.'),
       (3, 'Пати Бокс', 144, 'https://images.restaurant/?id=3&item-id=45', 'Пати Бокс'),
       (3, 'Соус', 44, 'https://images.restaurant/?id=3&item-id=46', 'Соус'),
       (3, 'Мороженое Кокос в шоколаде', 129, 'https://images.restaurant/?id=3&item-id=47',
        'Мороженое Кокос в шоколаде'),
       (3, 'Кола мал.', 94, 'https://images.restaurant/?id=3&item-id=48', 'Кола мал.'),
       (3, 'Кола ср.', 114, 'https://images.restaurant/?id=3&item-id=49', 'Кола ср.'),
       (3, 'Кола бол.', 139, 'https://images.restaurant/?id=3&item-id=50', 'Кола бол.');


--################# Customers ################################
insert into customers (email, phone,address)
values ('cust1@mail.ru', '+78856785354','ул. Бетанкура 2, кв 15'),
       ('cust2@mail.ru', '+78189455354','ул. Керченская 22, кв 55'),
       ('cust3@mail.ru', '+78189411894','ул. Большая покровская 13, кв 34');

insert into customer_coordinate (customer_id,latitude, longitude)
values (1,56.333355, 43.954962),
       (2,56.334847, 43.957576),
       (3,56.324162, 44.001351);

--################# Couriers ################################
insert into couriers (phone)
values ('+78805535354' ),
       ('+78807864354'),
       ('+78607867864');

insert into courier_coordinate (courier_id,latitude, longitude)
values (1,56.325236, 44.011908),
       (2,56.332932, 43.939183),
       (3,56.323400, 43.924440);


--################# Orders ################################
insert into orders (id,customer_id,courier_id,restaurant_id, price,kitchen_status, delivery_status)
values ('feb8f606-cc02-4049-9c41-578319ea5a71',1,1,1,2421,  'DONE','DELIVERED'),
       ('8d848ee4-348c-4a34-ad0e-87dea8ee9b73',2,3,2,3290,  'DONE','DELIVERED');


insert into order_items (order_id,  item_id, quantity,price)
values ('feb8f606-cc02-4049-9c41-578319ea5a71',  1, 1,182),
       ('feb8f606-cc02-4049-9c41-578319ea5a71',  2, 2,193),
       ('feb8f606-cc02-4049-9c41-578319ea5a71',  3, 3,219),
       ('feb8f606-cc02-4049-9c41-578319ea5a71',  4, 4,299);

insert into order_items (order_id,  item_id, quantity,price)
values ('8d848ee4-348c-4a34-ad0e-87dea8ee9b73',  22, 1,160),
       ('8d848ee4-348c-4a34-ad0e-87dea8ee9b73',  23, 2,290),
       ('8d848ee4-348c-4a34-ad0e-87dea8ee9b73',  24, 3,730),
       ('8d848ee4-348c-4a34-ad0e-87dea8ee9b73', 25, 4,90);


