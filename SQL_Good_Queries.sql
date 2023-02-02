use orders;

-- 1. Write a query to Display the product details (product_class_code, product_id, product_desc, product_price,) as per the following criteria and sort them in descending order of category: a. If the category is 2050, increase the price by 2000 b. If the category is 2051, increase the price by 500 c. If the category is 2052, increase the price by 600. Hint: Use case statement. no permanent change in table required. (60 ROWS) [NOTE: PRODUCT TABLE]
select product_class_code , product_id, product_desc,
case product_class_code
	when 2050 then product_price+2000
    when 2051 then product_price+500
    when 2052 then product_price+600
else product_price
end
from product
order by product_desc;

-- 2. Write a query to display (product_class_desc, product_id, product_desc, product_quantity_avail ) and Show inventory status of products as below as per their available quantity: a. For Electronics and Computer categories, if available quantity is <= 10, show 'Low stock', 11 <= qty <= 30, show 'In stock', >= 31, show 'Enough stock' b. For Stationery and Clothes categories, if qty <= 20, show 'Low stock', 21 <= qty <= 80, show 'In stock', >= 81, show 'Enough stock' c. Rest of the categories, if qty <= 15 – 'Low Stock', 16 <= qty <= 50 – 'In Stock', >= 51 – 'Enough stock' For all categories, if available quantity is 0, show 'Out of stock'. Hint: Use case statement. (60 ROWS) [NOTE: TABLES TO BE USED – product, product_class]
select pc.product_class_desc, prod.product_id, prod.product_desc, prod.product_quantity_avail,
case
when product_class_desc="Electronics" or product_class_desc ="Computer" then
	(case
		when prod.product_quantity_avail >=31 then "Enough stock"
        when prod.product_quantity_avail >=11 then "In stock"
        when prod.product_quantity_avail <=10 then "Low stock"
	end
    )
when product_class_desc="Stationery" or product_class_desc ="Clothesr" then
	(case
		when prod.product_quantity_avail >=81 then "Enough stock"
        when prod.product_quantity_avail >=21 then "In stock"
        when prod.product_quantity_avail <=20 then "Low stock"
	end
    )
else
	(case
		when prod.product_quantity_avail >=51 then "Enough stock"
        when prod.product_quantity_avail >=16 then "In stock"
        when prod.product_quantity_avail <=15 then "Low stock"
	end
    )
end as inverntory_status

from product prod 
join product_class pc on prod.product_class_code=pc.product_class_code;

-- 3. Write a query to Show the count of cities in all countries other than USA & MALAYSIA, with more than 1 city, in the descending order of CITIES. (2 rows) [NOTE: ADDRESS TABLE, Do not use Distinct]
select country, count(city) 
from address 
where country not in ("USA", "Malaysia") group by country having count(city) > 2;

-- 4. Write a query to display the customer_id,customer full name ,city,pincode,and order details (order id, product class desc, product desc, subtotal(product_quantity * product_price)) for orders shipped to cities whose pin codes do not have any 0s in them. Sort the output on customer name and subtotal. (52 ROWS) [NOTE: TABLE TO BE USED - online_customer, address, order_header, order_items, product, product_class]

select oc.customer_id, concat(oc.customer_fname," ", customer_lname) as customer_fullname, addr.city, addr.pincode, oh.order_id, oh.order_date, pc.product_class_desc, prod.product_desc, (oi.product_quantity*prod.product_price) as subtotal

from address addr

right join online_customer oc on addr.address_id=oc.address_id

join order_header oh on oc.customer_id=oh.customer_id

join order_items oi on oh.order_id=oi.order_id

join product prod on oi.product_id=prod.product_id

join product_class pc on prod.product_class_code=pc.product_class_code

where addr.pincode not like "%0%" and oh.order_status="Shipped";

