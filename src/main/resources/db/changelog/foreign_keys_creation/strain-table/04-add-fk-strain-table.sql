alter table strain
    add constraint fk_vid_id
        foreign key ("vid_id")
            references vid_strain("id");

GO