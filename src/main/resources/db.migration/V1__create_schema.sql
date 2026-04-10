CREATE TABLE theatre (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    city VARCHAR(50) NOT NULL,
    address TEXT,
    integration_type VARCHAR(20) DEFAULT 'MANUAL'
);

CREATE TABLE movie (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    language VARCHAR(30),
    genre VARCHAR(50),
    release_date DATE
);

CREATE TABLE show (
    id BIGSERIAL PRIMARY KEY,
    theatre_id BIGINT NOT NULL REFERENCES theatre(id),
    movie_id BIGINT NOT NULL REFERENCES movie(id),
    show_time TIMESTAMP NOT NULL,
    price_per_seat DECIMAL(10,2) NOT NULL,
    total_seats INT NOT NULL,
    UNIQUE(theatre_id, show_time)
);

CREATE TABLE seat_inventory (
    show_id BIGINT NOT NULL REFERENCES show(id),
    seat_number VARCHAR(5) NOT NULL,
    status VARCHAR(20) DEFAULT 'AVAILABLE',
    booking_id BIGINT,
    PRIMARY KEY (show_id, seat_number)
);

CREATE TABLE booking (
    id BIGSERIAL PRIMARY KEY,
    show_id BIGINT NOT NULL REFERENCES show(id),
    customer_email VARCHAR(100),
    total_amount DECIMAL(10,2),
    discount_amount DECIMAL(10,2),
    status VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    payment_intent_id VARCHAR(100)
);

CREATE TABLE booking_seat (
    id BIGSERIAL PRIMARY KEY,
    booking_id BIGINT NOT NULL REFERENCES booking(id),
    show_id BIGINT NOT NULL,
    seat_number VARCHAR(5) NOT NULL
);

-- Indexes for performance
CREATE INDEX idx_show_movie_city_date ON show(movie_id, theatre_id, show_time);
CREATE INDEX idx_seat_inventory_status ON seat_inventory(show_id, status);