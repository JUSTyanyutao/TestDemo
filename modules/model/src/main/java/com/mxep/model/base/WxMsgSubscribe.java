package com.mxep.model.base;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the wx_msg_subscribe database table.
 * 
 */
@Entity
@Table(name="wx_msg_subscribe")
public class WxMsgSubscribe implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String content;

    public WxMsgSubscribe() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}