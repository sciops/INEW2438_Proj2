package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
INEW 2438 Project 2 Assignment
Stephen R. Williams

REST service using Spring
This class and main method use SpringApplication.run to set up the service without web.xml
Reference: https://spring.io/guides/gs/rest-service/

Assignment text:
Create a REST service that do the following.
Create two REST services that use path parameter and query parameter.
    the two methods should  collect your favorite websites' url, and  category.
    Create a class that captures the favorite websites' url, and  category.
    Store the captured data in a file
Create a REST API that displays the content of the updated file upon request.
The program should be testable by calling the REST APIs directly from any browser
Upload your project in GitHub and just submit the url in a word/text document


*/


@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
