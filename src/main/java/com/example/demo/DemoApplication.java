package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.IntStream;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}

@Controller
class TestController {

    @GetMapping(value = "/test", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    @ResponseBody
    public Flux<Integer> handleRequestEntity() {

		return Flux.fromStream(IntStream.range(1, 100000).boxed());
//		String[] strings = {"1","2","3","4","5","6","7","8"};
//		return Flux.fromArray(strings);

//		return Flux.interval(Duration.ofMillis(1000)).map(k -> "Object: " + k).take(2);


//        return Flux.interval(ofMillis(100)).onBackpressureBuffer(10).map(index -> "{\"N\":\"" + index + "\"}");
    }

    @GetMapping(value = "/see", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    @ResponseBody
    Flux<String> string() {
        return Flux
                .interval(Duration.ofMillis(500))
                .map(l -> "foo " + l);
    }
}