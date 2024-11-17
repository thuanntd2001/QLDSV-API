USE [QLSVTC]
GO

/****** Object:  StoredProcedure [dbo].[sp_DangNhap]    Script Date: 11/17/2024 3:12:04 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[sp_DangNhap] @TENLOGIN NVARCHAR(100)
AS
BEGIN
    SET NOCOUNT ON; -- Ng?n ch?n thông báo s? hàng b? ?nh h??ng

    DECLARE @TENNHOM NVARCHAR(128);
    
    -- L?y vai trò c?a ng??i dùng
    SELECT TOP 1
        @TENNHOM = r.name
    FROM 
        sys.database_role_members AS rm
    INNER JOIN 
        sys.database_principals AS r ON rm.role_principal_id = r.principal_id
    INNER JOIN 
        sys.database_principals AS u ON rm.member_principal_id = u.principal_id
    WHERE 
        u.name = @TENLOGIN AND r.name IN ('PGV', 'KHOA', 'SV');

    -- Ki?m tra vai trò và l?y thông tin ng??i dùng
    IF @TENNHOM = 'PGV'
    BEGIN
        SELECT @TENLOGIN AS MANV, 
               @TENNHOM AS TENNHOM, 
               HOTEN = (SELECT TOP 1 TENNV FROM dbo.NHANVIEN WHERE MANV = @TENLOGIN);
        
    END
    ELSE IF @TENNHOM = 'KHOA'
    BEGIN
        SELECT @TENLOGIN AS MANV, 
               @TENNHOM AS TENNHOM, 
               HOTEN = (SELECT TOP 1 HO + ' ' + TEN FROM dbo.GIANGVIEN WHERE MAGV = @TENLOGIN);
        
    END
    ELSE IF @TENNHOM = 'SV'
    BEGIN
        SELECT @TENLOGIN AS MANV, 
               @TENNHOM AS TENNHOM, 
               HOTEN = (SELECT TOP 1 HO + ' ' + TEN FROM dbo.SINHVIEN WHERE MASV = @TENLOGIN);
        
    END
    ELSE
    BEGIN
        SELECT 'User not found or role not assigned' AS ErrorMessage; -- Thông báo n?u không tìm th?y vai trò
    END
END

GO

