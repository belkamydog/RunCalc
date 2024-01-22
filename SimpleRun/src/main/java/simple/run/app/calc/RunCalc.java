package simple.run.app.calc;

import lombok.Data;
import lombok.NonNull;
import simple.run.app.exceptions.LessThenZeroValueException;
import simple.run.app.exceptions.UncorrectedTimeFormatException;

/**
 * RunCalc help you calculate pace, distance, speed, etc.
 * All of you need is call constructor which one you need
 */
@Data
public class RunCalc {
    /**
     * Speed of run km/h
     */
    private float speed = 0;
    /**
     * Pace of run min/km
     */
    private float pace = 0;
    /**
     * Distance of the run km
     */
    private float distance = 0;

    private int time = 0;

    public RunCalc() {

    }

    /**
     * @param time String in format "hh:mm:ss"
     * @return int count seconds
     * @exception LessThenZeroValueException if value hour, minute, sec less than zero
     * @exception UncorrectedTimeFormatException if format time-string wrong
     */
    public static int getSeconds(@NonNull String time) throws Exception{
        int result = 0;
        String[] arrStr = time.split(":");
        if (arrStr.length != 3) throw new UncorrectedTimeFormatException();
        int hour = Integer.parseInt(arrStr[0]);
        int minute = Integer.parseInt(arrStr[1]);
        int seconds = Integer.parseInt(arrStr[2]);
        if (hour < 0 || minute < 0 || seconds < 0) throw new LessThenZeroValueException();
        result = hour * 3600 + minute * 60 + seconds;
        return  result;
    }
}
