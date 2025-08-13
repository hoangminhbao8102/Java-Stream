# Java-Stream

Một website nhỏ minh họa **Java Stream API** bằng dữ liệu in‑memory. Không cần database.

## Yêu cầu
- JDK 17+
- Maven 3.9+

## Chạy dự án
```bash
mvn spring-boot:run
# hoặc
mvn clean package && java -jar target/java-stream-demo-1.0.0.jar
```
Truy cập: http://localhost:8080

## Các trang chính
- `/products`: lọc/sắp xếp, reduce tổng giá trị, groupingBy + counting, Collector tùy biến để tính giá trung bình.
- `/students`: groupingBy (GPA theo ngành), partitioningBy (honors), summarizingDouble.
- `/collectors`: flatMap order items, tổng số lượng theo sản phẩm (toMap merge), tổng doanh thu.
- `/parallel`: so sánh thời gian sequential vs parallel stream.

## Ghi chú học tập
- Ưu tiên **stream pipeline rõ ràng**: source → intermediate ops → terminal.
- Dùng **Collector** sẵn có trước khi tự viết `Collector.of`.
- **Parallel Stream** chỉ dùng khi workload nặng, dữ liệu đủ lớn, không chia sẻ trạng thái.

---
