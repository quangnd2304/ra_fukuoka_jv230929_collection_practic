package ra.run;

import ra.entity.IStudentManagement;
import ra.entity.Student;
import ra.entity.StudentClass;

import java.util.*;

public class StudentManagement {
    //Khai báo 2 danh sách chứa lớp và sinh viên mà chúng ta quản lý
    public static List<StudentClass> listClass = new ArrayList<>();
    public static List<Student> listStudents = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("***************QUẢN LÝ HỌC VIỆN***************");
            System.out.println("1. Quản lý lớp");
            System.out.println("2. Quản lý sinh viên");
            System.out.println("3. Thoát");
            System.out.print("Lựa chọn của bạn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    StudentManagement.displayMenuClass(scanner);
                    break;
                case 2:
                    StudentManagement.displayMenuStudent(scanner);
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.err.println("Vui lòng chọn từ 1-3");
            }
        } while (true);
    }

    public static void displayMenuClass(Scanner scanner) {
        boolean isExist = true;
        do {
            System.out.println("*************QUẢN LÝ LỚP****************");
            System.out.println("1. Thêm mới lớp học");
            System.out.println("2. Cập nhật thông tin lớp học");
            System.out.println("3. Hiển thị thông tin lớp học");
            System.out.println("4. Thống kê các lớp hoc đang hoạt động");
            System.out.println("5. Tìm kiếm lớp học theo tên lớp học");
            System.out.println("6. Thoát");
            System.out.print("Lựa chọn của bạn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    StudentManagement.inputListClass(scanner);
                    break;
                case 2:
                    StudentManagement.updateClass(scanner);
                    break;
                case 3:
                    StudentManagement.displayListData();
                    break;
                case 4:
                    StudentManagement.staticticClassActive();
                    break;
                case 5:
                    StudentManagement.searchClassByName(scanner);
                    break;
                case 6:
                    isExist = false;
                    break;
                default:
                    System.err.println("Vui lòng chọn từ 1-6");
            }
        } while (isExist);
    }

    public static void inputListClass(Scanner scanner) {
        System.out.println("Nhập vào số lớp cần nhập thông tin:");
        int numberOfClass = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < numberOfClass; i++) {
            StudentClass stClass = new StudentClass();
            stClass.inputData(scanner);
            listClass.add(stClass);
        }
    }

    public static void updateClass(Scanner scanner) {
        System.out.println("Nhập vào mã lớp học cần cập nhật thông tin:");
        String classIdUpdate = scanner.nextLine();
        int indexUpdate = getIndexByClassId(classIdUpdate);
        if (indexUpdate >= 0) {
            //Tìm thấy lớp cần cập nhật --> tiến hành cập nhật
            listClass.get(indexUpdate).inputClassName(scanner);
            listClass.get(indexUpdate).inputDescription(scanner);
            listClass.get(indexUpdate).inputStatus(scanner);
            System.out.println("Đã cập nhật xong thông tin lớp " + classIdUpdate);
        } else {
            //Không tìm thấy lớp
            System.err.printf("Lớp %s không tìm thấy, vui lòng nhập lại\n", classIdUpdate);
        }
    }

    //Lấy chỉ số phần tử lớp học theo mã lớp
    public static int getIndexByClassId(String classId) {
        for (int i = 0; i < listClass.size(); i++) {
            if (listClass.get(i).getClassId().equals(classId)) {
                return i;
            }
        }
        //Không tìm thấy mã lớp trong listClass
        return -1;
    }

    public static void displayListData() {
        for (StudentClass stClass : listClass) {
            stClass.displayData();
        }
    }

    public static void staticticClassActive() {
        int cntClass = 0;
        for (StudentClass stClass : listClass) {
            if (stClass.isClassStatus() == 1) {
                cntClass++;
            }
        }
        System.out.printf("Có %d lớp đang hoạt động\n", cntClass);
    }

    public static void searchClassByName(Scanner scanner) {
        System.out.println("Nhập vào tên lớp cần tìm:");
        String className = scanner.nextLine();
        int cntClass = 0;
        for (StudentClass stClass : listClass) {
            if (stClass.getClassName().toLowerCase().contains(className.toLowerCase())) {
                stClass.displayData();
                cntClass++;
            }
        }
        System.out.printf("Tìm thấy %d lớp có chứa %s\n", cntClass, className);
    }

    public static void displayMenuStudent(Scanner scanner) {
        boolean isExist = true;
        do {
            System.out.println("***************QUẢN LÝ SINH VIÊN****************");
            System.out.println("1. Thêm mới sinh viên");
            System.out.println("2. Cập nhật thông tin sinh viên");
            System.out.println("3. Hiển thị thông tin sinh viên");
            System.out.println("4. Tính điểm trung bình");
            System.out.println("5. Xếp loại sinh viên");
            System.out.println("6. Sắp xếp sinh viên theo điểm trung bình tăng dần");
            System.out.println("7. Tìm kiếm sinh viên theo tên sinh viên");
            System.out.println("8. Thống kê số sinh viên đạt loại giỏi, khá, trung bình, yếu");
            System.out.println("9. Thống kê sinh viên pass qua môn học");
            System.out.println("10. Thoát");
            System.out.print("Lựa chọn của bạn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    StudentManagement.inputListStudent(scanner);
                    break;
                case 2:
                    StudentManagement.updateStudent(scanner);
                    break;
                case 3:
                    StudentManagement.displayListStudent();
                    break;
                case 4:
                    StudentManagement.calAvgListMark();
                    break;
                case 5:
                    StudentManagement.calListGPA();
                    break;
                case 6:
                    StudentManagement.sortStudentByMark();
                    break;
                case 7:
                    StudentManagement.searchStudentByName(scanner);
                    break;
                case 8:
                    StudentManagement.staticticStudentByGPA();
                    break;
                case 9:
                    StudentManagement.staticticStudentPass();
                    break;
                case 10:
                    isExist = false;
                    break;
                default:
                    System.err.println("Vui lòng nhập từ 1-10");
            }
        } while (isExist);
    }

    public static void inputListStudent(Scanner scanner) {
        System.out.println("Nhập vào số sinh viên cần nhập thông tin:");
        int numberOfStudents = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < numberOfStudents; i++) {
            Student student = new Student();
            student.inputData(scanner);
            listStudents.add(student);
        }
    }

    public static void updateStudent(Scanner scanner) {
        System.out.println("Nhập vào mã sinh viên cần cập nhật:");
        String studentIdUpdate = scanner.nextLine();
        int indexUpdate = getIndexByStudentId(studentIdUpdate);
        if (indexUpdate >= 0) {
            listStudents.get(indexUpdate).inputStudentName(scanner);
            listStudents.get(indexUpdate).inputAge(scanner);
            listStudents.get(indexUpdate).inputSex(scanner);
            listStudents.get(indexUpdate).inputClass(scanner);
            listStudents.get(indexUpdate).inputMark(scanner, "Javascript");
            listStudents.get(indexUpdate).inputMark(scanner, "Java Core");
            listStudents.get(indexUpdate).inputMark(scanner, "Java Web");
            listStudents.get(indexUpdate).inputStatus(scanner);
            System.out.println("Cập nhật xong thông tinh sinh viên");
        } else {
            System.err.println("Không tìm thấy mã sinh viên " + studentIdUpdate);
        }
    }

    public static int getIndexByStudentId(String studentId) {
        for (int i = 0; i < listStudents.size(); i++) {
            if (listStudents.get(i).getStudentId().equals(studentId)) {
                return i;
            }
        }
        return -1;
    }

    public static void displayListStudent() {
        listStudents.forEach(student -> student.displayData());
    }

    public static void calAvgListMark() {
        listStudents.forEach(student -> student.calAvgMark());
    }

    public static void calListGPA() {
        listStudents.forEach(student -> student.getGPAByAvgMark());
    }

    public static void sortStudentByMark() {
        Collections.sort(listStudents, new Comparator<Student>() {
            @Override
            public int compare(Student student, Student t1) {
                if (student.getAvgMark() > t1.getAvgMark()) {
                    return 1;
                } else if (student.getAvgMark() == t1.getAvgMark()) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });
    }

    public static void searchStudentByName(Scanner scanner) {
        System.out.println("Nhập vào tên sinh viên cần tìm:");
        String studentName = scanner.nextLine();
        int cntStudent = 0;
        for (Student student : listStudents) {
            if (student.getStudentName().toLowerCase().contains(studentName.toLowerCase())) {
                student.displayData();
                cntStudent++;
            }
        }
        System.out.printf("Tìm thấy %d sinh viên có tên chứa %s\n", cntStudent, studentName);
    }

    public static void staticticStudentByGPA() {
        int numberOfWeak = 0;
        int numberOfAvange = 0;
        int numberOfNormal = 0;
        int numberOfGood = 0;
        for (Student student : listStudents) {
            switch (student.getGpa()) {
                case "Yếu":
                    numberOfWeak++;
                    break;
                case "Trung bình":
                    numberOfAvange++;
                    break;
                case "Khá":
                    numberOfNormal++;
                    break;
                case "Giỏi":
                    numberOfGood++;
                    break;
            }
        }
        System.out.printf("Giỏi: %d - Khá: %d - Trung bình: %d - Yếu: %d\n",
                numberOfGood, numberOfNormal, numberOfAvange, numberOfWeak);
    }

    public static void staticticStudentPass() {
        int numberOfPass = 0;
        for (Student student : listStudents) {
            if (student.getAvgMark() >= IStudentManagement.MARK_PASS) {
                numberOfPass++;
            }
        }
        System.out.printf("Có %d sinh viên Pass\n", numberOfPass);
    }
}
