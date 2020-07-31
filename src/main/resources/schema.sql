CREATE table t_coffee (

  id bigint not null auto_increment,
  name VARCHAR(255),
  price bigint not null,
  create_time TIMESTAMP,
  update_time TIMESTAMP,
  PRIMARY KEY (id)
);
CREATE TABLE t_order (
  id BIGINT NOT NULL AUTO_INCREMENT,
  create_time TIMESTAMP,
  update_time TIMESTAMP,
  customer VARCHAR(255),
  state INTEGER NOT NULL,
  PRIMARY KEY (id)
);
CREATE TABLE t_order_coffee (
  coffee_order_id BIGINT NOT NULL,
  items_id BIGINT NOT NULL
);