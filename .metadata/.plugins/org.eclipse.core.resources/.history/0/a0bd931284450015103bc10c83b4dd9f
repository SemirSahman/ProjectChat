package ba.bitcamp.project1.chat.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ProtocolEnum {

	INIT_CONNECTION("INIT_CONNECTION"), CLOSE_CONNECTION("CLOSE_CONNECTION"), SEND_MSG("SEND_MESSAGE"), SEND_IMG("SEND_IMG"), JOIN_GROUP_CHAT("JOIN_GROUP_CHAT"), 
	SUCCESS("SUCCESS"), FAIL("FAIL"), ALREADY_CONNECTED("ALREADY_REGISTERED"), GET_CONNECTED_USERS("GET_CONNECTED_USERS");
	
	private String value;
	
	private ProtocolEnum(String value) {
		this.value = value;
	}
	
	@JsonCreator
	public static ProtocolEnum fromValue(String value) {
		return valueOf(value);
	}
	
	@JsonValue
	public String getValue() {
		return this.value;
	}
	
}
