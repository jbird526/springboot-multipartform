package edu.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The BaseController class implements common functionality for all Controller
 * classes. The <code>@ExceptionHandler</code> methods provide a consistent
 * response when Exceptions are thrown from <code>@RequestMapping</code>
 * annotated Controller methods.
 * 
 * @author Jason Pecsek
 */
public class BaseController {

	/**
     * The Logger for this class.
     */
    protected Logger LOG = LoggerFactory.getLogger(this.getClass());
	
}
