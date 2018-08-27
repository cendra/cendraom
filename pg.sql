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
    isVirtual BOOLEAN NOT NULL DEFAULT false,
    
    isExtendable BOOLEAN NOT NULL DEFAULT false,
    visibility VARCHAR  NOT NULL,
    name VARCHAR  NOT NULL,
    extendsFrom VARCHAR REFERENCES cendraom.Clazz (id),
    comment VARCHAR,
    
    labelName VARCHAR, -- NOT NULL,    
    labelComment VARCHAR
);

-- SELECT * FROM cendraom.Clazz;
-- DELETE FROM cendraom.Clazz;

-- --------------

DROP INDEX IF EXISTS u_Clazz_name CASCADE;

CREATE UNIQUE INDEX u_Clazz_name ON cendraom.Clazz (LOWER(TRIM(name)));

-- --------------

DROP FUNCTION IF EXISTS cendraom.ftgFormatClazz() CASCADE;

CREATE OR REPLACE FUNCTION cendraom.ftgFormatClazz() RETURNS TRIGGER AS $formatClazz$
DECLARE
BEGIN
   
    NEW.id := cendraom.white_is_null(TRIM(NEW.id));
	-- NEW.comentario := massoftware.white_is_null(REPLACE(TRIM(NEW.comentario), '"', ''));	
    NEW.visibility := cendraom.white_is_null(LOWER(TRIM(NEW.visibility)));
    NEW.name := cendraom.white_is_null(TRIM(NEW.name));
    NEW.extendsFrom := cendraom.white_is_null(TRIM(NEW.extendsFrom));
    NEW.comment := cendraom.white_is_null(TRIM(NEW.comment));
    NEW.labelName := cendraom.white_is_null(TRIM(NEW.labelName));
    NEW.labelComment := cendraom.white_is_null(TRIM(NEW.labelComment));

	RETURN NEW;
END;
$formatClazz$ LANGUAGE plpgsql;

-- --------------

DROP TRIGGER IF EXISTS tgFormatClazz ON cendraom.Clazz CASCADE;

CREATE TRIGGER tgFormatClazz BEFORE INSERT OR UPDATE 
    ON cendraom.Clazz FOR EACH ROW 
    EXECUTE PROCEDURE cendraom.ftgFormatClazz();

-- ---------------------------------------------------------------------------------------------------------------------------

-- Table: cendraom.ClazzLabel

DROP TABLE IF EXISTS cendraom.ClazzLabel CASCADE;

CREATE TABLE cendraom.ClazzLabel
(    
    id VARCHAR PRIMARY KEY DEFAULT uuid_generate_v4(),
    
    labelOf VARCHAR NOT NULL REFERENCES cendraom.Clazz (id),	
    
    locale VARCHAR NOT NULL,    
    name VARCHAR NOT NULL,        
    comment VARCHAR
);

-- --------------

DROP INDEX IF EXISTS u_ClazzLabel_labelOf_locale_name CASCADE;

CREATE UNIQUE INDEX u_ClazzLabel_labelOf_locale_name ON cendraom.ClazzLabel (TRIM(labelOf), LOWER(TRIM(locale)), LOWER(TRIM(name)));

-- --------------

DROP FUNCTION IF EXISTS cendraom.ftgFormatClazzLabel() CASCADE;

CREATE OR REPLACE FUNCTION cendraom.ftgFormatClazzLabel() RETURNS TRIGGER AS $formatClazzLabel$
DECLARE
BEGIN
   
   	NEW.id := cendraom.white_is_null(TRIM(NEW.id));
    NEW.labelOf := cendraom.white_is_null(TRIM(NEW.labelOf));
    NEW.locale := cendraom.white_is_null(TRIM(NEW.locale));
    NEW.name := cendraom.white_is_null(TRIM(NEW.name));
    NEW.comment := cendraom.white_is_null(TRIM(NEW.comment));    

	RETURN NEW;
END;
$formatClazzLabel$ LANGUAGE plpgsql;

-- --------------

DROP TRIGGER IF EXISTS tgFormatClazzLabel ON cendraom.ClazzLabel CASCADE;

