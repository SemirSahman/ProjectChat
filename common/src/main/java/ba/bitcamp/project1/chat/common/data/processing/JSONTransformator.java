package ba.bitcamp.project1.chat.common.data.processing;

import java.io.IOException;

import ba.bitcamp.project1.chat.common.json.JSONMapper;
import ba.bitcamp.project1.chat.common.model.BaseModel;
import ba.bitcamp.project1.chat.common.model.ClientRequestModel;
import ba.bitcamp.project1.chat.common.model.MessageModel;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class JSONTransformator {
	
	public static Object convertString(String data) throws JsonParseException, JsonMappingException, IOException {
		BaseModel baseModel = JSONMapper.getObjectMapper().readValue(data, BaseModel.class);
		
		if(baseModel instanceof ClientRequestModel) {
			return (ClientRequestModel) baseModel;
		} else if(baseModel instanceof MessageModel) {
			return (MessageModel) baseModel;
		}
		
		return null;
	}

}
