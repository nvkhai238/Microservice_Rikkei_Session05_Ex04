# Session 05 - Bài 4: Post-Filter thêm response header

Gateway đăng ký với Eureka và dùng URI `lb://CUSTOMER-SERVICE`, vì vậy không
phụ thuộc cổng thực tế của `customer-service`.

`LoggingFilter` là `GlobalFilter` có `@Component`; mỗi request sẽ ghi thời gian
và path theo mẫu `Incoming request at ... to: /api/v1/customers/1`, sau đó tiếp
tục chuỗi xử lý bằng `chain.filter(exchange)`.

Header `X-System-Name: Api-Gateway-System` được triển khai đủ hai cách:

- `AddResponseHeader` trong `application.properties`.
- `SystemNameHeaderFilter` bằng code và `beforeCommit`.

Kiểm tra bằng `curl -i http://localhost:8080/api/v1/customers/1`.

## Thứ tự chạy

1. Eureka Server (`8761`).
2. `customer-service` có `spring.application.name=CUSTOMER-SERVICE`.
3. `api-gateway` (`8080`).

```bash
cd api-gateway
gradle bootRun
curl http://localhost:8080/api/v1/customers/1
```
