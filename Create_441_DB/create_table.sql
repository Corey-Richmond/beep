create DATABASE beep;
use beep;
SET FOREIGN_KEY_CHECKS=0;


create table City(
	cityID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	cityName    VARCHAR(40), 
	cityState   VARCHAR(40),
	cityCountry VARCHAR(40)
);

create table MovieGenre(
	movieGenreID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	genre VARCHAR(40),
	totalLikes INT
);

create table Person(
	personID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	firstName  VARCHAR(40),
	middleName VARCHAR(40),
	lastName VARCHAR(40)
);

create table Sport(
	sportID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name  VARCHAR(40),
	totalLikes INT
);

create table musicGenre(
	musicGenreID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	genre VARCHAR(40) NOT NULL,
	totalLikes INT
);

create table Actor(
	actorID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	personID INT,
	totalLikes INT,
	FOREIGN KEY (personID) REFERENCES Person(personID)
);

create table ActorCitiesList(
	actorCitiesListID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	cityID INT,
	actorID INT,
	cityRank INT,
	likes INT,
	FOREIGN KEY (cityID) REFERENCES City(cityID),
	FOREIGN KEY (actorID) REFERENCES Actor(actorID)
);

create table Artist(
	artistID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	personID INT,
	musicGenreID INT,
	totalLikes INT,
	FOREIGN KEY (personID) REFERENCES Person(personID),
	FOREIGN KEY (musicGenreID) REFERENCES MusicGenre(musicGenreID)
);

create table ArtistCitiesList(
	artistsCitiesListID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	cityID INT,
	artistID INT,
	cityRank INT,
	likes INT,
	FOREIGN KEY (cityID) REFERENCES City(cityID),
	FOREIGN KEY (artistID) REFERENCES Artist(artistID)
);


create table Movie(
	movieID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	title VARCHAR(40) NOT NULL,
	movieGenreID INT,
	totalLikes INT,
	rating INT,
	revenue INT,
	FOREIGN KEY (movieGenreID) REFERENCES MovieGenre(movieGenreID)
);

create table MovieCast(
	castID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	movieID INT,
	actorID INT,
	FOREIGN KEY (movieID) REFERENCES Movie(movieID),
	FOREIGN KEY (actorID) REFERENCES Actor(actorID)
);

create table MovieCitiesList(
	citiesListID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	cityID INT,
	movieID INT,
	cityRank INT,
	likes INT,
	FOREIGN KEY (cityID) REFERENCES City(cityID),
	FOREIGN KEY (movieID) REFERENCES Movie(movieID)
);




create table SportCitiesList(
	sportCitiesListID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	cityID INT,
	sportID INT,
	cityRank INT,
	likes INT,
	FOREIGN KEY (cityID) REFERENCES City(cityID),
	FOREIGN KEY (sportID) REFERENCES Sport(sportID)
);

create table SportsVenue(
	venueID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name    VARCHAR(40) NOT NULL,
	address VARCHAR(80),
	cityID INT,
	FOREIGN KEY (cityID) REFERENCES City(cityID)
);


create table Team(
	teamID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	teamName  VARCHAR(40) NOT NULL,
	sportID INT,
	cityID INT,
	venueID INT,
	totalLikes INT,
	FOREIGN KEY (sportID) REFERENCES Sport(sportID),
	FOREIGN KEY (cityID) REFERENCES City(cityID),
	FOREIGN KEY (venueID) REFERENCES SportsVenue(venueID)
);

create table TeamCitiesList(
	teamCitiesListID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	cityID INT,
	teamID INT,
	cityRank INT,
	likes INT,
	FOREIGN KEY (cityID) REFERENCES City(cityID),
	FOREIGN KEY (teamID) REFERENCES Team(teamID)
);

create table Athlete(
	athleteID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	personID INT,
	totalLikes INT,
	rating INT,
	teamID INT,	
	FOREIGN KEY (personID) REFERENCES Person(personID),
	FOREIGN KEY (teamID) REFERENCES Team(teamID)
);

create table AthleteCitiesList(
	athleteCitiesListID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	cityID INT,
	athleteID INT,
	cityRank INT,
	likes INT,
	FOREIGN KEY (cityID) REFERENCES City(cityID),
	FOREIGN KEY (athleteID) REFERENCES Athlete(athleteID)
);


create table MovieVenue(
	venueID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name    VARCHAR(40) NOT NULL,
	address VARCHAR(80),
	cityID INT,
	FOREIGN KEY (cityID) REFERENCES City(cityID)


);

create table ConcertVenue(
	venueID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name    VARCHAR(80) NOT NULL,
	address VARCHAR(80),
	cityID INT,
	FOREIGN KEY (cityID) REFERENCES City(cityID)
);


