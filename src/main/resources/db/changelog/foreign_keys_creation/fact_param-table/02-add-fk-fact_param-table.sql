alter table fact_param
    add constraint fk_strain_id
        foreign key ("strain_id")
            references strain("id"),
    add constraint fk_property_id
        foreign key ("property_id")
            references property("id");

GO