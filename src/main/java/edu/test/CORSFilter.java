package edu.test;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component( "corsFilter" )
//@PropertySource( "classpath:EnterpriseDataWarehouse.properties" )
public class CORSFilter implements Filter {

    private static final Logger LOG = LoggerFactory.getLogger( CORSFilter.class );
    private static final String ORIGIN_PREFIX = "cors.origin";

    //@Autowired
    //private Environment env;

    //@Override
    //public void init( final FilterConfig filterConfig ) throws ServletException {
    //    LOG.trace( "init" );
    //}

    //@Override
    public void doFilter( final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain chain ) //
            throws IOException, ServletException {
        //LOG.trace( "doFilter" );
        HttpServletResponse response = ( HttpServletResponse ) servletResponse;
        //final String origin = env.getRequiredProperty( ORIGIN_PREFIX);

        //if ( StringUtils.isNotBlank( origin ) ) {
            //LOG.debug( "Adding CORS headers to response object." );

            response.setHeader( "Access-Control-Allow-Methods", "POST, GET, OPTIONS" );
            response.setHeader( "Access-Control-Max-Age", "3600" );
            response.setHeader( "Access-Control-Allow-Headers", "x-requested-with, Content-Type" );

            //LOG.debug( "Setting Access-Control-Allow-Origin to: {}", "*" );
            response.setHeader( "Access-Control-Allow-Origin", "https://ltidev.umuc.edu" );
            
            response.setHeader( "Access-Control-Allow-Credentials", "true" );
        //} else {
         //   LOG.error( "Unable to locate specified origin \"{}\" in properties file.", ORIGIN_PREFIX );
        //}
        //LOG.trace( "Calling next filter in the chain." );
        chain.doFilter( servletRequest, servletResponse );
    }

    //@Override
    public void destroy() {
        LOG.trace( "destroy" );
    }

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}