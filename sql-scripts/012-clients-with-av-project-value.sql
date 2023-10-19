
-- As a member of the Sales team I want to see a list of clients with the average value of their projects

SELECT c.name as `Client`, AVG(value) as `Average Value of Projects`  
FROM client c 
LEFT JOIN project p USING (client_id)
GROUP BY c.client_id ;