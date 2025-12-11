# Comic App - README

## Giới thiệu
Comic App là một ứng dụng đọc truyện, nơi bạn có thể tìm kiếm và đọc các truyện yêu thích một cách dễ dàng. Ứng dụng bao gồm hai thành phần chính:
- **Backend**: Xử lý logic, lưu trữ dữ liệu và cung cấp API.
- **Client**: Ứng dụng Android Native để người dùng truy cập và sử dụng dịch vụ.

## Demo
<img width="516" height="1149" alt="image" src="https://github.com/user-attachments/assets/25cf7380-8cdd-4245-b1b0-31651b86b29f" />

<img width="516" height="1149" alt="image" src="https://github.com/user-attachments/assets/6e07f735-4f09-4059-82b3-6a4893bc643b" />
<img width="516" height="1149" alt="image" src="https://github.com/user-attachments/assets/47f8929f-4620-4b1e-9e6d-f6a20a78b4ef" />
<img width="413" height="917" alt="image" src="https://github.com/user-attachments/assets/a59b2493-3c1e-415e-a9f2-6f6a6c416e4f" />

<img width="408" height="908" alt="image" src="https://github.com/user-attachments/assets/e8a1239d-0395-4f74-a586-751676cad3ab" />

<img width="408" height="908" alt="image" src="https://github.com/user-attachments/assets/3f2436c3-1419-414e-b4dc-4c35ff463c3c" />

<img width="411" height="914" alt="image" src="https://github.com/user-attachments/assets/8a62e42e-b985-415f-86ea-422660d67fa2" />
<img width="412" height="916" alt="image" src="https://github.com/user-attachments/assets/a9cb665b-72b8-4c57-a463-246233e457e5" />
<img width="483" height="1073" alt="image" src="https://github.com/user-attachments/assets/29eac9e8-7853-403c-9fa1-c9e9aa0a45ff" />

<img width="402" height="893" alt="image" src="https://github.com/user-attachments/assets/febe579d-cad5-45b2-8f6e-2b5be9ad7d55" />
<img width="420" height="934" alt="image" src="https://github.com/user-attachments/assets/cf49f804-d788-410a-b927-026ac4188d2a" />

<img width="390" height="867" alt="image" src="https://github.com/user-attachments/assets/cbbdbdcd-5306-4053-8883-0eb30693b4fd" />
<img width="389" height="864" alt="image" src="https://github.com/user-attachments/assets/c37747a3-fa4b-4396-97c7-431c79473173" />


<img width="402" height="893" alt="image" src="https://github.com/user-attachments/assets/ceefd435-678b-4564-b502-1646e43d29fa" />

<img width="407" height="905" alt="image" src="https://github.com/user-attachments/assets/d46e213e-71b9-4758-b256-bdb0a8ed79bd" />

<img width="497" height="1105" alt="image" src="https://github.com/user-attachments/assets/2453d57b-614f-4d17-8d3e-903bfa8a4b16" />

<img width="489" height="1088" alt="image" src="https://github.com/user-attachments/assets/ff0e4426-b0f4-46a6-85b5-61e634a53a55" />


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

