package edu.test;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
public class FormController extends BaseController {
	
	@Autowired
	public FormRepository formRepo;
    
	@Autowired 
    private JavaMailSender javaMailSender;
	
	protected static final ResponseEntity<String> FAILED_TO_WRITE = new ResponseEntity<>("{'Response': 'Failed to write file'}", new HttpHeaders(),HttpStatus.OK);
	protected static final ResponseEntity<String> FILE_EMPTY = new ResponseEntity<>("{'Response': 'Uploaded file empty'}", new HttpHeaders(),HttpStatus.OK);
	
	//http://stackoverflow.com/questions/27294838/how-to-process-a-multipart-request-consisting-of-a-file-and-a-json-object-in-spr
	@RequestMapping(value="/", method=RequestMethod.POST )
    public @ResponseBody Object singleSave(
    		//Allow no file to be attached on POST by required=false
    		@RequestParam(value = "file", required=false) MultipartFile file, 
    		@RequestParam(value = "form", required=true) String form ) throws IOException{
		
		//Create object from form JSON string
		Form jsonForm = new ObjectMapper().readValue(form, Form.class);		
		
		String attachment = jsonForm.getAttachment_state();
		
		//If file is not null and form value attachment_state equals "No", save the file to local disk 
		if(file != null && attachment.equals("No")){
			//Save file to local disk and use returned file name to add to form values
			String fileNameReturned = Utility.SaveFile(file);
			//If file was not saved, do not insert the form record. Return from controller call.
			if (fileNameReturned == "Failed") {
				return FAILED_TO_WRITE;
			} else if(fileNameReturned == "Empty") {
				return FILE_EMPTY;
			}
			
			//Set filename so it can be added to the database record. 
			//May have integer appended to the original file name if it already exists.
			jsonForm.setStudent_Attachment(fileNameReturned);
		}
				
		//Pass form values to repository to be inserted in database
		//return formRepo.insert(jsonForm);
		formRepo.insert(jsonForm);
		
		//String mailTo = jsonForm.getEmail();
		
		//TODO: Send email to recipients
		String mailTo = "jason.pecsek@umuc.edu";
		String mailReplyTo = "jason.pecsek@umuc.edu";
		String mailFrom = "jason.pecsek@umuc.edu";
		String mailSubject = "Form Handler Test Subject";
		String mailText = "Form Handler Test Text";
		
		Email email = new Email(javaMailSender);
		email.sendMail(mailTo, mailReplyTo, mailFrom, mailSubject, mailText);
		
		return "mail/send";
		
    	
    }
	
		@RequestMapping(value="/email", method = RequestMethod.GET)
	    public String send(HttpServletRequest request) {
			
			String mailTo = "me@localhost";
			String mailReplyTo = "someone@localhost";
			String mailFrom = "someone@localhost";
			String mailSubject = "someone@localhost";
			String mailText = "someone@localhost";
			
			Email email = new Email(javaMailSender);
			email.sendMail(mailTo, mailReplyTo, mailFrom, mailSubject, mailText);
			return "mail/send";
			
	    }


	//Test mapping
	@RequestMapping(value="/request", method = RequestMethod.GET,  produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Form> request() {				
		return formRepo.request();	        											    
    }
	
	
	
}