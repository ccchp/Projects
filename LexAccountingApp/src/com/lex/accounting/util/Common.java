package com.lex.accounting.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.lex.accounting.R;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

public class Common {

	public static void showDataPickerDialog(Context context, Button button){
		final Button btn = button;
		DatePickerDialog dialog = null;
//		AlertDialog.Builder builder = new AlertDialog.Builder(context);
//		View view = LayoutInflater.from(context).inflate(R.layout.view_datapicker_layout, null);
//		DatePicker picker = (DatePicker) view.findViewById(R.id.dataPicker);
//		builder.setView(view);
		Calendar calendar = Calendar.getInstance();
		OnDateSetListener dataSetListenter = new OnDateSetListener() {
			@Override
			public void onDateSet(DatePicker dataPicker, int year, int month, int day) {
				btn.setText(year + "-" + month + "-" + day);
			}
		};
		dialog = new DatePickerDialog(context, dataSetListenter, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
		dialog.show();
	}
	
	public static String getCurrentDate(){
		SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");       
		String date = sDateFormat.format(new java.util.Date());
		return date;
	}
	
}
