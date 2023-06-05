CREATE TABLE miCiudad.BARRIO (id BIGINT NOT NULL, FIRSTNAME VARCHAR(40), LASTNAME VARCHAR(40) NOT NULL, version BIGINT NOT NULL, PRIMARY KEY (id))
CREATE TABLE miCiudad.OBRA (id BIGINT NOT NULL, empresa VARCHAR, especificacion VARCHAR(4000) NOT NULL, fechaFinal LONGVARBINARY, fechaInicio LONGVARBINARY, presupuesto DOUBLE, titulo VARCHAR(40) NOT NULL, barrio_id BIGINT, PRIMARY KEY (id))
ALTER TABLE miCiudad.BARRIO ADD CONSTRAINT Barrio__lastName__UNQ UNIQUE (LASTNAME)
ALTER TABLE miCiudad.OBRA ADD CONSTRAINT Barrio_name__UNQ UNIQUE (owner_id, name)
ALTER TABLE miCiudad.OBRA ADD CONSTRAINT FK_OBRA_barrio_id FOREIGN KEY (barrio_id) REFERENCES miCiudad.BARRIO (id)
CREATE TABLE SEQUENCE (SEQ_NAME VARCHAR(50) NOT NULL, SEQ_COUNT NUMERIC(38), PRIMARY KEY (SEQ_NAME))
INSERT INTO SEQUENCE(SEQ_NAME, SEQ_COUNT) values ('SEQ_GEN', 0)