package com.lex.accounting.xmlparser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.lex.accounting.entity.ParentCategoryEntity;
import com.lex.accounting.entity.SubCategoryEntity;

import android.content.Context;
import android.util.Log;
import android.util.Xml;

public class CategoryXmlPullParser {

	public static List<ParentCategoryEntity> getDefaultIncomeCategoryList(Context context){
		XmlPullParser parser = Xml.newPullParser();
		InputStream is;
		List<ParentCategoryEntity> parentList = null;
		List<SubCategoryEntity> subList = null;
		String parentName = "";
		
		ParentCategoryEntity parentEntity = null;
		SubCategoryEntity subEntity = null;
		try {
			is = context.getAssets().open("default_income_category.xml");
			parser.setInput(is, "UTF-8");
			int event = parser.getEventType();
			while(event != XmlPullParser.END_DOCUMENT){
				switch (event) {
				case XmlPullParser.START_DOCUMENT:
					break;
				case XmlPullParser.START_TAG:
					if(parser.getName().equals("income")){
						parentList = new ArrayList<ParentCategoryEntity>();
					}else if(parser.getName().equals("parent")){
						parentEntity = new ParentCategoryEntity();
						parentName = parser.getAttributeValue(0);
						Log.i("lex", "parentName = " + parentName);
						parentEntity.setName(parentName);
						subList = new ArrayList<SubCategoryEntity>();
					}else if(parser.getName().equals("sub")){
						subEntity = new SubCategoryEntity();
						subEntity.setName(parser.nextText());
						Log.i("lex", "subName = " + subEntity.getName());
						subList.add(subEntity);
						event = parser.next();
					}
					break;
				case XmlPullParser.END_TAG:
					if(parser.getName().equals("parent")){
						parentEntity.setSubList(subList);
						parentList.add(parentEntity);
						subList = null;
						parentEntity = null;
					}else if(parser.getName().equals("income")){
						
					}
					break;
				default:
					break;
				}
				event = parser.next();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}
		return parentList;
	}
	public static List<ParentCategoryEntity> getDefaultExpenditureCategoryList(Context context){
		XmlPullParser parser = Xml.newPullParser();
		InputStream is;
		List<ParentCategoryEntity> parentList = null;
		List<SubCategoryEntity> subList = null;
		String parentName = "";
		
		ParentCategoryEntity parentEntity = null;
		SubCategoryEntity subEntity = null;
		try {
			is = context.getAssets().open("default_expenditure_category.xml");
			parser.setInput(is, "UTF-8");
			int event = parser.getEventType();
			while(event != XmlPullParser.END_DOCUMENT){
				switch (event) {
				case XmlPullParser.START_DOCUMENT:
					break;
				case XmlPullParser.START_TAG:
					if(parser.getName().equals("expenditure")){
						parentList = new ArrayList<ParentCategoryEntity>();
					}else if(parser.getName().equals("parent")){
						parentEntity = new ParentCategoryEntity();
						parentName = parser.getAttributeValue(0);
						Log.i("lex", "parentName = " + parentName);
						parentEntity.setName(parentName);
						subList = new ArrayList<SubCategoryEntity>();
					}else if(parser.getName().equals("sub")){
						subEntity = new SubCategoryEntity();
						subEntity.setName(parser.nextText());
						Log.i("lex", "subName = " + subEntity.getName());
						subList.add(subEntity);
						event = parser.next();
					}
					break;
				case XmlPullParser.END_TAG:
					if(parser.getName().equals("parent")){
						parentEntity.setSubList(subList);
						parentList.add(parentEntity);
						subList = null;
						parentEntity = null;
					}else if(parser.getName().equals("expenditure")){
						
					}
					break;
				default:
					break;
				}
				event = parser.next();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}
		return parentList;
	}
	
}
