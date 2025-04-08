CREATE TABLE Clientes (
    ID TEXT PRIMARY KEY CHECK(length(ID) = 36),  -- UUIDv4
    NumeroDocumento TEXT NOT NULL UNIQUE,
    TipoDocumento TEXT NOT NULL CHECK(TipoDocumento IN ('CC', 'CE', 'PASAPORTE')),
    NombreCompleto TEXT NOT NULL,
    FechaNacimiento DATE,
    Direccion TEXT,
    Telefono TEXT UNIQUE,
    Email TEXT UNIQUE,
    FechaRegistro DATETIME DEFAULT CURRENT_TIMESTAMP,
    EstadoCuenta TEXT NOT NULL DEFAULT 'Activa' CHECK(EstadoCuenta IN ('Activa', 'Bloqueada', 'Eliminada'))
);

CREATE INDEX idx_clientes_numero_documento ON Clientes (NumeroDocumento);
CREATE INDEX idx_clientes_email ON Clientes (Email);
CREATE INDEX idx_clientes_telefono ON Clientes (Telefono);


-- DATOS DE EJEMPLO
INSERT INTO Clientes (
    ID,
    NumeroDocumento,
    TipoDocumento,
    NombreCompleto,
    FechaNacimiento,
    Direccion,
    Telefono,
    Email,
    FechaRegistro,
    EstadoCuenta
) VALUES
(
    '550e8400-e29b-41d4-a716-446655440000',
    '123456789',
    'CC',
    'Juan Pérez',
    '1985-06-15',
    'Calle 123 #45-67, Bogotá',
    '3101234567',
    'juan.perez@email.com',
    CURRENT_TIMESTAMP,
    'Activa'
),
(
    'c4a760a2-47d7-4f0c-9aef-0e9ad3a5e47f',
    '987654321',
    'CE',
    'María Gómez',
    '1990-09-20',
    'Carrera 78 #12-34, Medellín',
    '3209876543',
    'maria.gomez@email.com',
    CURRENT_TIMESTAMP,
    'Bloqueada'
),
(
    'f47ac10b-58cc-4372-a567-0e02b2c3d479',
    '1029384756',
    'PASAPORTE',
    'Carlos Ramírez',
    '1978-12-05',
    'Avenida Siempre Viva 742, Cali',
    '3112345678',
    'carlos.ramirez@email.com',
    CURRENT_TIMESTAMP,
    'Eliminada'
),
(
    '6fa459ea-ee8a-3ca4-894e-db77e160355e',
    '5647382910',
    'CC',
    'Laura Rodríguez',
    '1995-03-10',
    'Diagonal 45 #98-76, Barranquilla',
    '3057654321',
    'laura.rodriguez@email.com',
    CURRENT_TIMESTAMP,
    'Activa'
),
(
    '123e4567-e89b-12d3-a456-426614174000',
    '1122334455',
    'CE',
    'Andrés Martínez',
    '2000-07-25',
    'Calle Falsa 123, Cartagena',
    '3128765432',
    'andres.martinez@email.com',
    CURRENT_TIMESTAMP,
    'Activa'
);

