create table homework_management_system.homework
(
    id        varchar(20)  not null,
    name      varchar(20)  null,
    content   varchar(255) null,
    resource  varchar(50)  null,
    status    int          null,
    studentid varchar(20)  null,
    teacherid varchar(20)  null,
    answer    text         null,
    primary key (id)
);

create table homework_management_system.student
(
    id       varchar(20) not null,
    name     varchar(20) null,
    password varchar(20) null,
    primary key (id)
);

create table homework_management_system.teacher
(
    id       varchar(20) not null,
    name     varchar(20) null,
    password varchar(20) null,
    primary key (id)
);


