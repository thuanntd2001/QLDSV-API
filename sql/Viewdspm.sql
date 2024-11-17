CREATE VIEW view_DanhSachPhanManh
AS
	SELECT TENKHOA=PUBS.description, TENSERVER=SUBS.subscriber_server
	 FROM sysmergepublications  PUBS, sysmergesubscriptions SUBS
 	WHERE PUBS.pubid = SUBS.pubid AND  publisher <> subscriber_server
GO