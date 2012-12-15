package com.example.logistics_ui.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * 
 * 物流追踪信息
 * @author zhenggangji
 *
 */
public class TrackInfo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String time;
	
	private String context;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}
	
	
	public String toString(){
		return ReflectionToStringBuilder.toString(this);
	}
	
}
