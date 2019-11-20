create table branch
(
	location text not null,
	city text not null,
	constraint branch_pkey
		primary key (location, city)
);

alter table branch owner to postgres;

