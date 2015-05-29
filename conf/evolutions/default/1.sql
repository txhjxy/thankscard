# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table categories (
  category_id               integer not null,
  category_name             varchar(255),
  constraint pk_categories primary key (category_id))
;

create table departments (
  dept_id                   integer not null,
  dept_name                 varchar(255),
  constraint pk_departments primary key (dept_id))
;

create table employees (
  emp_id                    varchar(255) not null,
  emp_name                  varchar(255),
  dept_id                   integer,
  password                  varchar(255),
  job_id                    integer,
  constraint pk_employees primary key (emp_id))
;

create table thanks (
  tnkcard_id                integer not null,
  tnk_id                    varchar(255),
  help_contents             varchar(255),
  tnk_contents              varchar(255),
  category_id               integer,
  ywk_id                    varchar(255),
  tnk_date                  timestamp,
  tnk_point                 integer,
  constraint pk_thanks primary key (tnkcard_id))
;

create sequence categories_seq;

create sequence departments_seq;

create sequence employees_seq;

create sequence thanks_seq;

alter table employees add constraint fk_employees_dept_id_1 foreign key (dept_id) references departments (dept_id) on delete restrict on update restrict;
create index ix_employees_dept_id_1 on employees (dept_id);
alter table thanks add constraint fk_thanks_emp_id_2 foreign key (tnk_id) references employees (emp_id) on delete restrict on update restrict;
create index ix_thanks_emp_id_2 on thanks (tnk_id);
alter table thanks add constraint fk_thanks_category_id_3 foreign key (category_id) references categories (category_id) on delete restrict on update restrict;
create index ix_thanks_category_id_3 on thanks (category_id);
alter table thanks add constraint fk_thanks_emp_id2_4 foreign key (ywk_id) references employees (emp_id) on delete restrict on update restrict;
create index ix_thanks_emp_id2_4 on thanks (ywk_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists categories;

drop table if exists departments;

drop table if exists employees;

drop table if exists thanks;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists categories_seq;

drop sequence if exists departments_seq;

drop sequence if exists employees_seq;

drop sequence if exists thanks_seq;

