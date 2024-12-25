package Controllers;

import Constants.FileConstants;
import Models.User;
import Services.FileService;
import Services.Interfaces.IFileService;
import java.util.Scanner;

public class FileController {

    private final IFileService fileService;
    private final Scanner scanner = new Scanner(System.in);
    private User loggedInUser;


    public FileController() {
        this.fileService = FileService.getInstance();
    }

    public void createUserFile(){
        //truoc khi tao thi dùng ham check, tra ve false thi moi tao, ko thi return
        if (!fileService.checkFile(FileConstants.USER_FILE_NAME)) {
            fileService.createFile(FileConstants.USER_FILE_NAME);
            System.out.println("File Created: " + FileConstants.USER_FILE_NAME);
        } else {
            System.out.println("File Already Exists");
        }
        return;
    }
}
