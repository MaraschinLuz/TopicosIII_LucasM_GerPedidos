CREATE TABLE pedido (
    id SERIAL PRIMARY KEY,
    data_pedido TIMESTAMP NOT NULL,
    cliente_id INTEGER NOT NULL,
    produtos VARCHAR(1000) NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);
