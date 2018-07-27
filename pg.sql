-- CREATE EXTENSION "uuid-ossp";
-- SELECT uuid_generate_v4();

DROP SCHEMA IF EXISTS cendraom CASCADE;

CREATE SCHEMA cendraom AUTHORIZATION postgres;	

 
-- ==========================================================================================================================
-- ==========================================================================================================================
-- ==========================================================================================================================
-- =======================																				=====================
-- =======================				FUNCIONES UTILES												=====================	
-- =======================																				=====================
-- ==========================================================================================================================
-- ==========================================================================================================================
-- ==========================================================================================================================



DROP FUNCTION IF EXISTS cendraom.white_is_null (att_val VARCHAR) CASCADE;

CREATE OR REPLACE FUNCTION cendraom.white_is_null(att_val VARCHAR) RETURNS VARCHAR AS $$
BEGIN
	IF CHAR_LENGTH(TRIM(att_val)) = 0 THEN
	
		RETURN null::VARCHAR;
	END IF;

	RETURN att_val::VARCHAR;
		
END;
$$  LANGUAGE plpgsql;


-- ==========================================================================================================================
-- ==========================================================================================================================
-- ==========================================================================================================================
-- =======================																				=====================
-- =======================				TABLAS y TRIGGERS												=====================	
-- =======================																				=====================
-- ==========================================================================================================================
-- ==========================================================================================================================
-- ==========================================================================================================================


-- Table: cendraom.Clazz

DROP TABLE IF EXISTS cendraom.Clazz CASCADE;

CREATE TABLE cendraom.Clazz
(
    -- id VARCHAR NOT NULL PRIMARY KEY DEFAULT uuid_generate_v4(),  
    id VARCHAR PRIMARY KEY DEFAULT uuid_generate_v4(),  
    name VARCHAR  NOT NULL UNIQUE
    
);

-- SELECT * FROM cendraom.Clazz;

-- ---------------------------------------------------------------------------------------------------------------------------

DROP FUNCTION IF EXISTS cendraom.ftgFormatClazz() CASCADE;

CREATE OR REPLACE FUNCTION cendraom.ftgFormatClazz() RETURNS TRIGGER AS $formatClazz$
DECLARE
BEGIN
   
	-- NEW.comentario := massoftware.white_is_null(REPLACE(TRIM(NEW.comentario), '"', ''));	
    NEW.name := cendraom.white_is_null(TRIM(NEW.name));

	RETURN NEW;
END;
$formatClazz$ LANGUAGE plpgsql;

-- ---------------------------------------------------------------------------------------------------------------------------

DROP TRIGGER IF EXISTS tgFormatClazz ON cendraom.Clazz CASCADE;

CREATE TRIGGER tgFormatClazz BEFORE INSERT OR UPDATE 
    ON cendraom.Clazz FOR EACH ROW 
    EXECUTE PROCEDURE cendraom.ftgFormatClazz();
    

-- ==========================================================================================================================