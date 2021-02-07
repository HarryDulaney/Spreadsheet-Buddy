create table project
(
	project_id varchar(45) not null
		primary key
);

create table recent_files
(
	id int auto_increment
		primary key,
	recent_file varchar(90) null,
	assoc_project_id varchar(45) not null,
	constraint recent_files_project_project_id_fk
		foreign key (assoc_project_id) references project (project_id)
);

