import java.time.LocalDate;
import java.io.Serializable;

public class Date implements Serializable{
    String year,month,day;

    public Date(String year, String month, String day){
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public Date(){
        LocalDate date = LocalDate.now();
        this.year = String.valueOf(date.getYear());
        this.month = String.format("%02d",date.getMonthValue()); // getting two digits as the month number
        this.day  = String.format("%02d",date.getDayOfMonth());

    }

    public String getYear(){
        return this.year;
    }
    public String getMonth(){
        return this.month;
    }
    public String getDay(){
        return this.day;
    }
}
