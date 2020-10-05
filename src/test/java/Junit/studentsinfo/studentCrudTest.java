package Junit.studentsinfo;

import TestBase.TestBase;
import Utils.TestUtils;
import com.studentapp.model.studentClass;
import io.restassured.http.ContentType;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

@RunWith(SerenityRunner.class)
public class studentCrudTest extends TestBase {
    static String firstName=TestUtils.getRandomValue()+"Smokeuser1";
    static String lastName=TestUtils.getRandomValue()+"Smokeuser1";
    static String programmed=TestUtils.getRandomValue()+"ComputerScience1";
    static String email= TestUtils.getRandomValue()+"xyz1@email.com";

    @Title("This test will create new student")
    @Test
    public void createStudent(){
        ArrayList<String> courses=new ArrayList<String>();
        courses.add("Java");
        courses.add("C++");

        studentClass student=new studentClass();
        student.setCourses(courses);
        student.setEmail(email);
        student.setProgramme(programmed);
        student.setFirstName(firstName);
        student.setLastName(lastName);

        SerenityRest.given().contentType(ContentType.JSON).log().all().when().
                body(student).post().then().log().all().statusCode(201);
    }
}
