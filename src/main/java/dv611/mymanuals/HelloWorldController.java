package dv611.mymanuals;

/**
 * Created by ilyakruikov on 11/10/16.
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @RequestMapping("/hello")
    public String helloWorld() {
        return "Greetings from Spring Boot!";
    }

}