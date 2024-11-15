package Services.Interfaces;

public interface IFileService {
    void createFile(String fileName);
    boolean checkFile(String fileName);
    String readFile(String fileName);
    void writeFile(String userFileName, String content);
}
