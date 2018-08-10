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
    virtual BOOLEAN NOT NULL DEFAULT false,
   -- typeComponent VARCHAR  NOT NULL,
    
    name VARCHAR  NOT NULL UNIQUE,
    visibility VARCHAR  NOT NULL,
    finalClass BOOLEAN NOT NULL DEFAULT false,
    abstractClass BOOLEAN NOT NULL DEFAULT false
    
);

-- SELECT * FROM cendraom.Clazz;
-- DELETE FROM cendraom.Clazz;

-- CREATE UNIQUE INDEX u_Clazz_name ON cendraom.Clazz (LOWER(TRIM(name)));
DROP INDEX IF EXISTS u_Clazz_name CASCADE;
CREATE UNIQUE INDEX u_Clazz_name ON cendraom.Clazz (TRIM(name));

-- ---------------------------------------------------------------------------------------------------------------------------

DROP FUNCTION IF EXISTS cendraom.ftgFormatClazz() CASCADE;

CREATE OR REPLACE FUNCTION cendraom.ftgFormatClazz() RETURNS TRIGGER AS $formatClazz$
DECLARE
BEGIN
   
   NEW.id := cendraom.white_is_null(TRIM(NEW.id));
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

-- Table: cendraom.ClazzExtends

DROP TABLE IF EXISTS cendraom.ClazzExtends CASCADE;

CREATE TABLE cendraom.ClazzExtends
(
    id VARCHAR PRIMARY KEY DEFAULT uuid_generate_v4(),  
  
    clazz VARCHAR NOT NULL REFERENCES cendraom.Clazz (id),	
    clazzExtends VARCHAR NOT NULL REFERENCES cendraom.Clazz (id)
    
);

-- SELECT * FROM cendraom.ClazzExtends;

DROP INDEX IF EXISTS u_ClazzExtends_clazz_clazzExtends CASCADE;
CREATE UNIQUE INDEX u_ClazzExtends_clazz_clazzExtends ON cendraom.ClazzExtends (TRIM(clazz), TRIM(clazzExtends));

-- ---------------------------------------------------------------------------------------------------------------------------

DROP FUNCTION IF EXISTS cendraom.ftgFormatClazzExtends() CASCADE;

CREATE OR REPLACE FUNCTION cendraom.ftgFormatClazzExtends() RETURNS TRIGGER AS $formatClazzExtends$
DECLARE
BEGIN
   
   	NEW.id := cendraom.white_is_null(TRIM(NEW.id));
   
	-- NEW.comentario := massoftware.white_is_null(REPLACE(TRIM(NEW.comentario), '"', ''));	
    -- NEW.name := cendraom.white_is_null(TRIM(NEW.name));

	RETURN NEW;
END;
$formatClazzExtends$ LANGUAGE plpgsql;

-- ---------------------------------------------------------------------------------------------------------------------------

DROP TRIGGER IF EXISTS tgFormatClazzExtends ON cendraom.ClazzExtends CASCADE;

CREATE TRIGGER tgFormatClazzExtends BEFORE INSERT OR UPDATE 
    ON cendraom.ClazzExtends FOR EACH ROW 
    EXECUTE PROCEDURE cendraom.ftgFormatClazzExtends();
    

-- ==========================================================================================================================

-- Table: cendraom.ClazzAtt

DROP TABLE IF EXISTS cendraom.ClazzAtt CASCADE;

CREATE TABLE cendraom.ClazzAtt
(
    
    id VARCHAR PRIMARY KEY DEFAULT uuid_generate_v4(),
    
    clazz VARCHAR NOT NULL REFERENCES cendraom.Clazz (id),	
    name VARCHAR  NOT NULL,
    dataType VARCHAR  NOT NULL,
    typeCardinality VARCHAR  NOT NULL,
    orderAtt INTEGER NOT NULL DEFAULT 0
    
);

-- SELECT * FROM cendraom.ClazzAtt;
-- DELETE FROM cendraom.ClazzAtt;

