package com.dongxie.adminserver.dingtalk;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.context.expression.MapAccessor;
import org.springframework.expression.Expression;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.domain.events.InstanceEvent;
import de.codecentric.boot.admin.server.domain.values.Endpoint;
import de.codecentric.boot.admin.server.domain.values.Endpoints;
import de.codecentric.boot.admin.server.notify.AbstractStatusChangeNotifier;
import reactor.core.publisher.Mono;

/**
 * ${钉钉发送服务启停消息} <br>
 *
 * @author guan.xb <br>
 * @version 1.0 <br>
 * @taskId <br>
 * @CreateDate 2019年04月23日 <br>
 * @see com.dongxie.adminserver.dingtalk <br>
 * @since V7.3 <br>
 */
public class DingTalkStatusChangeNotifier extends AbstractStatusChangeNotifier {

    private static final String DEFAULT_MESSAGE = "*#{instance.registration.name}* (#{endpoint}) is *#{lastStatus}*";

    // private static final String DEFAULT_MESSAGE = "*#{application.name}* (*#{application.id}*) status changed from
    // *#{from.status}* to *#{to.status}* *#{application.healthUrl}*";
    private final SpelExpressionParser parser = new SpelExpressionParser();

    private RestTemplate restTemplate = new RestTemplate();

    private String webhookToken;

    // private String atMobiles;

    private String msgtype = "markdown";

    // private String title = "服务告警";

    private Expression message;

    public DingTalkStatusChangeNotifier(InstanceRepository repositpry) {
        super(repositpry);
        this.message = parser.parseExpression(DEFAULT_MESSAGE, ParserContext.TEMPLATE_EXPRESSION);
    }

    @Override
    protected Mono<Void> doNotify(InstanceEvent instanceEvent, Instance instance) {

        return Mono.fromRunnable(
            () -> restTemplate.postForEntity(webhookToken, createMessage(instanceEvent, instance), Void.class));
    }

    private HttpEntity<Map<String, Object>> createMessage(InstanceEvent event, Instance instance) {
        Map<String, Object> msgJson = new HashMap<>(3);
        Map<String, String> params = new HashMap<>(2);
        params.put("text", this.getMessage(event, instance));
        // params.put("title", this.title);
        // msgJson.put("atMobiles", this.atMobiles);
        msgJson.put("msgtype", this.msgtype);
        msgJson.put(this.msgtype, params);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new HttpEntity<>(msgJson, headers);
    }

    private String getMessage(InstanceEvent event, Instance instance) {
        Map<String, Object> root = new HashMap<>(3);
        root.put("event", event);
        root.put("instance", instance);
        root.put("lastStatus", getLastStatus(event.getInstance()));
        Endpoints endpoints = instance.getEndpoints();
        Optional<Endpoint> health = endpoints.get("health");

        root.put("endpoint", health.get().getUrl());
        StandardEvaluationContext context = new StandardEvaluationContext(root);
        context.addPropertyAccessor(new MapAccessor());
        return message.getValue(context, String.class);
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // public String getWebhookToken() {
    // return webhookToken;
    // }
    //
    public void setWebhookToken(String webhookToken) {
        this.webhookToken = webhookToken;
    }
    //
    // public String getAtMobiles() {
    // return atMobiles;
    // }
    //
    // public void setAtMobiles(String atMobiles) {
    // this.atMobiles = atMobiles;
    // }

    // public String getMsgtype() {
    // return msgtype;
    // }
    //
    // public void setMsgtype(String msgtype) {
    // this.msgtype = msgtype;
    // }
    //
    // public Expression getMessage() {
    // return message;
    // }
    //
    // public void setMessage(String message) {
    // this.message = (Expression) this.parser.parseExpression(message, ParserContext.TEMPLATE_EXPRESSION);
    // }

    // public String getTitle() {
    // return title;
    // }
    //
    // public void setTitle(String title) {
    // this.title = title;
    // }
}
