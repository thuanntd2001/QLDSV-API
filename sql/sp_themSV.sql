USE [QLSVTC]
GO

DECLARE	@return_value int

EXEC	@return_value = [dbo].[SP_THEM_SV]
		@MALOP = N'D19CQVT01 ',
		@HO = N'Nguyễn Anh ',
		@TEN = N'Công',
		@PHAI = 1,
		@DIACHI = N'GiaLai',
		@NGAYSINH = '2001-10-30'

SELECT	'Return Value' = @return_value

GO
