use sakila;

-- Part 1

-- Q1 Display all tables available in the database “sakila”
show tables;

-- Q2 Display structure of table “actor”. (4 row) 
desc actor;

-- Q3 Display the schema which was used to create table “actor” and view the complete schema using the viewer. (1 row) 
show create table sakila.actor;

-- Q4 Display the first and last names of all actors from the table actor. (200 rows) 
select first_name,last_name from actor;

-- Q5 Which actors have the last name ‘Johansson’. (3 rows) 
select first_name,last_name from actor where last_name="Johansson";

-- Q6 Display the first and last name of each actor in a single column in upper case letters. Name the column Actor Name. (200 rows) 
select upper(concat(first_name," ",last_name)) from actor;

-- Q7 You need to find the ID number, first name, and last name of an actor, of whom you know only the first name, "Joe." What is one query would you use to obtain this information? (1 row) 
select actor_id, first_name,last_name from actor where first_name="Joe";

-- Q8 Which last names are not repeated? (66 rows) 
select last_name from actor group by last_name having count(last_name)=1;

-- Q9 List the last names of actors, as well as how many actors have that last 
select last_name,count(last_name) from actor group by last_name;

-- Q10 Use JOIN to display the first and last names, as well as the address, of each staff member. Use the tables “staff” and “address”. (2 rows)
select first_name, last_name, address from staff join address using(address_id);

-- Part 2

-- Q1 Which actor has appeared in the most films? (‘107', 'GINA', 'DEGENERES', '42') 
select fa.actor_id,a.first_name,a.last_name,count(fa.film_id) from actor a
join film_actor fa on a.actor_id=fa.actor_id
join film f on fa.film_id=f.film_id
group by fa.actor_id order by count(fa.film_id) desc limit 1;

-- Q2 What is the average length of films by category? (16 rows) 
select name ,avg(length) from film
join film_category using(film_id) 
join category using(category_id)
group by name;

-- Q3 Which film categories are long? (5 rows) 
select c.name, avg(length)
from category c
join film_category fc on c.category_id=fc.category_id
join film f on fc.film_id=f.film_id
group by name having avg(length) between (select avg(length) from film) and (select max(length) from film);

-- Q4 How many copies of the film “Hunchback Impossible” exist in the inventory system? (6) 
select count(*) from inventory where film_id=(select film_id from film where title = "Hunchback Impossible");

-- Q5 Using the tables “payment” and “customer” and the JOIN command, list the total paid by each customer. List the customers alphabetically by last name (599 rows) 
select first_name, last_name,sum(amount) 
from customer 
join payment using(customer_id)
group by customer_id order by last_name;


