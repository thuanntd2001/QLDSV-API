# Sử dụng image mở rộng của Java 11
FROM openjdk:11

# Thêm các biến môi trường cho ứng dụng
ENV APP_HOME=/app
ENV APP_NAME=QLSVTC-0.0.1-SNAPSHOT


# Tạo thư mục ứng dụng và sao chép các tệp vào đó
RUN mkdir $APP_HOME
COPY target/$APP_NAME.jar $APP_HOME


# Thiết lập thư mục làm việc và cổng mặc định của ứng dụng
WORKDIR $APP_HOME
EXPOSE 9090
EXPOSE 8080

# Chạy ứng dụng khi container được khởi động
CMD java -jar $APP_NAME.jar --spring.config.location=file:///D:/app.properties

