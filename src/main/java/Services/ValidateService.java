package Services;

import Services.Interfaces.IValidateService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;


// validateService dung de chua cac phuong thuc de kiem tra va xac thuc du lieu input tu user (lop singleton, chi ton tai 1 ínstance duy nhat
public class ValidateService implements IValidateService {
    public static ValidateService instance; //instance duy nhat

    public ValidateService() {
    } //constructor mac dinh

    //getInstance su dung singleton de dam bao only instance, neu instance chua dc khoi tao -> tao ra 1 instance ms
    //Nếu instance chưa được khởi tạo, nó sẽ tạo một instance mới.
    public static ValidateService getInstance() {
        if (instance == null) {
            instance = new ValidateService();
        }
        return instance;
    }

    public Scanner sc = new Scanner(System.in);

    public int inputInt(String mess, int min, int max) { //inputInt nhan dau vao la 1 so nguyen trong khoang min max
        System.out.print(mess);
        while (true) {
            String input = sc.nextLine();
            try {
                int number = Integer.parseInt(input);
                //check range of number
                if (number < min || number > max) {
                    System.out.print("Please input between " + min + ", " + max + ": ");
                    continue;
                }
                return number;
            } catch (Exception e) {
                System.out.print("Please input an integer number: ");
            }
        }
    }

    public double inputDouble(String mess) {
        System.out.print(mess);

        while (true) {
            String input = sc.nextLine();
            try {
                double number = Double.parseDouble(input);
                double min = 1;
                double max = 1000;
                if (number < min || number > max) {
                    System.out.print("Please input between " + min + ", " + max + ": ");
                    continue;
                }
                return number;
            } catch (Exception e) {
                System.out.print("Please input an double number: ");
            }
        }
    }

    public String inputString(String mess /*thong bao hien thi cho user*/, String regex) { //nhan 1 chuoi hop le tu nguoi dung, dua vao regex
        if (regex == null || regex.isEmpty()) {
            regex = "[A-Za-z0-9\\s_-]+"; //mac dinh regex cho so, chu cai, va khoang trang (regex: bthuc chinh quy de kiem tra chuoi dau vao)
        }

        System.out.print(mess);
        while (true) {
            String input = sc.nextLine();
            if (!input.matches(regex)) {
                System.out.print("Please input match regex: " + regex);
                continue;
            }
            if (input.trim().isEmpty()) {
                System.out.print("Please input a non-empty string: ");
                continue;
            }
            return input;
        }
    }

    public String inputDate(String mess) {
        System.out.print(mess);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);

        while (true) {
            String input = sc.nextLine();
            try {
                Date date = dateFormat.parse(input);
                Date curDate = Calendar.getInstance().getTime();
                if (curDate.compareTo(date) < 0) {
                    System.out.print("Please input date that before current date: ");
                    continue;
                }
                dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
                return dateFormat.format(date);
            } catch (Exception e) {
                System.out.print("Please input valid date (dd-MM-yyyy): ");
            }
        }
    }

    public Boolean inputYesNo(String mess) { //nhan cau tra loi Y/N tu user
        while (true) {
            String input = inputString(mess+"(Y/N)",null);
            if (input.equalsIgnoreCase("y")) { //true se y/Y
                return true;
            }
            if (input.equalsIgnoreCase("n")) { //false se n/N
                return false;
            }
        }
    }

}