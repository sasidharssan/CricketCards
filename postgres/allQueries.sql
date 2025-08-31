create table rooms (
room_id varchar(20) primary key,
password varchar(255) NOT NULL,
users INT[2],
DATE DATE NOT NULL
);

create table cric_stats (
player_id SERIAL PRIMARY KEY,
player_name VARCHAR(20) NOT NULL,
PLAYER_DESIGNATION VARCHAR(20) NOT NULL,
matches INT NOT NULL,
runs INT,
balls_faced INT,
HIGHEST_SCORE INT,
STRIKE_RATE REAL,
AVERAGE REAL,
CENTURIES INT,
WICKETS INT,
BOWLING_BEST VARCHAR(6),
FIFERS INT,
BALLS_BOWLED INT,
ECONOMY REAL,
BOWLING_AVG REAL
);

CREATE TABLE USER_STACK (
	user_id SERIAL PRIMARY KEY,
	room_id varchar(20) REFERENCES rooms(room_id) ON DELETE CASCADE,
	USERNAME VARCHAR(20) NOT NULL,
	STACK INT[],
	logged_in int not null default 0
);

CREATE TABLE GAME_INFO (
	room_id varchar(20) REFERENCES rooms(room_id) ON DELETE CASCADE UNIQUE,
	USER_ID int NOT NULL REFERENCES USER_STACK(user_id) ON DELETE CASCADE,
	CURRENT_CARD INT,
	OPTION_CLICKED VARCHAR(20)
);
INSERT INTO CRIC_STATS(player_name,player_designation,matches,runs,balls_faced,highest_score,strike_rate,average,centuries,wickets,bowling_best,fifers,balls_bowled,economy,bowling_avg) VALUES ('MS Dhoni', 'Right Hand Batsman', 350, 10773, 12303, 183, 87.56, 50.57, 10, 1, '1/14', 0, 36, 5.16, 31.00);

INSERT INTO CRIC_STATS(player_name,player_designation,matches,runs,balls_faced,highest_score,strike_rate,average,centuries,wickets,bowling_best,fifers,balls_bowled,economy,bowling_avg) VALUES ('Sachin Tendulkar', 'Right Hand Batsman', 463, 18426, 21368, 200, 86.23, 44.83, 49, 154, '5/32', 2, 8054, 5.10, 44.48);

INSERT INTO CRIC_STATS(player_name,player_designation,matches,runs,balls_faced,highest_score,strike_rate,average,centuries,wickets,bowling_best,fifers,balls_bowled,economy,bowling_avg) VALUES ('AB De Villiers', 'Right Hand Batsman', 228, 9577, 9473, 176, 101.09, 53.50, 25, 7, '2/15', 0, 192, 6.31, 28.85);

INSERT INTO CRIC_STATS(player_name,player_designation,matches,runs,balls_faced,highest_score,strike_rate,average,centuries,wickets,bowling_best,fifers,balls_bowled,economy,bowling_avg) VALUES ('Muttiah Muralidharan', 'Right Arm Offspinner', 350, 674, 869, 33, 77.56, 6.80, 0, 534, '7/30', 10, 18811, 3.93, 23.03);

INSERT INTO CRIC_STATS(player_name,player_designation,matches,runs,balls_faced,highest_score,strike_rate,average,centuries,wickets,bowling_best,fifers,balls_bowled,economy,bowling_avg) VALUES ('Dale Steyn', 'Right Arm Fast', 125, 365, 562, 60, 64.94, 9.35, 0, 196, '6/39', 3, 6256, 4.87, 25.95);

INSERT INTO CRIC_STATS(player_name,player_designation,matches,runs,balls_faced,highest_score,strike_rate,average,centuries,wickets,bowling_best,fifers,balls_bowled,economy,bowling_avg) VALUES ('Yuvraj Singh', 'Allrounder', 304, 8701, 9924, 150, 87.67, 36.55, 14, 111, '5/31', 1, 5048, 5.10, 38.68);

