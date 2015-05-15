package service.c.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.c.bean.MessageBean;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Oreste Luci
 */
@EnableAutoConfiguration
@RefreshScope
@RestController
public class ServiceCController {

    @Value("${default.message:Service C }")
    String message;

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(
            value = "/test",
            method= RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public MessageBean test() {
        return new MessageBean("Service C");
    }

    @RequestMapping(
            value = "/echo",
            method= RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public MessageBean echo(@RequestParam(value="msg", required=false, defaultValue="Hello") String msg) {

        System.out.println(counter.incrementAndGet() + ". ServiceBController.echo: " + msg);

        MessageBean messageBean = new MessageBean(message + " " + msg);

        return messageBean;
    }
}
