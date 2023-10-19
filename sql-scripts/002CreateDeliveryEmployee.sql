-- As a member of the HR team I want to be able to create a new delivery employee. 
-- I should be able to store a name, salary, bank account number and national insurance number

USE TheRuntimeTerrors_StephenH;

-- creating delivery_employee table
CREATE TABLE delivery_employee (
    employee_id smallint unsigned AUTO_INCREMENT PRIMARY KEY,
    FOREIGN KEY (employee_id)
	REFERENCES employee(employee_id)
);

-- insert new employees into delivery_employee table
INSERT INTO delivery_employee
(employee_id) 
VALUES (3);

INSERT INTO delivery_employee
(employee_id) 
VALUES (4);

INSERT INTO delivery_employee
(employee_id) 
VALUES (5);

-- create right join with employee table to see id of delivery employee and name
SELECT delivery_employee.employee_id, CONCAT(first_name, ' ', last_name) AS `Name` 
FROM delivery_employee
LEFT JOIN employee
ON delivery_employee.employee_id = employee.employee_id;

