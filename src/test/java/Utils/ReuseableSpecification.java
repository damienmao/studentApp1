package Utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.lessThan;

public class ReuseableSpecification {

    public static RequestSpecification requestSpecification;
    public static RequestSpecBuilder requestSpecBuilder;
    public static ResponseSpecification responseSpecification;
    public static ResponseSpecBuilder responseSpecBuilder;

    public static RequestSpecification getGenericRequestSpec(){
        requestSpecBuilder=new RequestSpecBuilder();
        requestSpecBuilder.setContentType(ContentType.JSON);
        requestSpecification=requestSpecBuilder.build();
        return requestSpecification;
    }

    public static ResponseSpecification getGenericResponseSpec(){
        responseSpecBuilder=new ResponseSpecBuilder();
        responseSpecBuilder.expectHeader("Content-Type","application/json;charset-UTF-8");
        responseSpecBuilder.expectHeader("Tranfer-Encoding","chunked");
        responseSpecBuilder.expectResponseTime(lessThan(5L), TimeUnit.SECONDS);

        responseSpecification=responseSpecBuilder.build();
        return responseSpecification;

    }
}
