USE [QLSVTC]
GO

/****** Object:  StoredProcedure [dbo].[SP_THEM_SV]    Script Date: 12/8/2024 4:28:52 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

/*Mục đích: chuyển sinh viên từ chi lớp này sang lớp khác, dk sinh chưa có điểm thành phần nào */

/* Trong sp này sử dụng transaction mức Serializable -  mức an toàn - TT2 đang đọc một bảng dữ liệu thì TT1 không thể chỉnh sửa
 bảng dữ liệu này cho đến khi TT2 hoàn tất việc đọc dữ liệu đó. Nói nôm na là dữ liệu đang được đọc sẽ được bảo vệ khỏi cập nhật bởi các transaction khác */

CREATE PROCEDURE [dbo].[SP_THEM_SV]
	@MALOP NCHAR(10),
	@MACN NCHAR(10) = NULL,
	@MASV NCHAR(10) = NULL,
	@HO NVARCHAR(50),
	@TEN NVARCHAR(10),
	@PHAI BIT,
	@DIACHI NVARCHAR(100),
	@NGAYSINH DATE
AS
DECLARE @MACN_TEMP NCHAR(10) = NULL;
DECLARE @MASV_TEMP NCHAR(10) = NULL;
SET XACT_ABORT ON;
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
BEGIN
	BEGIN TRY
	BEGIN DISTRIBUTED TRANSACTION; --bat dau giao tac
	IF EXISTS (SELECT 1 FROM dbo.LOP WHERE MALOP = @MALOP)
		BEGIN
			SELECT TOP 1 @MACN_TEMP = MACN FROM SINHVIEN WHERE MALOP=@MALOP
			-- Kiem tra lop co rong ko
			IF @MACN_TEMP IS NULL 
				SET @MACN_TEMP = @MACN;
			IF @MACN_TEMP IS NULL 
				BEGIN
					RAISERROR(N'SP_Them_SV mong đợi tham số @MACN!', 16, 1);
				END
			SELECT TOP 1 @MASV_TEMP=MASV FROM SINHVIEN WHERE MALOP=@MALOP ORDER BY MASV DESC;
			-- Kiem tra lop rong ko
			IF @MASV_TEMP IS NULL 
				SET @MASV_TEMP = @MASV;
			IF @MASV_TEMP IS NULL
				BEGIN
					RAISERROR(N'SP_Them_SV mong đợi tham số @MASV!', 16, 1);
				END
			INSERT INTO SINHVIEN (MASV, HO, TEN, PHAI, DIACHI, NGAYSINH, MALOP, MACN)
			VALUES (  -- Tạo MANV mới bằng cách lấy phần số cuối, cộng thêm 1 và đệm 0
			LEFT(@MASV_TEMP, LEN(@MASV_TEMP) - 3) + RIGHT('000' + CAST(CAST(SUBSTRING(@MASV_TEMP, LEN(@MASV_TEMP) - 2, 3) AS INT) + 1 AS VARCHAR), 3),
						@HO, @TEN, @PHAI, @DIACHI, @NGAYSINH, @MALOP, @MACN_TEMP)
		END
	ELSE
		BEGIN
			SELECT TOP 1 @MACN_TEMP = MACN FROM LINK1.QLSVTC.dbo.SINHVIEN WHERE MALOP=@MALOP
			IF @MACN_TEMP IS NULL 
				SET @MACN_TEMP = @MACN;
			IF @MACN_TEMP IS NULL
				BEGIN
					RAISERROR(N'SP_Them_SV mong đợi tham số @MACN!', 16, 1);
				END
			SELECT TOP 1 @MASV_TEMP=MASV FROM LINK1.QLSVTC.dbo.SINHVIEN WHERE MALOP=@MALOP ORDER BY MASV DESC;
			-- Kiem tra lop rong ko
			IF @MASV_TEMP IS NULL 
				SET @MASV_TEMP = @MASV;
			IF @MASV_TEMP IS NULL 
				BEGIN
					RAISERROR(N'SP_Them_SV mong đợi tham số @MASV!', 16, 1);
				END
			INSERT INTO LINK1.QLSVTC.dbo.SINHVIEN (MASV, HO, TEN, PHAI, DIACHI, NGAYSINH, MALOP, MACN)
			VALUES (  -- Tạo MANV mới bằng cách lấy phần số cuối, cộng thêm 1 và đệm 0
			LEFT(@MASV_TEMP, LEN(@MASV_TEMP) - 3) + RIGHT('000' + CAST(CAST(SUBSTRING(@MASV_TEMP, LEN(@MASV_TEMP) - 2, 3) AS INT) + 1 AS VARCHAR), 3),
						@HO, @TEN, @PHAI, @DIACHI, @NGAYSINH, @MALOP, @MACN_TEMP)
		END
		COMMIT TRANSACTION; -- Kết thúc giao tác
		SELECT N'Thêm sinh viên thành công.' AS MESSAGE;
	END TRY
	BEGIN CATCH
		ROLLBACK TRANSACTION; -- Hủy giao tác nếu có lỗi
		PRINT N'Thêm sinh viên được hủy do lỗi.';
		-- Trả về chi tiết của lỗi
		SELECT
			ERROR_MESSAGE() AS ErrorMessage,
			ERROR_NUMBER() AS ErrorNumber,
			ERROR_LINE() AS ErrorLine;
	END CATCH
END

GO