CREATE TRIGGER tgFormatClazzLabel BEFORE INSERT OR UPDATE 
    ON cendraom.ClazzLabel FOR EACH ROW 
    EXECUTE PROCEDURE cendraom.ftgFormatClazzLabel();


-- ---------------------------------------------------------------------------------------------------------------------------

-- Table: cendraom.ClazzAttribute

DROP TABLE IF EXISTS cendraom.ClazzAttribute CASCADE;

CREATE TABLE cendraom.ClazzAttribute
(
    
    id VARCHAR PRIMARY KEY DEFAULT uuid_generate_v4(),
    
    memberOfAttribute VARCHAR REFERENCES cendraom.ClazzAttribute (id),	
    memberOfClazz VARCHAR REFERENCES cendraom.Clazz (id),
    
    
    code VARCHAR,--  NOT NULL,
    orderAtt INTEGER NOT NULL DEFAULT 0,
    name VARCHAR NOT NULL,        
    comment VARCHAR,            
    
    dataTypeBoolean BOOLEAN NOT NULL DEFAULT false,
    dataTypeString BOOLEAN NOT NULL DEFAULT false,
    dataTypeShort BOOLEAN NOT NULL DEFAULT false,
    dataTypeInteger BOOLEAN NOT NULL DEFAULT false,
    dataTypeLong BOOLEAN NOT NULL DEFAULT false,
    dataTypeFloat BOOLEAN NOT NULL DEFAULT false,
    dataTypeDouble BOOLEAN NOT NULL DEFAULT false,
    dataTypeBigDecial BOOLEAN NOT NULL DEFAULT false,    
    dataTypeDate BOOLEAN NOT NULL DEFAULT false,
    dataTypeTimestamp BOOLEAN NOT NULL DEFAULT false,
    dataTypeTime BOOLEAN NOT NULL DEFAULT false,
    
    dataTypeClazz VARCHAR REFERENCES cendraom.Clazz (id),	
    
    isArray BOOLEAN NOT NULL DEFAULT false,
  --  isTable BOOLEAN NOT NULL DEFAULT false,
    
    labelName VARCHAR,-- NOT NULL,    
    labelComment VARCHAR
    
);


-- SELECT * FROM cendraom.ClazzAttribute;
-- DELETE FROM cendraom.ClazzAttribute;

-- --------------

DROP INDEX IF EXISTS u_ClazzAttribute_memberOfAttribute_name CASCADE;
DROP INDEX IF EXISTS u_ClazzAttribute_memberOfClazz_name CASCADE;
DROP INDEX IF EXISTS u_ClazzAttribute_memberOfAttribute_orderAtt CASCADE;
DROP INDEX IF EXISTS u_ClazzAttribute_memberOfClazz_orderAtt CASCADE;

CREATE UNIQUE INDEX u_ClazzAttribute_memberOfAttribute_name ON cendraom.ClazzAttribute ( TRIM(memberOfAttribute), LOWER(TRIM(name)) );
CREATE UNIQUE INDEX u_ClazzAttribute_memberOfClazz_name ON cendraom.ClazzAttribute (TRIM(memberOfClazz), LOWER(TRIM(name)));
CREATE UNIQUE INDEX u_ClazzAttribute_memberOfAttribute_orderAtt ON cendraom.ClazzAttribute (TRIM(memberOfAttribute), orderAtt);
CREATE UNIQUE INDEX u_ClazzAttribute_memberOfClazz_orderAtt ON cendraom.ClazzAttribute (TRIM(memberOfClazz), orderAtt);

-- --------------

DROP FUNCTION IF EXISTS cendraom.ftgFormatClazzAttribute() CASCADE;