-- 5. Write a Query to display product id,product description,totalquantity(sum(product quantity) for an item which has been bought maximum no. of times (Quantity Wise) along with product id 201. (USE SUB-QUERY) (1 ROW) [NOTE: ORDER_ITEMS TABLE, PRODUCT TABLE]

select max(oi.product_id) as productid, prod.product_desc, count(order_id) as total_quantity

from order_items oi

join product prod  using(product_id)

group by prod.product_desc having prod.product_desc in  (select product_desc from order_items where product_id=201)

order by count(order_id) desc limit 1;

-- 6. Write a query to display the customer_id,customer name, email and order details (order id, product desc,product qty, subtotal(product_quantity * product_price)) for all customers even if they have not ordered any item.(225 ROWS) [NOTE: TABLE TO BE USED - online_customer, order_header, order_items, product]

select oc.customer_id, oc.customer_fname, oc.customer_email, oh.order_id, prod.product_desc, oi.product_quantity, (oi.product_quantity*prod.product_price) as subtotal

from online_customer oc

left join order_header oh on oc.customer_id=oh.customer_id

left join order_items oi on oh.order_id=oi.order_id

left join product prod on oi.product_id=prod.product_id;

-- 7. Write a query to display carton id, (len*width*height) as carton_vol and identify the optimum carton (carton with the least volume whose volume is greater than the total volume of all items (len * width * height * product_quantity)) for a given order whose order id is 10006, Assume all items of an order are packed into one single carton (box). (1 ROW) [NOTE: CARTON TABLE]

select carton_id, (c.len*c.width*c.height) as carton_vol,s.vol

from carton c,(select sum(prod.len*prod.width*prod.height*oi.product_quantity) as vol from product prod join order_items oi on prod.product_id=oi.product_id where oi.order_id=10006) s

where c.len*c.width*c.height>vol

order by carton_vol limit 1;

-- 8. Write a query to display details (customer id,customer fullname,order id,product quantity) of customers who bought more than ten (i.e. total order qty) products per shipped order. (11 ROWS) [NOTE: TABLES TO BE USED - online_customer, order_header, order_items,]

select oc.customer_id, concat(oc.customer_fname," ",customer_lname) as customer_fullname, oh.order_id, sum(oi.product_quantity) as total

from online_customer oc 

 join order_header oh on oc.customer_id= oh.customer_id
 
 join order_items oi on oh.order_id=oi.order_id
 
 where oh.order_status="Shipped"
 
group by oh.order_id having sum(oi.product_quantity)>10;

-- 9. Write a query to display the order_id, customer id and customer full name of customers along with (product_quantity) as total quantity of products shipped for order ids > 10060. (6 ROWS) [NOTE: TABLES TO BE USED - online_customer, order_header, order_items]

select oh.order_id, oc.customer_id, concat(oc.customer_fname," ",customer_lname) as customer_fullname, sum(oi.product_quantity) as total_quantity

from online_customer oc 

join order_header oh on oc.customer_id = oh.customer_id

join order_items oi on oh.order_id = oi.order_id

where oh.order_id >10060 and oh.order_status="Shipped"

group by oh.order_id;

-- 10. Write a query to display product class description ,total quantity (sum(product_quantity),Total value (product_quantity * product price) and show which class of products have been shipped highest(Quantity) to countries outside India other than USA? Also show the total value of those items. (1 ROWS)[NOTE:PRODUCT TABLE,ADDRESS TABLE,ONLINE_CUSTOMER TABLE,ORDER_HEADER TABLE,ORDER_ITEMS TABLE,PRODUCT_CLASS TABLE]

select max(addr.country), pc.product_class_desc, sum(oi.product_quantity) as total_quantity, sum(oi.product_quantity*prod.product_price) as total_value

from address addr

join online_customer oc on addr.address_id=oc.address_id

join order_header oh on oc.customer_id=oh.customer_id

join order_items oi on oh.order_id=oi.order_id

join product prod on oi.product_id=prod.product_id

join product_class pc on prod.product_class_code=pc.product_class_code

where addr.country not in ("India","USA")

group by pc.product_class_desc

order by total_quantity desc limit 1;