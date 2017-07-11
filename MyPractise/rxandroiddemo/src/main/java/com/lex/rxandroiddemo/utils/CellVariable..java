package com.lex.rxandroiddemo.utils;

import Newtonsoft.Json.*;

public class CellVariable
{
//C# TO JAVA CONVERTER TODO TASK: There is no preprocessor in Java:
		///#region //表及字段变量
	private static final String VW_BIZ_FIELD_REPORT = "VW_BIZ_FIELD_REPORT";
	private static final String FK_REPORT = "FK_REPORT";
	private static final String FK_REPORT_TABLE = "FK_REPORT_TABLE";
	private static final String FK_REPORT_DISPLAY = "FK_REPORT_DISPLAY";
	private static final String F_ID = "F_ID";
	private static final String FK_DICTIONARY = "FK_DICTIONARY";
	private static final String F_CELL_POSITION = "F_CELL_POSITION";
	private static final String FK_FIELD_NAME = "FK_FIELD_NAME";
	private static final String F_IS_UNIQUE = "F_IS_UNIQUE";
	private static final String F_IS_EXTERNAL = "F_IS_EXTERNAL";
	private static final String FK_EXTERNAL_TABLE_NAME = "FK_EXTERNAL_TABLE_NAME";
	private static final String FK_EXTERNAL_FIELD = "FK_EXTERNAL_FIELD";
	private static final String FK_EXTERNAL_FIELD_NAME = "FK_EXTERNAL_FIELD_NAME";
	private static final String FK_DICTIONARY_NAME = "FK_DICTIONARY_NAME";
	private static final String PK_ID = "PK_ID";
	private static final String TB_BIZ_REPORT = "TB_BIZ_REPORT";
	private static final String F_PAGE_COUNT = "F_PAGE_COUNT";
	private static final String VW_BIZ_VERIFY = "VW_BIZ_VERIFY";
	private static final String FK_RECORD = "FK_RECORD";
	private static final String F_COMMENT = "F_COMMENT";
	private static final String F_URL = "F_URL";
	private static final String VW_BIZ_PHOTO = "VW_BIZ_PHOTO";
	private static final String F_NAME = "F_NAME";
	private static final String FK_DOCUMENT = "FK_DOCUMENT";
//C# TO JAVA CONVERTER TODO TASK: There is no preprocessor in Java:
		///#endregion
	private IDAHelper _dao = DAFactory.CreateHelper();
	private JsonHelper _json = new JsonHelper();
	private String _reportName; //存储表名
	private java.util.HashMap<String, java.util.HashMap<String, String>> _feildDictionary = new java.util.HashMap<String, java.util.HashMap<String, String>>();
	private StreamWriter writer;
	private static final String F_CODE = "f_code";
	private static final String F_TERM = "f_term";
	private static final String F_FLOATINGS = "F_FLOATINGS";
	private static final String F_CONTENT = "F_CONTENT";
	private static final String F_CONTENT_NOCOMMENT = "F_CONTENT_NOCOMMENT";
	private static final String FK_REPORT_STYLE = "FK_REPORT_STYLE";
	private static final String F_ROW = "F_ROW";
	private static final String F_COL = "F_COL";

	public final int GetRecordCount(int reportID, String fieldValue)
	{
		int count = 0;
		String sql = String.format("select * from %1$s  where %2$s='%3$s'", VW_BIZ_FIELD_REPORT, FK_REPORT, reportID);
		DataTable dtRreport = _dao.ExecuteDataTable(sql);
		if (dtRreport.Rows.size() > 0)
		{
			String reportName = dtRreport.Rows[0][FK_REPORT_DISPLAY].toString();
			String whereSql = GetFieldValueSql(fieldValue);
			if (whereSql.equals(""))
			{
				sql = String.format("select count(0) from %1$s", reportName);
			}
			else
			{
				sql = String.format("select count(0) from %1$s where %2$s", reportName, whereSql);
			}
			DataTable dtRecordCount = _dao.ExecuteDataTable(sql);
			count= Integer.parseInt(dtRecordCount.Rows[0][0].toString());
		}
		return count;
	}

