# Sử dụng image mở rộng của Java 11
FROM openjdk:11

# Thêm các biến môi trường cho ứng dụng
ENV APP_HOME=/app \
    APP_NAME=QLSVTC-0.0.1-SNAPSHOT \
    DBUSER_CHU=sa \
    DBPASS_CHU=1234 \
    DBUSER_CNTT=sa \
    DBPASS_CNTT=1234 \
    DBUSER_VT=sa \
    DBPASS_VT=1234 \
    DBCHU_URL=jdbc:sqlserver://192.168.100.16\\MSSQLSERVER0;Database=QLSVTC \
    DBCNTT_URL=jdbc:sqlserver://192.168.100.16\\MSSQLSERVER1;Database=QLSVTC \
    DBVT_URL=jdbc:sqlserver://192.168.100.16\\MSSQLSERVER2;Database=QLSVTC
# Set the JVM options to enable TLS 1.0
ENV JAVA_OPTS="-Dhttps.protocols=TLSv1"

# Tạo thư mục ứng dụng và sao chép các tệp vào đó
RUN mkdir $APP_HOME
COPY target/$APP_NAME.jar $APP_HOME



# Thiết lập thư mục làm việc và cổng mặc định của ứng dụng
WORKDIR $APP_HOME
EXPOSE 9090
# Chạy ứng dụng khi container được khởi động
CMD ["sh", "-c", "java $JAVA_OPTS -jar $APP_NAME.jar"]

