# Java Training

## About
Learning Git and GitHub.

## Java Core Practices

- `Practice1`: Shape, Rectangle, Circle; tinh dien tich va chu vi.
- `Practice2`: quan ly Food, Electronics, Crockery bang mang `Goods[100]`;
  thong ke ton kho, VAT va danh gia kha nang ban.
- `Practice3`: quan ly Car, Motorcycle, Truck va Owner bang `ArrayList`;
  them, tim kiem, xoa theo hang, thong ke va sap xep hang theo so xe giam dan.

### Bien dich va chay

Can JDK 8 tro len va ca `java`, `javac` trong PATH. Mo terminal tai tung
thu muc Practice, chay rieng tung bai vi ca ba deu co class `Main`:

```powershell
javac -encoding UTF-8 *.java
java Main
```

Practice2 va Practice3 co menu; nhap `0` de thoat. Neu stdin ket thuc,
chuong trinh cung thoat an toan. Ngay nhap theo `yyyy-MM-dd`; so thap phan
dung dau cham. File `.class` va thu muc build duoc bo qua boi `.gitignore`.

### Quy tac trong bai tap

- Kich thuoc hinh phai > 0. Cac so thuc phai huu han.
- Hang hoa: ma khong trung (khong phan biet hoa/thuong), so luong va don gia
  >= 0; han su dung >= ngay san xuat; bao hanh va cong suat >= 0.
- VAT tren gia tri ton kho: Food 5%, Electronics va Crockery 10%.
  Day la muc VAT theo bai tap.
- Food kho ban khi con hang va da qua han; Electronics duoc coi la da ban
  khi ton < 3; Crockery ban cham khi ton > 50 va luu kho > 10 ngay.
- Bien so dung 5 ky tu; hang Honda/Yamaha/Toyota/Suzuki; nam san xuat
  > 2000 va <= nam hien tai; CCCD dung 12 chu so; email dung dinh dang.
- Mot chu xe co the so huu nhieu xe; cung CCCD phai cung ten va email.
  So cho > 0; dung tich va trong tai >= 0. Khong them trung bien so.
