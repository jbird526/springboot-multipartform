package edu.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.NullNode;



@RestController
//@RequestMapping( "/test" )
public class FormTestController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger( FormTestController.class );
    
    protected static final ResponseEntity<JsonNode> UNAUTHORIZED_JSON_NODE = new ResponseEntity<>( BooleanNode.FALSE, HttpStatus.UNAUTHORIZED );
    protected static final ResponseEntity<JsonNode> FORBIDDEN_JSON_NODE = new ResponseEntity<>( BooleanNode.FALSE, HttpStatus.FORBIDDEN );
    protected static final ResponseEntity<JsonNode> NO_CONTENT_JSON_NODE = new ResponseEntity<>( NullNode.getInstance(), HttpStatus.NO_CONTENT );
    protected static final ResponseEntity<JsonNode> NOT_FOUND_JSON_NODE = new ResponseEntity<>( NullNode.getInstance(), HttpStatus.NOT_FOUND );
    protected static final ResponseEntity<JsonNode> NOT_IMPLEMENTED_JSON_NODE = new ResponseEntity<>( NullNode.getInstance(), HttpStatus.NOT_IMPLEMENTED );
    protected static final ResponseEntity<JsonNode> BAD_REQUEST_JSON_NODE = new ResponseEntity<>( NullNode.getInstance(), HttpStatus.BAD_REQUEST );
    protected static final ResponseEntity<JsonNode> AMAZON_SERVICE_EXCEPTION_JSON_NODE = new ResponseEntity<>( NullNode.getInstance(),
            HttpStatus.INTERNAL_SERVER_ERROR );

    //http://localhost:8080/test
    @RequestMapping( value = "/test", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonNode> test(final @RequestBody Test test) {
    	LOG.debug( "Test debug: ", test );
    	
    	return getFormResponseEntityForAnObject( test );
    }
    //http://localhost:8080/version
    @RequestMapping( value = "/version", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<JsonNode> version() {
    	//String version = getVersionFromProp();
    	
    	ObjectMapper mapper = new ObjectMapper();
    	String json = "{\"version\":\"1.0\"}";
    	Map<String, Object> map = new HashMap<String, Object>();

		// convert JSON string to Map
		try {
			map = mapper.readValue(json, new TypeReference<Map<String, String>>(){});
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	return getFormResponseEntityForAMap( map );
    }
    
    private ResponseEntity<JsonNode> getFormResponseEntityForAnObject( final Object test ) {

        if ( test == null ) {
            return NO_CONTENT_JSON_NODE;
        }

        final ObjectMapper mapper = new ObjectMapper();
        final JsonNode value = mapper.valueToTree( test );
        if ( value == null ) {
            return NO_CONTENT_JSON_NODE;
        }
        return new ResponseEntity<JsonNode>( value, HttpStatus.OK );
    }
    
    private ResponseEntity<JsonNode> getFormResponseEntityForAMap( final Map<String, Object> formValues ) {

        if ( formValues == null ) {
            return NO_CONTENT_JSON_NODE;
        }

        final ObjectMapper mapper = new ObjectMapper();
        final JsonNode value = mapper.valueToTree( formValues );
        if ( value == null ) {
            return NO_CONTENT_JSON_NODE;
        }
        return new ResponseEntity<JsonNode>( value, HttpStatus.OK );
    }

}
