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


-- Table: cendraom.DataType

DROP TABLE IF EXISTS cendraom.DataType CASCADE;

CREATE TABLE cendraom.DataType
(
    -- id VARCHAR NOT NULL PRIMARY KEY DEFAULT uuid_generate_v4(),  
    id VARCHAR PRIMARY KEY DEFAULT uuid_generate_v4(),  
    virtual BOOLEAN NOT NULL DEFAULT false,
   -- typeComponent VARCHAR  NOT NULL,    
    
    name VARCHAR  NOT NULL UNIQUE,
    typeExtends VARCHAR REFERENCES cendraom.DataType (id),
    visibility VARCHAR  NOT NULL,
    finalType BOOLEAN NOT NULL DEFAULT false,
    internalType BOOLEAN NOT NULL DEFAULT false,
    isClass BOOLEAN NOT NULL DEFAULT true
    
);

-- SELECT * FROM cendraom.DataType;
-- DELETE FROM cendraom.DataType;


DROP INDEX IF EXISTS u_DataType_name CASCADE;
CREATE UNIQUE INDEX u_DataType_name ON cendraom.DataType (TRIM(name));
-- CREATE UNIQUE INDEX u_Type_name ON cendraom.DataType (LOWER(TRIM(name)));

-- ---------------------------------------------------------------------------------------------------------------------------

DROP FUNCTION IF EXISTS cendraom.ftgFormatDataType() CASCADE;

CREATE OR REPLACE FUNCTION cendraom.ftgFormatDataType() RETURNS TRIGGER AS $formatDataType$
DECLARE
BEGIN
   
   NEW.id := cendraom.white_is_null(TRIM(NEW.id));
	-- NEW.comentario := massoftware.white_is_null(REPLACE(TRIM(NEW.comentario), '"', ''));	
    NEW.name := cendraom.white_is_null(TRIM(NEW.name));
    NEW.typeExtends := cendraom.white_is_null(TRIM(NEW.typeExtends));

	RETURN NEW;
END;
$formatDataType$ LANGUAGE plpgsql;

-- ---------------------------------------------------------------------------------------------------------------------------

DROP TRIGGER IF EXISTS tgFormatDataType ON cendraom.DataType CASCADE;

CREATE TRIGGER tgFormatDataType BEFORE INSERT OR UPDATE 
    ON cendraom.DataType FOR EACH ROW 
    EXECUTE PROCEDURE cendraom.ftgFormatDataType();
    

-- ==========================================================================================================================

-- Table: cendraom.TypesExtends

DROP TABLE IF EXISTS cendraom.TypesExtends CASCADE;

CREATE TABLE cendraom.TypesExtends
(
    id VARCHAR PRIMARY KEY DEFAULT uuid_generate_v4(),  
  
    childType VARCHAR NOT NULL REFERENCES cendraom.DataType (id),	
    fatherType VARCHAR NOT NULL REFERENCES cendraom.DataType (id)
    
);

-- SELECT * FROM cendraom.TypesExtends;

DROP INDEX IF EXISTS u_TypesExtends_childType_fatherType CASCADE;
CREATE UNIQUE INDEX u_TypesExtends_childType_fatherType ON cendraom.TypesExtends (TRIM(childType), TRIM(fatherType));

-- ---------------------------------------------------------------------------------------------------------------------------

DROP FUNCTION IF EXISTS cendraom.ftgFormatTypesExtends() CASCADE;

CREATE OR REPLACE FUNCTION cendraom.ftgFormatTypesExtends() RETURNS TRIGGER AS $formatTypesExtends$
DECLARE
BEGIN
   
   	NEW.id := cendraom.white_is_null(TRIM(NEW.id));
   
	NEW.childType := cendraom.white_is_null(TRIM(NEW.childType));
    NEW.fatherType := cendraom.white_is_null(TRIM(NEW.fatherType));

	RETURN NEW;
END;
$formatTypesExtends$ LANGUAGE plpgsql;

-- ---------------------------------------------------------------------------------------------------------------------------

DROP TRIGGER IF EXISTS tgFormatTypesExtends ON cendraom.TypesExtends CASCADE;

CREATE TRIGGER tgFormatTypesExtends BEFORE INSERT OR UPDATE 
    ON cendraom.TypesExtends FOR EACH ROW 
    EXECUTE PROCEDURE cendraom.ftgFormatTypesExtends();


-- ==========================================================================================================================

-- Table: cendraom.TypeAttribute

DROP TABLE IF EXISTS cendraom.TypeAttribute CASCADE;

CREATE TABLE cendraom.TypeAttribute
(
    
    id VARCHAR PRIMARY KEY DEFAULT uuid_generate_v4(),
    
    memberOfType VARCHAR NOT NULL REFERENCES cendraom.DataType (id),	
    name VARCHAR  NOT NULL,
    dataType VARCHAR  NOT NULL,
    typeCardinality VARCHAR  NOT NULL,
    orderAtt INTEGER NOT NULL DEFAULT 0
    
);

-- SELECT * FROM cendraom.TypeAttribute;
-- DELETE FROM cendraom.TypeAttribute;


DROP INDEX IF EXISTS u_TypeAttribute_memberOfType_name CASCADE;
CREATE UNIQUE INDEX u_TypeAttribute_memberOfType_name ON cendraom.TypeAttribute (TRIM(TypeAttribute.memberOfType), TRIM(TypeAttribute.name));

-- ---------------------------------------------------------------------------------------------------------------------------

