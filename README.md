# Comic App

## Giới thiệu
Comic App là một ứng dụng đọc truyện, nơi bạn có thể tìm kiếm và đọc các truyện yêu thích một cách dễ dàng. Ứng dụng bao gồm hai thành phần chính:
- **Backend**: Xử lý logic, lưu trữ dữ liệu và cung cấp API.
- **Client**: Ứng dụng Android Native để người dùng truy cập và sử dụng dịch vụ.

- [Backend Repo](https://github.com/sontungexpt/comic)

## Demo


[Watch the demo](https://drive.google.com/file/d/1IHzQCcBRxD4eooPkdMzu3eKpTlJUhJKa/view)


## Hướng dẫn cài đặt project

### Cài đặt Backend và Client

```bash
# Clone repository Backend
git clone https://github.com/sontungexpt/comic.git

# Cài đặt Redis trên Ubuntu
sudo apt update
sudo apt install redis

# Hoặc trên Windows, tải Redis từ https://github.com/microsoftarchive/redis/releases và cài đặt

# Kiểm tra Redis đã cài đặt thành công
redis-server

# Tải và cài đặt Ngrok từ https://ngrok.com/download
# Sau đó thiết lập Ngrok với port 8080
ngrok http 8080

# Ghi lại URL công khai từ Ngrok, ví dụ: https://abc123.ngrok.io

# Di chuyển đến thư mục dự án backend
cd comic

# Chạy Backend với Maven và Java 17
mvn spring-boot:run

# Clone repository Client
cd .. # Trở lại thư mục gốc nếu cần
git clone https://github.com/sontungexpt/comic-native-android-client.git

# Chỉnh sửa BASE_URL trong file
# com/comic/android_native_client/network/constants/MainEndpoint
# Thay đổi BASE_URL thành URL từ Ngrok thêm /api/
# Ví dụ: https://abc123.ngrok.io/api/

# Mở Android Studio, import dự án client và chạy ứng dụng
```

## Liên hệ
Nếu gặp bất kỳ vấn đề gì, vui lòng liên hệ qua GitHub: [sontungexpt](https://github.com/sontungexpt).

