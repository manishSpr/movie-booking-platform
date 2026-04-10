INSERT INTO theatre (name, city) VALUES ('PVR Koramangala', 'Bangalore');
INSERT INTO movie (name, language, genre) VALUES ('Oppenheimer', 'English', 'Biography');
INSERT INTO show (theatre_id, movie_id, show_time, price_per_seat, total_seats)
VALUES (1, 1, '2026-04-10 14:30:00', 250.00, 100);

-- Generate seats A1..A10 for this show (run a loop or manually)
INSERT INTO seat_inventory (show_id, seat_number, status) VALUES
(1, 'A1', 'AVAILABLE'), (1, 'A2', 'AVAILABLE'), (1, 'A3', 'AVAILABLE'),
(1, 'A4', 'AVAILABLE'), (1, 'A5', 'AVAILABLE'), (1, 'A6', 'AVAILABLE'),
(1, 'A7', 'AVAILABLE'), (1, 'A8', 'AVAILABLE'), (1, 'A9', 'AVAILABLE'), (1, 'A10', 'AVAILABLE');