INSERT INTO CRIC_STATS(player_name,player_designation,matches,runs,balls_faced,highest_score,strike_rate,average,centuries,wickets,bowling_best,fifers,balls_bowled,economy,bowling_avg) VALUES ('Shahid Afridi', 'Allrounder', 398, 8064, 6892, 124, 117.00, 23.57, 6, 395, '7/12', 9, 17670, 4.62, 34.51);

INSERT INTO CRIC_STATS(player_name,player_designation,matches,runs,balls_faced,highest_score,strike_rate,average,centuries,wickets,bowling_best,fifers,balls_bowled,economy,bowling_avg) VALUES ('Zaheer Khan', 'Left Arm Fast', 200, 792, 1078, 34, 73.46, 12.00, 0, 282, '5/42', 1, 10097, 4.93, 29.43);

INSERT INTO CRIC_STATS(player_name,player_designation,matches,runs,balls_faced,highest_score,strike_rate,average,centuries,wickets,bowling_best,fifers,balls_bowled,economy,bowling_avg) VALUES ('Kumar Sangakkara', 'Left Hand Batsman', 404, 14234, 18048, 169, 78.86, 41.98, 25, 0, '0/0', 0, 84, 3.50, 100);

INSERT INTO CRIC_STATS(player_name,player_designation,matches,runs,balls_faced,highest_score,strike_rate,average,centuries,wickets,bowling_best,fifers,balls_bowled,economy,bowling_avg) VALUES ('Chris Gayle', 'Left Hand Batsman', 301, 10480, 12019, 215, 87.19, 37.83, 25, 167, '5/46', 1, 7424, 4.78, 35.48);

INSERT INTO CRIC_STATS(player_name,player_designation,matches,runs,balls_faced,highest_score,strike_rate,average,centuries,wickets,bowling_best,fifers,balls_bowled,economy,bowling_avg) VALUES ('Brett Lee', 'Right Arm Fast', 221, 1176, 1407, 59, 83.58, 17.81, 0, 380, '5/22', 9, 11185, 4.76, 23.46);

INSERT INTO CRIC_STATS(player_name,player_designation,matches,runs,balls_faced,highest_score,strike_rate,average,centuries,wickets,bowling_best,fifers,balls_bowled,economy,bowling_avg) VALUES ('Virender Sehwag', 'Right Hand Batsman', 251, 8273, 7929, 219, 104.3, 35.05, 15, 96, '4/6', 0, 4392, 5.26, 40.13);

INSERT INTO CRIC_STATS(player_name,player_designation,matches,runs,balls_faced,highest_score,strike_rate,average,centuries,wickets,bowling_best,fifers,balls_bowled,economy,bowling_avg) VALUES ('Rahul Dravid', 'Right Hand Batsman', 344, 10889, 15285, 153, 71.23, 39.16, 12, 4, '2/43', 0, 186, 5.48, 42.50);

INSERT INTO CRIC_STATS(player_name,player_designation,matches,runs,balls_faced,highest_score,strike_rate,average,centuries,wickets,bowling_best,fifers,balls_bowled,economy,bowling_avg) VALUES ('Ashish Nehra', 'Left Arm fast', 120, 141, 246, 24, 57.31, 5.64, 0, 157, '6/23', 2, 5751, 5.19, 36.6);

INSERT INTO CRIC_STATS(player_name,player_designation,matches,runs,balls_faced,highest_score,strike_rate,average,centuries,wickets,bowling_best,fifers,balls_bowled,economy,bowling_avg) VALUES ('Jacques Kallis', 'Allrounder', 328, 11579, 15885, 139, 72.89, 44.36, 17, 273, '5/30', 2, 10750, 4.84, 31.79);

INSERT INTO CRIC_STATS(player_name,player_designation,matches,runs,balls_faced,highest_score,strike_rate,average,centuries,wickets,bowling_best,fifers,balls_bowled,economy,bowling_avg) VALUES ('Mahela Jayawardene', 'Right Hand Batsman', 448, 12650, 16020, 144, 78.96, 33.37, 19, 8, '2/56', 0, 593, 5.69, 74.1);

