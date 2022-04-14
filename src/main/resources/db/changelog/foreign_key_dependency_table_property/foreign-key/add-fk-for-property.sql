alter table dependency_table
    add constraint fk_property_id
        foreign key ("property_id")
            references property ("id")
            ON DELETE CASCADE
            ON UPDATE CASCADE;

GO