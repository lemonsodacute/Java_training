# Employee Management System

Mini project quản lý nhân viên bằng Spring Boot.

## Công nghệ
- Java 21, Spring Boot 3.5.16, Maven
- MySQL (Docker), Spring Data JPA
- Spring Security, Thymeleaf

## Nội dung thực hành
- **Module 1:** Hello World API.
- **Module 2:** Custom Bean, IoC, Dependency Injection.
- **Module 3:** REST API với ArrayList.
- **Module 4:** CRUD nhân viên bằng Spring Data JPA và MySQL.
- **Module 5:** Bean Validation, BindingResult và Global Exception Handling (HTTP 400/404).
- **Module 6:** Spring MVC + Thymeleaf: trang danh sách, form thêm/sửa và tìm kiếm nhân viên.
- **Module 7:** Logging SLF4J + Logback, cấu hình riêng cho dev/prod.

## Chức năng
- Đăng ký, đăng nhập, phân quyền Admin/User.
- Thêm, sửa, xóa nhân viên.
- Quản lý phòng ban.
- Tìm kiếm nhân viên theo tên và phòng ban.

## Cách chạy

Khởi động MySQL:

```powershell
docker start employee-mysql
```

Chạy Spring Boot:

```powershell
$env:DB_PASSWORD="123456"
mvn spring-boot:run
```

Truy cập: http://localhost:8080/login

**Tài khoản Admin:** `admin` / `admin123`

## Kiểm thử
Sử dụng Postman để kiểm tra các API GET, POST, PUT, DELETE.
## Module 5 - Validation & Exception Handling

- Validate `name` không để trống, `email` đúng định dạng (tối đa 150 ký tự), và `departmentId` phải có.
- API trả HTTP 400 với `{"error":"..."}` khi request sai hoặc JSON lỗi; HTTP 404 khi không tìm thấy nhân viên.
- Form Thymeleaf dùng `@Valid` và `BindingResult` để hiển thị lỗi cạnh từng trường.
- Kiểm thử API bằng `mvn test`; thử thêm nhân viên lỗi qua Postman với `POST /api/employees`.


## Module 6 - Spring MVC & Thymeleaf

- `GET /employees/list`: hiển thị danh sách nhân viên từ MySQL và tìm kiếm theo `name`, `departmentId`.
- `GET /employees/add`: form thêm nhân viên mới; `POST /employees/save`: lưu và hiển thị lỗi validation.
- Chỉ ADMIN được thêm/sửa/xóa; USER có thể xem và tìm kiếm.
- Vẫn giữ đường dẫn cũ `/employees`, `/employees/new` để tương thích với bài cũ.
- Truy cập `http://localhost:8080/employees/list` sau khi đăng nhập.


## Module 7 - Logging & Profiles

- Thao tác thêm, sửa, xóa nhân viên ghi log qua SLF4J; Logback xuất log ra console.
- `application.properties`: cấu hình dùng chung, mặc định profile `dev`.
- `application-dev.yml`: MySQL localhost, `ddl-auto=update`, SQL log bật.
- `application-prod.yml`: đọc DB từ biến môi trường bắt buộc, `ddl-auto=validate`, tắt SQL log.

Chạy môi trường DEV (PowerShell):

```powershell
$env:SPRING_PROFILES_ACTIVE="dev"
$env:DB_PASSWORD="mat_khau_mysql_cua_ban"
mvn spring-boot:run
```

Chạy PROD chỉ khi đã tạo database/schema và khai báo đầy đủ `DB_URL`,
`DB_USERNAME`, `DB_PASSWORD`, `ADMIN_USERNAME`, `ADMIN_PASSWORD`. Không dùng
tài khoản và mật khẩu demo trong môi trường thật.

Kiểm thử `mvn test`; đăng nhập Admin, thêm/sửa/xóa nhân viên và quan sát log trong terminal.

## Module 8 - Advanced Spring Boot

Đã thêm Actuator để xem health/metrics. API `GET /api/reports/employees/total` trả số nhân viên và lưu cache trong 1 phút, khi thêm hoặc xóa nhân viên sẽ xóa cache cũ. Scheduled task ghi `System running` ra console mỗi 30 giây.

## Module 9 - Spring Security Basics

Giữ đăng ký và đăng nhập qua web, User chỉ được xem còn Admin được thêm/sửa/xóa. API có thể dùng Basic Auth hoặc JWT. Gửi POST `/api/auth/login` với JSON `{"username":"admin","password":"admin123"}` để nhận accessToken, sau đó đặt header `Authorization: Bearer <token>` khi gọi API. Token có thời hạn 1 giờ. Ở môi trường dev, nếu chưa đặt `JWT_SECRET_BASE64` thì khóa được tạo mới khi chạy ứng dụng; môi trường prod bắt buộc đặt khóa Base64 dài ít nhất 32 byte.
