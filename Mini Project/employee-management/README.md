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

