package yfu.practice.springboot.test;

import yfu.practice.springboot.enums.CcyCodeEnum;

public class TestEnum {
	
	public static void main(String[] args) {
		CcyCodeEnum usd = CcyCodeEnum.valueOf("USD");
		System.err.println(usd.getCcyCode());
		System.err.println(CcyCodeEnum.getCcyName("02"));
	}

}