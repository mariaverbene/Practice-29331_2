package kafkaspring;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.*;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class SomeClass   {

    @Autowired
    private ConfigProperties configProperties;

    public Message handlerMessage (Message message) throws MessagingException {

        ObjectMapper mapper;
        JsonNode jsonNode;

       try {
        mapper = new ObjectMapper();
        jsonNode = mapper.readTree((String) message.getPayload());

        ((ObjectNode) jsonNode).put(configProperties.getHandledTimestamp(), System.currentTimeMillis());
            message = MessageBuilder
                    .withPayload(mapper.writeValueAsString(jsonNode))
                    .build();
        }
       catch (IOException e) {
            e.printStackTrace();
       }
       finally {
           return message;
       }
    }

}