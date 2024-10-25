-- Crear base de datos
CREATE DATABASE IF NOT EXISTS bancolocal;
USE bancolocal;

-- Crear tabla Persona
CREATE TABLE IF NOT EXISTS Persona (
    id_persona INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    genero VARCHAR(10) NOT NULL,
    edad VARCHAR(2) NOT NULL,
    identificacion VARCHAR(50) NOT NULL UNIQUE,
    direccion VARCHAR(255) NOT NULL,
    telefono VARCHAR(15) NOT NULL
);

-- Crear tabla Cliente
CREATE TABLE IF NOT EXISTS Cliente (
    clienteid INT AUTO_INCREMENT PRIMARY KEY,
    contrasena VARCHAR(100) NOT NULL,
    estado BOOLEAN NOT NULL,
    id_persona INT,
    UNIQUE (clienteid),
    FOREIGN KEY (id_persona) REFERENCES Persona(id_persona) ON DELETE CASCADE
);

-- Crear tabla Cuenta
CREATE TABLE IF NOT EXISTS Cuenta (
    numero_cuenta CHAR(6) PRIMARY KEY,
    tipo_cuenta VARCHAR(50) NOT NULL,
    saldo_inicial DECIMAL(10, 2) NOT NULL,
    estado BOOLEAN NOT NULL,
    clienteid INT,
    UNIQUE (numero_cuenta),
    FOREIGN KEY (clienteid) REFERENCES Cliente(clienteid) ON DELETE CASCADE
);

-- Crear tabla Movimientos
CREATE TABLE IF NOT EXISTS Movimientos (
    id_movimiento INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATETIME NOT NULL,
    tipo_movimiento VARCHAR(50) NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    saldo DECIMAL(10, 2) NOT NULL,
    numero_cuenta CHAR(6),
    UNIQUE (id_movimiento),
    FOREIGN KEY (numero_cuenta) REFERENCES Cuenta(numero_cuenta) ON DELETE CASCADE
);

-- Crear trigger para generar número de cuenta único
DELIMITER //

CREATE TRIGGER before_insert_cuenta
BEFORE INSERT ON Cuenta
FOR EACH ROW
BEGIN
    DECLARE random_num INT;
    SET random_num = FLOOR(100000 + RAND() * 900000); -- Genera un número aleatorio de 6 dígitos
    WHILE EXISTS (SELECT * FROM Cuenta WHERE numero_cuenta = random_num) DO
        SET random_num = FLOOR(100000 + RAND() * 900000);
    END WHILE;
    SET NEW.numero_cuenta = random_num;
END; //

DELIMITER ;
