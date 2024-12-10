USE [QLSVTC]
GO

/****** Object:  StoredProcedure [dbo].[SP_THEM_SV_2]    Script Date: 12/10/2024 2:33:04 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE [dbo].[SP_THEM_SV_2]
	@MALOP NCHAR(10),
	@MACN NCHAR(10)
AS
DECLARE @nam NCHAR(2)
DECLARE @cn_character NCHAR(2)
DECLARE @masv_pre NCHAR(8)
DECLARE @svnum NCHAR(3)
SET XACT_ABORT ON;

BEGIN
	
	IF EXISTS (SELECT 1 FROM dbo.LOP WHERE MALOP = @MALOP)
		BEGIN
			select @nam= KHOAHOC % 100 FROM LOP WHERE MALOP=@MALOP
			select @cn_character = SUBSTRING(MACN, 3, 2) FROM CHUYENNGANH WHERE MACN=@MACN
			select @masv_pre = concat('D', @nam, 'DC', @cn_character,'%')
			SELECT @svnum = RIGHT('000'+LTRIM(RTRIM(CAST(count(MASV) +1 as NCHAR(3)))), 3) FROM SINHVIEN WHERE MASV LIKE @masv_pre
			select LEFT(@masv_pre, LEN(@masv_pre) - 1) + @svnum as MASV


		END
	ELSE 
		BEGIN
			IF EXISTS (SELECT 1 FROM LINK1.QLSVTC.dbo.LOP WHERE MALOP = @MALOP)
				BEGIN
					select @nam= KHOAHOC % 100 FROM LINK1.QLSVTC.dbo.LOP WHERE MALOP=@MALOP
					select @cn_character = SUBSTRING(MACN, 3, 2) FROM LINK1.QLSVTC.dbo.CHUYENNGANH WHERE MACN=@MACN
					select @masv_pre = concat('D', @nam, 'DC', @cn_character,'%')
					SELECT @svnum = RIGHT('000'+LTRIM(RTRIM(CAST(count(MASV) +1 as NCHAR(3)))), 3) FROM LINK1.QLSVTC.dbo.SINHVIEN WHERE MASV LIKE @masv_pre
					select LEFT(@masv_pre, LEN(@masv_pre) - 1) + @svnum as MASV

				END
			ELSE
				RAISERROR(N'Lỗi không tìm thấy lớp trong hệ thông!',16,1)
		END
			
	
END


GO

