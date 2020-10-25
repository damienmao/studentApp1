package com.studentapp.cucumber.serenity;

import Utils.ReuseableSpecification;
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

            return SerenityRest.rest().
                    given().
                    spec(ReuseableSpecification.getGenericRequestSpec()).
                    when().
                    body(student).
                    post().
                    then();
            //since this is a post request, you may want to specify the content type
            //but you dont need a content type for the get request


        }
        @Step ("getting student info with firstname:{0}")
        public HashMap<String,Object> getStudentInfoByFirstName(String firstName){

            String p1="findAll{it.firstName=='";
            String p2="'}.get(0)";
            // findAll{it.firstName=''}.get(0)

            return SerenityRest.rest().given().when().get("/list").then().log().all().statusCode(200).extract().path(p1+firstName+p2);

        }

        @Step("Update student with studentID:{0},firstName:{1},lastName:{2},email:{3},programmed:{4},courses:{5}")
        public ValidatableResponse updateStudent(int id,String firstName,String lastName,String email,String programmed, List<String> courses){
            studentClass student =new studentClass();
            student.setFirstName(firstName);
            student.setLastName(lastName);
            student.setEmail(email);
            student.setProgramme(programmed);
            student.setCourses(courses);
            return SerenityRest.rest().given().spec(ReuseableSpecification.getGenericRequestSpec()).log().all().when().body(student).put("/"+id).then();

        }

        @Step ("Delete student information with ID:{0}")
        public void deleteStudent(int id){
            SerenityRest.rest().given().when().delete("/"+id);
        }


        @Step("Getting information by student ID:{0}")
        public ValidatableResponse getstudentInfowithID(int id){
            return SerenityRest.rest().given().when().get("/"+id).then();
        }



}
