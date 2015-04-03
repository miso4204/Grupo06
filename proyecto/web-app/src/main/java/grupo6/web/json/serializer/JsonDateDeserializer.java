package grupo6.web.json.serializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

/**
 * Used to deserialize Java.util.Date, which is not a common JSON
 * type, so we have to create a custom serialize method;.
 */
public class JsonDateDeserializer  extends JsonDeserializer<Date> {
	
	/**
	 * (non-Javadoc)
	 * @see org.codehaus.jackson.map.JsonDeserializer#deserialize(org.codehaus.jackson.JsonParser, org.codehaus.jackson.map.DeserializationContext)
	 */
	 @Override
	    public Date deserialize(JsonParser jsonparser,
	            DeserializationContext deserializationcontext) throws IOException, JsonProcessingException {

	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	        String date = jsonparser.getText();
	        try {
	            return format.parse(date);
	        } catch (ParseException e) {
	            throw new RuntimeException(e);
	        }
	 }

}
