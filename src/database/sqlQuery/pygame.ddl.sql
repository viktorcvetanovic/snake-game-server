
-- table user
drop table if exists USER;
CREATE TABLE USER(
user_id INT(10) AUTO_INCREMENT PRIMARY KEY,
username VARCHAR(40),
password VARCHAR(500),
email VARCHAR(20),
game_name VARCHAR(10),
money INT(10)

);

-- userskin
drop table if exists USERSKIN;
CREATE TABLE USERSKIN(
user_id INT,
skin_id INT,
 FOREIGN KEY (skin_id) REFERENCES SKINS(skin_id),
 FOREIGN KEY (user_id) REFERENCES USER(user_id),
PRIMARY KEY(user_id, skin_id)
);

-- skins table
drop table if exists SKINS;
CREATE TABLE SKINS(
skin_id INT(10) AUTO_INCREMENT PRIMARY KEY,
skin_name  VARCHAR(40),
skin_price INT (10),
skin_image_path  VARCHAR(150)
);

-- bestScores table
drop table if exists BESTSCORES;
CREATE TABLE BESTSCORES(
score_id INT(10) AUTO_INCREMENT PRIMARY KEY,
score INT(10),
user_id int,
FOREIGN KEY (user_id) REFERENCES USER(user_id)
);
