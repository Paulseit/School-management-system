import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat; import java.util.stream.Collectors;


class Main 
{
  
  public static void main(String[] args) 
  {
    Scanner myScan = new Scanner(System.in);
      System.out.println("Welcome to my Personal Management Program");
      int temp = 0;
      PersonList List = new PersonList();
    // switch statement within a while for the menu

        while(temp == 0)
        {
        System.out.println("\nChoose one of the options");
        System.out.println("1. Enter the information a faculty");
        System.out.println("2. Enter the information of a student");
        System.out.println("3. Print tuition invoice for a student");
        System.out.println("4. Print faculty information");
        System.out.println("5. Enter the information of a staff member");
        System.out.println("6. Print the information of a staff member");
        System.out.println("7. Delete a person");
        System.out.println("8. Exit Program");
        System.out.print("\nEnter Your Selection: ");
          try{
          int Decision = myScan.nextInt();
          myScan.nextLine();
          
          switch(Decision)
          {
              //adding faculty
              case 1:
                      System.out.println("\nEnter the faculty info:\n");
                      System.out.print("Name of the faculty: ");
                      String facultyname = myScan.nextLine().toLowerCase();
                      String facultyid = List.validId(myScan);

                      System.out.print("Rank: ");
                      String facultyrank = myScan.nextLine();
                      while(facultyrank.equalsIgnoreCase("professor") == false && facultyrank.equalsIgnoreCase("adjunct") == false){
                        System.out.println("      "+'"'+ facultyrank + '"'+  " is invalid");
                        System.out.print("Rank: ");
                        facultyrank = myScan.nextLine();
                      }
                    
          
                      System.out.print("Department: ");
                      String facultydepartment = myScan.nextLine();
                      while(facultydepartment.equalsIgnoreCase("mathematics") == false && facultydepartment.equalsIgnoreCase("engineering") == false && facultydepartment.equalsIgnoreCase("english") == false){
                        System.out.println("Invalid department, try again");
                        System.out.println("Department: ");
                        facultydepartment = myScan.nextLine();
                      }
                     
                      
                      Person newfaculty = new Faculty(facultyname, facultyid, facultydepartment,facultyrank); 
                      
                    if(List.Enroll(newfaculty) == false){
                      List.addPerson(newfaculty);
                      System.out.println("Faculty added!");
                    }
                    else{
                      System.out.println("Duplicate ID, Not added");
                    }
                    
                      break;
              
              // student add
              case 2:
                      // Input
                      
              
                      System.out.println("\nEnter the student info:\n");
              
                      System.out.print("Name of Student:");
                      String studentName = myScan.nextLine();
                      String id = List.validId(myScan);

                      
              
                      System.out.print("Gpa:");
                      double gpa = myScan.nextDouble();
                      myScan.nextLine();
                      while(gpa > 4.0 || gpa < 0){
                        System.out.println("Thats out of the GPA range, please enter a gpa between 0 and 4.0");
                        System.out.print("Gpa:");
                        gpa = myScan.nextDouble();
                      }
              
                      System.out.print("Credit hours:");
                      int creditHour = myScan.nextInt();
                      myScan.nextLine();
        
                      
                      // if id is valid, add student to list
                      Person student = new Student(studentName, id, gpa, creditHour);
              
                      if(List.Enroll(student) == false)
                      {
                        List.addPerson(student);
                        System.out.println("added!");
                      }
                      else
                      {
                        System.out.println("Duplicate ID, Not added");
                      }
                      
                      break;
              
              // invoice for student
              case 3:
      
                      System.out.print("Enter the student's id: ");
                      String id1 = myScan.nextLine();
                      Person invoice = List.search(id1);
                      if(invoice == null || !(invoice instanceof Student)){
                        System.out.println("No student matched!");
                      }else{
                        Student.printInvoice((Student)invoice);
                      }
                      break;
              
              //print faculty
              case 4:
                System.out.print("Enter the faculty's id: ");
                String findID = myScan.nextLine();
                Person findFaculty = List.search(findID);
                if(findFaculty == null || !(findFaculty instanceof Faculty)){
                  System.out.println("No Faculty matched!");
                }else{
                  Faculty.printFaculty((Faculty)findFaculty);
                }
              
              break;
              
              //staff
              case 5:
              
                System.out.print("Name of the staff member:");
              
                String staffName = myScan.nextLine();
              
                System.out.print("Enter the ");
              
                String staffID = List.validId(myScan);
              
                System.out.print("Department: ");
              
                String staffDepartment = myScan.nextLine();
              
                while(staffDepartment.equalsIgnoreCase("mathematics") == false && staffDepartment.equalsIgnoreCase("engineering") == false && staffDepartment.equalsIgnoreCase("english") == false){
                  System.out.println("Sorry, that's invalid");
                  System.out.print("Department:");
                  staffDepartment = myScan.nextLine();
                }
              
                System.out.print("Status, Enter P for Part Time, or Enter F for Full Time:");
              
                String staffStatus = myScan.nextLine();  
              
                while(staffStatus.equalsIgnoreCase("p") == false && staffStatus.equalsIgnoreCase("f") == false){
                  System.out.println("Sorry, that's invalid");
                  System.out.print("Status, Enter P for Part Time, or Enter F for Full Time:");
                  staffStatus = myScan.nextLine();
                }
              
                Person staff = new Staff(staffName, staffID, staffDepartment, staffStatus);
              
                if(List.Enroll(staff) == false){
                  List.addPerson(staff);
                  System.out.println("Staff added!");
                
                }else{
                  System.out.println("Duplicate ID, Not added");
                }
                  
                break;
              
             
              
              //print staff
              case 6:
                System.out.print("Enter the staff's id: ");
                String IDstaff = myScan.nextLine();
                Person findstaff = List.search(IDstaff);
                if(findstaff == null || !(findstaff instanceof Staff))
                {
                  System.out.println("No Staff matched!");
                }
                else
                 {
                  Staff.printStaff((Staff)findstaff);
                 }
              
                break;
              
              //delete person
              case 7:
              System.out.print("Enter the id of the person to delete:");
              String varDeleted = myScan.nextLine();
              List.deletePerson(varDeleted);
              
              break;
              
              // exit
              case 8:
              System.out.println("Would you like to create the report? (Y/N):");
              
              String choice = myScan.nextLine();
/*
              System.out.println("Would you like to sort by GPA or Name?  (1 for gpa, 2 for name):");
              String choice2 = myScan.nextLine();
              while(choice2.equalsIgnoreCase("1") == false && choice2.equalsIgnoreCase("2") == false){
                System.out.println("Sorry, that's invalid");
                System.out.println("Would you like to sort by GPA or Name?  (1 for gpa, 2 for name):");
                choice2 = myScan.nextLine();
              }
              */
              while(choice.compareToIgnoreCase("y") != 0 && choice.compareToIgnoreCase("n") != 0){
                System.out.println("Sorry, thats not an valid choice");
                System.out.println("Would you like to create the report? (Y/N):");
                choice = myScan.nextLine();
              }
              if(choice.compareToIgnoreCase("Y") == 0)
              {
                System.out.println("Would you like to sort by GPA or Name?  (1 for gpa, 2 for name):");
                String choice2 = myScan.nextLine();
                while(choice2.equalsIgnoreCase("1") == false && choice2.equalsIgnoreCase("2") == false){
                  System.out.println("Sorry, that's invalid");
                  System.out.println("Would you like to sort by GPA or Name?  (1 for gpa, 2 for name):");
                  choice2 = myScan.nextLine();
                }
                  List.printReport(choice2);
                System.out.println("Report created and saved on your hard drive!");
                System.out.println("Goodbye!");
              }else{
                System.out.println("Goodbye!");
              }
              
              temp =1;
              break;
              
              default:
              System.out.println("Invalid entry- please try again");
              break;
      
          }
        }
        catch(InputMismatchException e)
        {
            System.out.println("Invalid entry - please try again");
            myScan.nextLine();
        }
        
        }
        
  }
}
  