	/**
	 读取
	 
	 @param reportID 报表ID
	 @param fieldValue 字段值（例如：'f_name':'xxx','f_id':'123'）
	 @param pageNum 页码数
	 @return 
	*/
	public final String LoadVariable(int reportID, String fieldValue, int pageNum)
	{
		VariableEntity variableEntity = new VariableEntity();
		String result = "";
		java.util.HashMap<String, String> positionValue = new java.util.HashMap<String, String>();
		try
		{
			//查询表信息
			String sql = String.format("select * from %1$s  where %2$s='%3$s'", VW_BIZ_FIELD_REPORT, FK_REPORT, reportID);
			DataTable reportInfo = _dao.ExecuteDataTable(sql);
			if (reportInfo.Rows.size() == 0)
			{
				return result;
			}
			//主键字段
			DataRow[] keyFieldRows = reportInfo.Select(F_IS_UNIQUE + "='1'");
			String[] keyField = new String[keyFieldRows.length];
			for (int i = 0; i < keyFieldRows.length; i++)
			{
				keyField[i] = keyFieldRows[i][FK_FIELD_NAME].toString();
			}
			//每页记录数
			sql = String.format("select %1$s from %2$s  where %3$s='%4$s'", F_PAGE_COUNT, TB_BIZ_REPORT, PK_ID, reportID);
			DataTable pageCountDt = _dao.ExecuteDataTable(sql);
			int pageCount = Integer.parseInt(pageCountDt.Rows[0][F_PAGE_COUNT].toString());
			//根据页码计算记录范围
			int minIndex = (pageNum - 1) * pageCount;
			int maxIndex = pageNum * pageCount;

			String reportName = reportInfo.Rows[0][FK_REPORT_DISPLAY].toString();
			//解析字段值
			String whereSql = "where " + GetFieldValueSql(fieldValue);
			//查询记录条数
			sql = String.format("select count(0) from %1$s %2$s", reportName, whereSql);
			DataTable recordCount = _dao.ExecuteDataTable(sql);
			variableEntity.RecordCount = Integer.parseInt(recordCount.Rows[0][0].toString());
			//查询指定范围内的记录条数
			sql = String.format("select * from(select t.*,rownum rn from (select * from %1$s %2$s order by %3$s) t ) s where s.rn>%4$s and s.rn<=%5$s", reportName, whereSql, PK_ID, minIndex, maxIndex);
			DataTable recodes = _dao.ExecuteDataTable(sql);
			if (recodes.Rows.size() == 0 && pageNum > 1) //只返回表头
			{
				sql = String.format("select * from %1$s ", reportName);
				recodes = _dao.ExecuteDataTable(sql);
				for (int i = 0; i < reportInfo.Rows.size(); i++)
				{
					String fieldName = reportInfo.Rows[i][FK_FIELD_NAME].toString();
					if (fieldName.equals(""))
					{
						continue;
					}
					//读取字典值
					java.util.HashMap<String, String> fieldDictionary = null;
					if (!reportInfo.Rows[i][FK_DICTIONARY_NAME].toString().equals(""))
					{
						fieldDictionary = GetDictionary(reportInfo.Rows[i][FK_DICTIONARY_NAME].toString(), "PK_ID", "F_NAME");
					}
					String cellPosition = reportInfo.Rows[i][F_CELL_POSITION].toString();
					if (cellPosition.indexOf(":") < 0) //离散的
					{
						String value = recodes.Rows[0][fieldName].toString();
						if (fieldDictionary != null && fieldDictionary.containsKey(value))
						{
							value = fieldDictionary.get(value);
						}
						if (!value.equals(""))
						{
							positionValue.put(cellPosition, value);
						}
					}
				}
			}
			else if (recodes.Rows.size() > 0)
			{
				for (int i = 0; i < reportInfo.Rows.size(); i++)
				{
					String fieldName = reportInfo.Rows[i][FK_FIELD_NAME].toString();
					if (fieldName.equals(""))
					{
						continue;
					}
					//读取字典值
					java.util.HashMap<String, String> fieldDictionary = null;
					if (!reportInfo.Rows[i][FK_DICTIONARY_NAME].toString().equals(""))
					{
						fieldDictionary = GetDictionary(reportInfo.Rows[i][FK_DICTIONARY_NAME].toString(), "PK_ID", "F_NAME");
					}
					String cellPosition = reportInfo.Rows[i][F_CELL_POSITION].toString();
					if (cellPosition.indexOf(":") < 0) //离散的 A1
					{
						String value = recodes.Rows[0][fieldName].toString();
						if (fieldDictionary != null && fieldDictionary.containsKey(value))
						{
							value = fieldDictionary.get(value);
						}
						if (!value.equals(""))
						{
							positionValue.put(cellPosition, value);
						}
					}
					else //连续的A1:A10或A1:E1
					{
						CellPosition cellPositionClass = new CellPosition(cellPosition);
						int cellCount = Math.min(recodes.Rows.size(), cellPositionClass.getLength());
						for (int j = 0; j < cellCount; j++)
						{
							String position = cellPositionClass.Current;
							cellPositionClass.Next();
							String value = recodes.Rows[j][fieldName].toString();
							if (fieldDictionary != null && fieldDictionary.containsKey(value))
							{
								value = fieldDictionary.get(value);
							}
							if (!value.equals(""))
							{
								positionValue.put(position, value);
							}
						}
					}
				}
			}
			variableEntity.Variable = positionValue;
			result = JsonConvert.SerializeObject(variableEntity);
		}
		catch (RuntimeException ex)
		{
		}
		return result;
	}

