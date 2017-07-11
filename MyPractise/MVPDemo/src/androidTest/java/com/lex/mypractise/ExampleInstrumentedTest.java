package com.lex.mypractise;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.lex.mypractise", appContext.getPackageName());
    }
    @Test
    public void reverse(){
        String x = "abc";
        String y = "我是谁";
        String z = "abc3我是谁";
        android.util.Log.i("lex", "x = " + x.length() + "   y = " + y.length() + "   z = " + z.length());

        char[] value = z.toCharArray();
        int n = z.length() - 1;
        for(int i = (n - 1) >> 1;i>=0;i--){
            char temp = value[i];
            char temp1 = value[n - i];
            value[n - i] = temp;
            value[i] = temp1;
        }
        Log.i("lex", " result = " + new String(value));

        assertEquals(x,y,z);
    }
}
