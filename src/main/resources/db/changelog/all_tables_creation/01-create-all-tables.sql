create table data_type
(
    id   bigserial
        constraint data_type_pkey
            primary key,
    name varchar(255)
);

GO

alter table data_type
    owner to postgres;

GO

create table dependency_table
(
    id                    bigserial UNIQUE NOT NULL,
    second_subproperty_id bigint,
    first_subproperty_id  bigint,
    constraint dependency_table_pkey
        primary key (id)
);

GO

alter table dependency_table
    owner to postgres;

GO

create table fact_param
(
    id          bigserial UNIQUE NOT NULL,
    value       varchar(255),
    strain_id   bigint,
    property_id bigint,
    constraint fact_param_pkey
        primary key(id, strain_id, property_id)
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
        primary key(id, strain_id, dependency_table_id)
);

GO

alter table fact_param_func
    owner to postgres;

GO

create table property
(
    id            bigserial
        constraint property_pkey
            primary key,
    cypher        bigint,
    description   varchar(255),
    name          varchar(255),
    property_type boolean
);

GO

alter table property
    owner to postgres;

GO

create table rod_strain
(
    id     bigserial
        constraint rod_strain_pkey
            primary key,
    cypher bigint,
    name   varchar(255)
);

GO

alter table rod_strain
    owner to postgres;

GO

create table strain
(
    id               bigserial UNIQUE NOT NULL,
    annotation       varchar(255),
    exemplar         varchar(255),
    modification     varchar(255),
    obtaining_method varchar(255),
    origin           varchar(255),
    vid_id           bigint,
    constraint strain_pkey
        primary key(id, vid_id)
);

GO

alter table strain
    owner to postgres;

GO

create table subproperty
(
    id          bigserial UNIQUE NOT NULL,
    name        varchar(255),
    property_id bigint,
    datatype_id bigint,
    constraint subproperty_pkey
        primary key(id, property_id)
);

GO

alter table subproperty
    owner to postgres;

GO

create table vid_strain
(
    id     bigserial UNIQUE NOT NULL,
    cypher bigint,
    name   varchar(255),
    rod_id bigint,
    constraint vid_strain_pkey
        primary key (id, rod_id)
);

GO

alter table vid_strain
    owner to postgres;

GO
