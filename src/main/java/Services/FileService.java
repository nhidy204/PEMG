package Services;
import Services.Interfaces.IFileService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileService implements IFileService {
    public static FileService instance;
    public String rootPath;

    public FileService() {
        this.rootPath = System.getProperty("user.dir");
    }
    public static FileService getInstance() {
        if (instance == null) {
            instance = new FileService();
        }
        return instance;
    }
    @Override
    public void createFile(String fileName) {
        try {
            File directory = new File(rootPath);
            // Kiểm tra xem thư mục đã tồn tại chưa, nếu chưa thì tạo thư mục
            if (!directory.exists()) {
                directory.mkdirs(); // Tạo thư mục nếu không tồn tại
            }

            File file = new File(directory, fileName);
            if (file.createNewFile()) {
                System.out.println("File Created: " + file.getAbsolutePath());
            } else {
                System.out.println("File Already Exists: " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            System.out.println("Error creating file");
            e.printStackTrace();
        }
    }


    @Override
    public boolean checkFile(String fileName) {
        File file = new File(rootPath, fileName);
        return file.exists();
    }

    @Override
    public String readFile(String fileName) {
        //doc nd cua file
        try {
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
            FileWriter writer = new FileWriter(new File(rootPath, fileName), false);

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
