-- Use the team's database.
USE TheRuntimeTerrors_StephenH;

-- Create client table
CREATE TABLE IF NOT EXISTS client(
	client_id SMALLINT UNSIGNED AUTO_INCREMENT NOT NULL,
	name VARCHAR(255) NOT NULL,
	address VARCHAR(255) NOT NULL,
	phone_number VARCHAR(11) NOT NULL,
	PRIMARY KEY (client_id),
    sales_employee_id SMALLINT UNSIGNED NOT NULL,
    FOREIGN KEY (sales_employee_id) REFERENCES sales_employee(employee_id)
);

-- Insert data into table
INSERT INTO client (name, address, phone_number, sales_employee_id)
VALUES ('Bob Dylan', '123 somewhere over the rainbow St', '07935754333', 1);

INSERT INTO client (name, address, phone_number, sales_employee_id)
VALUES ('New client', '12 Main Street', '07932094333', 2);

INSERT INTO client (name, address, phone_number, sales_employee_id)
VALUES ('Best Client', '15 Belfast Road', '07935092633', 1);


-- join and select column specifically for other branch
SELECT client_id, name, address, phone_number, sales_employee_id from client;
