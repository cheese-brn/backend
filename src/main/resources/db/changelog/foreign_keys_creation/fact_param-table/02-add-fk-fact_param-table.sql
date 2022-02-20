alter table fact_param
    add constraint fk_strain_id
        foreign key ("strain_id")
            references strain("id"),
    add constraint fk_property_id
        foreign key ("property_id")
            references property("id"),
    add constraint fk_subproperty_id
        foreign key ("subproperty_id")
            references subproperty("id")
                ON DELETE CASCADE;

GO