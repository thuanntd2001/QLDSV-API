USE [master]
GO
/****** Object:  Database [QLSVTC]    Script Date: 11/3/2024 4:38:07 PM ******/
IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = N'QLSVTC')
BEGIN
CREATE DATABASE [QLSVTC]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'QLSVTC', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER0\MSSQL\DATA\QLSVTC.mdf' , SIZE = 3264KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'QLSVTC_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER0\MSSQL\DATA\QLSVTC_log.ldf' , SIZE = 816KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
END

GO
ALTER DATABASE [QLSVTC] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [QLSVTC].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [QLSVTC] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [QLSVTC] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [QLSVTC] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [QLSVTC] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [QLSVTC] SET ARITHABORT OFF 
GO
ALTER DATABASE [QLSVTC] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [QLSVTC] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [QLSVTC] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [QLSVTC] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [QLSVTC] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [QLSVTC] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [QLSVTC] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [QLSVTC] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [QLSVTC] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [QLSVTC] SET  ENABLE_BROKER 
GO
ALTER DATABASE [QLSVTC] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [QLSVTC] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [QLSVTC] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [QLSVTC] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [QLSVTC] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [QLSVTC] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [QLSVTC] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [QLSVTC] SET RECOVERY FULL 
GO
ALTER DATABASE [QLSVTC] SET  MULTI_USER 
GO
ALTER DATABASE [QLSVTC] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [QLSVTC] SET DB_CHAINING OFF 
GO
ALTER DATABASE [QLSVTC] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [QLSVTC] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [QLSVTC] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'QLSVTC', N'ON'
GO
USE [QLSVTC]
GO
/****** Object:  Table [dbo].[CHUYENNGANH]    Script Date: 11/3/2024 4:38:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[CHUYENNGANH]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[CHUYENNGANH](
	[MACN] [nchar](10) NOT NULL,
	[TENCN] [nvarchar](50) NOT NULL,
	[MAKHOA] [nchar](10) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[MACN] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
END
GO
/****** Object:  Table [dbo].[DANGKY]    Script Date: 11/3/2024 4:38:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[DANGKY]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[DANGKY](
	[MALTC] [int] NOT NULL,
	[MASV] [nchar](10) NOT NULL,
	[DIEM_CC] [int] NULL,
	[DIEM_GK] [numeric](4, 2) NULL,
	[DIEM_CK] [numeric](4, 2) NULL,
	[HUYDANGKY] [bit] NULL,
	[MADK] [int] NOT NULL,
 CONSTRAINT [PK__DANGKY__EC3F136490AEC4FC] PRIMARY KEY CLUSTERED 
(
	[MADK] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [MALTC_MASV] UNIQUE NONCLUSTERED 
(
	[MALTC] ASC,
	[MASV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
END
GO
/****** Object:  Table [dbo].[GIANGDAY]    Script Date: 11/3/2024 4:38:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[GIANGDAY]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[GIANGDAY](
	[MAGIANGDAY] [int] IDENTITY(1,1) NOT NULL,
	[MAGV] [nchar](10) NOT NULL,
	[MATB] [nchar](10) NOT NULL,
	[MALTC] [int] NOT NULL,
 CONSTRAINT [PK__GIANGDAY__8AB53872FB161F32] PRIMARY KEY CLUSTERED 
(
	[MAGIANGDAY] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [MAGV_MATB_MLTC] UNIQUE NONCLUSTERED 
(
	[MAGV] ASC,
	[MATB] ASC,
	[MALTC] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
END
GO
/****** Object:  Table [dbo].[GIANGVIEN]    Script Date: 11/3/2024 4:38:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[GIANGVIEN]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[GIANGVIEN](
	[MAGV] [nchar](10) NOT NULL,
	[MAKHOA] [nchar](10) NOT NULL,
	[HO] [nvarchar](50) NOT NULL,
	[TEN] [nvarchar](10) NOT NULL,
	[HOCVI] [nvarchar](7) NULL,
	[HOCHAM] [nvarchar](11) NULL,
	[CHUYENMON] [nvarchar](50) NULL,
 CONSTRAINT [PK__GIANGVIE__603F38B16BE6D5FD] PRIMARY KEY CLUSTERED 
(
	[MAGV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
END
GO
/****** Object:  Table [dbo].[KEHOACH]    Script Date: 11/3/2024 4:38:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[KEHOACH]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[KEHOACH](
	[MAKEHOACH] [int] IDENTITY(1,1) NOT NULL,
	[MAMONHOC] [nchar](10) NOT NULL,
	[MACN] [nchar](10) NOT NULL,
	[MANKHK] [int] NOT NULL,
	[HOCKY] [int] NULL,
 CONSTRAINT [PK__KEHOACH__C7DD0EFB91B3E300] PRIMARY KEY CLUSTERED 
(
	[MAKEHOACH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
END
GO
/****** Object:  Table [dbo].[KHANANG]    Script Date: 11/3/2024 4:38:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[KHANANG]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[KHANANG](
	[MAKHANANG] [int] IDENTITY(1,1) NOT NULL,
	[MAGV] [nchar](10) NOT NULL,
	[MAMH] [nchar](10) NOT NULL,
 CONSTRAINT [PK__KHANANG__ACB117147A2DB50D] PRIMARY KEY CLUSTERED 
(
	[MAKHANANG] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [MAGV_MAMH] UNIQUE NONCLUSTERED 
(
	[MAGV] ASC,
	[MAMH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
END
GO
/****** Object:  Table [dbo].[KHOA]    Script Date: 11/3/2024 4:38:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[KHOA]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[KHOA](
	[MAKHOA] [nchar](10) NOT NULL,
	[TENKHOA] [nvarchar](50) NOT NULL,
	[MATRUONGKHOA] [nchar](10) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[MAKHOA] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [IX_KHOA] UNIQUE NONCLUSTERED 
(
	[MATRUONGKHOA] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
END
GO
/****** Object:  Table [dbo].[LOP]    Script Date: 11/3/2024 4:38:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[LOP]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[LOP](
	[MALOP] [nchar](10) NOT NULL,
	[TENLOP] [nvarchar](50) NOT NULL,
	[KHOAHOC] [int] NOT NULL,
	[MAKHOA] [nchar](10) NOT NULL,
 CONSTRAINT [PK__LOP__7A3DE211E2500A7B] PRIMARY KEY CLUSTERED 
(
	[MALOP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
END
GO
/****** Object:  Table [dbo].[LOPTINCHI]    Script Date: 11/3/2024 4:38:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[LOPTINCHI]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[LOPTINCHI](
	[MALTC] [int] IDENTITY(1,1) NOT NULL,
	[MANKHK] [int] NOT NULL,
	[MAMH] [nchar](10) NOT NULL,
	[NHOM] [int] NOT NULL,
	[MAKHOA] [nchar](10) NOT NULL,
	[SOSVTOITHIEU] [int] NOT NULL,
	[HUYLOP] [bit] NOT NULL,
 CONSTRAINT [PK__LOPTINCH__7A3D3BC6EBD9AADD] PRIMARY KEY CLUSTERED 
(
	[MALTC] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [MANKHK_MAMH_NHOM] UNIQUE NONCLUSTERED 
(
	[MANKHK] ASC,
	[MAMH] ASC,
	[NHOM] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
END
GO
/****** Object:  Table [dbo].[MONHOC]    Script Date: 11/3/2024 4:38:08 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[MONHOC]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[MONHOC](
	[MAMH] [nchar](10) NOT NULL,
	[TENMONHOC] [nvarchar](50) NOT NULL,
	[SOTIETLT] [int] NOT NULL,
	[SOTIETTH] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[MAMH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
END
GO
/****** Object:  Table [dbo].[NHANVIEN]    Script Date: 11/3/2024 4:38:08 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[NHANVIEN]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[NHANVIEN](
	[MANV] [nchar](10) NOT NULL,
	[TENNV] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[MANV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
END
GO
/****** Object:  Table [dbo].[NKHK]    Script Date: 11/3/2024 4:38:08 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[NKHK]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[NKHK](
	[MANKHK] [int] NOT NULL,
	[MANV] [nchar](10) NOT NULL,
	[NAMHOC] [int] NOT NULL,
	[HK] [int] NOT NULL,
	[NGAYMODK] [date] NOT NULL,
	[NGAYDONGDK] [date] NOT NULL,
 CONSTRAINT [PK__NKHK__1BC29D282E925C1D] PRIMARY KEY CLUSTERED 
(
	[MANKHK] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
END
GO
/****** Object:  Table [dbo].[SINHVIEN]    Script Date: 11/3/2024 4:38:08 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[SINHVIEN]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[SINHVIEN](
	[MASV] [nchar](10) NOT NULL,
	[HO] [nvarchar](50) NOT NULL,
	[TEN] [nvarchar](10) NOT NULL,
	[PHAI] [bit] NOT NULL,
	[DIACHI] [nvarchar](100) NULL,
	[NGAYSINH] [date] NULL,
	[MALOP] [nchar](10) NOT NULL,
	[MACN] [nchar](10) NOT NULL,
	[DANGHIHOC] [bit] NOT NULL,
	[PASSWORD] [nvarchar](40) NULL,
 CONSTRAINT [PK__SINHVIEN__60228A281047CB87] PRIMARY KEY CLUSTERED 
(
	[MASV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
END
GO
/****** Object:  Table [dbo].[THUBUOI]    Script Date: 11/3/2024 4:38:08 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[THUBUOI]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[THUBUOI](
	[MATHUBUOI] [nchar](10) NOT NULL,
	[THU] [int] NOT NULL,
	[BUOI] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[MATHUBUOI] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
END
GO
IF NOT EXISTS (SELECT * FROM dbo.sysobjects WHERE id = OBJECT_ID(N'[dbo].[DF__LOPTINCHI__HUYLO__29572725]') AND type = 'D')
BEGIN
ALTER TABLE [dbo].[LOPTINCHI] ADD  CONSTRAINT [DF__LOPTINCHI__HUYLO__29572725]  DEFAULT ('false') FOR [HUYLOP]
END

GO
IF NOT EXISTS (SELECT * FROM dbo.sysobjects WHERE id = OBJECT_ID(N'[dbo].[DF__SINHVIEN__PHAI__2A4B4B5E]') AND type = 'D')
BEGIN
ALTER TABLE [dbo].[SINHVIEN] ADD  CONSTRAINT [DF__SINHVIEN__PHAI__2A4B4B5E]  DEFAULT ('false') FOR [PHAI]
END

GO
IF NOT EXISTS (SELECT * FROM dbo.sysobjects WHERE id = OBJECT_ID(N'[dbo].[DF__SINHVIEN__DANGHI__2B3F6F97]') AND type = 'D')
BEGIN
ALTER TABLE [dbo].[SINHVIEN] ADD  CONSTRAINT [DF__SINHVIEN__DANGHI__2B3F6F97]  DEFAULT ('false') FOR [DANGHIHOC]
END

GO
IF NOT EXISTS (SELECT * FROM dbo.sysobjects WHERE id = OBJECT_ID(N'[dbo].[DF__SINHVIEN__PASSWO__2C3393D0]') AND type = 'D')
BEGIN
ALTER TABLE [dbo].[SINHVIEN] ADD  CONSTRAINT [DF__SINHVIEN__PASSWO__2C3393D0]  DEFAULT ('') FOR [PASSWORD]
END

GO
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK__CHUYENNGA__MAKHO__31EC6D26]') AND parent_object_id = OBJECT_ID(N'[dbo].[CHUYENNGANH]'))
ALTER TABLE [dbo].[CHUYENNGANH]  WITH CHECK ADD FOREIGN KEY([MAKHOA])
REFERENCES [dbo].[KHOA] ([MAKHOA])
GO
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_DANGKY_LOPTINCHI]') AND parent_object_id = OBJECT_ID(N'[dbo].[DANGKY]'))
ALTER TABLE [dbo].[DANGKY]  WITH CHECK ADD  CONSTRAINT [FK_DANGKY_LOPTINCHI] FOREIGN KEY([MALTC])
REFERENCES [dbo].[LOPTINCHI] ([MALTC])
ON UPDATE CASCADE
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_DANGKY_LOPTINCHI]') AND parent_object_id = OBJECT_ID(N'[dbo].[DANGKY]'))
ALTER TABLE [dbo].[DANGKY] CHECK CONSTRAINT [FK_DANGKY_LOPTINCHI]
GO
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_DANGKY_SINHVIEN]') AND parent_object_id = OBJECT_ID(N'[dbo].[DANGKY]'))
ALTER TABLE [dbo].[DANGKY]  WITH CHECK ADD  CONSTRAINT [FK_DANGKY_SINHVIEN] FOREIGN KEY([MASV])
REFERENCES [dbo].[SINHVIEN] ([MASV])
ON UPDATE CASCADE
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_DANGKY_SINHVIEN]') AND parent_object_id = OBJECT_ID(N'[dbo].[DANGKY]'))
ALTER TABLE [dbo].[DANGKY] CHECK CONSTRAINT [FK_DANGKY_SINHVIEN]
GO
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_GIANGDAY_GIANGVIEN]') AND parent_object_id = OBJECT_ID(N'[dbo].[GIANGDAY]'))
ALTER TABLE [dbo].[GIANGDAY]  WITH CHECK ADD  CONSTRAINT [FK_GIANGDAY_GIANGVIEN] FOREIGN KEY([MAGV])
REFERENCES [dbo].[GIANGVIEN] ([MAGV])
ON UPDATE CASCADE
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_GIANGDAY_GIANGVIEN]') AND parent_object_id = OBJECT_ID(N'[dbo].[GIANGDAY]'))
ALTER TABLE [dbo].[GIANGDAY] CHECK CONSTRAINT [FK_GIANGDAY_GIANGVIEN]
GO
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_GIANGDAY_LOPTINCHI]') AND parent_object_id = OBJECT_ID(N'[dbo].[GIANGDAY]'))
ALTER TABLE [dbo].[GIANGDAY]  WITH CHECK ADD  CONSTRAINT [FK_GIANGDAY_LOPTINCHI] FOREIGN KEY([MALTC])
REFERENCES [dbo].[LOPTINCHI] ([MALTC])
ON UPDATE CASCADE
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_GIANGDAY_LOPTINCHI]') AND parent_object_id = OBJECT_ID(N'[dbo].[GIANGDAY]'))
ALTER TABLE [dbo].[GIANGDAY] CHECK CONSTRAINT [FK_GIANGDAY_LOPTINCHI]
GO
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_GIANGDAY_THUBUOI]') AND parent_object_id = OBJECT_ID(N'[dbo].[GIANGDAY]'))
ALTER TABLE [dbo].[GIANGDAY]  WITH CHECK ADD  CONSTRAINT [FK_GIANGDAY_THUBUOI] FOREIGN KEY([MATB])
REFERENCES [dbo].[THUBUOI] ([MATHUBUOI])
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_GIANGDAY_THUBUOI]') AND parent_object_id = OBJECT_ID(N'[dbo].[GIANGDAY]'))
ALTER TABLE [dbo].[GIANGDAY] CHECK CONSTRAINT [FK_GIANGDAY_THUBUOI]
GO
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_GIANGVIEN_KHOA]') AND parent_object_id = OBJECT_ID(N'[dbo].[GIANGVIEN]'))
ALTER TABLE [dbo].[GIANGVIEN]  WITH CHECK ADD  CONSTRAINT [FK_GIANGVIEN_KHOA] FOREIGN KEY([MAKHOA])
REFERENCES [dbo].[KHOA] ([MAKHOA])
ON UPDATE CASCADE
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_GIANGVIEN_KHOA]') AND parent_object_id = OBJECT_ID(N'[dbo].[GIANGVIEN]'))
ALTER TABLE [dbo].[GIANGVIEN] CHECK CONSTRAINT [FK_GIANGVIEN_KHOA]
GO
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_KEHOACH_CHUYENNGANH]') AND parent_object_id = OBJECT_ID(N'[dbo].[KEHOACH]'))
ALTER TABLE [dbo].[KEHOACH]  WITH CHECK ADD  CONSTRAINT [FK_KEHOACH_CHUYENNGANH] FOREIGN KEY([MACN])
REFERENCES [dbo].[CHUYENNGANH] ([MACN])
ON UPDATE CASCADE
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_KEHOACH_CHUYENNGANH]') AND parent_object_id = OBJECT_ID(N'[dbo].[KEHOACH]'))
ALTER TABLE [dbo].[KEHOACH] CHECK CONSTRAINT [FK_KEHOACH_CHUYENNGANH]
GO
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_KEHOACH_MONHOC]') AND parent_object_id = OBJECT_ID(N'[dbo].[KEHOACH]'))
ALTER TABLE [dbo].[KEHOACH]  WITH CHECK ADD  CONSTRAINT [FK_KEHOACH_MONHOC] FOREIGN KEY([MAMONHOC])
REFERENCES [dbo].[MONHOC] ([MAMH])
ON UPDATE CASCADE
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_KEHOACH_MONHOC]') AND parent_object_id = OBJECT_ID(N'[dbo].[KEHOACH]'))
ALTER TABLE [dbo].[KEHOACH] CHECK CONSTRAINT [FK_KEHOACH_MONHOC]
GO
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_KEHOACH_NKHK]') AND parent_object_id = OBJECT_ID(N'[dbo].[KEHOACH]'))
ALTER TABLE [dbo].[KEHOACH]  WITH CHECK ADD  CONSTRAINT [FK_KEHOACH_NKHK] FOREIGN KEY([MANKHK])
REFERENCES [dbo].[NKHK] ([MANKHK])
ON UPDATE CASCADE
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_KEHOACH_NKHK]') AND parent_object_id = OBJECT_ID(N'[dbo].[KEHOACH]'))
ALTER TABLE [dbo].[KEHOACH] CHECK CONSTRAINT [FK_KEHOACH_NKHK]
GO
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK__KHANANG__MAMONHO__33D4B598]') AND parent_object_id = OBJECT_ID(N'[dbo].[KHANANG]'))
ALTER TABLE [dbo].[KHANANG]  WITH CHECK ADD  CONSTRAINT [FK__KHANANG__MAMONHO__33D4B598] FOREIGN KEY([MAMH])
REFERENCES [dbo].[MONHOC] ([MAMH])
ON UPDATE CASCADE
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK__KHANANG__MAMONHO__33D4B598]') AND parent_object_id = OBJECT_ID(N'[dbo].[KHANANG]'))
ALTER TABLE [dbo].[KHANANG] CHECK CONSTRAINT [FK__KHANANG__MAMONHO__33D4B598]
GO
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_KHANANG_GIANGVIEN]') AND parent_object_id = OBJECT_ID(N'[dbo].[KHANANG]'))
ALTER TABLE [dbo].[KHANANG]  WITH CHECK ADD  CONSTRAINT [FK_KHANANG_GIANGVIEN] FOREIGN KEY([MAGV])
REFERENCES [dbo].[GIANGVIEN] ([MAGV])
ON UPDATE CASCADE
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_KHANANG_GIANGVIEN]') AND parent_object_id = OBJECT_ID(N'[dbo].[KHANANG]'))
ALTER TABLE [dbo].[KHANANG] CHECK CONSTRAINT [FK_KHANANG_GIANGVIEN]
GO
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_KHOA_GIANGVIEN1]') AND parent_object_id = OBJECT_ID(N'[dbo].[KHOA]'))
ALTER TABLE [dbo].[KHOA]  WITH CHECK ADD  CONSTRAINT [FK_KHOA_GIANGVIEN1] FOREIGN KEY([MATRUONGKHOA])
REFERENCES [dbo].[GIANGVIEN] ([MAGV])
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_KHOA_GIANGVIEN1]') AND parent_object_id = OBJECT_ID(N'[dbo].[KHOA]'))
ALTER TABLE [dbo].[KHOA] CHECK CONSTRAINT [FK_KHOA_GIANGVIEN1]
GO
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK__LOP__MAKHOA__3B75D760]') AND parent_object_id = OBJECT_ID(N'[dbo].[LOP]'))
ALTER TABLE [dbo].[LOP]  WITH CHECK ADD  CONSTRAINT [FK__LOP__MAKHOA__3B75D760] FOREIGN KEY([MAKHOA])
REFERENCES [dbo].[KHOA] ([MAKHOA])
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK__LOP__MAKHOA__3B75D760]') AND parent_object_id = OBJECT_ID(N'[dbo].[LOP]'))
ALTER TABLE [dbo].[LOP] CHECK CONSTRAINT [FK__LOP__MAKHOA__3B75D760]
GO
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK__LOP__MAKHOA__3C69FB99]') AND parent_object_id = OBJECT_ID(N'[dbo].[LOP]'))
ALTER TABLE [dbo].[LOP]  WITH CHECK ADD  CONSTRAINT [FK__LOP__MAKHOA__3C69FB99] FOREIGN KEY([MAKHOA])
REFERENCES [dbo].[KHOA] ([MAKHOA])
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK__LOP__MAKHOA__3C69FB99]') AND parent_object_id = OBJECT_ID(N'[dbo].[LOP]'))
ALTER TABLE [dbo].[LOP] CHECK CONSTRAINT [FK__LOP__MAKHOA__3C69FB99]
GO
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK__LOP__MAKHOA__3D5E1FD2]') AND parent_object_id = OBJECT_ID(N'[dbo].[LOP]'))
ALTER TABLE [dbo].[LOP]  WITH CHECK ADD  CONSTRAINT [FK__LOP__MAKHOA__3D5E1FD2] FOREIGN KEY([MAKHOA])
REFERENCES [dbo].[KHOA] ([MAKHOA])
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK__LOP__MAKHOA__3D5E1FD2]') AND parent_object_id = OBJECT_ID(N'[dbo].[LOP]'))
ALTER TABLE [dbo].[LOP] CHECK CONSTRAINT [FK__LOP__MAKHOA__3D5E1FD2]
GO
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK__LOPTINCHI__MAMH__3E52440B]') AND parent_object_id = OBJECT_ID(N'[dbo].[LOPTINCHI]'))
ALTER TABLE [dbo].[LOPTINCHI]  WITH CHECK ADD  CONSTRAINT [FK__LOPTINCHI__MAMH__3E52440B] FOREIGN KEY([MAMH])
REFERENCES [dbo].[MONHOC] ([MAMH])
ON UPDATE CASCADE
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK__LOPTINCHI__MAMH__3E52440B]') AND parent_object_id = OBJECT_ID(N'[dbo].[LOPTINCHI]'))
ALTER TABLE [dbo].[LOPTINCHI] CHECK CONSTRAINT [FK__LOPTINCHI__MAMH__3E52440B]
GO
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_LOPTINCHI_KHOA]') AND parent_object_id = OBJECT_ID(N'[dbo].[LOPTINCHI]'))
ALTER TABLE [dbo].[LOPTINCHI]  WITH CHECK ADD  CONSTRAINT [FK_LOPTINCHI_KHOA] FOREIGN KEY([MAKHOA])
REFERENCES [dbo].[KHOA] ([MAKHOA])
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_LOPTINCHI_KHOA]') AND parent_object_id = OBJECT_ID(N'[dbo].[LOPTINCHI]'))
ALTER TABLE [dbo].[LOPTINCHI] CHECK CONSTRAINT [FK_LOPTINCHI_KHOA]
GO
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_LOPTINCHI_NKHK]') AND parent_object_id = OBJECT_ID(N'[dbo].[LOPTINCHI]'))
ALTER TABLE [dbo].[LOPTINCHI]  WITH CHECK ADD  CONSTRAINT [FK_LOPTINCHI_NKHK] FOREIGN KEY([MANKHK])
REFERENCES [dbo].[NKHK] ([MANKHK])
ON UPDATE CASCADE
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_LOPTINCHI_NKHK]') AND parent_object_id = OBJECT_ID(N'[dbo].[LOPTINCHI]'))
ALTER TABLE [dbo].[LOPTINCHI] CHECK CONSTRAINT [FK_LOPTINCHI_NKHK]
GO
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_NKHK_NHANVIEN]') AND parent_object_id = OBJECT_ID(N'[dbo].[NKHK]'))
ALTER TABLE [dbo].[NKHK]  WITH CHECK ADD  CONSTRAINT [FK_NKHK_NHANVIEN] FOREIGN KEY([MANV])
REFERENCES [dbo].[NHANVIEN] ([MANV])
ON UPDATE CASCADE
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_NKHK_NHANVIEN]') AND parent_object_id = OBJECT_ID(N'[dbo].[NKHK]'))
ALTER TABLE [dbo].[NKHK] CHECK CONSTRAINT [FK_NKHK_NHANVIEN]
GO
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK__SINHVIEN__MACN__440B1D61]') AND parent_object_id = OBJECT_ID(N'[dbo].[SINHVIEN]'))
ALTER TABLE [dbo].[SINHVIEN]  WITH CHECK ADD  CONSTRAINT [FK__SINHVIEN__MACN__440B1D61] FOREIGN KEY([MACN])
REFERENCES [dbo].[CHUYENNGANH] ([MACN])
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK__SINHVIEN__MACN__440B1D61]') AND parent_object_id = OBJECT_ID(N'[dbo].[SINHVIEN]'))
ALTER TABLE [dbo].[SINHVIEN] CHECK CONSTRAINT [FK__SINHVIEN__MACN__440B1D61]
GO
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK__SINHVIEN__MALOP__45F365D3]') AND parent_object_id = OBJECT_ID(N'[dbo].[SINHVIEN]'))
ALTER TABLE [dbo].[SINHVIEN]  WITH CHECK ADD  CONSTRAINT [FK__SINHVIEN__MALOP__45F365D3] FOREIGN KEY([MALOP])
REFERENCES [dbo].[LOP] ([MALOP])
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK__SINHVIEN__MALOP__45F365D3]') AND parent_object_id = OBJECT_ID(N'[dbo].[SINHVIEN]'))
ALTER TABLE [dbo].[SINHVIEN] CHECK CONSTRAINT [FK__SINHVIEN__MALOP__45F365D3]
GO
IF NOT EXISTS (SELECT * FROM sys.check_constraints WHERE object_id = OBJECT_ID(N'[dbo].[CK_DANGKY]') AND parent_object_id = OBJECT_ID(N'[dbo].[DANGKY]'))
ALTER TABLE [dbo].[DANGKY]  WITH CHECK ADD  CONSTRAINT [CK_DANGKY] CHECK  (([DIEM_CC]>=(0) AND [DIEM_CC]<=(10) AND ([DIEM_GK]>=(0) AND [DIEM_GK]<=(10)) AND ([DIEM_CK]>=(0) AND [DIEM_CK]<=(10))))
GO
IF  EXISTS (SELECT * FROM sys.check_constraints WHERE object_id = OBJECT_ID(N'[dbo].[CK_DANGKY]') AND parent_object_id = OBJECT_ID(N'[dbo].[DANGKY]'))
ALTER TABLE [dbo].[DANGKY] CHECK CONSTRAINT [CK_DANGKY]
GO
IF NOT EXISTS (SELECT * FROM sys.check_constraints WHERE object_id = OBJECT_ID(N'[dbo].[CK_LOP]') AND parent_object_id = OBJECT_ID(N'[dbo].[LOP]'))
ALTER TABLE [dbo].[LOP]  WITH CHECK ADD  CONSTRAINT [CK_LOP] CHECK  (([KHOAHOC]>=(1997)))
GO
IF  EXISTS (SELECT * FROM sys.check_constraints WHERE object_id = OBJECT_ID(N'[dbo].[CK_LOP]') AND parent_object_id = OBJECT_ID(N'[dbo].[LOP]'))
ALTER TABLE [dbo].[LOP] CHECK CONSTRAINT [CK_LOP]
GO
IF NOT EXISTS (SELECT * FROM sys.check_constraints WHERE object_id = OBJECT_ID(N'[dbo].[CK_LOPTINCHI]') AND parent_object_id = OBJECT_ID(N'[dbo].[LOPTINCHI]'))
ALTER TABLE [dbo].[LOPTINCHI]  WITH CHECK ADD  CONSTRAINT [CK_LOPTINCHI] CHECK  (([SOSVTOITHIEU]>(0) AND [NHOM]>(0)))
GO
IF  EXISTS (SELECT * FROM sys.check_constraints WHERE object_id = OBJECT_ID(N'[dbo].[CK_LOPTINCHI]') AND parent_object_id = OBJECT_ID(N'[dbo].[LOPTINCHI]'))
ALTER TABLE [dbo].[LOPTINCHI] CHECK CONSTRAINT [CK_LOPTINCHI]
GO
IF NOT EXISTS (SELECT * FROM sys.check_constraints WHERE object_id = OBJECT_ID(N'[dbo].[CK_MONHOC]') AND parent_object_id = OBJECT_ID(N'[dbo].[MONHOC]'))
ALTER TABLE [dbo].[MONHOC]  WITH CHECK ADD  CONSTRAINT [CK_MONHOC] CHECK  (([SOTIETTH]>=(0) AND [SOTIETLT]>=(0) AND ([SOTIETLT]+[SOTIETTH])>(0)))
GO
IF  EXISTS (SELECT * FROM sys.check_constraints WHERE object_id = OBJECT_ID(N'[dbo].[CK_MONHOC]') AND parent_object_id = OBJECT_ID(N'[dbo].[MONHOC]'))
ALTER TABLE [dbo].[MONHOC] CHECK CONSTRAINT [CK_MONHOC]
GO
IF NOT EXISTS (SELECT * FROM sys.check_constraints WHERE object_id = OBJECT_ID(N'[dbo].[CK_NGAYDONGDK_GREATER_THAN_NGAYMODK]') AND parent_object_id = OBJECT_ID(N'[dbo].[NKHK]'))
ALTER TABLE [dbo].[NKHK]  WITH CHECK ADD  CONSTRAINT [CK_NGAYDONGDK_GREATER_THAN_NGAYMODK] CHECK  (([NGAYDONGDK]>[NGAYMODK]))
GO
IF  EXISTS (SELECT * FROM sys.check_constraints WHERE object_id = OBJECT_ID(N'[dbo].[CK_NGAYDONGDK_GREATER_THAN_NGAYMODK]') AND parent_object_id = OBJECT_ID(N'[dbo].[NKHK]'))
ALTER TABLE [dbo].[NKHK] CHECK CONSTRAINT [CK_NGAYDONGDK_GREATER_THAN_NGAYMODK]
GO
IF NOT EXISTS (SELECT * FROM sys.check_constraints WHERE object_id = OBJECT_ID(N'[dbo].[CK_THUBUOI]') AND parent_object_id = OBJECT_ID(N'[dbo].[THUBUOI]'))
ALTER TABLE [dbo].[THUBUOI]  WITH CHECK ADD  CONSTRAINT [CK_THUBUOI] CHECK  (([THU]>=(2) AND [THU]<=(8) AND ([BUOI]>=(1) AND [BUOI]<=(3))))
GO
IF  EXISTS (SELECT * FROM sys.check_constraints WHERE object_id = OBJECT_ID(N'[dbo].[CK_THUBUOI]') AND parent_object_id = OBJECT_ID(N'[dbo].[THUBUOI]'))
ALTER TABLE [dbo].[THUBUOI] CHECK CONSTRAINT [CK_THUBUOI]
GO
USE [master]
GO
ALTER DATABASE [QLSVTC] SET  READ_WRITE 
GO
