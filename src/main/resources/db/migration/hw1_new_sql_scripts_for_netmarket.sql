create table categories --категория продукта
(
    id    bigserial primary key,
    title varchar(255)
);

create table manufactures --производитель продукта
(
    id    bigserial primary key,
    title varchar(255)
);

create table products --продукты
(
    id          bigserial primary key,
    title       varchar(255),
    category_id bigint references categories (id),
    manufacture_id bigint references manufactures (id),
    expiration_date int --срок годности (в днях)
);

create table cart --корзина
(
    id       bigserial primary key,
    user_id  bigint not null references users (id),
    product_id  bigint not null references products (id),
    amount      int
);

create table orders --заказы
(
    id       bigserial primary key,
    user_id  bigint not null references users (id),
    product_id  bigint not null references products (id),
    amount      int,
    order_date  date,
    price       int,
    address     varchar(255),
    phonenumber varchar(15)
);

create table stock --склад
(
    id       bigserial primary key,
    product_id  bigint not null references products (id),
    amount      int,
    delivery_date  date,
    price       int
);

create table users --пользователи
(
    id         bigserial primary key,
    username   varchar(30) not null,
    password   varchar(60) not null,
    email      varchar(50) unique,
    firstname  varchar(30) not null,
    lastname   varchar(30) not null,
    birthday   date,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table roles --имеющиеся роли
(
    id         bigserial primary key,
    name       varchar(50) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

CREATE TABLE users_roles --сопоставление пользователям их ролей
(
    user_id bigint not null references users (id),
    role_id bigint not null references roles (id),
    primary key (user_id, role_id)
);

