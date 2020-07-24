package com.empresa.course.enums;

public enum OrderStatus {	
	WAITING_PAYMENT(1),
	PAID(2),
	SHIPPED(3),
	DELIVERED(4),
	CANCELED(5);
	
	//entre parênteses enum(code enum) determinamos os códigos de cada enum
	
	private int code;
	
	private OrderStatus(int code) {
		this.code = code;
		//construtor do tipo enum é private
	}
	
	public int getCode() {
		return code;		
	}
	
	public static OrderStatus valueOf(int code) {
		for(OrderStatus orderStatus : OrderStatus.values()) {
			//enum.values() retorna um arranjo de enums
			if(orderStatus.getCode() == code)
				return orderStatus;
		}
		
		throw new IllegalArgumentException("Invalid order status code.");
	}	
}
