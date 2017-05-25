package edu.test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

//import hello.Form;

public class FormMapper implements RowMapper<Form> {	
	
	public Form mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		/*private String First_Name;
	    private String Last_Name;
	    private String SSN;
	    private String Email;
	    private String Student_Phone;
	    private String Course;
	    private String Due_Date;
	    private String Description;
	    private Date Date_reg;
	    private String Date_reg_search;
	    private String Instructor_lname;
	    private String Instructor_Fname;
	    private String Instructor_email;
	    private String Instructor_phone;
	    private String student_comments;
	    private String CitationStyle;
	    private String CitationStyleOther;
	    private String Student_Attachment;
	    private String request_type;
	    private String attachment_state;*/
		
		Form levels = new Form();		

		levels.setFirst_Name(rs.getString("first_Name"));
		levels.setLast_Name(rs.getString("last_Name"));
		levels.setSsn(rs.getString("ssn"));
		levels.setEmail(rs.getString("email"));
		levels.setStudent_Phone(rs.getString("student_Phone"));
		levels.setCourse(rs.getString("course"));
		levels.setDue_Date(rs.getString("due_Date"));
		levels.setDescription(rs.getString("description"));
		
		levels.setDate_reg(rs.getDate("date_reg"));
		
		levels.setDate_reg_search(rs.getString("date_reg_search"));
		levels.setInstructor_lname(rs.getString("instructor_lname"));
		levels.setInstructor_Fname(rs.getString("instructor_Fname"));
		levels.setInstructor_email(rs.getString("instructor_email"));
		levels.setInstructor_phone(rs.getString("instructor_phone"));
		levels.setStudent_comments(rs.getString("student_comments"));
		levels.setCitationStyle(rs.getString("citationStyle"));
		
		levels.setCitationStyleOther(rs.getString("citationStyleOther"));
		levels.setStudent_Attachment(rs.getString("student_attachment"));
		levels.setRequest_type(rs.getString("request_type"));
		levels.setAttachment_state(rs.getString("attachment_state"));

		

				
		return levels;
	}

}
