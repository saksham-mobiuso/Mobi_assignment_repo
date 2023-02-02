use world_x;

-- Part 1

-- Q1 Display all columns and 10 rows from table “city”.(10 rows) 
select * from city limit 10;

-- Q2 Modify the above query to display from row # 16 to 20 with all columns. (5 rows) 
select * from city limit 5 offset 15; 

-- Q3 How many rows are available in the table city. (1 row)-4079. 
select count(*) from city;

-- Q4 Using city table find out which is the most populated city. 

select name, info from city order by json_extract(info,'$.Population') desc limit 1;
-- select name,info from city where info = (select min(info) from city); 
-- select min(name), max(info) from city group by info having info =(select max(info) from city);

-- Q5 Using city table find out the least populated city. 
select min(name), min(info) from city group by info having info = (select min(info) from city);

-- Q6 Display name of all cities where population is between 670000 to 700000. (13 rows) 
select name, info from city where json_extract(info, '$.Population') between 670000 and 700000;

-- Q7 Find out 10 most populated cities and display them in a decreasing order i.e. most populated city to appear first. 
select name, info from city order by json_extract(info,'$.Population') desc limit 10;

-- Q8 Order the data by city name and get first 10 cities from city table. 
select name from city order by name limit 10;

-- Q9 Display all the districts of USA where population is greater than 3000000, from city table. (6 rows) 
select district from city where CountryCode="USA" and json_extract(info,'$.Population')>3000000; -- Check

-- Q10 What is the value of name and population in the rows with ID =5, 23, 432 and 2021. Pl. write a single query to display the same. (4 rows). 
select name,info from city where id in (5,23,432,2021);


-- Part 2


-- Q1 Write a query in SQL to display the code, name, continent and GNP for all the countries whose country name last second word is 'd’, using “country” table. (22 rows) 
select json_extract(doc,'$.Code'), json_extract(doc,'$.Name'), json_extract(doc,'$.geography.Continent'), json_extract(doc,'$.GNP')
from countryinfo where json_extract(doc,'$.Name') like '%d_"';

-- Q2 Write a query in SQL to display the code, name, continent and GNP of the 2nd and 3rd highest GNP from “country” table. (Japan & Germany) 
select json_extract(doc,'$.Code'), json_extract(doc,'$.Name'), json_extract(doc,'$.geography.Continent'), json_extract(doc,'$.GNP')
from countryinfo order by json_extract(doc,'$.GNP') desc limit 2 offset 1;