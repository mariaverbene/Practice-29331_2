package kafkaspring;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties("config")
public class ConfigProperties {
private String topicFrom;
private String topicTo;
private String handledTimestamp;

    public String getTopicFrom() { return topicFrom; }
    public void setTopicFrom(String topicFrom) { this.topicFrom = topicFrom; }

    public String getTopicTo() { return topicTo; }
    public void setTopicTo(String topicTo) { this.topicTo = topicTo; }

    public String getHandledTimestamp() { return handledTimestamp; }
    public void setHandledTimestamp(String handledTimestamp) { this.handledTimestamp = handledTimestamp; }
}
