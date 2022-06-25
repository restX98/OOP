package gymmanager;

import java.io.*;
import java.text.*;
import java.util.*;
import java.util.logging.*;

/**
 *
 * @author Enrico Restuccia
 */
public class Gym {

    private static final Logger LOG = Logger.getLogger(Gym.class.getName());
    
    public static String COURSES_FILE = "courses.txt";
    public static String PERSONS_FILE = "persons.txt";

    private List<Course> courses;
    
    public String maxRevenueCourse;
    
    public Gym(){
        courses = new LinkedList<>();
        maxRevenueCourse = null;
    }

    public List<Course> getCourses() {
        return courses;
    }
    
    public synchronized void createReport(){
        float maxRevenue = 0;
        for(Course course : this.getCourses()){
            float tmp = course.getPersons().size() * course.getPriceForMonth();
            if(tmp>maxRevenue){
                maxRevenue = tmp;
                this.maxRevenueCourse = course.name;
            }
        }
    }
    
    public synchronized void loadCourses(){
        try(BufferedReader file = new BufferedReader(new FileReader(this.COURSES_FILE))){    
            String courseLine;
            while((courseLine = file.readLine()) != null){
                this.createCourse(courseLine);
            }
        }catch(Exception ex){
            LOG.log(Level.SEVERE, Arrays.toString(ex.getStackTrace()), ex);
            System.exit(1);
        }
    }
    
    private synchronized void createCourse(String courseLine){
        try{
            String[] fields = courseLine.split(","); 
            trimFields(fields);
            this.courses.add(new Course(fields[0],fields[1],
                    Integer.parseInt(fields[2]), Float.parseFloat(fields[3])));
        }catch(Exception ex){
            LOG.log(Level.WARNING, "Error On File formatting\n"
                    + Arrays.toString(ex.getStackTrace()), ex);
        }
    }
    
    public synchronized void loadPersons(String expireDate){
        try(BufferedReader file = new BufferedReader(new FileReader(Gym.PERSONS_FILE))){    
            String personLine;
            while((personLine = file.readLine()) != null){
                this.createPerson(personLine, expireDate);
            }
        }catch(Exception ex){
            LOG.log(Level.SEVERE, Arrays.toString(ex.getStackTrace()), ex);
            System.exit(1);
        }
    }
    
    private synchronized void createPerson(String personLine, String expireDate){
        try{
            String[] fields = personLine.split(","); 
            trimFields(fields);
            
            Course course = getCourseByName(fields[3]);
            
            if(expireDate.compareTo(fields[2]) < 0 )
                course.addPerson(new Person(fields[0], fields[1], fields[2], fields[3]));
        }catch(Exception ex){
            LOG.log(Level.WARNING, "Error On File formatting\n"
                    + Arrays.toString(ex.getStackTrace()), ex);
        }
    }
    
    public synchronized float removePerson(String ID) throws Exception{
        boolean exist = false;
        float lostRevenue = 0;
        for(Course course : this.courses){
            for(Iterator<Person> persons = course.getPersons().iterator();
                    persons.hasNext(); ){
                Person person = persons.next();
                if(person.getIDCode().compareTo(ID) == 0){
                    persons.remove();
                    lostRevenue+=course.getPriceForMonth();
                    exist = true;
                }
            }
        }
        if(!exist) throw new Exception("Person not found!");
        
        return lostRevenue;
    }
    
    public synchronized Course getCourseByName(String courseName) throws Exception{
        for(Course c : this.courses){
            if(c.name.compareTo(courseName) == 0) return c;
        }
        throw new Exception("Course " + courseName + " Not found");
    }
    
    private synchronized void trimFields(String[] fields){
        for(int i = 0; i<fields.length; i++) fields[i] = fields[i].trim();
    }

    @Override
    public String toString() {
        String gymString = "";
        for(Course course : this.courses){
            gymString = gymString.concat(course.toString()).concat("\n");
        }
        return gymString;
    }
    
    
}
