package Junit.studentsinfo;

import TestBase.TestBase;
import Utils.ReuseableSpecification;
import Utils.TestUtils;
import com.studentapp.cucumber.serenity.studentSerenitySteps;
import com.studentapp.model.studentClass;
import io.restassured.http.ContentType;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
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

    @Steps
    studentSerenitySteps steps;

    @Title("This test will create new student")
    @Test
    public void test001(){
        ArrayList<String> courses=new ArrayList<String>();
        courses.add("Java");
        courses.add("C++");

        steps.createStudent(firstName,lastName,email,programmed,courses).
                statusCode(201).spec(ReuseableSpecification.getGenericResponseSpec());

        //since this is a post request, you may want to specify the content type
        //but you dont need a content type for the get request
    }

    @Title("Verify if the student is added to the application")
    @Test
    public void test002(){

        HashMap<String,Object> value= steps.getStudentInfoByFirstName(firstName);
        System.out.println("the value is "+value);

        assertThat(value,hasValue(firstName));

        id=(int) value.get("id");
    }

    @Title("Update the user's info and verify the update information")
    @Test
    public void test003(){
        ArrayList<String> courses=new ArrayList<>();
        courses.add("Java");
        courses.add("C++");
        firstName=firstName+"_updated";

        steps.updateStudent(id,firstName,lastName,email,programmed,courses);


        HashMap<String,Object> value=steps.getStudentInfoByFirstName(firstName);
        System.out.println("the value is "+value);
        assertThat(value,hasValue(firstName));
    }

    @Title("Delete the student and verify if the student is deleted!")
    @Test
    public void test004(){
        steps.deleteStudent(id);
        steps.getstudentInfowithID(id).statusCode(404);
         }


}
