CREATE database foories;

CREATE table food (
id int not null primary key,
name varchar(255) not null,
sum_calori int(100) not null
)

insert into food values (1,'bakso 1 mangkok',283),
(2,'bread per 1 porsi sedang',106),
(3,'dessert per 1 porsi',49),
(4,'egg per 1 porsi',74),
(5,'french fries per 1 porsi',211),
(6,'fried rice per 1 porsi',267),
(7,'hamburger per 1 porsi',257),
(8,'meat per 100 gram',288),
(9,'nasi padang per porsi',330),
(10,'pizza per slice',276),
(11,'sate per 1 porsi',729),
(12,'soto per 1 porsi',101),
(13,'soup per 1 porsi',72),
(14,'spaghetti per 1 porsi',642),
(15,'sushi per 1 buah',31),
(16,'vegfruit per 1 porsi',261)

select * from food

[{}]