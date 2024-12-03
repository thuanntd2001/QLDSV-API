USE [QLSVTC]
GO

/****** Object:  StoredProcedure [dbo].[sp_DangNhap]    Script Date: 12/3/2024 5:19:49 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE [dbo].[sp_DangNhap] 
    @TENLOGIN NVARCHAR(100)
AS
BEGIN
DECLARE @UID INT
	DECLARE @MANV NVARCHAR(10)
	SELECT @UID= uid , @MANV= NAME FROM sys.sysusers 
  	WHERE sid = SUSER_SID(@TENLOGIN)

	DECLARE @TENNHOM NVARCHAR(100)
	SELECT TOP 1  @TENNHOM=NAME
  	FROM sys.sysusers
    	WHERE UID = (SELECT TOP 1 groupuid FROM sys.sysmembers WHERE memberuid=@uid)
	

	DECLARE @HOTEN NVARCHAR(100)

	IF @TENNHOM='SV'
	begin
	select top 1 @HOTEN= ho +' ' + ten from SINHVIEN where MASV=@MANV
	end


	ELSE IF @TENNHOM='KHOA'
	begin
	select top 1 @HOTEN= ho +' ' + ten from GIANGVIEN where MAGV=@MANV
	end

	
	ELSE IF @TENNHOM='PGV'
	begin
	select top 1 @HOTEN=tennv from nhanvien where manv=@MANV
	end

	select @manv as MANV, @hoten as HOTEN, @tennhom as TENNHOM
END

GO

