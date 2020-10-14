package com.forum.model.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class MyConvert implements Converter<String,Date> {




 

	@Override

	public Date convert(String source) {

		Date result = null;

		try {

			//ָ�����ڵĸ�ʽ

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			//��Դ����תΪָ�����ڸ�ʽ

			result = sdf.parse(source);

		} catch (ParseException e) {

			e.printStackTrace();

		}

		//����ת�����ʱ���ʽ����

		return result;

	}

 

	
}
