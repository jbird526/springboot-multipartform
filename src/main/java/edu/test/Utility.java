package edu.test;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

public class Utility {
	
	private static String FileLocation = "/Users/jpecsek/Development/gitroot/MultiPartFormHandler/src/main/resources/webapps/";

	static String SaveFile(MultipartFile file) throws IOException {
		
		//If file not empty
		if (!file.isEmpty()) {
			//Get filename
			String fileName = file.getOriginalFilename();
			
			BufferedOutputStream buffStream = null;
				//Write file to disk
				try {
					//Check for existence of file and increment if necessary								        
					File f = new File(FileLocation + fileName);

					String baseName = FilenameUtils.getBaseName( f.getName() );
				    String extension = FilenameUtils.getExtension( f.getName() );
				    int counter = 1;
				    while(f.exists())
				    {
				        f = new File( f.getParent(), baseName + (counter++) + "." + extension );
				    }
				    //Get new file name to be submitted back to form value. 
				    //If file does not already exist on the file system, the new name and sent name will be the same.
					String newFileName = f.getName();					
	                byte[] bytes = file.getBytes();
	                buffStream = new BufferedOutputStream(new FileOutputStream(f));
	                
	                buffStream.write(bytes);
	                buffStream.close();
	                
	                //Return fileName string as it needs to be added to the form values for "student_attachment" column
	                return newFileName;
	            } catch (Exception e) {
	                return "Failed";
	            } 
			
		} else {
            return "Empty";
        }
		
	}	
	
	
}
