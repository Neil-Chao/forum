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

			//指定日期的格式

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			//将源数据转为指定日期格式

			result = sdf.parse(source);

		} catch (ParseException e) {

			e.printStackTrace();

		}

		//返回转换后的时间格式日期

		return result;

	}

 

	
}
