CREATE database foories;

CREATE table food (
id int not null primary key,
name varchar(255) not null,
sum_calori int(100) not null
)

insert into food values (1,'bakso',260),
(2,'bread',128),
(3,'dessert',0),
(4,'egg',188),
(5,'french fries',211),
(6,'fried rice',267),
(7,'hamburger',257),
(8,'meat',150),
(9,'nasi padang',0),
(10,'pizza',163),
(11,'sate',729),
(12,'soto',101),
(13,'soup',0),
(14,'spaghetti',642),
(15,'sushi',0),
(16,'apel',92),
(17,'alpukat',85),
(18,'anggur',60)

select * from food

