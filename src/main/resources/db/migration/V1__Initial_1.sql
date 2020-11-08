--drop table if exists composition CASCADE;
--
--    drop table if exists composition_genre CASCADE;
--
--    drop table if exists composition_mark CASCADE;
--
--    drop table if exists composition_tag CASCADE;
--
--    drop table if exists genre CASCADE;
--
--
--    drop table if exists mark CASCADE;
--
--    drop table if exists mark_user CASCADE;
--
--    drop table if exists paragraph CASCADE;
--
--    drop table if exists paragraph_user CASCADE;
--
--    drop table if exists tag CASCADE;
--
--    drop table if exists user CASCADE;
--
--    drop table if exists user_role CASCADE;
--
--
--drop sequence if exists hibernate_sequence;

create sequence hibernate_sequence start with 1 increment by 1;

    create table composition (
       id bigint not null,
        name varchar(255),
        shot_description varchar(255),
        user_id bigint,
        primary key (id)
    );

    create table composition_genre (
       composition_id bigint not null,
        genre_id bigint not null,
        primary key (composition_id, genre_id)
    );

    create table composition_mark (
       composition_id bigint not null,
        mark_id bigint not null
    );

    create table composition_tag (
       composition_id bigint not null,
        tag_id bigint not null,
        primary key (composition_id, tag_id)
    );

    create table genre (
       id bigint not null,
        name varchar(255),
        primary key (id)
    );

    create table mark (
       id bigint not null,
        value integer,
        primary key (id)
    );

    create table mark_user (
       mark_id bigint not null,
        user_id bigint not null,
        primary key (mark_id, user_id)
    );

    create table paragraph (
       id bigint not null,
        link_to_image varchar(255),
        name varchar(255),
        text varchar(255),
        composition_id bigint,
        primary key (id)
    );

    create table paragraph_user (
       paragraph_id bigint not null,
        user_id bigint not null,
        primary key (paragraph_id, user_id)
    );

    create table tag (
       id bigint not null,
        text varchar(255),
        primary key (id)
    );

    create table usr (
       id bigint not null,
        email varchar(255),
        name varchar(255),
        password varchar(255),
        primary key (id)
    );

    create table user_role (
       user_id bigint not null,
        roles varchar(255)
    );

    alter table composition
       add constraint FK9gnhc2t8jr18l41snrv1lehlr
       foreign key (user_id)
       references usr;

    alter table composition_genre
       add constraint FKlvvfidww8483jthldfrn8p9ch
       foreign key (genre_id)
       references genre;

    alter table composition_genre
       add constraint FKalmabyoy8ce6mpn8b93ea2sd0
       foreign key (composition_id)
       references composition;

    alter table composition_mark
       add constraint FK590npxbs22wsmtk98r9rdk5oj
       foreign key (mark_id)
       references mark;

    alter table composition_mark
       add constraint FK1as8eun1g10hf645bhmse28e
       foreign key (composition_id)
       references composition;

    alter table composition_tag
       add constraint FKmw88fokdrrkwxm5anb68esm39
       foreign key (tag_id)
       references tag;

    alter table composition_tag
       add constraint FKsgmwvbm7pfkxr8d3rjj5w6cky
       foreign key (composition_id)
       references composition;

    alter table mark_user
       add constraint FKktew669703sh9mcxae5khhcbx
       foreign key (user_id)
       references usr;

    alter table mark_user
       add constraint FK6hh2biwjy4hm6omhedkmr4by9
       foreign key (mark_id)
       references mark;

    alter table paragraph
       add constraint FKlxda95b8tqip4cv6w2aqhl630
       foreign key (composition_id)
       references composition;

    alter table paragraph_user
       add constraint FKtp00st2mr7avb6ji4py9fag1p
       foreign key (user_id)
       references usr;

    alter table paragraph_user
       add constraint FK1lphnlqwk9n7mjwghf211pgag
       foreign key (paragraph_id)
       references paragraph;

    alter table user_role
       add constraint FK859n2jvi8ivhui0rl0esws6o
       foreign key (user_id)
       references usr;