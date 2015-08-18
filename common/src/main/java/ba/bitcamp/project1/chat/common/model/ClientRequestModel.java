package ba.bitcamp.project1.chat.common.model;
//
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public class ClientRequestModel extends BaseModel {

	@JsonProperty("nick_name")
	private String nickName;

	@JsonProperty("uid")
	private String uid;

	@JsonInclude(Include.NON_NULL)
	@JsonProperty("data")
	private Object data;

	public ClientRequestModel(String nickName) {
		this.nickName = nickName;
		this.uid = UUID.randomUUID().toString();
	}

	public ClientRequestModel() {
		this.uid = UUID.randomUUID().toString();
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
