USE soSOLIDcrew_StephenH;

CREATE TABLE `Role` (
	RoleID TINYINT NOT NULL,
	Name VARCHAR(64) NOT NULL,
	PRIMARY KEY(RoleID)
);

INSERT INTO Role(RoleID, Name) VALUES
(1,"Management"),
(2,"Sales");
(3,"HR")

CREATE TABLE `User` (
	Username VARCHAR(64) NOT NULL,
	Password VARCHAR(64) NOT NULL,
	RoleID TINYINT NOT NULL,
	PRIMARY KEY (Username),
	FOREIGN KEY (RoleID) REFERENCES Role(RoleID)
);

INSERT INTO `User`(Username, Password, RoleID) VALUES
('management','management',1),
("sales","sales",2),
("hr","hr");

CREATE TABLE Token(
	Username VARCHAR(64) NOT NULL,
	Token VARCHAR(64) NOT NULL,
	Expiry DATETIME NOT NULL,
	FOREIGN KEY (Username) REFERENCES `User`(Username)
);