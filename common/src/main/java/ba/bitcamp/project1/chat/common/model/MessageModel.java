package ba.bitcamp.project1.chat.common.model;
//
import ba.bitcamp.project1.chat.common.model.meta.UserMetaModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public class MessageModel extends BaseModel {

	@JsonProperty("from")
	private UserMetaModel from;

	@JsonProperty("to")
	private UserMetaModel to;

	@JsonProperty("message")
	private String message;

	@JsonProperty("type")
	private String type;

	public MessageModel() {

	}

	public UserMetaModel getFrom() {
		return from;
	}

	public void setFrom(UserMetaModel from) {
		this.from = from;
	}

	public UserMetaModel getTo() {
		return to;
	}

	public void setTo(UserMetaModel to) {
		this.to = to;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