	/**
	 保存
	 
	 @param reportID 报表ID
	 @param varialbeValueint 变量值 {"E4":"11","B7":"999","B8":"998","E2":"实施阶段"}
	 @param pageNum 页码数
	 @return 
	*/
	public final boolean SaveVariable(int reportID, String varialbeValue, int pageNum)
	{
		try
		{
			//位置、值(如：A1,2)
			java.util.HashMap<String, String> positionValue = GetPositionValue(varialbeValue);
			//离散字段（如：f_code：A1）
			java.util.HashMap<String, String> discreteFieldPosition = null;
			//连续字段（如：f_code：A1:A3）
			RefObject<java.util.HashMap<String, String>> tempRef_discreteFieldPosition = new RefObject<java.util.HashMap<String, String>>(discreteFieldPosition);
			java.util.HashMap<String, CellPosition> fieldCellPosition = GetFeildCellPosition((new Integer(reportID)).toString(), tempRef_discreteFieldPosition);
			discreteFieldPosition = tempRef_discreteFieldPosition.argvalue;
			//创建要保存的数据表
			DataTable records = new DataTable();
			//插入语句中的字段部分
			String fieldSql = "";
			//位置长度（A1:A10长度为10）
			int lenth = 0;
			//处理连续字段
			for (java.util.Map.Entry<String, CellPosition> item : fieldCellPosition.entrySet())
			{
				if (lenth == 0)
				{
					lenth = item.getValue().getLength();
				}
				records.Columns.Add(item.getKey(), Class.forName("System.String"));
				fieldSql = fieldSql + item.getKey() + ",";
			}
			//取值插入记录表
			for (int i = 0; i < lenth; i++)
			{
				DataRow dr = records.NewRow();
				boolean toInsertRow = false;
				for (java.util.Map.Entry<String, CellPosition> item : fieldCellPosition.entrySet())
				{
					String field = item.getKey();
					CellPosition cellPosition = item.getValue();
					if (positionValue.containsKey(cellPosition.Current))
					{
						String value = positionValue.get(cellPosition.Current);
						//获取字典对应的值
						if (_feildDictionary.containsKey(field))
						{
							if (_feildDictionary.get(field).containsKey(value))
							{
								value = _feildDictionary.get(field).get(value);
							}
						}
						toInsertRow = true;
						dr[field] = value;
					}
					cellPosition.Next();
				}
				if (toInsertRow == true)
				{
					records.Rows.Add(dr);
				}
			}

//C# TO JAVA CONVERTER TODO TASK: There is no preprocessor in Java:
				///#region 处理离散元素
			String keySql = "";
			String discreteSql = "";
			for (java.util.Map.Entry<String, String> item : discreteFieldPosition.entrySet())
			{
				if (positionValue.containsKey(item.getValue()))
				{
					String value = positionValue.get(item.getValue());
					String field = item.getKey();
					//获取字典对应的值
					if (_feildDictionary.containsKey(field))
					{
						if (_feildDictionary.get(field).containsKey(value))
						{
							value = _feildDictionary.get(field).get(value);
						}
					}
					DataColumn discreteColumn = new DataColumn();
					discreteColumn.DataType = Class.forName("System.String");
					discreteColumn.ColumnName = field;
					discreteColumn.DefaultValue = value;
					fieldSql = fieldSql + field + ",";
					records.Columns.Add(discreteColumn);
					discreteSql = discreteSql + field + "='" + value + "',";
					//先删除已有记录
					if (field.toLowerCase().equals(F_CODE) || field.toLowerCase().equals(F_TERM))
					{
						keySql = keySql + field + " = '" + value + "' and ";
					}
				}
			}
			//只有离散字段，没有连续字段
			if (records.Columns.size() > 0 && records.Rows.size() == 0)
			{
				records.Rows.Add(records.NewRow());
			}
//C# TO JAVA CONVERTER TODO TASK: There is no preprocessor in Java:
				///#endregion

			if (!fieldSql.equals(""))
			{
				fieldSql = fieldSql.substring(0, fieldSql.length() - 1);
			}
			//查询每页记录数
			String sql = String.format("select %1$s from %2$s  where %3$s='%4$s'", F_PAGE_COUNT, TB_BIZ_REPORT, PK_ID, reportID);
			DataTable pageCountDt = _dao.ExecuteDataTable(sql);
			int pageCount = Integer.parseInt(pageCountDt.Rows[0][F_PAGE_COUNT].toString());

			//根据页码计算记录范围
			int minIndex = (pageNum - 1) * pageCount;
			int maxIndex = pageNum * pageCount;

			//按主键删除已有记录
			if (!keySql.equals(""))
			{
				keySql = keySql.substring(0, keySql.length() - 5);
				String deleteSql = String.format("delete from %1$s where %2$s in(select %2$s from (SELECT ROWNUM rn,t.* FROM (select * from %1$s where %3$s)t )b where  b.rn>%4$s and  b.rn<%5$s)", _reportName, PK_ID, keySql, minIndex, maxIndex + 1);
				_dao.ExecuteNonQuery(deleteSql);
			}
			else
			{
				return false;
			}
			java.util.ArrayList<String> sqlList = new java.util.ArrayList<String>();
			//遍历记录，插入数据库
			for (int i = 0; i < records.Rows.size(); i++)
			{
				String valueSql = "";
				for (int j = 0; j < records.Columns.size(); j++)
				{
					valueSql = valueSql + "'" + records.Rows[i][j].toString() + "',";
				}
				if (!valueSql.equals(""))
				{
					valueSql = valueSql.substring(0, valueSql.length() - 1);
				}
				sql = "insert into " + _reportName + " (" + fieldSql + ") values (" + valueSql + ")";
				sqlList.add(sql);
			}
			if (sqlList.size() > 0)
			{
				_dao.ExecuteNonQuery(sqlList.toArray(new String[]{}));
			}
			//更新所有页的离散字段
			if (!discreteSql.equals("") && !keySql.equals(""))
			{
				discreteSql = discreteSql.substring(0, discreteSql.length() - 1);
				sql = String.format("update %1$s set %2$s where %3$s", _reportName, discreteSql, keySql);
				_dao.ExecuteNonQuery(sql);
			}
			return true;
		}
		catch (RuntimeException ex)
		{
			return false;
		}
	}

