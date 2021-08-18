package br.com.yunikonshine.fillawsdynamo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FillDynamoJob {

    private final JobBuilderFactory jobBuilderFactory;

    private final FillDynamoSteps fillDynamoSteps;

    @Bean
    public Job job() {
        return this.jobBuilderFactory.get("Fill Dynamo Job").start(fillDynamoSteps.fillDynamo()).build();
    }

}
