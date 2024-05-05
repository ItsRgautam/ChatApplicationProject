package ChatApplicationProject.Controller;

import ChatApplicationProject.services.AIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AIController {

    @Autowired
    AIService aiService;

    @GetMapping("/request")
    public String getJoke(@RequestParam String query){
        return aiService.getResponse(query);
    }

}
