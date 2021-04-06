package college_Class_Interface;

import java.sql.Connection;
import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import college_Models.Course_Model;
import college_Models.Fee_Remainder_Model;
import college_Models.Lecturer_Model;
import college_Models.Marks_Model;
import college_Models.Principal_Model;
import college_Models.Recption_Model;
import college_Models.Student_Model;


public class College_Class implements College_Interface
{
	static Connection con=null;
	SessionFactory sf=null;
	public College_Class()
	{
		Configuration cfg=new Configuration().configure().addAnnotatedClass(Course_Model.class).addAnnotatedClass(Fee_Remainder_Model.class).addAnnotatedClass(Lecturer_Model.class).addAnnotatedClass(Marks_Model.class).addAnnotatedClass(Principal_Model.class).addAnnotatedClass(Recption_Model.class).addAnnotatedClass(Student_Model.class);
		sf=cfg.buildSessionFactory();
	}
	public boolean principalLogin(Principal_Model p) 
	{
		boolean b=false;
		Session s=sf.openSession();
		p=(Principal_Model) s.get(Principal_Model.class,p.getUsername());
		if(p!=null)
		{
			b=true;
		}
		s.close();
		return b;
	}
	public boolean addLecturer(Lecturer_Model l) 
	{
		boolean b=false;
		Session s=sf.openSession();
		Transaction t=s.beginTransaction();
		Object obj=s.save(l);
		if(obj!=null)
		{
			b=true;
		}
		t.commit();
		s.close();
		return b;
	}
	public boolean addRecptionist(Recption_Model r)
	{
		boolean b=false;
		Session s=sf.openSession();
		Transaction t=s.beginTransaction();
		Object obj= s.save(r);
		if(obj!=null);
		{
			b=true;
		}
		t.commit();
		s.close();
		return b;
	}
	public boolean addCourse(Course_Model c) 
	{
		boolean b=false;
		Session s=sf.openSession();
		Transaction t=s.beginTransaction();
		Object obj= s.save(c);
		if(obj!=null)
		{
			b=true;
		}
		t.commit();
		s.close();
		return b;
	}
	public ArrayList<Lecturer_Model> viewlecturers() 
	{
		Session s=sf.openSession();
		Lecturer_Model l=new Lecturer_Model();
		Query q=s.createQuery("from Lecturer_Model");
		ArrayList<Lecturer_Model> al=(ArrayList<Lecturer_Model>) q.list();
		s.close();
		return al;
	}
	public ArrayList<Recption_Model> viewRecptionist() 
	{
		Recption_Model r=new Recption_Model();
		Session s=sf.openSession();
		Query q= s.createQuery("from Recption_Model");
		ArrayList<Recption_Model> al=(ArrayList<Recption_Model>) q.list();
		s.close();
		return al;
	}
	public ArrayList<Course_Model> viewCources() 
	{
		Session s=sf.openSession();
		Course_Model c=new Course_Model();
		Query q=s.createQuery("from Course_Model");
		ArrayList<Course_Model> al=(ArrayList<Course_Model>) q.list();
		s.close();
		return al;
	}
	
	
//====================================================================================================================================================//
	//Lecturer Related Methods//
	public boolean lecturerLogin(Lecturer_Model l)
	{
		boolean b=false;
		Session s=sf.openSession();
		l=(Lecturer_Model) s.get(Lecturer_Model.class,l.getUsername());
		if(l!=null)
		{
			b=true;
		}
		s.close();
		return b;
	}
	public boolean createPassword(Lecturer_Model l) 
	{
		boolean b=false;
		Session s=sf.openSession();
		Lecturer_Model l1=new Lecturer_Model();
		l1=(Lecturer_Model) s.get(Lecturer_Model.class,l.getUsername());
		l1.setPassword(l.getPassword());
		s.close();
		Session ss=sf.openSession();
		Transaction t=ss.beginTransaction();
		ss.update(l1);
		t.commit();
		b=true;
		ss.close();
		return b;
	}
	public ArrayList<Student_Model> checkMyStudents(Lecturer_Model l) 
	{
	    ArrayList<Student_Model> al=new ArrayList<Student_Model>();
		Session s=sf.openSession();
		l=(Lecturer_Model) s.get(Lecturer_Model.class,l.getUsername());
		s.close();
		Student_Model st=new Student_Model();
		if(l!=null)
		{
			Session ss=sf.openSession();
	        al=(ArrayList<Student_Model>) ss.createQuery("from Student_Model where course='"+l.getSubject()+"'").list();
			ss.close();
		}
	    return al;
	}
	public ArrayList<Student_Model> enterStudentMarks(Student_Model s)
	{
		ArrayList<Student_Model> al=new ArrayList<Student_Model>();
		Session se=sf.openSession();
		s=(Student_Model) se.get(Student_Model.class,s.getSid());
		al.add(s);
		se.close();
		return al;
	}
	public ArrayList<Marks_Model> enterTotalMarksOfStudent(Marks_Model m) 
	{
		ArrayList<Marks_Model> al=new ArrayList<Marks_Model>();
		Session se=sf.openSession();
		m=(Marks_Model) se.get(Marks_Model.class,m.getSid());
		al.add(m);
		se.close();
		return al;
	}
	public String gradesOfStudents(int totalmarks) 
	{
		String grade=null;
		if(totalmarks>=85)
		{
			grade="A";
		}
		else if(totalmarks>=75)
		{
			grade="B";
		}
		else if(totalmarks>=65)
		{
			grade="C";
		}
		else if(totalmarks>=55)
		{
			grade="D";
		}
		else if(totalmarks>=40)
		{
			grade="E";
		}
		else
		{
			grade="Failed";
		}
		return grade;
	}
	public boolean totalMarksOfStudent(Marks_Model m) 
	{
        boolean b=false;
        Session se=sf.openSession();
        Marks_Model m1=new Marks_Model();
        m1=(Marks_Model) se.get(Marks_Model.class,m.getSid());
        se.close();
        Session ss=sf.openSession();
        Transaction t=ss.beginTransaction();
        m1.setExternalmarks(m.getExternalmarks());
        m1.setGrade(gradesOfStudents(m.getTotalmarks()));
        m1.setInternalmarks(m.getInternalmarks());
        m1.setSemestermarks(m.getSemestermarks());
        m1.setTotalmarks(m.getTotalmarks());
        ss.update(m1);
        t.commit();
        b=true;
        ss.close();
		return b;
	}
	public ArrayList<Marks_Model> checkMarksOfStudents(Lecturer_Model l) 
	{
		ArrayList<Marks_Model> al=new ArrayList<Marks_Model>();
		Session s=sf.openSession();
		l=(Lecturer_Model) s.get(Lecturer_Model.class,l.getUsername());
		s.close();
		Marks_Model m=new Marks_Model();
		if(l!=null)
		{
			Session ss=sf.openSession();
			al=(ArrayList<Marks_Model>) ss.createQuery("from Marks_Model where course='"+l.getSubject()+"'").list();
			ss.close();
		}
		return al;		
	}
	
	
   //==================================================================================================================================================//
	//Reciptionist Related Methods//
	
