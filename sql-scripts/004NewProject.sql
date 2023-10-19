-- 4. As a member of the Sales team I want to be able to create a new project. I should be able to store a project name, 
-- value and a list of technologies that the project will use

USE TheRuntimeTerrors_StephenH;

-- create project table
CREATE TABLE project (
	project_id smallint unsigned AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(255) NOT NULL,
    `value` decimal(11,2) NOT NULL, 
    start_date DATE NOT NULL,
    completion_date DATE,
    tech_lead_delivery_id smallint unsigned NOT NULL,
    client_id smallint unsigned NOT NULL,
    FOREIGN KEY (tech_lead_delivery_id)
    REFERENCES delivery_employee(employee_id),
    FOREIGN KEY (client_id)
    REFERENCES `client`(client_id),
    CHECK (completion_date >= start_date)
);

-- create technology table
CREATE TABLE technology (
	technology_id smallint unsigned AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(100) NOT NULL
);

-- create project_technology table
CREATE TABLE project_technology (
	project_id smallint unsigned NOT NULL,
    FOREIGN KEY(project_id) REFERENCES project(project_id),
	technology_id smallint unsigned NOT NULL,
    FOREIGN KEY(technology_id) REFERENCES technology(technology_id)
);

-- make composite key in project_technology table using project_id and technology_id
ALTER TABLE project_technology
ADD CONSTRAINT pk_project_id_technology_id
PRIMARY KEY (project_id, technology_id);

-- ADD DATA TO PROJECT TABLE

-- invalid data, tech_lead_delivery_id is not a delivery employee
INSERT INTO project
(`name`, `value`, start_date, completion_date, tech_lead_delivery_id, client_id)
VALUES
('first project', 10000, '2023-10-12', '2023-11-11', 1, 2);

-- valid data, will be insterted since completion date allowed to be null
INSERT INTO project
(`name`, `value`, start_date, tech_lead_delivery_id, client_id)
VALUES
('cool project', 20000, '2023-11-12', 3, 3);

-- valid data, completion date is after start date
INSERT INTO project
(`name`, `value`, start_date, completion_date, tech_lead_delivery_id, client_id)
VALUES
('good project', 20000, '2023-11-12', '2023-12-12', 3, 4);

-- valid data, completion date is after start date
INSERT INTO project
(`name`, `value`, start_date, completion_date, tech_lead_delivery_id, client_id)
VALUES
('nice project', 20000, '2023-01-02', '2023-10-14', 3, 3);

-- valid data, completion date is after start date
INSERT INTO project
(`name`, `value`, start_date, completion_date, tech_lead_delivery_id, client_id)
VALUES
('ok project', 18000, '2023-05-02', '2024-10-14', 4, 3);

-- invalid data, will fail since completion date BEFORE start date
INSERT INTO project
(`name`, `value`, start_date, completion_date, tech_lead_delivery_id, client_id)
VALUES
(' project', 30000, '2023-12-17', '2023-10-17', 1, 1);

-- ADD DATA TO TECHNOLOGY TABLE
INSERT INTO technology (`name`) VALUES ('Java');
INSERT INTO technology (`name`) VALUES ('SQL');
INSERT INTO technology (`name`) VALUES ('php');
INSERT INTO technology (`name`) VALUES ('Python');
INSERT INTO technology (`name`) VALUES ('ChatGPT');
INSERT INTO technology (`name`) VALUES ('Docker');

-- ADD DATA TO PROJECT TECHNOLOGY TABLE

-- will fail to insert, project_id=1 doesn't exist
INSERT INTO project_technology (project_id, technology_id) VALUES (1,1);

-- will fail to insert, technology_id=13 doesn't exist
INSERT INTO project_technology (project_id, technology_id) VALUES (9,13);

-- will insert, both ids exist
INSERT INTO project_technology (project_id, technology_id) VALUES (9,1);
INSERT INTO project_technology (project_id, technology_id) VALUES (9,2);
INSERT INTO project_technology (project_id, technology_id) VALUES (9,5);
INSERT INTO project_technology (project_id, technology_id) VALUES (9,6);
INSERT INTO project_technology (project_id, technology_id) VALUES (11,2);
INSERT INTO project_technology (project_id, technology_id) VALUES (11,3);
INSERT INTO project_technology (project_id, technology_id) VALUES (11,6);
INSERT INTO project_technology (project_id, technology_id) VALUES (8,4);
INSERT INTO project_technology (project_id, technology_id) VALUES (8,5);
INSERT INTO project_technology (project_id, technology_id) VALUES (12,1);

-- will fail, same values inserted, not allowed due to composite key
INSERT INTO project_technology (project_id, technology_id) VALUES (14,1);

-- join statements to find name of project and names of technologies on project
SELECT project.`name`, GROUP_CONCAT(technology.`name` SEPARATOR ', ') 
FROM project
RIGHT JOIN project_technology
ON project.project_id = project_technology.project_id
LEFT JOIN technology
ON technology.technology_id = project_technology.technology_id
WHERE project.project_id = 9
GROUP BY project.`name`;




