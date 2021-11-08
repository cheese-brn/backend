alter table dependency_table
    add constraint second_subproperty_fk
        foreign key ("second_subproperty_id")
            references subproperty ("id"),
    add constraint first_subproperty_fk
        foreign key ("first_subproperty_id")
            references subproperty ("id");

GO