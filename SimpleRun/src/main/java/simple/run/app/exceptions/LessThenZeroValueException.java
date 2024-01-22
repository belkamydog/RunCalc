package simple.run.app.exceptions;

public class LessThenZeroValueException extends Exception{
    @Override
    public String getMessage() {
        return "Value can't be less then 0";
    }
}
