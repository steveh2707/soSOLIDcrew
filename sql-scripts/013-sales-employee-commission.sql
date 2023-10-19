-- As a member of the HR team I want to see a list of sales employees and how much each has made in commission this year

SELECT concat(first_name, ' ', last_name) as `Sales Employee Name`, SUM(value)*se.commission_rate as `Total Commission this year` 
FROM project p 
LEFT JOIN client c USING (client_id)
LEFT JOIN sales_employee se ON c.sales_employee_id = se.employee_id 
RIGHT JOIN employee e USING (employee_id)
WHERE  YEAR(p.start_date) = YEAR(CURDATE())
GROUP BY e.employee_id;

