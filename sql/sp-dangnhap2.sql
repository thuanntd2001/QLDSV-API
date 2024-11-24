USE [QLSVTC]
GO

/****** Object:  StoredProcedure [dbo].[sp_DangNhap]    Script Date: 11/24/2024 7:40:33 PM ******/
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

	DECLARE @HOTEN NVARCHAR(100)

	IF exists (select MASV from SINHVIEN where MASV=@MANV) 
	begin
	select top 1 @HOTEN= ho +' ' + ten from SINHVIEN where MASV=@MANV
	end


	ELSE IF exists (select MAGV from GIANGVIEN where MAGV=@MANV) 
	begin
	select top 1 @HOTEN= ho +' ' + ten from GIANGVIEN where MAGV=@MANV
	end

	
	ELSE IF exists (select manv from nhanvien where MANV=@MANV) 
	begin
	select top 1 @HOTEN=tennv from nhanvien where manv=@MANV
	end

	DECLARE @TENNHOM NVARCHAR(100)

	SELECT TOP 1  @TENNHOM=NAME
  	FROM sys.sysusers
    	WHERE UID = (SELECT TOP 1 groupuid FROM sys.sysmembers WHERE memberuid=@uid)
	select @manv as MANV, @hoten as HOTEN, @tennhom as TENNHOM
END

GO

