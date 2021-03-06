    create table Game (
        gauid bigint generated by default as identity (start with 1),
        blackUuid bigint,
        board varchar(255),
        created timestamp,
        description varchar(255),
        lastStep timestamp,
        modified timestamp,
        name varchar(255),
        resolution varchar(255),
        state varchar(255),
        type varchar(255),
        whiteUuid bigint,
        black_uuid bigint,
        white_uuid bigint,
        primary key (gauid)
    );

    create table Step (
        suid bigint generated by default as identity (start with 1),
        created timestamp,
        gauid bigint,
        step varchar(255),
        uuid bigint,
        game_gauid bigint,
        user_uuid bigint,
        primary key (suid)
    );

    create table User (
        uuid bigint generated by default as identity (start with 1),
        created timestamp,
        email varchar(255),
        enabled boolean,
        firstName varchar(255),
        lastLogin timestamp,
        lastName varchar(255),
        login varchar(255),
        modified timestamp,
        password varchar(255),
        role varchar(255),
        primary key (uuid)
    );

    alter table Game 
        add constraint UK_gdtr9fjw6icy8hhf02kpmvqpc  unique (name);

    alter table Game 
        add constraint FK_o8kp7ypj437prts49s3av849u 
        foreign key (black_uuid) 
        references User;

    alter table Game 
        add constraint FK_shf4whclvnfusnev6c7pl9e41 
        foreign key (white_uuid) 
        references User;

    alter table Step 
        add constraint FK_7fvhlmnltltnqmgxke55ddmch 
        foreign key (game_gauid) 
        references Game;

    alter table Step 
        add constraint FK_jsof4a5hypu1xirjxaspsmvga 
        foreign key (user_uuid) 
        references User;

    alter table User 
        add constraint UK_e6gkqunxajvyxl5uctpl2vl2p  unique (email);

    alter table User 
        add constraint UK_587tdsv8u5cvheyo9i261xhry  unique (login);
