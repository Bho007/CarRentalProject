create table if not exists timeperiod
(
	fromdate date not null,
	fromtime time not null,
	todate date not null,
	totime time not null,
	constraint timeperiod_pkey
		primary key (fromdate, fromtime, todate, totime),
	constraint timeperiod_check
		check (todate >= fromdate)
);

alter table timeperiod owner to postgres;

create table if not exists branch
(
	location text not null,
	city text not null,
	constraint branch_pkey
		primary key (location, city)
);

alter table branch owner to postgres;

create table if not exists vehicletype
(
	vtname text not null
		constraint vehicletype_pkey
			primary key,
	features text,
	wrate integer,
	drate integer,
	hrate integer,
	wirate integer,
	dirate integer,
	hirate integer,
	krate integer
);

alter table vehicletype owner to postgres;

create table if not exists vehicle
(
	vid integer generated always as identity,
	vlicense text not null
		constraint vehicle_pk
			primary key,
	make text,
	model text,
	year text,
	color text,
	odometer integer,
	status vehicle_status,
	vtname text not null
		constraint vehicle_vtname_fkey
			references vehicletype
				on delete cascade,
	location text,
	city text,
	constraint fk_vehicle
		foreign key (location, city) references branch
);

alter table vehicle owner to postgres;

create table if not exists equiptype
(
	etname text not null
		constraint equiptype_pkey
			primary key,
	drate integer,
	hrate integer
);

alter table equiptype owner to postgres;

create table if not exists equipment
(
	eid integer generated always as identity
		constraint equipment_pkey
			primary key,
	etname text,
	status equipment_status,
	location text,
	city text,
	constraint fk_equipment
		foreign key (location, city) references branch
);

alter table equipment owner to postgres;

create table if not exists eforv
(
	etname text
		constraint eforv_etname_fkey
			references equiptype,
	vtname text
		constraint eforv_vtname_fkey
			references vehicletype
);

alter table eforv owner to postgres;

create table if not exists customer
(
	cellphone bigint not null
		constraint customer_pkey
			primary key,
	name text,
	address text,
	dlicense text
);

alter table customer owner to postgres;

create unique index if not exists customer_dlicense_uindex
	on customer (dlicense);

create table if not exists clubmember
(
	cellphone bigint not null
		constraint clubmember_pkey
			primary key
		constraint clubmember_cellphone_fkey
			references customer
				on delete cascade,
	points integer,
	fees integer
);

alter table clubmember owner to postgres;

create table if not exists reservation
(
	confno integer generated always as identity
		constraint reservation_pkey
			primary key,
	vtname text not null
		constraint reservation_vtname_fkey
			references vehicletype
				on delete cascade,
	cellphone bigint not null
		constraint reservation_cellphone_fkey
			references customer
				on delete cascade,
	fromdate date,
	fromtime time,
	todate date,
	totime time,
	constraint fk_timeperiod
		foreign key (fromdate, fromtime, todate, totime) references timeperiod
);

alter table reservation owner to postgres;

create table if not exists reserve_includes
(
	confno integer not null
		constraint reserve_includes_confno_fkey
			references reservation
				on delete cascade,
	etname text not null
		constraint reserve_includes_etname_fkey
			references equiptype
				on delete cascade,
	constraint reserve_includes_pkey
		primary key (confno, etname)
);

alter table reserve_includes owner to postgres;

create table if not exists rent
(
	rid integer generated always as identity
		constraint rent_pkey
			primary key,
	vlicense text
		constraint rent_vehicle_vlicense_fk
			references vehicle
				on update cascade on delete cascade,
	fromdate date not null,
	fromtime time not null,
	todate date not null,
	totime time not null,
	odometer integer,
	cardname text,
	cardno bigint,
	expdate date,
	confno integer
		constraint rent_confno_fkey
			references reservation,
	cellphone bigint not null
		constraint cellphone_fk
			references customer
				on update cascade on delete cascade
);

alter table rent owner to postgres;

create table if not exists rent_includes
(
	rid integer not null
		constraint rent_includes_rid_fkey
			references rent
				on delete cascade,
	eid integer not null
		constraint rent_includes_eid_fkey
			references equipment
				on delete cascade,
	constraint rent_includes_pkey
		primary key (rid, eid)
);

alter table rent_includes owner to postgres;

create table if not exists return
(
	rid integer not null
		constraint return_pkey
			primary key
		constraint return_rid_fkey
			references rent
				on delete cascade,
	date date,
	time time,
	odometer integer,
	fulltank boolean,
	value integer
);

alter table return owner to postgres;

