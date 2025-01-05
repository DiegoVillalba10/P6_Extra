-- CREACIÓN DE LAS TABLAS --

CREATE TABLE Departamento (
                              id INT AUTO_INCREMENT PRIMARY KEY,
                              nombre VARCHAR(50) NOT NULL
);

CREATE TABLE Empleado (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          nombre VARCHAR(50) NOT NULL,
                          apellido1 VARCHAR(50) NOT NULL,
                          apellido2 VARCHAR(50),
                          dni VARCHAR(10) UNIQUE NOT NULL,
                          salario DECIMAL(10, 2) NOT NULL,
                          fecha_incorporacion DATE NOT NULL,
                          departamento_id INT,
                          foto BLOB,
                          FOREIGN KEY (departamento_id) REFERENCES Departamento(id)
);


-- DATOS INSERTADOS EN LA TABLA 'DEPARTAMENTO' --

INSERT INTO Departamento (nombre) VALUES ('Recursos Humanos');
INSERT INTO Departamento (nombre) VALUES ('Tecnología');
INSERT INTO Departamento (nombre) VALUES ('Marketing');
INSERT INTO Departamento (nombre) VALUES ('Ventas');
INSERT INTO Departamento (nombre) VALUES ('Administración');

-- DATOS INSERTADOS EN LA TABLA 'EMPLEADO' --

INSERT INTO Empleado (nombre, apellido1, apellido2, dni, salario, fecha_incorporacion, departamento_id)
VALUES ('Carlos', 'Gómez', 'Pérez', '12345678A', 2500.00, '2022-03-15', 1);

INSERT INTO Empleado (nombre, apellido1, apellido2, dni, salario, fecha_incorporacion, departamento_id)
VALUES ('María', 'López', 'Martínez', '23456789B', 3000.00, '2021-06-01', 2);

INSERT INTO Empleado (nombre, apellido1, apellido2, dni, salario, fecha_incorporacion, departamento_id)
VALUES ('Juan', 'Fernández', 'Gutiérrez', '34567890C', 2800.00, '2020-01-10', 2);

INSERT INTO Empleado (nombre, apellido1, apellido2, dni, salario, fecha_incorporacion, departamento_id)
VALUES ('Ana', 'Sánchez', 'Rodríguez', '45678901D', 2300.00, '2023-05-20', 3);

INSERT INTO Empleado (nombre, apellido1, apellido2, dni, salario, fecha_incorporacion, departamento_id)
VALUES ('Luis', 'Torres', 'González', '56789012E', 2100.00, '2021-11-15', 4);
