package Junit.studentIDInfo;

import io.restassured.RestAssured;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Manual;
import net.thucydides.core.annotations.Pending;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

@RunWith(SerenityRunner.class)
public class FirstSerenityTest_ID {

    @BeforeClass
    public static void init(){
        RestAssured.baseURI="http://localhost:8085/student";
    }


    @Test
    public void getAllStudents(){
        SerenityRest.
                given().
                when().
                get("/list").
                then().
                log().all().
                statusCode(200);
    }


    @Test
    public void thisIsaFailing(){
        SerenityRest.given().when().get("/list").then().statusCode(500);
    }

    @Pending
    @Test
    public void thisisaPendingTest(){

    }
    @Ignore
    @Test
    public void thisisaSkippedTest(){

    }

    @Test
    public void thisisATestWithError(){
        System.out.println("this is an error"+(5/0));
    }

    @Test
    public void filedoesntexist()throws FileNotFoundException {
        File file=new File("E://damon/test/1.txt");
        FileReader fr=new FileReader(file);
    }

    @Manual
    @Test
    public void thisisManual() {

    }
}
