package Controllers;

import Constants.FileConstants;
import Models.User;
import Services.FileService;
import Services.Interfaces.IFileService;
import Services.Interfaces.IUserService;
import Services.Interfaces.IValidateService;
import Services.UserService;
import Services.ValidateService;
import java.util.Scanner;

public class FileController {

    private final IFileService fileService;
    private final Scanner scanner = new Scanner(System.in);
    private User loggedInUser;


    public FileController() {
        this.fileService = FileService.getInstance();
    }

    void createUserFile(){
        //truoc khi tao thi dùng ham check, tra ve false thi moi tao, ko thi return
        if (!fileService.checkFile(FileConstants.USER_FILE_NAME)) {
            fileService.createFile(FileConstants.USER_FILE_NAME);
            System.out.println("File Created: " + FileConstants.USER_FILE_NAME);
        } else {
            System.out.println("File Already Exists");
            return;
        }
    }

    void writeToFile(String content) {
        // Ghi nd vào file
        fileService.writeFile(FileConstants.USER_FILE_NAME, content);
    }

    void readFileContent() {
        // Đọc và hiển thị nd
        String content = fileService.readFile(FileConstants.USER_FILE_NAME);
        System.out.println("Nội dung file: " + content);
    }
}
