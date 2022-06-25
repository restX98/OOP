package gymmanager;

import java.util.*;

/**
 *
 * @author Enrico Restuccia
 */
public class Course {
    
    public final String name;
    private String teacherName;
    private int maxPerson;
    private float priceForMonth;
    private List<Person> persons;
    
    public Course(String name, String teacherName, int maxPerson, float priceForMonth) {
        this.name = name;
        this.teacherName = teacherName;
        this.maxPerson = maxPerson;
        this.priceForMonth = priceForMonth;
        this.persons = new LinkedList<>();
    }

    public List<Person> getPersons() {
        return persons;
    }
    
    public void addPerson(Person person) throws Exception{
        if(persons.size() < maxPerson)
            this.persons.add(person);
        else throw new Exception("Full Course");
    }
    
    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public int getMaxPerson() {
        return maxPerson;
    }

    public void setMaxPerson(int maxPerson) {
        this.maxPerson = maxPerson;
    }

    public float getPriceForMonth() {
        return priceForMonth;
    }

    public void setPriceForMonth(float priceForMonth) {
        this.priceForMonth = priceForMonth;
    }

    @Override
    public String toString() {
        String courseString = name + ", "+ teacherName + ", "
                + maxPerson + ", " + priceForMonth + "\n";
        
        for(Person person : this.persons)
            courseString = courseString.concat(person + "\n");
        
        return courseString;
    }    
}