	public boolean reciptionistLogin(Recption_Model r) 
	{
		boolean b=false;
		Session s=sf.openSession();
		r=(Recption_Model) s.get(Recption_Model.class,r.getUsername());
		if(r!=null)
		{
			b=true;
		}
		s.close();
		return b;
	}
	public boolean reciptionistCreatePassword(Recption_Model r) 
	{
		boolean b=false;
		Session s=sf.openSession();
		Recption_Model r1=new Recption_Model();
		r1=(Recption_Model)s.get(Recption_Model.class,r.getUsername());
		r1.setPassword(r.getPassword());
		s.close();
		Session ss=sf.openSession();
		Transaction t=ss.beginTransaction();
		ss.update(r1);
		t.commit();
		b=true;
		ss.close();
		return b;
	}
	public ArrayList<Course_Model> getAllCources()
	{
		Session s=sf.openSession();
		ArrayList<Course_Model> al= (ArrayList<Course_Model>) s.createQuery("from Course_Model").list();
		s.close();
		return al;
	}
	public boolean reciptionistAddStudents(Student_Model s) 
	{
		boolean b=false;
		Session ss=sf.openSession();
		ArrayList<Course_Model> al= (ArrayList<Course_Model>) ss.createQuery("from Course_Model where coursename='"+s.getCourse()+"'").list();
		ss.close();
		for(Course_Model c:al)
		{
			Session sse=sf.openSession();
			Transaction t=sse.beginTransaction();
			s.setFee(c.getCoursefee());
			s.setDue(c.getCoursefee());
			Marks_Model m=new Marks_Model();
			m.setSid(s.getSid());
			m.setStudentname(s.getStudentName());
			m.setCourse(s.getCourse());
			Object obj=sse.save(s);
			Object obj1= sse.save(m);
			if(obj!=null && obj1!=null)
			{
				b=true;
			}
			t.commit();
			sse.close();
		}					
		return b;
	}
	public ArrayList<Student_Model> viewAllStudents() 
	{		
		Session ss=sf.openSession();
		Query q=ss.createQuery("from Student_Model");
		ArrayList<Student_Model> al=(ArrayList<Student_Model>) q.list();
		ss.close();
		return al;
	}
	public boolean sendFeeRemainderToStudent(Fee_Remainder_Model f) 
	{
		boolean b=false;
		Student_Model st=new Student_Model();
		Session s=sf.openSession();
		st=(Student_Model) s.get(Student_Model.class,f.getSid());
		f.setSid(st.getSid());
		f.setStudentname(st.getStudentName());
		f.setCourse(st.getCourse());
		f.setFee(st.getFee());
		f.setDue(st.getDue());
		f.setPaid(st.getPaid());
		s.close();
		Session se=sf.openSession();
		Transaction t=se.beginTransaction();
		Object obj=se.save(f);
		if(obj!=null)
		{
			b=true;
		}
		t.commit();
		se.close();
		return b;
	}
	public ArrayList<Student_Model> checkStudentsByCourse(Course_Model c) 
	{
		ArrayList<Student_Model> al=new ArrayList<Student_Model>();
		Session s=sf.openSession();
		c=(Course_Model) s.get(Course_Model.class,c.getCoursename());
		s.close();
		Student_Model st=new Student_Model();
		if(c!=null)
		{
			Session se=sf.openSession();
			al=(ArrayList<Student_Model>) se.createQuery("from Student_Model where course='"+c.getCoursename()+"'").list();
			se.close();
		}
	    return al;
	}
	
	
	//============================================================================================================================================//
	//Student Related Menthods//
	
