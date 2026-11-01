# Luyện tập Java cơ bản

Các bài tập ôn Java Core, sử dụng kế thừa, lớp trừu tượng, mảng,
danh sách và xử lý ngoại lệ.

- `Practice1`: tính diện tích, chu vi hình chữ nhật và hình tròn
  với các lớp `Shape`, `Rectangle`, `Circle`.
- `Practice2`: quản lý thực phẩm, điện máy, sành sứ bằng mảng `Goods[100]`;
  thống kê tồn kho, thuế VAT và đánh giá khả năng bán.
- `Practice3`: quản lý ô tô, xe máy, xe tải và chủ xe bằng `ArrayList`;
  thêm, tìm kiếm, xóa theo hãng, thống kê và sắp xếp hãng theo số xe giảm dần.

## Biên dịch và chạy

Cần JDK 8 trở lên, có `java` và `javac` trong biến môi trường `PATH`.
Mở cửa sổ lệnh tại từng thư mục bài tập và chạy riêng từng bài:

```powershell
chcp 65001
javac -encoding UTF-8 *.java
java Main
```

Lưu mã nguồn bằng UTF-8. Lệnh `chcp 65001` giúp cửa sổ lệnh Windows
hiển thị tiếng Việt. Nhập `0` để thoát các bài có bảng chọn.
Nhập ngày theo `yyyy-MM-dd`, dùng dấu chấm cho số thập phân.
Các tệp `.class` và thư mục biên dịch được bỏ qua bởi `.gitignore`.
