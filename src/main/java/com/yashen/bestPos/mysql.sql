DROP DATABASE IF EXISTS bestPosTemp;
CREATE DATABASE IF NOT EXISTS bestPos;
USE bestPos;

CREATE TABLE IF NOT EXISTS customer(
                    cus_id VARCHAR (55),
                    cus_name VARCHAR (255),
                    cus_address VARCHAR (255),
                    cus_contact_no VARCHAR (55),
                    cus_email VARCHAR (155),
                    CONSTRAINT PRIMARY KEY (cus_id)
);

CREATE TABLE IF NOT EXISTS product(
                    p_id VARCHAR (55),
                    p_desc VARCHAR (155),
                    p_price double ,
                    p_qty int ,
                    CONSTRAINT PRIMARY KEY (p_id)
);

CREATE TABLE IF NOT EXISTS `order`(
                    ord_id VARCHAR (55),
                    customer VARCHAR (55),
                    ord_date DATETIME DEFAULT NOW(),
                    CONSTRAINT PRIMARY KEY(ord_id),
                    CONSTRAINT FOREIGN KEY (customer) REFERENCES customer(cus_id)
                    ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS order_details(
                    order_id VARCHAR (55),
                    product VARCHAR (55),
                    qty INT ,
                    CONSTRAINT PRIMARY KEY (order_id,order_id),
                    CONSTRAINT FOREIGN KEY (product) REFERENCES product(p_id)
                            ON DELETE CASCADE ON UPDATE CASCADE ,
                    CONSTRAINT FOREIGN KEY (order_id) REFERENCES `order`(ord_id)
                            ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS dilevery_agency(
                    com_id VARCHAR (55),
                    com_name VARCHAR (255),
                    com_contact VARCHAR (55),
                    com_email VARCHAR (55),
                    CONSTRAINT PRIMARY KEY (com_id)
);

CREATE TABLE IF NOT EXISTS dilevery_status(
                    dilevery_tracking_no VARCHAR (55),
                    dilevery_agency VARCHAR (55),
                    order_id VARCHAR (55),
                    delevery_charge DOUBLE,
                    CONSTRAINT PRIMARY KEY (dilevery_tracking_no)
);
DELETE FROM order_details WHERE order_id_id=? AND product = ?
select * from order_details;
