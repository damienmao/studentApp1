package Junit.studentsinfo;

import TestBase.TestBase;
import Utils.TestUtils;
import com.studentapp.model.studentClass;
import io.restassured.http.ContentType;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.HashMap;


import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;


@RunWith(SerenityRunner.class)

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class studentCrudTest extends TestBase {
    static String firstName=TestUtils.getRandomValue()+"Smokeuser1";
    static String lastName=TestUtils.getRandomValue()+"Smokeuser1";
    static String programmed=TestUtils.getRandomValue()+"ComputerScience1";
    static String email= TestUtils.getRandomValue()+"xyz1@email.com";
    static int id;

    @Title("This test will create new student")
    @Test
    public void test001(){
        ArrayList<String> courses=new ArrayList<String>();
        courses.add("Java");
        courses.add("C++");

        studentClass student=new studentClass();
        student.setCourses(courses);
        student.setEmail(email);
        student.setProgramme(programmed);
        student.setFirstName(firstName);
        student.setLastName(lastName);

        SerenityRest.rest().given().contentType(ContentType.JSON).log().all().when().
                body(student).post().then().log().all().statusCode(201);
        //since this is a post request, you may want to specify the content type
        //but you dont need a content type for the get request
    }



    @Title("Verify if the student is added to the application")
    @Test
    public void test002(){
        String p1="findAll{it.firstName=='";
        String p2="'}.get(0)";
        HashMap<String,Object> value=SerenityRest.rest().given().when().get("/list").then().log().all().statusCode(200).extract().path(p1+firstName+p2);
        System.out.println("the value is "+value);

        assertThat(value,hasValue(firstName));
        id=(int) value.get("id");
    }

    @Title("Update the user's info and verify the update information")
    @Test
    public void test003(){
        ArrayList<String> courses=new ArrayList<String>();
        courses.add("Java");
        courses.add("C++");
        firstName=firstName+"_updated";
        studentClass student=new studentClass();
        student.setCourses(courses);
        student.setEmail(email);
        student.setProgramme(programmed);
        student.setFirstName(firstName);
        student.setLastName(lastName);

        SerenityRest.rest().given().contentType(ContentType.JSON).log().all().when().
                body(student).put("/"+id).then().log().all();

        String p1="findAll{it.firstName=='";
        String p2="'}.get(0)";
        HashMap<String,Object> value=SerenityRest.rest().given().when().get("/list").then().log().all().statusCode(200).extract().path(p1+firstName+p2);
        System.out.println("the value is "+value);

        assertThat(value,hasValue(firstName));
    }

    @Title("Delete the student and verify if the student is deleted!")
    @Test
    public void test004(){
        SerenityRest.rest().given().when().delete("/"+id);

        SerenityRest.rest().given().when().get("/"+id).then().log().all().statusCode(404);
    }


}
