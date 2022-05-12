create table data_type
(
    id   bigserial UNIQUE NOT NULL,
    name varchar(255),
    constraint data_type_pkey
        primary key (id)
);

GO

alter table data_type
    owner to postgres;

GO

create table dependency_table
(
    id                    bigserial UNIQUE NOT NULL,
    first_subproperty_id  bigint,
    second_subproperty_id bigint,
    constraint dependency_table_pkey
        primary key (id)
);

GO

alter table dependency_table
    owner to postgres;

GO

create table fact_param
(
    id             bigserial UNIQUE NOT NULL,
    value          text,
    strain_id      bigint,
    property_id    bigint,
    subproperty_id bigint,
    constraint fact_param_pkey
        primary key (id)

);

GO

alter table fact_param
    owner to postgres;

GO

create table fact_param_func
(
    id                  bigserial UNIQUE NOT NULL,
    first_parametr      varchar(255),
    second_parametr     varchar(255),
    third_parametr      varchar(255),
    strain_id           bigint,
    dependency_table_id bigint,
    constraint fact_param_func_pkey
        primary key (id)
);

GO

alter table fact_param_func
    owner to postgres;

GO

create table property
(
    id            bigserial UNIQUE NOT NULL,
    cypher        bigint,
    description   text,
    name          text,
    property_type boolean,
    constraint property_pkey
        primary key (id)
);

GO

alter table property
    owner to postgres;

GO

create table rod_strain
(
    id     bigserial UNIQUE NOT NULL,
    cypher bigint,
    name   text,
    constraint rod_strain_pkey
        primary key (id)
);

GO

alter table rod_strain
    owner to postgres;

GO

create table strain
(
    id               bigserial UNIQUE NOT NULL,
    annotation       text,
    exemplar         text,
    modification     text,
    obtaining_method text,
    origin           text,
    vid_id           bigint,
    constraint strain_pkey
        primary key (id)
);

GO

alter table strain
    owner to postgres;

GO

create table subproperty
(
    id          bigserial UNIQUE NOT NULL,
    name        text,
    property_id bigint,
    datatype_id bigint,
    constraint subproperty_pkey
        primary key (id)
);

GO

alter table subproperty
    owner to postgres;

GO

create table vid_strain
(
    id     bigserial UNIQUE NOT NULL,
    cypher bigint,
    name   text,
    rod_id bigint,
    constraint vid_strain_pkey
        primary key (id)
);

GO

alter table vid_strain
    owner to postgres;

GO
