alter table dependency_table
    add constraint fk_first_subproperty_id
        foreign key ("first_subproperty_id")
            references subproperty ("id"),
    add constraint fk_second_subproperty_id
        foreign key ("second_subproperty_id")
            references subproperty ("id");

GO