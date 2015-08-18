package ba.bitcamp.project1.chat.common.json;
//
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONMapper {
	
	private static ObjectMapper objectMapper;

	public static ObjectMapper getObjectMapper() {
		
		if(JSONMapper.objectMapper == null)
			JSONMapper.objectMapper = new ObjectMapper();
		
		return JSONMapper.objectMapper;
	}

}