CREATE OR REPLACE FUNCTION cendraom.ftgFormatClazzAttribute() RETURNS TRIGGER AS $formatClazzAttribute$
DECLARE
BEGIN
   
    NEW.id := cendraom.white_is_null(TRIM(NEW.id));
    NEW.memberOfAttribute := cendraom.white_is_null(TRIM(NEW.memberOfAttribute));
    NEW.memberOfClazz := cendraom.white_is_null(TRIM(NEW.memberOfClazz));
    NEW.code := cendraom.white_is_null(TRIM(NEW.code));
    NEW.name := cendraom.white_is_null(TRIM(NEW.name));
    NEW.comment := cendraom.white_is_null(TRIM(NEW.comment));
    NEW.dataTypeClazz := cendraom.white_is_null(TRIM(NEW.dataTypeClazz));
    NEW.labelName := cendraom.white_is_null(TRIM(NEW.labelName));
    NEW.labelComment := cendraom.white_is_null(TRIM(NEW.labelComment));    

	RETURN NEW;
END;
$formatClazzAttribute$ LANGUAGE plpgsql;

-- --------------

DROP TRIGGER IF EXISTS tgFormatClazzAttribute ON cendraom.ClazzAttribute CASCADE;

CREATE TRIGGER tgFormatClazzAttribute BEFORE INSERT OR UPDATE 
    ON cendraom.ClazzAttribute FOR EACH ROW 
    EXECUTE PROCEDURE cendraom.ftgFormatClazzAttribute();

-- ---------------------------------------------------------------------------------------------------------------------------

-- Table: cendraom.ClazzAttributeLabel

DROP TABLE IF EXISTS cendraom.ClazzAttributeLabel CASCADE;

CREATE TABLE cendraom.ClazzAttributeLabel
(    
    id VARCHAR PRIMARY KEY DEFAULT uuid_generate_v4(),
    
    labelOf VARCHAR NOT NULL REFERENCES cendraom.ClazzAttribute (id),	
    
    locale VARCHAR NOT NULL,    
    name VARCHAR NOT NULL,        
    comment VARCHAR
);

-- --------------

DROP INDEX IF EXISTS u_ClazzAttributeLabel_labelOf_locale_name CASCADE;

CREATE UNIQUE INDEX u_ClazzAttributeLabel_labelOf_locale_name ON cendraom.ClazzAttributeLabel (TRIM(labelOf), LOWER(TRIM(locale)), LOWER(TRIM(name)));

-- --------------

DROP FUNCTION IF EXISTS cendraom.ftgFormatClazzAttributeLabel() CASCADE;

CREATE OR REPLACE FUNCTION cendraom.ftgFormatClazzAttributeLabel() RETURNS TRIGGER AS $formatClazzAttributeLabel$
DECLARE
BEGIN
   
   	NEW.id := cendraom.white_is_null(TRIM(NEW.id));
    NEW.labelOf := cendraom.white_is_null(TRIM(NEW.labelOf));
    NEW.locale := cendraom.white_is_null(TRIM(NEW.locale));
    NEW.name := cendraom.white_is_null(TRIM(NEW.name));
    NEW.comment := cendraom.white_is_null(TRIM(NEW.comment));    

	RETURN NEW;
END;
$formatClazzAttributeLabel$ LANGUAGE plpgsql;

-- --------------

DROP TRIGGER IF EXISTS tgFormatClazzAttributeLabel ON cendraom.ClazzAttributeLabel CASCADE;

CREATE TRIGGER tgFormatClazzAttributeLabel BEFORE INSERT OR UPDATE 
    ON cendraom.ClazzAttributeLabel FOR EACH ROW 
    EXECUTE PROCEDURE cendraom.ftgFormatClazzAttributeLabel();


