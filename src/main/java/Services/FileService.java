package Services;
import Services.Interfaces.IFileService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileService implements IFileService {
    public static FileService instance;
    public FileService() {}
    public static FileService getInstance() {
        if (instance == null) {
            instance = new FileService();
        }
        return instance;
    }
    @Override
    public void createFile(String fileName) {
        //tạo file ms, khi file chưa ton tia
        try {
            File file = new File(fileName);
            if (file.createNewFile()){
                System.out.println("File Created" + fileName);
            }
            else {
                System.out.println("File Existed" + fileName);
            }
        }
        catch (IOException e) {
            System.out.println("Error creating file");
            e.printStackTrace();
        }

    }

    @Override
    public boolean checkFile(String fileName) {
        File file = new File(fileName);
        return false;
    }

    @Override
    public String readFile(String fileName) {
        //doc noi dung cua file
        try {
            return new String(Files.readAllBytes(Paths.get(fileName)));
        }
        catch(IOException e)
        {
            System.out.println("Error reading file");
            e.printStackTrace();
        }
        return "";
    }
    public void writeFile(String fileName, String content) {
        //ghi nd vao file
        try (FileWriter writer = new FileWriter(fileName))
        {
            writer.write(content);
            System.out.println("File Written" + fileName);
        }
        catch (IOException e) {
            System.out.println("Error writing file");
            e.printStackTrace();
        }
    }
}
