USE [QLSVTC]
GO

/****** Object:  StoredProcedure [dbo].[SP_CAPNHAT_DIEM]    Script Date: 12/11/2024 1:30:40 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


Create PROCEDURE [dbo].[SP_CAPNHAT_DIEM]
@DIEMTHI TYPE_DANGKY  READONLY
	
AS
BEGIN
    -- Start the transaction
    BEGIN TRY
        BEGIN TRANSACTION;

        MERGE INTO DANGKY AS TARGET
        USING (SELECT MALTC, MASV, DIEM_CC, DIEM_GK, DIEM_CK FROM @DIEMTHI) AS SOURCE
        ON TARGET.MALTC = SOURCE.MALTC AND TARGET.MASV = SOURCE.MASV 
        WHEN MATCHED THEN
            UPDATE SET TARGET.DIEM_CC = SOURCE.DIEM_CC,
                       TARGET.DIEM_GK = SOURCE.DIEM_GK,
                       TARGET.DIEM_CK = SOURCE.DIEM_CK
        WHEN NOT MATCHED THEN 
            INSERT (MALTC, MASV, DIEM_CC, DIEM_GK, DIEM_CK)
            VALUES (SOURCE.MALTC, SOURCE.MASV, SOURCE.DIEM_CC, SOURCE.DIEM_GK, SOURCE.DIEM_CK);

        -- Commit the transaction if everything is successful
        COMMIT TRANSACTION;
    END TRY
    BEGIN CATCH
        -- Rollback the transaction if an error occurs
        ROLLBACK TRANSACTION;

        -- Optionally, you can log the error or re-throw it
        DECLARE @ErrorMessage NVARCHAR(4000);
        DECLARE @ErrorSeverity INT;
        DECLARE @ErrorState INT;

        SELECT 
            @ErrorMessage = ERROR_MESSAGE(),
            @ErrorSeverity = ERROR_SEVERITY(),
            @ErrorState = ERROR_STATE();

        RAISERROR (@ErrorMessage, @ErrorSeverity, @ErrorState);
    END CATCH
END
GO

