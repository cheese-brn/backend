alter table subproperty
    add constraint fk_property_id
        foreign key ("property_id")
            references property("id"),
    add constraint fk_datatype_id
        foreign key ("datatype_id")
            references data_type("id");

GO