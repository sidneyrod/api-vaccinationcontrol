insert into tb_recipient (birth_date, email, name, phone_number, number_cpf) values ('1983-03-12 00:00:00', 'joaopaulo@gmail.com', 'João Paulo', '5511999876532', 12345678911);
insert into tb_recipient (birth_date, email, name, phone_number, number_cpf) values ('1989-07-20 00:00:00', 'anamaria@gmail.com', 'Ana Maria', '5511999876533', 12345678912);
insert into tb_recipient (birth_date, email, name, phone_number, number_cpf) values ('1975-05-14 00:00:00', 'marianasilva@gmail.com', 'Mariana Silva', '5511999876534', 12345678913);
insert into tb_recipient (birth_date, email, name, phone_number, number_cpf) values ('1961-02-07 00:00:00', 'pedrooliveira@gmail.com', 'Pedro Oliveira', '5511999876535', 12345678914);
insert into tb_recipient (birth_date, email, name, phone_number, number_cpf) values ('1965-01-21 00:00:00', 'antoniobarbosa@gmail.com', 'Antônio Barbosa', '5511999876536', 12345678915);
insert into tb_recipient (birth_date, email, name, phone_number, number_cpf) values ('1992-11-07 00:00:00', 'jaquelinerocha@gmail.com', 'Jaqueline Rocha', '5511999876537', 12345678916);
insert into tb_recipient (birth_date, email, name, phone_number, number_cpf) values ('1953-06-09 00:00:00', 'osvaldocastro@gmail.com', 'Osvaldo Castro', '5511999876538', 12345678917);

insert into tb_registration (name_vaccine) values ('Pfizer Vaccine Covid-19');
insert into tb_registration (name_vaccine) values ('Oxford Vaccine Covid-19');
insert into tb_registration (name_vaccine) values ('Sanofi Pasteur Vaccine H1N1');

insert into tb_vaccinationcontrol (country_vaccination, number_dose, vaccine_application_date, vaccine_id, created ) 
values ('Brazil', 1, '2021-02-10 00:00:00', 1, NOW());
insert into tb_vaccinationcontrol (country_vaccination, number_dose, vaccine_application_date, vaccine_id, created ) 
values ('Brazil', 1, '2021-02-11 00:00:00', 2, NOW());
insert into tb_vaccinationcontrol (country_vaccination, number_dose, vaccine_application_date, vaccine_id, created ) 
values ('Brazil', 1, '2021-02-12 00:00:00', 3, NOW());
insert into tb_vaccinationcontrol (country_vaccination, number_dose, vaccine_application_date, vaccine_id, created ) 
values ('Brazil', 1, '2021-02-15 00:00:00', 1, NOW());
insert into tb_vaccinationcontrol (country_vaccination, number_dose, vaccine_application_date, vaccine_id, created ) 
values ('Brazil', 1, '2021-02-16 00:00:00', 2, NOW());
insert into tb_vaccinationcontrol (country_vaccination, number_dose, vaccine_application_date, vaccine_id, created ) 
values ('Brazil', 1, '2021-02-17 00:00:00', 1, NOW());
insert into tb_vaccinationcontrol (country_vaccination, number_dose, vaccine_application_date, vaccine_id, created ) 
values ('Brazil', 1, '2021-02-18 00:00:00', 2, NOW());
insert into tb_vaccinationcontrol (country_vaccination, number_dose, vaccine_application_date, vaccine_id, created ) 
values ('Brazil', 2, '2021-05-10 00:00:00', 2, NOW());

insert into tb_recipient_control (recipient_id, control_id) values (4, 1);
insert into tb_recipient_control (recipient_id, control_id) values (7, 2);
insert into tb_recipient_control (recipient_id, control_id) values (6, 3);
insert into tb_recipient_control (recipient_id, control_id) values (1, 4);
insert into tb_recipient_control (recipient_id, control_id) values (5, 5);
insert into tb_recipient_control (recipient_id, control_id) values (2, 6);
insert into tb_recipient_control (recipient_id, control_id) values (3, 7);
insert into tb_recipient_control (recipient_id, control_id) values (7, 8);