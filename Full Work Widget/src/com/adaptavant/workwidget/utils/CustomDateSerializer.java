package com.adaptavant.workwidget.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

public class CustomDateSerializer extends JsonSerializer <Object>
	{
		@Override
        public void serialize( Object arg0 , JsonGenerator arg1 , SerializerProvider arg2 ) throws IOException , JsonProcessingException
	        {
	        	Date value = (Date)arg0;
	        	SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
	    		String formattedDate = formatter.format(value);

	    		arg1.writeString(formattedDate);
	        }
	}
