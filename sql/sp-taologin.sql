USE [QLSVTC]
GO

/****** Object:  StoredProcedure [dbo].[SP_TAOLOGIN]    Script Date: 12/3/2024 5:21:57 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE [dbo].[SP_TAOLOGIN] @LGNAME VARCHAR(50),
  @PASS VARCHAR(50),
  @USERNAME VARCHAR(50),
  @ROLE VARCHAR(50)
AS
  DECLARE @RET INT
  EXEC @RET= SP_ADDLOGIN @LGNAME, @PASS,'QLSVTC'
  IF (@RET =1)  -- LOGIN NAME BI TRUNG
     RETURN 1
 
  EXEC @RET= SP_GRANTDBACCESS @LGNAME, @USERNAME
  IF (@RET =1)  -- USER  NAME BI TRUNG
	BEGIN
		EXEC SP_DROPLOGIN @LGNAME
		RETURN 2
	END

  EXEC sp_addrolemember @ROLE, @USERNAME

  

  IF @ROLE= 'PGV' OR @ROLE= 'KHOA'
	BEGIN 
		
		EXEC sp_addsrvrolemember @LGNAME, 'SecurityAdmin'
		EXEC sp_addsrvrolemember @LGNAME, 'ProcessAdmin'
	END

	
RETURN 0  -- THANH CONG


GO
