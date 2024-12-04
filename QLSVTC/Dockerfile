# Sử dụng image mở rộng của Java 11
FROM openjdk:11

# Thêm các biến môi trường cho ứng dụng
ENV APP_HOME=/app
ENV APP_NAME=QLSVTC-0.0.1-SNAPSHOT


# Tạo thư mục ứng dụng và sao chép các tệp vào đó
RUN mkdir $APP_HOME
COPY target/$APP_NAME.jar $APP_HOME

# Thêm các biến su dung ứng dụng

ENV DBUSER_CHU= sa
ENV DBPASS_CHU=1234

ENV DBUSER_CNTT=sa
ENV DBPASS_CNTT=1234

ENV DBUSER_VT=sa
ENV DBPASS_VT=1234

ENV DBCHU_URL=jdbc:sqlserver://192.168.100.16\\MSSQLSERVER0; Database=QLSVTC
ENV DBCNTT_URL=jdbc:sqlserver://192.168.100.16\\MSSQLSERVER1; Database=QLSVTC
ENV DBVT_URL=jdbc:sqlserver://192.168.100.16\\MSSQLSERVER2; Database=QLSVTC

# Thiết lập thư mục làm việc và cổng mặc định của ứng dụng
WORKDIR $APP_HOME
EXPOSE 9090
# Chạy ứng dụng khi container được khởi động
CMD java -jar $APP_NAME.jar

