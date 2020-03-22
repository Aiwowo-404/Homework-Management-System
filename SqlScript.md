```mysql
drop database if exists axw;

create database axw;

use axw;

create table student
(
	id varchar(20) not null
		primary key,
	name varchar(20) null,
	password varchar(20) null
);

create table teacher
(
	id varchar(20) not null
		primary key,
	name varchar(20) null,
	password varchar(20) null
);

create table homework
(
	tid varchar(20) not null,
	sid varchar(20) not null,
	homework text null,
	flag varchar(10) null,
	feedback text null,
	constraint homework_student_id_fk
		foreign key (sid) references student (id),
	constraint homework_teacher_id_fk
		foreign key (tid) references teacher (id)
);

```

