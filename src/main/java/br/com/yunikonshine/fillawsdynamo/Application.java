package br.com.yunikonshine.fillawsdynamo;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@EnableBatchProcessing
@SpringBootApplication
public class Application {

    public static void main(String[] args) throws IOException, InterruptedException {
//        Runtime rt = Runtime.getRuntime();
//        Process stop = rt.exec("docker stop localstack_main");
//        stop.waitFor();
//        Process start = rt.exec("docker start localstack_main");
//        start.waitFor();
        SpringApplication.run(Application.class, args);
    }

}