class PersonList
{
  private ArrayList<Person> list;
  int index;
  
  public PersonList()
  {
    list = new ArrayList<>(100);
    this.index = 0;
  }
  
  public void addPerson(Person person)
  {
    // need to make a for loop to check if that person already exists
    try
      {
        list.add(person);
        index++;
      }
      catch(Exception e)
      {
        System.out.println("list is full");
      }
  }
  
  public String validId(Scanner myScan)
  {
  
    String tempID="";
    while(true){
    System.out.print("ID: ");
    tempID = myScan.nextLine().toLowerCase();
      // i figured out a way to do it, but imma prob find a way to implement exception catching tho soon.
      
            if(tempID.length() == 6) 
            {
                  if(Character.isLetter(tempID.charAt(0)) && Character.isLetter(tempID.charAt(1)) &&
                     Character.isDigit(tempID.charAt(2)) && Character.isDigit(tempID.charAt(3)) && 
                     Character.isDigit(tempID.charAt(4)) && Character.isDigit(tempID.charAt(5)))
                  {
                    break;
                  }
                  else
                  {
                    System.out.println("Invalid ID format. Must be LetterLetterDigitDigitDigitDigit");
                  }
            }
            else
            {
                System.out.println("Invalid ID format. Must be LetterLetterDigitDigitDigitDigit");
            }

        }
        return tempID;

      }