	/**
	 加载样式
	 
	 @param reportID reportID
	 @param withComment 是否包含注释信息
	 @return 包含样式信息的json语句
	*/
	public final String LoadStyle(int reportID, boolean withComment)
	{
		String jsonStr = "";
		Json json = new Json();

		String selectStyleSql = String.format("select %2$s,%3$s,%4$s from %1$s WHERE %5$s = %6$s", TB_BIZ_REPORT_STYLE, PK_ID, F_NAME, F_FLOATINGS, FK_REPORT, reportID);
		DataTable styleInfo = _dao.ExecuteDataTable(selectStyleSql);
		if (styleInfo != null && styleInfo.Rows.size() > 0)
		{
			String styleId = styleInfo.Rows[0][PK_ID].toString();
			json.setName(styleInfo.Rows[0][F_NAME].toString());
			String floatings = styleInfo.Rows[0][F_FLOATINGS].toString();
			if (floatings != null)
			{
				String[] mergeCells = floatings.split("[[]", -1);
				if (mergeCells != null && mergeCells.length > 0)
				{
					Floating[] floating = new Floating[mergeCells.length - 1];
					for (int i = 1; i < mergeCells.length; i++)
					{
						String[] indexs = mergeCells[i].split("[,]", -1);
						if (indexs != null && indexs.length > 2)
						{
							floating[i - 1] = new Floating();
							floating[i - 1].Sheet = 0;
							floating[i - 1].Ftype = "meg";
							floating[i - 1].Json = "[" + mergeCells[i].split("[]]", -1)[0] + "]";
							floating[i - 1].setName("0$" + indexs[0] + "$" + indexs[1] + "$" + indexs[2] + "$" + indexs[3].split("[]]", -1)[0]);
						}
					}
					json.Floatings = floating;
				}
			}
			String contentField = F_CONTENT;
			if (!withComment)
			{
				contentField = F_CONTENT_NOCOMMENT;
			}
			String selectCellSql = String.format("select %2$s,%3$s,%4$s from %1$s WHERE %5$s = %6$s", VW_BIZ_CELL, F_ROW, F_COL, contentField, FK_REPORT_STYLE, styleId);
			DataTable cellInfo = _dao.ExecuteDataTable(selectCellSql);
			if (cellInfo != null && cellInfo.Rows.size() > 0)
			{
				Cell[] cell = new Cell[cellInfo.Rows.size()];
				for (int i = 0; i < cellInfo.Rows.size(); i++)
				{
					cell[i] = new Cell();
					cell[i].Row = Integer.parseInt(cellInfo.Rows[i][F_ROW].toString());
					cell[i].Col = Integer.parseInt(cellInfo.Rows[i][F_COL].toString());
					cell[i].Json = cellInfo.Rows[i][contentField].toString();
				}
				Sheet[] sheet = new Sheet[1];
				sheet[0] = new Sheet();
				sheet[0].Id = 0;
				sheet[0].Actived = true;
				json.Sheets = sheet;
				json.Cells = cell;
			}
		}
		jsonStr = _json.ObjectToJson(json);
		return jsonStr;
	}

