-- Insert data into booking table
INSERT INTO booking (id, booking_date, customer_id, product_id)
VALUES
    (1, '2024-06-01', 1, 1),
    (2, '2024-06-02', 2, 2),
    (3, '2024-06-03', 3, 3);

-- Insert data into customer table
INSERT INTO customer (id, name, email)
VALUES
    (1, 'John Doe', 'john.doe@example.com'),
    (2, 'Jane Smith', 'jane.smith@example.com'),
    (3, 'Michael Johnson', 'michael.johnson@example.com');

-- Insert data into products table
INSERT INTO products (id, name, price)
VALUES
    (1, 'Product A', 50.0),
    (2, 'Product B', 75.0),
    (3, 'Product C', 100.0);

-- Insert data into valuetable table
INSERT INTO valuetable (id, value_name, value_description)
VALUES
    (1, 'Value 1', 'Description of Value 1'),
    (2, 'Value 2', 'Description of Value 2'),
    (3, 'Value 3', 'Description of Value 3');
