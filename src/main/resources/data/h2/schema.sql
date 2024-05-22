CREATE TABLE PRODUCTOS(
     
     CODIGO				BIGINT          NOT NULL,
     NOMBRE				VARCHAR(50)		,
     DESCRIPCION		VARCHAR(100)	,
     PRECIO				DOUBLE			,
     FECHA_ALTA			DATE			,
     FAMILIA			VARCHAR(25)     ,
     DESCATALOGADO      TINYINT		    ,
     
     PRIMARY KEY(CODIGO)
     
);

  