	private String GetFieldValueSql(String fieldValue)
	{
		String whereSql = "";
		String[] arrFV = fieldValue.split("[,]", -1);
		for (int i = 0; i < arrFV.length; i++)
		{
			if (!arrFV[i].equals(""))
			{
				String[] FV = arrFV[i].split("[:]", -1);
				if (FV.length == 2)
				{
					if (!FV[1].replace("'", "").trim().equals("0"))
					{
						FV[0] = FV[0].replace("'", "");
						whereSql += FV[0] + " = " + FV[1] + " and ";
					}
				}
			}
		}
		if (whereSql.length() > 3)
		{
			whereSql = whereSql.substring(0, whereSql.length() - 4);
		}
		return whereSql;
	}

	private java.util.HashMap<String, String> GetPositionValue(String varialbeValue)
	{
		java.util.HashMap<String, String> positionValue = new java.util.HashMap<String, String>();
		varialbeValue = varialbeValue.replace("{", "");
		varialbeValue = varialbeValue.replace("}", "");
		String[] varialbeArry = varialbeValue.split("[,]", -1);
		for (int i = 0; i < varialbeArry.length; i++)
		{
			if (!varialbeArry[i].equals(""))
			{
				String[] positionValueSplit = varialbeArry[i].split("[:]", -1);
				if (positionValueSplit.length == 2)
				{
					positionValueSplit[0] = positionValueSplit[0].replace("\"", "");
					positionValueSplit[1] = positionValueSplit[1].replace("\"", "");
					if (!positionValueSplit[1].equals(""))
					{
						positionValue.put(positionValueSplit[0], positionValueSplit[1]);
					}
				}
			}
		}
		return positionValue;
	}

