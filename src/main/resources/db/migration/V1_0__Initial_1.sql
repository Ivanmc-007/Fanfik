
create sequence hibernate_sequence start with 1 increment by 1;

create table comment
(
   id bigint not null,
   text varchar(255),
   composition_id bigint,
   user_id bigint,
   primary key (id)
);

create table composition
(
   id bigint not null,
   date_update timestamp,
   name varchar(255),
   short_description varchar(255),
   user_id bigint,
   primary key (id)
);

create table composition_genre
(
   composition_id bigint not null,
   genre_id bigint not null,
   primary key (composition_id, genre_id)
);

create table composition_mark
(
   composition_id bigint not null,
   mark_id bigint not null
);

create table composition_tag
(
   composition_id bigint not null,
   tag_id bigint not null,
   primary key (composition_id, tag_id)
);

create table genre
(
   id bigint not null,
   name varchar(255),
   primary key (id)
);

create table mark
(
   id bigint not null,
   value integer,
   primary key (id)
);

create table mark_user
(
   mark_id bigint not null,
   user_id bigint not null,
   primary key (mark_id, user_id)
);

create table paragraph
(
   id bigint not null,
   link_to_image varchar(255),
   name varchar(255),
   text varchar(4020),
   composition_id bigint,
   primary key (id)
);

create table paragraph_user
(
   paragraph_id bigint not null,
   user_id bigint not null,
   primary key (paragraph_id, user_id)
);

create table tag
(
   id bigint not null,
   text varchar(255),
   primary key (id)
);

create table usr
(
   id bigint not null,
   active boolean not null,
   email varchar(255),
   name varchar(255),
   password varchar(255),
   primary key (id)
);

create table user_role
(
   user_id bigint not null,
   roles varchar(255)
);

alter table
if exists comment
add constraint FK64wdn7jrgs6gypxrvdmxowdjn 
   foreign key
(composition_id) 
   references composition;

alter table
if exists comment
add constraint FKgcgdcgly6u49hf4g8y2di3g4p 
   foreign key
(user_id) 
   references usr;

alter table
if exists composition
add constraint FK7n80x7yun6wcjcsnx5he1tho8 
   foreign key
(user_id) 
   references usr;

alter table
if exists composition_genre
add constraint FKlvvfidww8483jthldfrn8p9ch 
   foreign key
(genre_id) 
   references genre;

alter table
if exists composition_genre
add constraint FKalmabyoy8ce6mpn8b93ea2sd0 
   foreign key
(composition_id) 
   references composition;

alter table
if exists composition_mark
add constraint FK590npxbs22wsmtk98r9rdk5oj 
   foreign key
(mark_id) 
   references mark;

alter table
if exists composition_mark
add constraint FK1as8eun1g10hf645bhmse28e 
   foreign key
(composition_id) 
   references composition;

alter table
if exists composition_tag
add constraint FKmw88fokdrrkwxm5anb68esm39 
   foreign key
(tag_id) 
   references tag;

alter table
if exists composition_tag
add constraint FKsgmwvbm7pfkxr8d3rjj5w6cky 
   foreign key
(composition_id) 
   references composition;

alter table
if exists mark_user
add constraint FKi45774524mniv68bdghgkvlpg 
   foreign key
(user_id) 
   references usr;

alter table
if exists mark_user
add constraint FK6hh2biwjy4hm6omhedkmr4by9 
   foreign key
(mark_id) 
   references mark;

alter table
if exists paragraph
add constraint FKlxda95b8tqip4cv6w2aqhl630 
   foreign key
(composition_id) 
   references composition;

alter table
if exists paragraph_user
add constraint FKox02904bykka7md6xiwl65s2p 
   foreign key
(user_id) 
   references usr;

alter table
if exists paragraph_user
add constraint FK1lphnlqwk9n7mjwghf211pgag 
   foreign key
(paragraph_id) 
   references paragraph;

alter table
if exists user_role
add constraint FKfpm8swft53ulq2hl11yplpr5 
   foreign key
(user_id) 
   references usr;