  // checks if there is a id already in the list
  public boolean Enroll(Person person)
  {
    boolean result = false;
    for(int i=0;i<list.size();i++)
    {
      Person temp = list.get(i);
  
      
      if(temp.getId().equals(person.getId()))
      {
        result = true;
      }
  
    }
    return result;
  }

  
  // searches and returns the index of the person, used to delete
  public Person search(String id)
  {
    Person result = null;
    for(int i=0;i < list.size(); i++)
    {
      Person temp = list.get(i);
      if(temp.getId().equalsIgnoreCase(id))
      {
        result = temp;
      }
    }
    return result;
  }
  
    // delete a person based off of id
  public void deletePerson(String id)
  {
    Person temp = search(id);
    if(temp != null)
    {
      list.remove(temp);
      System.out.println("Deleted!.");
    }
    else
    {
      System.out.println("Sorry no such person exists.");
    }
  }



  public void printReport(String choice)
  {
    
    try{
      FileWriter writer = new FileWriter("report.txt");
      SimpleDateFormat temp1 = new SimpleDateFormat("MM/dd/yyyy");
      temp1.setTimeZone(TimeZone.getTimeZone("EST"));
      String RealDate = temp1.format(new Date());
      writer.write("              Report created on: " + RealDate + "\n");
      writer.write("              ******************************\n");
      writer.write("              \n");
       writer.write("              \n");

      
      writer.write("Faculty Members\n");
      writer.write("---------------------\n");
       writer.flush();
      int counter = 1;
            for(Person person:list){
              FileWriter temp = new FileWriter("report.txt",true);
              if(person instanceof Faculty){
                
                temp.write(counter+ "." );
                temp.flush();
                person.print(); 
                counter++;
                
              }
              temp.close();
            }
        writer.close();

      
      FileWriter writer2 = new FileWriter("report.txt",true);
      writer2.write("\nStaff Members\n");
      writer2.write("---------------------\n");
      writer2.close(); 
      
      counter =1;
        for(Person person:list)
        {
            FileWriter temp = new FileWriter("report.txt",true);
              if(person instanceof Staff)
              {  
                temp.write(counter+ "." );
                temp.flush();
                  person.print(); 
                counter++;
              }
          temp.close();
        }





      // this will prob be delted
      List<Student> students = new ArrayList<>();
      for(Person person:list)
      {
        if(person instanceof Student)
        {
          Student temp = (Student)person;
          students.add(temp);
        }
      }
      if(choice.compareTo("1") == 0){
        GpaCompare gpaCompare = new GpaCompare();
        Collections.sort(students, gpaCompare);
      }
      else if(choice.compareTo("2") == 0){
        NameCompare nameCompare = new NameCompare();
        Collections.sort(students,nameCompare);
      }
      
      FileWriter writer3 = new FileWriter("report.txt",true);
      //writer.flush();
      writer3.write("\nStudents\n");
      writer3.write("---------------------\n");
      writer3.close();
      counter = 1;
      for(int i = 0; i < students.size(); i++){
        FileWriter temp = new FileWriter("report.txt",true);

          temp.write(counter+ "." );
          temp.flush();
          students.get(i).print();
          counter++;

          temp.close();
      }
      /*
      for(Student student:students)
      {
          FileWriter temp = new FileWriter("report.txt",true);
          
          temp.write(counter+ "." );
          temp.flush();
          student.print();
          counter++;
            
        temp.close();
      }
    */
    }
      
    catch(Exception e)
      {}
    
    
  }


  
}