	/**
	 读取字典
	 @param tableName 表名
	 @param key key值字段名
	 @param value value值字段名
	 @return 
	*/
	private java.util.HashMap<String, String> GetDictionary(String tableName, String key, String value)
	{
		java.util.HashMap<String, String> dicResult = new java.util.HashMap<String, String>();
		String sql = String.format("select * from " + tableName);
		DataTable dtDictionary = _dao.ExecuteDataTable(sql);
		for (int i = 0; i < dtDictionary.Rows.size(); i++)
		{
			try
			{
				dicResult.put(dtDictionary.Rows[i][key].toString(), dtDictionary.Rows[i][value].toString());
			}
			catch (Exception e)
			{
			}
		}
		return dicResult;
	}

	private java.util.HashMap<String, CellPosition> GetFeildCellPosition(String reportID, RefObject<java.util.HashMap<String, String>> discreteFieldPosition)
	{
		discreteFieldPosition.argvalue = new java.util.HashMap<String, String>();
		_feildDictionary.clear();
		java.util.HashMap<String, CellPosition> feildCellPosition = new java.util.HashMap<String, CellPosition>();
		//查询字段信息
		String sql = String.format("select %1$s,%2$s,%3$s,%4$s,%5$s,%6$s from %7$s t where %8$s='%9$s'", F_CELL_POSITION, FK_FIELD_NAME, F_IS_UNIQUE, FK_REPORT_TABLE, FK_DICTIONARY_NAME, F_IS_EXTERNAL, VW_BIZ_FIELD_REPORT, FK_REPORT, reportID);
		DataTable fieldInfo = _dao.ExecuteDataTable(sql);
		if (fieldInfo.Rows.size() > 0)
		{
			_reportName = fieldInfo.Rows[0][FK_REPORT_TABLE].toString();
		}

		for (int i = 0; i < fieldInfo.Rows.size(); i++)
		{
			//非主键外部字段，不保存
			if (!fieldInfo.Rows[i][F_IS_UNIQUE].toString().equals("1") && fieldInfo.Rows[i][F_IS_EXTERNAL].toString().equals("1"))
			{
				continue;
			}
			String cellPosition = fieldInfo.Rows[i][F_CELL_POSITION].toString();
			String feildName = fieldInfo.Rows[i][FK_FIELD_NAME].toString();
			//读取字典值
			String dictionaryName = fieldInfo.Rows[i][FK_DICTIONARY_NAME].toString();
			if (!dictionaryName.equals(""))
			{
				java.util.HashMap<String, String> dic = GetDictionary(dictionaryName, "F_NAME", "PK_ID");
				if (dic.size() > 0)
				{
					_feildDictionary.put(feildName, dic);
				}
			}
			if (cellPosition.indexOf(":") < 0) //离散
			{
				discreteFieldPosition.argvalue.put(feildName, cellPosition);
			}
			else //连续
			{
				feildCellPosition.put(feildName, new CellPosition(cellPosition));
			}
		}
		return feildCellPosition;
	}
}