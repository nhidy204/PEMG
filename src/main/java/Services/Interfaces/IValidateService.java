package Services.Interfaces;

public interface IValidateService {
    int inputInt(String mess, int min, int max);
    double inputDouble(String mess, double min, double max);
    String inputString(String mess, String regex);
    String inputDate(String mess);
}
