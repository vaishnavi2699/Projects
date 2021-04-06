package college_Class_Interface;

import java.util.ArrayList;

import college_Models.Course_Model;
import college_Models.Fee_Remainder_Model;
import college_Models.Lecturer_Model;
import college_Models.Marks_Model;
import college_Models.Principal_Model;
import college_Models.Recption_Model;
import college_Models.Student_Model;

public interface College_Interface 
{ 
	//PRINCIPAL
     public boolean principalLogin(Principal_Model p);
     
     public boolean addLecturer(Lecturer_Model l);
     
     public boolean addRecptionist(Recption_Model r);
     
     public boolean addCourse(Course_Model c);
     
     public ArrayList<Lecturer_Model> viewlecturers();
     
     public ArrayList<Recption_Model> viewRecptionist();
     
     public ArrayList<Course_Model> viewCources();
     
     //Lecturer
     public boolean lecturerLogin(Lecturer_Model l);
     
     public boolean createPassword(Lecturer_Model l);
     
     public ArrayList<Student_Model> checkMyStudents(Lecturer_Model l);
     
     public boolean totalMarksOfStudent(Marks_Model m);
     
     public ArrayList<Marks_Model> checkMarksOfStudents(Lecturer_Model l);
     
     public String gradesOfStudents(int totalmarks);
     
     //reciption 
     public boolean reciptionistLogin(Recption_Model r);

     public boolean reciptionistCreatePassword(Recption_Model r);
     
     public boolean reciptionistAddStudents(Student_Model s);
     
     public ArrayList<Student_Model> viewAllStudents();
     
     public boolean sendFeeRemainderToStudent(Fee_Remainder_Model f);
     
     public ArrayList<Student_Model> checkStudentsByCourse(Course_Model c);
     
     //Student
     public boolean stuedentLogin(Student_Model s);
     
     public ArrayList<Fee_Remainder_Model> checkFeeRemainders(Student_Model s);
     
     public boolean payDueAmount(Fee_Remainder_Model f);
     
     public ArrayList<Student_Model> checkMyFeeStructure(Student_Model s);
     
     public ArrayList<Marks_Model> checkMyResult(Student_Model s);
}
