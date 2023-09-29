CREATE TABLE miCiudad.BARRIO (id BIGINT NOT NULL, nombre VARCHAR(40) NOT NULL, PRIMARY KEY (id))
CREATE TABLE miCiudad.CONTACTOPRIVADO (id BIGINT NOT NULL, cargo VARCHAR(40) NOT NULL, email VARCHAR(100) NOT NULL, nombre VARCHAR(40) NOT NULL, telefono VARCHAR(30) NOT NULL, empresa_id BIGINT, PRIMARY KEY (id))
CREATE TABLE miCiudad.CONTACTOPUBLICO (id BIGINT NOT NULL, link VARCHAR(4000) NOT NULL, empresa_id BIGINT, PRIMARY KEY (id))
CREATE TABLE miCiudad.EMPRESA (id BIGINT NOT NULL, nombre VARCHAR(40) NOT NULL, PRIMARY KEY (id))
CREATE TABLE miCiudad.OBRA (id BIGINT NOT NULL, especificacion VARCHAR(4000) NOT NULL, fechaFinal BYTEA, fechaInicio BYTEA, latitud FLOAT NOT NULL, longitud FLOAT NOT NULL, presupuesto FLOAT, tipo VARCHAR(255) NOT NULL, titulo VARCHAR(40) NOT NULL, barrio_id BIGINT, empresa_id BIGINT, PRIMARY KEY (id))
ALTER TABLE miCiudad.BARRIO ADD CONSTRAINT Barrio__nombre__UNQ UNIQUE (nombre)
ALTER TABLE miCiudad.CONTACTOPRIVADO ADD CONSTRAINT ContactoPrivado__nombre__UNQ UNIQUE (nombre)
ALTER TABLE miCiudad.CONTACTOPUBLICO ADD CONSTRAINT ContactoPrivado__nombre__UNQ UNIQUE (nombre)
ALTER TABLE miCiudad.EMPRESA ADD CONSTRAINT Empresa__nombre__UNQ UNIQUE (nombre)
ALTER TABLE miCiudad.OBRA ADD CONSTRAINT Obra_name__UNQ UNIQUE (owner_id, name)
ALTER TABLE miCiudad.CONTACTOPRIVADO ADD CONSTRAINT FK_CONTACTOPRIVADO_empresa_id FOREIGN KEY (empresa_id) REFERENCES miCiudad.EMPRESA (id)
ALTER TABLE miCiudad.CONTACTOPUBLICO ADD CONSTRAINT FK_CONTACTOPUBLICO_empresa_id FOREIGN KEY (empresa_id) REFERENCES miCiudad.EMPRESA (id)
ALTER TABLE miCiudad.OBRA ADD CONSTRAINT FK_OBRA_empresa_id FOREIGN KEY (empresa_id) REFERENCES miCiudad.EMPRESA (id)
ALTER TABLE miCiudad.OBRA ADD CONSTRAINT FK_OBRA_barrio_id FOREIGN KEY (barrio_id) REFERENCES miCiudad.BARRIO (id)
CREATE TABLE SEQUENCE (SEQ_NAME VARCHAR(50) NOT NULL, SEQ_COUNT DECIMAL(38), PRIMARY KEY (SEQ_NAME))
INSERT INTO SEQUENCE(SEQ_NAME, SEQ_COUNT) values ('SEQ_GEN', 0)
