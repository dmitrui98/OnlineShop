DROP TABLE IF EXISTS image;
CREATE TABLE image
(
  image_id BIGINT AUTO_INCREMENT,
  image_directory VARCHAR(255) NOT NULL
);

DROP TABLE IF EXISTS user;
CREATE TABLE user
(
  user_id BIGINT AUTO_INCREMENT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  discont DOUBLE,
  email VARCHAR(100) NOT NULL,
  fname NVARCHAR(50),
  lname NVARCHAR(50),
  login VARCHAR(100) NOT NULL,
  password VARCHAR(100) NOT NULL,
  phone VARCHAR(50),
  surname NVARCHAR(50),
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);
CREATE UNIQUE INDEX UK_ew1hvam8uwaknuaellwhqchhb ON user (login);
CREATE UNIQUE INDEX UK_ob8kqyqqgmefl0aco34akdtpe ON user (email);

DROP TABLE IF EXISTS admin;
CREATE TABLE admin
(
  admin_id   INT AUTO_INCREMENT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  email      VARCHAR(255) NOT NULL,
  login      VARCHAR(255) NOT NULL,
  password   VARCHAR(255) NOT NULL,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);
CREATE UNIQUE INDEX UK_3x1e18lmu2r0xekpql9xme0pp ON admin (login);
CREATE UNIQUE INDEX UK_c0r9atamxvbhjjvy5j8da1kam ON admin (email);

DROP TABLE IF EXISTS category;
CREATE TABLE category
(
  category_id INT AUTO_INCREMENT,
  created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  name        NVARCHAR(50)                        NOT NULL,
  updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  created_by  INT                                 NOT NULL,
  CONSTRAINT FKjhmnkmwm8awm5yy2mi46p6vd9 FOREIGN KEY (created_by) REFERENCES admin (admin_id)
);
CREATE INDEX FKjhmnkmwm8awm5yy2mi46p6vd9 ON category (created_by);
CREATE UNIQUE INDEX UK_46ccwnsi9409t36lurvtyljak ON category (name);

DROP TABLE IF EXISTS material;
CREATE TABLE material
(
  material_id BIGINT AUTO_INCREMENT,
  created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  name        NVARCHAR(50)                        NOT NULL,
  updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  created_by  INT                                 NOT NULL,
  CONSTRAINT FK2xg438n2qyvmrn66vu9cl2is6 FOREIGN KEY (created_by) REFERENCES admin (admin_id)
);
CREATE INDEX FK2xg438n2qyvmrn66vu9cl2is6 ON material (created_by);
CREATE UNIQUE INDEX UK_j8lh9456buiw3bl8pg6kbuwln ON material (name);

DROP TABLE IF EXISTS product;
CREATE TABLE product
(
  product_id     BIGINT AUTO_INCREMENT,
  count_products BIGINT                              NOT NULL,
  created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  description    NVARCHAR(255),
  name           NVARCHAR(100)                       NOT NULL,
  price          DOUBLE                              NOT NULL,
  updated_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  created_by     INT                                 NOT NULL,
  category_id    INT                                 NOT NULL,
  image_id       BIGINT,
  CONSTRAINT FKc5eq1tgd5q9flr1pm2svo8ck8 FOREIGN KEY (created_by) REFERENCES admin (admin_id),
  CONSTRAINT FK1mtsbur82frn64de7balymq9s FOREIGN KEY (category_id) REFERENCES category (category_id),
  CONSTRAINT FKsouy49035ik9r5ojgslbv3i3u FOREIGN KEY (image_id) REFERENCES image (image_id)
);
CREATE INDEX FK1mtsbur82frn64de7balymq9s ON product (category_id);
CREATE INDEX FKc5eq1tgd5q9flr1pm2svo8ck8 ON product (created_by);
CREATE INDEX FKsouy49035ik9r5ojgslbv3i3u ON product (image_id);

DROP TABLE IF EXISTS product_material;
CREATE TABLE product_material
(
  percent_material DOUBLE DEFAULT '0.00',
  material_id      BIGINT NOT NULL,
  product_id       BIGINT NOT NULL,
  PRIMARY KEY (material_id, product_id),
  CONSTRAINT FK1jea57j19wyli2rpqcp2ho163 FOREIGN KEY (material_id) REFERENCES material (material_id),
  CONSTRAINT FKk16tidor8p5yj7n3e9h8j784d FOREIGN KEY (product_id) REFERENCES product (product_id)
);
CREATE INDEX FKk16tidor8p5yj7n3e9h8j784d ON product_material (product_id);

DROP TABLE IF EXISTS address;
CREATE TABLE address
(
  address_id BIGINT AUTO_INCREMENT,
  address NVARCHAR(100) NOT NULL,
  city NVARCHAR(100) NOT NULL,
  country NVARCHAR(100) NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  user_id BIGINT,
  CONSTRAINT FKda8tuywtf0gb6sedwk7la1pgi FOREIGN KEY (user_id) REFERENCES user (user_id)
);
CREATE INDEX FKda8tuywtf0gb6sedwk7la1pgi ON address (user_id);

DROP TABLE IF EXISTS order_;
CREATE TABLE order_
(
  order_id BIGINT AUTO_INCREMENT,
  amount DOUBLE NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  address_id BIGINT,
  CONSTRAINT FKnbpg6djq6yhpobx18n2l4dfa0 FOREIGN KEY (address_id) REFERENCES address (address_id)
);
CREATE INDEX FKnbpg6djq6yhpobx18n2l4dfa0 ON order_ (address_id);

DROP TABLE IF EXISTS order_product;
CREATE TABLE order_product
(
  price DOUBLE NOT NULL,
  quantity INT NOT NULL,
  order_id BIGINT NOT NULL,
  product_id BIGINT NOT NULL,
  PRIMARY KEY (order_id, product_id),
  CONSTRAINT FKeho7awnrq9mu55b9tj51yf5t3 FOREIGN KEY (order_id) REFERENCES order_ (order_id),
  CONSTRAINT FKhnfgqyjx3i80qoymrssls3kno FOREIGN KEY (product_id) REFERENCES product (product_id)
);
CREATE INDEX FKhnfgqyjx3i80qoymrssls3kno ON order_product (product_id);