// person class
abstract class Person 
{
    private String fullName;
    private String id;
      
        public String getFullName() 
        {
            return this.fullName;
        }
        
        public void setFullName(String fullName)
        {
            this.fullName = fullName;
        }
        
        public void setId(String id) 
        {
          this.id = id;
        }
      
        public String getId() 
        {
            return this.id;
        }
        
        public Person(String fullName,String id)
        {
          this.fullName = fullName;
          this.id = id;
        }
      
        public Person()
        {
          this.fullName = "";
          this.id = "";
        }
      
        public abstract void print();
  
}


// TODO make sure student is a normal class and not abstract. I forgor what it really means
// student extends person
class Student extends Person 
{
    private double gpa;
    private int creditHours;
    
    public double getGpa() 
      {
        return gpa;
      }
    
    public void setGpa(double gpa) 
      {
        this.gpa = gpa;
      }
    
    public int getCreditHours() 
      {
        return creditHours;
      }
    
    public void setCreditHours(int creditHours) 
      {
        this.creditHours = creditHours;
      }

  
        public Student(String fullName, String id, double gpa, int creditHours)
        {
          super(fullName, id);
          this.gpa = gpa;
          this.creditHours = creditHours;
        }
        
        public Student(String fullName, String id, double gpa)
        {
          super(fullName, id);
          this.gpa = gpa;
          this.creditHours = 0;
        }
  
  
      // do the print function for the file outputi/// needs to be tution not just normal print
        public void print()
        {
          try{
            FileWriter writer = new FileWriter("report.txt",true);
            writer.write(getFullName() + "\n");
            writer.write("ID: " + getId() + "\n");
            writer.write("GPA: "  + gpa+ "\n");
            writer.write("Credit Hours: " + creditHours + "\n");
            writer.write("              \n");
            writer.close();
            }
            catch(IOException e)
              {
                System.out.println("Error writing to file");
              }
        }
  
        public double tuition(int creditHours)
        {
          double tuition = (creditHours * (236.45) + 52 );

          if(gpa >= 3.85){
            tuition *= .75;
          }

          return tuition;
        }
        public static void printInvoice(Student student){
          System.out.println("-----------------------------------");
          System.out.println(student.getFullName().toUpperCase() + "           " + student.getId());
          System.out.println("Credit Hours: " + student.getCreditHours() + "($236.45/credit hour)");
          System.out.println("Fees: $52\n");
          System.out.println("Total payment: $" + String.format("%.2f",student.tuition(student.getCreditHours())) + "			($" +String.format("%.2f",((student.getCreditHours() * (236.45) + 52) - student.tuition(student.getCreditHours()))) + " discount applied)");
          System.out.println("-----------------------------------");
        }

        
}

  

    // eployee extends person
abstract class Employee extends Person
{
  private String department;

    // In the Employee class
  public String getDepartment() 
  {
    return department;
  }

  public void setDepartment(String department) 
  {
    this.department = department;
  }

  public Employee(String fullName, String id, String department)
  {
    super(fullName, id);
    this.department = department;
  }
  
