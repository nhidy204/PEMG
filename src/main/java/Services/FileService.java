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
            String rootPath = System.getProperty("user.dir");
            File file = new File(rootPath, fileName);
            if (file.createNewFile()){
                System.out.println("File Created" + file.getAbsolutePath());
            }
            else {
                System.out.println("File Existed" + file.getAbsolutePath());
            }
        }
        catch (IOException e) {
            System.out.println("Error creating file");
            e.printStackTrace();

        }

    }

    @Override
    public boolean checkFile(String fileName) {
        String rootPath = System.getProperty("user.dir");
        File file = new File(rootPath, fileName);
        return file.exists();
    }

    @Override
    public String readFile(String fileName) {
        //doc noi dung cua file
        try {
            String rootPath = System.getProperty("user.dir");
            return new String(Files.readAllBytes(Paths.get(rootPath, fileName)));
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
        try
        {
            String rootPath = System.getProperty("user.dir");
            FileWriter writer = new FileWriter(new File(rootPath,fileName));
            writer.write(content);
            writer.close();
            System.out.println("File Written" + fileName);
        }
        catch (IOException e) {
            System.out.println("Error writing file");
            e.printStackTrace();
        }
    }
}
