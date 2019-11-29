package com.yvrun.officeprocess;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void str(){

        String msg = "$PCL���\u0005�������\n24 50 43 4C 41 00 05 00 00 00 B4 ";
        String mnb = "$PCL����������\n24 50 43 4C 41 00 05 00 00 00 B4";

        int i = mnb.indexOf("24 50 43 4C 41");
        System.out.println(i);
    }
}