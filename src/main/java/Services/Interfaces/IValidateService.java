package Services.Interfaces;

public interface IValidateService {
    int inputInt(String mess, int min, int max);
    double inputDouble(String mess);
    String inputString(String mess, String regex);
    String inputDate(String mess);

}
