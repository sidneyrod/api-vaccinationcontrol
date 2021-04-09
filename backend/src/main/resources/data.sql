insert into tb_users (birth_date, email, name, number_cpf) values ('1983-03-12 00:00:00', 'joaopaulo@gmail.com', 'João Paulo', '00100200304');
insert into tb_users (birth_date, email, name, number_cpf) values ('1989-07-20 00:00:00', 'anamaria@gmail.com', 'Ana Maria', '00100200305');

insert into tb_vaccine (user_email, vaccine_date, vaccine_name) values ('joaopaulo@gmail.com', '2021-02-01 00:00:00', 'Covid19Vaccine');
insert into tb_vaccine (user_email, vaccine_date, vaccine_name) values ('anamaria@gmail.com', '2021-02-02 00:00:00', 'H1N1Vaccine');
insert into tb_vaccine (user_email, vaccine_date, vaccine_name) values ('anamaria@gmail.com', '2021-02-02 00:00:00', 'Covid19Vaccine');
insert into tb_vaccine (user_email, vaccine_date, vaccine_name) values ('joaopaulo@gmail.com', '2021-02-01 00:00:00', 'H1N1Vaccine');

insert into tb_users_vaccine (users_id, vaccine_id) values (1, 1);
insert into tb_users_vaccine (users_id, vaccine_id) values (1, 4);
insert into tb_users_vaccine (users_id, vaccine_id) values (2, 2);
insert into tb_users_vaccine (users_id, vaccine_id) values (2, 3);