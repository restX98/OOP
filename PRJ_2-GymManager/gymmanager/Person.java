package gymmanager;

import java.util.Date;

/**
 *
 * @author Enrico Restuccia
 */
public class Person {
    
    private String name;
    private String IDCode;
    private String expireDate;
    private String course;
    
    public Person(String name, String IDCode, String expireDate, String course) {
        this.name = name;
        this.IDCode = IDCode;
        this.expireDate = expireDate;
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIDCode() {
        return IDCode;
    }

    public void setIDCode(String IDCode) {
        this.IDCode = IDCode;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return name + ", " + IDCode + ", " + expireDate;
    }

    
}
