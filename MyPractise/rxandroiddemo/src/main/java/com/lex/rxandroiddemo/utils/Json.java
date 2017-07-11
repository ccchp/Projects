package com.lex.rxandroiddemo.utils;

/**
 * Created by Lex lex on 2017/4/5.
 */

public class Json {

    //C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
    //[JsonProperty("name")]
    private String privateName;

    public final String getName() {
        return privateName;
    }

    public final void setName(String value) {
        privateName = value;
    }

    //C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
    //[JsonProperty("sheets")]
    private Sheet[] privateSheets;

    public final Sheet[] getSheets() {
        return privateSheets;
    }

    public final void setSheets(Sheet[] value) {
        privateSheets = value;
    }

    //C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
    //[JsonProperty("cells")]
    private Cell[] privateCells;

    public final Cell[] getCells() {
        return privateCells;
    }

    public final void setCells(Cell[] value) {
        privateCells = value;
    }

    //C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
    //[JsonProperty("floatings")]
    private Floating[] privateFloatings;

    public final Floating[] getFloatings() {
        return privateFloatings;
    }

    public final void setFloatings(Floating[] value) {
        privateFloatings = value;
    }

    //C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
    //[JsonProperty("fileConfig")]
    private Object[] privateFileConfig;

    public final Object[] getFileConfig() {
        return privateFileConfig;
    }

    public final void setFileConfig(Object[] value) {
        privateFileConfig = value;
    }


    public static class Sheet {

        //C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
        //[JsonProperty("id")]
        private int privateId;

        public final int getId() {
            return privateId;
        }

        public final void setId(int value) {
            privateId = value;
        }

        //C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
        //[JsonProperty("name")]
        private String privateName;

        public final String getName() {
            return privateName;
        }

        public final void setName(String value) {
            privateName = value;
        }

        //C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
        //[JsonProperty("actived")]
        private boolean privateActived;

        public final boolean getActived() {
            return privateActived;
        }

        public final void setActived(boolean value) {
            privateActived = value;
        }

        //C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
        //[JsonProperty("color")]
        private String privateColor;

        public final String getColor() {
            return privateColor;
        }

        public final void setColor(String value) {
            privateColor = value;
        }

        //C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
        //[JsonProperty("hidden")]
        private boolean privateHidden;

        public final boolean getHidden() {
            return privateHidden;
        }

        public final void setHidden(boolean value) {
            privateHidden = value;
        }

        //C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
        //[JsonProperty("tabOrder")]
        private int privateTabOrder;

        public final int getTabOrder() {
            return privateTabOrder;
        }

        public final void setTabOrder(int value) {
            privateTabOrder = value;
        }
    }

    public static class Cell {

        //C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
        //[JsonProperty("sheet")]
        private int privateSheet;

        public final int getSheet() {
            return privateSheet;
        }

        public final void setSheet(int value) {
            privateSheet = value;
        }

        //C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
        //[JsonProperty("row")]
        private int privateRow;

        public final int getRow() {
            return privateRow;
        }

        public final void setRow(int value) {
            privateRow = value;
        }

        //C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
        //[JsonProperty("col")]
        private int privateCol;

        public final int getCol() {
            return privateCol;
        }

        public final void setCol(int value) {
            privateCol = value;
        }

        //C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
        //[JsonProperty("json")]
        private String privateJson;

        public final String getJson() {
            return privateJson;
        }

        public final void setJson(String value) {
            privateJson = value;
        }
    }

    public static class Floating {

        //C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
        //[JsonProperty("sheet")]
        private int privateSheet;

        public final int getSheet() {
            return privateSheet;
        }

        public final void setSheet(int value) {
            privateSheet = value;
        }

        //C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
        //[JsonProperty("name")]
        private String privateName;

        public final String getName() {
            return privateName;
        }

        public final void setName(String value) {
            privateName = value;
        }

        //C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
        //[JsonProperty("ftype")]
        private String privateFtype;

        public final String getFtype() {
            return privateFtype;
        }

        public final void setFtype(String value) {
            privateFtype = value;
        }

        //C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
        //[JsonProperty("json")]
        private String privateJson;

        public final String getJson() {
            return privateJson;
        }

        public final void setJson(String value) {
            privateJson = value;
        }
    }
}