-- CREATE UNIQUE INDEX u_Clazz_name ON cendraom.Clazz (LOWER(TRIM(name)));
DROP INDEX IF EXISTS u_ClazzAtt_calzz_name CASCADE;
CREATE UNIQUE INDEX u_ClazzAtt_calzz_name ON cendraom.ClazzAtt (TRIM(ClazzAtt.clazz), TRIM(ClazzAtt.name));


-- INSERT INTO cendraom.ClazzAtt (id, clazz, name, dataType, typeCardinality, orderAtt) 
-- VALUES ('b465fcf8-71bc-4e9b-8597-98c42bfdbabc', '46171755-b975-4431-be81-dd8de9919022', 'edad', 'org.cendra.om.model.datatype.String', '1-1', '3')

-- ---------------------------------------------------------------------------------------------------------------------------

DROP FUNCTION IF EXISTS cendraom.ftgFormatClazzAtt() CASCADE;

CREATE OR REPLACE FUNCTION cendraom.ftgFormatClazzAtt() RETURNS TRIGGER AS $formatClazzAtt$
DECLARE
BEGIN
   
	NEW.id := cendraom.white_is_null(TRIM(NEW.id));
    NEW.name := cendraom.white_is_null(TRIM(NEW.name));
    NEW.dataType := cendraom.white_is_null(TRIM(NEW.dataType));

	RETURN NEW;
END;
$formatClazzAtt$ LANGUAGE plpgsql;

-- ---------------------------------------------------------------------------------------------------------------------------

DROP TRIGGER IF EXISTS tgFormatClazzAtt ON cendraom.ClazzAtt CASCADE;

CREATE TRIGGER tgFormatClazzAtt BEFORE INSERT OR UPDATE 
    ON cendraom.ClazzAtt FOR EACH ROW 
    EXECUTE PROCEDURE cendraom.ftgFormatClazzAtt();
    
-- ==========================================================================================================================



-- ==========================================================================================================================

/*

select 	z.name,
		e.name
from 	cendraom.clazzExtends r
join	cendraom.clazz z
		on r.clazz = z.id
join	cendraom.clazz e
		on r.clazzExtends = e.id     
        

SELECT Clazz.name,
		ClazzAtt.name,
        ClazzAtt.dataType,
        ClazzAtt.orderAtt
        
from cendraom.Clazz
join cendraom.ClazzAtt 
	on ClazzAtt.clazz = Clazz.id
    
DELETE FROM cendraom.ClazzAtt;
DELETE FROM cendraom.ClazzExtends;
DELETE FROM cendraom.Clazz;    


SELECT * FROM cendraom.clazz ORDER BY name OFFSET 0 LIMIT 100


SELECT cendraom.Clazz.id, cendraom.Clazz.name FROM cendraom.ClazzExtends JOIN cendraom.Clazz ON cendraom.ClazzExtends.clazzExtends = cendraom.Clazz.id WHERE cendraom.ClazzExtends.clazz = 'eb8ef90e-f5db-41e4-9d63-a454b0902336'

SELECT ClazzAtt.*, cendraom.Clazz.name AS clazzDataTypeName FROM cendraom.ClazzAtt LEFT JOIN cendraom.Clazz ON cendraom.ClazzAtt.dataType = cendraom.Clazz.id WHERE cendraom.ClazzAtt.clazz = 'eb8ef90e-f5db-41e4-9d63-a454b0902336'

SELECT ClazzAtt.*, cendraom.Clazz.name AS clazzDataTypeName FROM cendraom.ClazzAtt LEFT JOIN cendraom.Clazz ON cendraom.ClazzAtt.dataType = cendraom.Clazz.id WHERE cendraom.ClazzAtt.clazz = '21c149ca-c3b6-424c-a2c1-ee94e8b8eb50';

        
*/


DROP SCHEMA IF EXISTS org_cendra_person CASCADE;
DROP SCHEMA IF EXISTS org_cendra_person_human CASCADE;
