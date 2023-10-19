USE ThePrimaryKeys_DylanC;

-- As a member of the HR team I want to see a list of sales employees who haven't won any clients this year

SELECT concat(first_name, ' ', last_name) as `Sales Employee Name` 
FROM  sales_employee se
LEFT JOIN employee e ON se.employee_id = e.employee_id 
LEFT JOIN client c ON c.sales_employee_id = se.employee_id
WHERE  YEAR(c.date_added) != YEAR(CURDATE()) OR client_id IS NULL;