  public Employee(String fullName, String id)
  {
    super(fullName, id);
    this.department = "";
  }

  public abstract void print();
}



    // faculty extends employee
class Faculty extends Employee
{
  private String Rank;


  public String getRank() 
  {
    return Rank;
  }

  public void setRank(String rank) 
  {
    Rank = rank;
  }
    
  public Faculty(String fullName, String id, String department, String Rank)
  {
    super(fullName, id,department);
    this.Rank = Rank;
  }

  public Faculty(String fullName, String id, String department)
  {
    super(fullName, id, department);
    this.Rank = "";
  }


    public void print() 
    {
      try{
      FileWriter writer = new FileWriter("report.txt",true);
        String department = getDepartment();
        department = department.toLowerCase();
        department = department.substring(0, 1).toUpperCase() + department.substring(1);

         String rank = getRank();
          rank = rank.toLowerCase();
          rank = rank.substring(0, 1).toUpperCase() + rank.substring(1);
      writer.write(getFullName() + "\n");
      writer.write("ID: " + getId() + "\n");
      writer.write(rank + ", "+ department + "\n");
      writer.write("              \n");
      writer.close();
      }
      catch(IOException e)
        {
        }
     
      
    }

  
  public static void printFaculty(Faculty member){
    String department = member.getDepartment();
    department = department.toLowerCase();
    department = department.substring(0, 1).toUpperCase() + department.substring(1).toLowerCase();

     String rank = member.getRank();
      rank = rank.toLowerCase();
      rank = rank.substring(0, 1).toUpperCase() + rank.substring(1).toLowerCase();

    System.out.println("--------------------------------------");
    System.out.println(member.getFullName() +"         " + member.getId());
    System.out.println(department + " Department, " + rank);
    System.out.println("--------------------------------------");
  }
}



    // staff extends employee
class Staff extends Employee
{
    private String Status;

  public void setStatus(String status) 
  {
      this.Status = status;
  }
  
  public String getStatus() 
  {
      return this.Status;
  }
  
  public Staff(String fullName, String id, String Department, String Status)
  {
    super(fullName, id, Department);
    this.Status = Status;
  }

  public Staff(String fullName, String id, String Department)
  {
    super(fullName, id, Department);
    this.Status = "";
  }
  
  public void print() 
  {
    try{
      FileWriter writer = new FileWriter("report.txt",true);
      writer.write(getFullName() + "\n");
      writer.write("ID: " + getId() + "\n");
      String department = getDepartment();
      department = department.toLowerCase();
      department = department.substring(0, 1).toUpperCase() + department.substring(1);
      if(getStatus().compareToIgnoreCase("P")==0){
          writer.write(department+", Part Time \n");
        }
      else{
        writer.write(department+", Full Time \n");
      }
      writer.close();
      }
      catch(IOException e)
        {
        }
    
  }
  public static void printStaff(Staff member)
  {
    //make the department name the first letter capitalized
    String department = member.getDepartment();
    department = department.toLowerCase();
    department = department.substring(0, 1).toUpperCase() + department.substring(1);
    String Status = member.getStatus().toUpperCase();
    String StatusFull;
    if(Status.equalsIgnoreCase("F")){
      StatusFull = "Full Time";
    }
    else{
      StatusFull = "Part Time";
    }
      
    System.out.println("--------------------------------------");
    System.out.println(member.getFullName() + "         " + member.getId());
    System.out.println(department + " Department, " + StatusFull);
    System.out.println("--------------------------------------");
  }

  
}
class GpaCompare implements Comparator<Student>{
    public int compare(Student s1, Student s2){
      if(s1.getGpa() > s2.getGpa()){
        return -1;
      }
      if(s1.getGpa() > s2.getGpa()){
        return 1;
      }
      else{
        return 0;
      }
    }

}
class NameCompare implements Comparator<Student>{
  public int compare(Student s1, Student s2){
    return s1.getFullName().compareTo(s2.getFullName());
  }
}

