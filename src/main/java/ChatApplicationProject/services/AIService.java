package ChatApplicationProject.services;

import org.springframework.ai.client.AiClient;
import org.springframework.ai.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AIService {

    @Autowired
    AiClient aiClient;

    public String getResponse(String query){
        PromptTemplate promptTemplate = new PromptTemplate("""
                Please act as a customer support chatbot for a realtime messaging website and answer {query}?
                Please be mindful of FAQ and provide simple solutions.
               """);
        promptTemplate.add("query", query);
        return this.aiClient.generate(promptTemplate.create()).getGeneration().getText();
    }



}
