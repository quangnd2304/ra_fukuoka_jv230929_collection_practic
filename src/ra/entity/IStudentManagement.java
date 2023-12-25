package ra.entity;

import java.util.Scanner;

public interface IStudentManagement {
    //casting: implicit - explicit
    /*
    *  implicit - ngầm định --> mặc định sẽ convert kiểu dữ liệu từ thấp lên cao
    *  explicit - tường minh --> Người lập trình phải tự ép kiểu --> convert từ cao xuống thấp
    * */
    //5: int - MARK_PASS: float
    float MARK_PASS = 5;
    //Thiết kế phương thức nhập dữ liệu cho các đối tượng
    void inputData(Scanner scanner);
    //Thiết kế phương thức hiển thị thông tin của đối tượng
    void displayData();
}
