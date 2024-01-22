package simple.run.app.exceptions;

public class UncorrectedTimeFormatException extends Exception{
    @Override
    public String getMessage() {
        return "Uncorrected time format";
    }
}