DROP FUNCTION IF EXISTS cendraom.ftgFormatTypeAttribute() CASCADE;

CREATE OR REPLACE FUNCTION cendraom.ftgFormatTypeAttribute() RETURNS TRIGGER AS $formatTypeAttribute$
DECLARE
BEGIN
   
	NEW.id := cendraom.white_is_null(TRIM(NEW.id));
    NEW.memberOfType := cendraom.white_is_null(TRIM(NEW.memberOfType));
    NEW.name := cendraom.white_is_null(TRIM(NEW.name));
    NEW.dataType := cendraom.white_is_null(TRIM(NEW.dataType));
    NEW.typeCardinality := cendraom.white_is_null(TRIM(NEW.typeCardinality));

	RETURN NEW;
END;
$formatTypeAttribute$ LANGUAGE plpgsql;

-- ---------------------------------------------------------------------------------------------------------------------------

DROP TRIGGER IF EXISTS tgFormatTypeAttribute ON cendraom.TypeAttribute CASCADE;

CREATE TRIGGER tgFormatTypeAttribute BEFORE INSERT OR UPDATE 
    ON cendraom.TypeAttribute FOR EACH ROW 
    EXECUTE PROCEDURE cendraom.ftgFormatTypeAttribute();
    
-- ==========================================================================================================================
-- ==========================================================================================================================
-- ==========================================================================================================================
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
    name VARCHAR  NOT NULL UNIQUE,
    extendsFrom VARCHAR REFERENCES cendraom.Clazz (id),
    comment VARCHAR,
    
    labelName VARCHAR, -- NOT NULL,    
    labelComment VARCHAR
);

-- SELECT * FROM cendraom.Clazz;
-- DELETE FROM cendraom.Clazz;

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

-- ---------------------------------------------------------------------------------------------------------------------------

-- Table: cendraom.ClazzAttribute

DROP TABLE IF EXISTS cendraom.ClazzAttribute CASCADE;

CREATE TABLE cendraom.ClazzAttribute
(
    
    id VARCHAR PRIMARY KEY DEFAULT uuid_generate_v4(),
    
    memberOfClazz VARCHAR REFERENCES cendraom.Clazz (id),
    memberOfAttribute VARCHAR REFERENCES cendraom.ClazzAttribute (id),	
    
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

-- ---------------------------------------------------------------------------------------------------------------------------

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


    
-- ==========================================================================================================================
-- ==========================================================================================================================
-- ==========================================================================================================================
-- ==========================================================================================================================
-- ==========================================================================================================================
-- ==========================================================================================================================

-- ==========================================================================================================================

/*

SELECT 	CASE 	WHEN z.isClass = true THEN 'Class'            
            	ELSE 'Interface'
		END,
		z.name,
        CASE 	WHEN z.isClass = true THEN 'implements'            
            	ELSE 'extends'
		END,
		e.name
FROM 	cendraom.TypesExtends r
JOIN	cendraom.DataType z
		on r.childType = z.id
JOIN	cendraom.DataType e
		on r.fatherType = e.id
ORDER BY z.name; 
        
--------------------------------------------------        

SELECT 	CASE 	WHEN DataType.isClass = true THEN 'Class'            
            	ELSE 'Interface'
		END,
		DataType.name,
		TypeAttribute.name,
        TypeAttribute.dataType,
        TypeAttribute.orderAtt        
FROM 	cendraom.DataType
JOIN 	cendraom.TypeAttribute 
		ON TypeAttribute.memberOfType = DataType.id
-- WHERE DataType.name = 'org.cendra.person.human.HumanIdType'
ORDER BY	DataType.isClass, DataType.name, TypeAttribute.orderAtt
    
--------------------------------------------------            
    
DELETE FROM cendraom.ClazzAtt;
DELETE FROM cendraom.ClazzExtends;
DELETE FROM cendraom.Clazz;    


SELECT * FROM cendraom.clazz ORDER BY name OFFSET 0 LIMIT 100


SELECT cendraom.Clazz.id, cendraom.Clazz.name FROM cendraom.ClazzExtends JOIN cendraom.Clazz ON cendraom.ClazzExtends.clazzExtends = cendraom.Clazz.id WHERE cendraom.ClazzExtends.clazz = 'eb8ef90e-f5db-41e4-9d63-a454b0902336'

SELECT ClazzAtt.*, cendraom.Clazz.name AS clazzDataTypeName FROM cendraom.ClazzAtt LEFT JOIN cendraom.Clazz ON cendraom.ClazzAtt.dataType = cendraom.Clazz.id WHERE cendraom.ClazzAtt.clazz = 'eb8ef90e-f5db-41e4-9d63-a454b0902336'

SELECT ClazzAtt.*, cendraom.Clazz.name AS clazzDataTypeName FROM cendraom.ClazzAtt LEFT JOIN cendraom.Clazz ON cendraom.ClazzAtt.dataType = cendraom.Clazz.id WHERE cendraom.ClazzAtt.clazz = '21c149ca-c3b6-424c-a2c1-ee94e8b8eb50';

        
*/

DROP SCHEMA IF EXISTS org_cendra CASCADE;
-- DROP SCHEMA IF EXISTS org_cendra_person CASCADE;
-- DROP SCHEMA IF EXISTS org_cendra_person_human CASCADE;
-- DROP SCHEMA IF EXISTS org_cendra_person_org CASCADE;
-- DROP SCHEMA IF EXISTS person_org_cendra_person CASCADE;
