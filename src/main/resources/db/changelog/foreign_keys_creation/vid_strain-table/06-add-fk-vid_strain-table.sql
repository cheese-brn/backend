alter table vid_strain
    add constraint fk_rod_id
        foreign key ("rod_id")
            references rod_strain("id");

GO