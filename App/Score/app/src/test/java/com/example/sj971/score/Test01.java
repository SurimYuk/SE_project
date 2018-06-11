package com.example.sj971.score;

/**
 * Created by sj971 on 2018-05-30.
 */

import android.renderscript.Script;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static junit.framework.Assert.assertTrue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnit4.class)
public class Test01 {
    private ScoreItem scoreItem;
    private SubjectItem subjectItem;
    private StudentItem studentItem;
    private ListItem listItem;

    @Before
    public void setUp(){
        scoreItem = new ScoreItem("소프트웨어 공학","A+");
        subjectItem = new SubjectItem("20180000","소프트웨어 공학");
        //studentItem = new StudentItem("201635835","A+");
        listItem = new ListItem("201635835","이수정");
    }

    @Test
    public void TestSample01() {
        String result = scoreItem.getSubject();
        assertThat(result, is("소프트웨어 공학"));
    }

    @Test
    public void TestSample02() {
        String result = scoreItem.getScore();
        assertThat(result, is("A+"));
    }

    @Test
    public void TestSample03() {
        String result = subjectItem.getNumber();
        assertThat(result, is("20180000"));
    }

    @Test
    public void TestSample04() {
        String result = subjectItem.getSubject();
        assertThat(result, is("소프트웨어 공학"));
    }

    //@Test
    public void TestSample05() {
        //studentItem.setStudent_name("이수정");
        String result = studentItem.getStudent_name();
        assertThat(result, is("이수정"));
    }

   // @Test
    public void TestSample06() {
        //studentItem.setStudent_score("B+");
        String result = studentItem.getStudent_score();
        assertThat(result, is("B+"));
    }

    @Test
    public void TestSample07() {
        listItem.setNumber("201635835");
        String result = listItem.getNumber();
        assertThat(result, is("201635835"));
    }

    @Test
    public void TestSample08() {
        String result = listItem.getName();
        assertThat(result, is("이수정"));
    }
}