	public boolean stuedentLogin(Student_Model st) 
	{
		boolean b=false;
		Session s=sf.openSession();
		st=(Student_Model) s.get(Student_Model.class,st.getSid());
		if(st!=null)
		{
			b=true;
		}
		s.close();
		return b;
	}
	public ArrayList<Fee_Remainder_Model> checkFeeRemainders(Student_Model s) 
	{
		ArrayList<Fee_Remainder_Model> al=new ArrayList<Fee_Remainder_Model>();
		Session ss=sf.openSession();
		Fee_Remainder_Model f=new Fee_Remainder_Model();
		f=(Fee_Remainder_Model) ss.get(Fee_Remainder_Model.class,s.getSid());
		al.add(f);
		ss.close();
		return al;
	}
	public ArrayList<Fee_Remainder_Model> feePaymentForm(Fee_Remainder_Model f)
	{
		ArrayList<Fee_Remainder_Model> al=new ArrayList<Fee_Remainder_Model>();
		Session ss=sf.openSession();
		f=(Fee_Remainder_Model) ss.get(Fee_Remainder_Model.class,f.getSid());
		al.add(f);
		ss.close();
		return al;
	}
	public boolean payDueAmount(Fee_Remainder_Model f) 
	{
		boolean b=false;
		Session ss=sf.openSession();
		Transaction tr=ss.beginTransaction();
		Student_Model s=new Student_Model();
		s=(Student_Model) ss.get(Student_Model.class,f.getSid());
		s.setPaid(f.getPaid()+(f.getPay()));
		s.setDue(f.getDue()-(f.getPay()));
		ss.update(s);
		tr.commit();
		ss.close();
		Session ses=sf.openSession();
		Transaction t=ses.beginTransaction();
		Fee_Remainder_Model f1=new Fee_Remainder_Model();
		f1=(Fee_Remainder_Model) ses.get(Fee_Remainder_Model.class,f.getSid());
		f1.setPaid(f.getPaid()+(f.getPay()));
		f1.setDue(f.getDue()-(f.getPay()));
		ses.update(f1);
		t.commit();
        b=true;
        ses.close();
		return b;
	}
	public ArrayList<Student_Model> checkMyFeeStructure(Student_Model s1) 
	{
		ArrayList<Student_Model> al=new ArrayList<Student_Model>();
		Session s=sf.openSession();
		s1=(Student_Model) s.get(Student_Model.class,s1.getSid());
		al.add(s1);
		s.close();
		return al;
	}
	public ArrayList<Marks_Model> checkMyResult(Student_Model s) 
	{
		ArrayList<Marks_Model> al=new ArrayList<Marks_Model>();
		Session se=sf.openSession();
		Marks_Model m=new Marks_Model();
		m.setSid(s.getSid());
		m=(Marks_Model) se.get(Marks_Model.class,m.getSid());
		al.add(m);
		se.close();
		return al;
	}
}
