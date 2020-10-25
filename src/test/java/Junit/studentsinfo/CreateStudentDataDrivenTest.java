package Junit.studentsinfo;

import TestBase.TestBase;
import com.studentapp.cucumber.serenity.studentSerenitySteps;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.Concurrent;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

@Concurrent(threads ="4x") //serenity will exexcute the task with 4 threads per CPU core; if dont specify here, by default, its 2 threads
//note that @Concurrent can only be used with Parameterized tests
//it cannot be used for running parallel methods and classes.

@UseTestDataFrom("testdata/studentInfo.csv")
@RunWith(SerenityParameterizedRunner.class)  //when using SerenityParameterizeRunner,program will know this is a DDT, and  will run the test multiple test
public class CreateStudentDataDrivenTest extends TestBase {
    private String firstName;
    private String lastName;
    private String email;
    private String programmed;
    private String course;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getProgrammed() {
        return programmed;
    }

    public String getCourse() {
        return course;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setProgrammed(String programmed) {
        this.programmed = programmed;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Steps
    studentSerenitySteps steps;

    @Title("DataDriven Test For adding multiple students to the Student App")
    @Test
    public void createMultipleStudents(){
        ArrayList<String> courses=new ArrayList<>();
        courses.add(course);
        steps.createStudent(firstName,lastName,email,programmed,courses).statusCode(201);
    }


}
