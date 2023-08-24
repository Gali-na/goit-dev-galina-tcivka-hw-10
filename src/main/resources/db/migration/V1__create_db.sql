CREATE TABLE IF NOT EXISTS client (
id  IDENTITY PRIMARY KEY,
client_name VARCHAR(200) NOT NULL CHECK (LENGTH(client_name) >= 2 AND LENGTH(client_name) <= 200)
);

CREATE TABLE IF NOT EXISTS planet (
id VARCHAR(20) PRIMARY KEY,
name VARCHAR(500) NOT NULL,
CONSTRAINT planet_id CHECK (id REGEXP '[A-Z0-9]+')
);
CREATE TABLE IF NOT EXISTS ticket (
id IDENTITY PRIMARY KEY,
created_at TIMESTAMP NOT NULL,
client_id BIGINT NOT NULL,
from_planet_id VARCHAR(20) NOT NULL,
to_planet_id VARCHAR(20) NOT NULL,
foreign key (client_id) references client (id),
foreign key (from_planet_id) references planet (id),
foreign key (to_planet_id) references planet (id)
);





