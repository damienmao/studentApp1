package com.studentapp.cucumber.serenity;

import com.studentapp.model.studentClass;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;
import java.util.List;

public class studentSerenitySteps {

        @Step ("Creating student with firstName:{0},lastName:{1},email:{2},programmed:{3},courses:{4}")
        public ValidatableResponse createStudent(String firstName, String lastName, String email, String programmed, List<String> courses){
            studentClass student=new studentClass();
            student.setCourses(courses);
            student.setEmail(email);
            student.setProgramme(programmed);
            student.setFirstName(firstName);
            student.setLastName(lastName);

            return SerenityRest.rest().given().contentType(ContentType.JSON).when().
                    body(student).post().then();


        }
        @Step ("getting student info with firstname:{0}")
        public HashMap<String,Object> getStudentInfoByFirstName(String firstName){
            String p1="findAll{it.firstName=='";
            String p2="'}.get(0)";

            return SerenityRest.rest().given().when().get("/list").then().log().all().statusCode(200).extract().path(p1+firstName+p2);

        }
}