-- ---------------------------------------------------------------------------------------------------------------------------
/*
-- Table: cendraom.Interfaze

DROP TABLE IF EXISTS cendraom.Interfaze CASCADE;

CREATE TABLE cendraom.Interfaze
(
    -- id VARCHAR NOT NULL PRIMARY KEY DEFAULT uuid_generate_v4(),  
    id VARCHAR PRIMARY KEY DEFAULT uuid_generate_v4(),  
    virtual BOOLEAN NOT NULL DEFAULT false,
    
    visibility VARCHAR  NOT NULL,
    name VARCHAR  NOT NULL UNIQUE,    
    comment VARCHAR,
    
    labelName VARCHAR NOT NULL,    
    labelComment VARCHAR
);

-- SELECT * FROM cendraom.Interfaze;
-- DELETE FROM cendraom.Interfaze;

-- ---------------------------------------------------------------------------------------------------------------------------

-- Table: cendraom.InterfazeLabel

DROP TABLE IF EXISTS cendraom.InterfazeLabel CASCADE;

CREATE TABLE cendraom.InterfazeLabel
(    
    id VARCHAR PRIMARY KEY DEFAULT uuid_generate_v4(),
    
    labelOf VARCHAR NOT NULL REFERENCES cendraom.Interfaze (id),	
    
    locale VARCHAR NOT NULL,    
    name VARCHAR NOT NULL,        
    comment VARCHAR
);

-- ---------------------------------------------------------------------------------------------------------------------------

-- Table: cendraom.InterfazeAttribute

DROP TABLE IF EXISTS cendraom.InterfazeAttribute CASCADE;

CREATE TABLE cendraom.InterfazeAttribute
(
    
    id VARCHAR PRIMARY KEY DEFAULT uuid_generate_v4(),
    
    memberOfInterfaze VARCHAR NOT NULL REFERENCES cendraom.Interfaze (id),
    memberOfAttribute VARCHAR NOT NULL REFERENCES cendraom.InterfazeAttribute (id),	
    
    code VARCHAR  NOT NULL,
    orderAtt INTEGER NOT NULL DEFAULT 0,
    name VARCHAR NOT NULL,        
    comment VARCHAR,            
    
    dataTypeBoolean BOOLEAN NOT NULL DEFAULT false,
    dataTypeString BOOLEAN NOT NULL DEFAULT true,
    dataTypeShort BOOLEAN NOT NULL DEFAULT false,
    dataTypeInteger BOOLEAN NOT NULL DEFAULT false,
    dataTypeLong BOOLEAN NOT NULL DEFAULT false,
    dataTypeFloat BOOLEAN NOT NULL DEFAULT false,
    dataTypeDouble BOOLEAN NOT NULL DEFAULT false,
    dataTypeBigDecial BOOLEAN NOT NULL DEFAULT false,    
    dataTypeDate BOOLEAN NOT NULL DEFAULT false,
    dataTypeTimestamp BOOLEAN NOT NULL DEFAULT false,
    dataTypeTime BOOLEAN NOT NULL DEFAULT false,
    
    dataTypeInterfaze VARCHAR NOT NULL REFERENCES cendraom.Clazz (id),	
    dataTypeClazz VARCHAR NOT NULL REFERENCES cendraom.Clazz (id),	        
    
    isArray BOOLEAN NOT NULL DEFAULT false,
    isTable BOOLEAN NOT NULL DEFAULT false,
    
    labelName VARCHAR NOT NULL,    
    labelComment VARCHAR
    
);

-- ---------------------------------------------------------------------------------------------------------------------------

-- Table: cendraom.InterfazeAttributeLabel

DROP TABLE IF EXISTS cendraom.InterfazeAttributeLabel CASCADE;

CREATE TABLE cendraom.InterfazeAttributeLabel
(    
    id VARCHAR PRIMARY KEY DEFAULT uuid_generate_v4(),
    
    labelOf VARCHAR NOT NULL REFERENCES cendraom.InterfazeAttribute (id),	
    
    locale VARCHAR NOT NULL,    
    name VARCHAR NOT NULL,        
    comment VARCHAR
);

*/

    
-- ==========================================================================================================================
-- ==========================================================================================================================
-- ==========================================================================================================================
-- ==========================================================================================================================
-- ==========================================================================================================================
-- ==========================================================================================================================
-- ==========================================================================================================================

/*




        
*/

DROP SCHEMA IF EXISTS org_cendra CASCADE;
-- DROP SCHEMA IF EXISTS org_cendra_person CASCADE;
-- DROP SCHEMA IF EXISTS org_cendra_person_human CASCADE;
-- DROP SCHEMA IF EXISTS org_cendra_person_org CASCADE;
-- DROP SCHEMA IF EXISTS person_org_cendra_person CASCADE;
