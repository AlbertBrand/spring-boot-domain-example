package domainexample.adapter.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecuredController {
    
    @GetMapping("/secured")
    public String secured() {
        return "Hello, secured!";
    }
}
