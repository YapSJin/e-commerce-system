CREATE TABLE Users (
	id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	avatar VARCHAR(255),
	username VARCHAR(50) UNIQUE NOT NULL,
	name VARCHAR(255),
	email VARCHAR(255) UNIQUE NOT NULL,
	contact VARCHAR(20),
	password CHAR(64) NOT NULL,
	role VARCHAR(50) NOT NULL CHECK (role IN ('customer', 'staff', 'manager')),
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	is_archived BOOLEAN DEFAULT FALSE
);

CREATE TABLE CardInfo (
	id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	user_id INT,
	card_number VARCHAR(25),
	card_name VARCHAR(255),
	exp_month SMALLINT NOT NULL,
	exp_year SMALLINT NOT NULL,
	is_archived BOOLEAN DEFAULT FALSE,
	FOREIGN KEY (user_id) REFERENCES Users(id)
);

CREATE TABLE Addresses (
	id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	user_id INT,
	receiver_name VARCHAR(255) NOT NULL,
	contact_number VARCHAR(20) NOT NULL,
	address1 VARCHAR(120) NOT NULL,
	address2 VARCHAR(120),
	city VARCHAR(100) NOT NULL,
	state VARCHAR(50) NOT NULL,
	postal_code VARCHAR(20) NOT NULL,
	is_archived BOOLEAN DEFAULT FALSE,
	FOREIGN KEY (user_id) REFERENCES Users(id)
);

CREATE TABLE Categories (
	id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	name VARCHAR(50) UNIQUE NOT NULL,
	description VARCHAR(255)
);

CREATE TABLE Products (
	id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	name VARCHAR(255) NOT NULL,
	description VARCHAR(255),
	price DECIMAL(10,2) NOT NULL CHECK (price >= 0),
	stock INT NOT NULL CHECK (stock >= 0),
	category_id INT,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	is_archived BOOLEAN DEFAULT FALSE,
	FOREIGN KEY (category_id) REFERENCES Categories(id)
);

CREATE TABLE Cart (
	id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	user_id INT,
	product_id INT,
	quantity INT NOT NULL CHECK (quantity > 0),
	added_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	FOREIGN KEY (user_id) REFERENCES Users(id),
	FOREIGN KEY (product_id) REFERENCES Products(id),
	UNIQUE (user_id, product_id)
);

CREATE TABLE Promotions (
	id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	promo_code VARCHAR(50) UNIQUE NOT NULL,
	discount DECIMAL(5,2) NOT NULL CHECK (discount > 0),
	valid_from DATE NOT NULL,
	valid_to DATE NOT NULL,
	is_active BOOLEAN DEFAULT TRUE
);

CREATE TABLE Orders (
	id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	user_id INT,
	address_id INT,
	payment_method VARCHAR(50) NOT NULL CHECK (payment_method IN ('cash', 'card','e-wallet')),
	card_id INT,
	status VARCHAR(50) NOT NULL CHECK (status IN ('packaging', 'shipping', 'delivery')),
	total_price DECIMAL(10,2) NOT NULL CHECK (total_price >= 0),
	delivery_cost DECIMAL(10,2) DEFAULT 0.00,
	promo_id INT,
	discount DECIMAL(10,2) DEFAULT 0.00,
	order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	FOREIGN KEY (user_id) REFERENCES Users(id),
	FOREIGN KEY (address_id) REFERENCES Addresses(id),
	FOREIGN KEY (card_id) REFERENCES CardInfo(id),
	FOREIGN KEY (promo_id) REFERENCES Promotions(id)
);

CREATE TABLE OrderDetails (
	id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	order_id INT,
	product_id INT,
	quantity INT NOT NULL CHECK (quantity > 0),
	price DECIMAL(10,2) NOT NULL,
	FOREIGN KEY (order_id) REFERENCES Orders(id),
	FOREIGN KEY (product_id) REFERENCES Products(id)
);

CREATE TABLE Reviews (
	id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	user_id INT,
	product_id INT,
	rating INT CHECK (rating BETWEEN 1 AND 5),
	review VARCHAR(255),
	review_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	is_archived BOOLEAN DEFAULT FALSE,
	FOREIGN KEY (user_id) REFERENCES Users(id),
	FOREIGN KEY (product_id) REFERENCES Products(id)
);

CREATE TABLE Reports (
	id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	report_type VARCHAR(255) NOT NULL,
	generated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	details VARCHAR(4000),
	generated_by_id INT,
	FOREIGN KEY (generated_by_id) REFERENCES Users(id)
);
