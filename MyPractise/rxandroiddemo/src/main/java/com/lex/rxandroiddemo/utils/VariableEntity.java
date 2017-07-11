package com.lex.rxandroiddemo.utils;

/**
 * Created by Lex lex on 2017/4/5.
 */

public class VariableEntity {
    //C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
    //[JsonProperty("recordCount")]
    private int privateRecordCount;

    public final int getRecordCount() {
        return privateRecordCount;
    }

    public final void setRecordCount(int value) {
        privateRecordCount = value;
    }

    //C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
    //[JsonProperty("variable")]
    private java.util.HashMap<String, String> privateVariable;

    public final java.util.HashMap<String, String> getVariable() {
        return privateVariable;
    }

    public final void setVariable(java.util.HashMap<String, String> value) {
        privateVariable = value;
    }

    //C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
    //[JsonProperty("verify")]
    private VerifyEntity[] privateVerify;

    public final VerifyEntity[] getVerify() {
        return privateVerify;
    }

    public final void setVerify(VerifyEntity[] value) {
        privateVerify = value;
    }
}