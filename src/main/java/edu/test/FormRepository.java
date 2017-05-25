package edu.test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Repository
public class FormRepository {
	
	protected final Logger LOG = LoggerFactory.getLogger( this.getClass() );

    @Autowired
    protected JdbcTemplate jdbc;
    
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    
    @SuppressWarnings("unchecked")
	public Object insert(Form form) {
    	Date curDate = new Date();
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");    	
    	String DateToStr = format.format(curDate);
    	
        String sql = "insert into Request" +
                "(First_Name, Last_Name, SSN, Email, Student_Phone, Course, Due_Date, Description,  Date_reg, Date_reg_search," +
                 "Instructor_Lname,Instructor_Fname, Instructor_Email, Instructor_Phone, " +
                 "Student_Comments,Citationstyle,citationstyleother,student_attachment,request_type,attachment_state)" +
            "values " +
                "(:first_Name, :last_Name, :ssn, :email, :student_Phone, :course, :due_Date, :description, :date_reg, :date_reg_search," +
                " :instructor_lname, :instructor_Fname,:instructor_email,:instructor_phone," +
                " :student_comments,:citationStyle,:citationStyleOther,:student_Attachment,:request_type,:attachment_state)";
        
        Map namedParameters = new HashMap();   
        namedParameters.put("first_Name", form.getFirst_Name());
        namedParameters.put("last_Name", form.getLast_Name()); 
        namedParameters.put("ssn", form.getSsn()); 
        namedParameters.put("email", form.getEmail()); 
        namedParameters.put("student_Phone", form.getStudent_Phone()); 
        namedParameters.put("course", form.getCourse()); 
        namedParameters.put("due_Date", form.getDue_Date()); 
        namedParameters.put("description", form.getDescription()); 
        namedParameters.put("date_reg", DateToStr); 
        namedParameters.put("date_reg_search", DateToStr); 
        namedParameters.put("instructor_lname", form.getInstructor_lname()); 
        namedParameters.put("instructor_Fname", form.getInstructor_Fname()); 
        namedParameters.put("instructor_email", form.getInstructor_email());
        namedParameters.put("instructor_phone", form.getInstructor_phone());
        namedParameters.put("student_comments", form.getStudent_comments());
        namedParameters.put("citationStyle", form.getCitationStyle());
        namedParameters.put("citationStyleOther", form.getCitationStyleOther());
        namedParameters.put("student_Attachment", form.getStudent_Attachment());
        namedParameters.put("request_type", form.getRequest_type());
        namedParameters.put("attachment_state", form.getAttachment_state());
        
        try {
        	return namedParameterJdbcTemplate.update(sql, namedParameters);
        } catch(Exception e){
        	LOG.error("Error while executing query", e);
        	int i = -1;
        	return i;
        }        

    }
    
    public List<Form> request() {
        String sql = "SELECT TOP 10 * FROM Request";
        
        return jdbc.query(sql, new FormMapper());

    }

}