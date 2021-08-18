package br.com.yunikonshine.fillawsdynamo.config;

import br.com.yunikonshine.fillawsdynamo.model.BaseEntity;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FillDynamoSteps {

    private final DynamoDB dynamoDB;

    private final StepBuilderFactory stepBuilderFactory;

    private final List<BaseEntity> baseEntityList;

    private final Integer itemsNumber = 0;

    @Bean
    public Step fillDynamo() {
        return stepBuilderFactory.get("Fill Dynamo tables").tasklet((contribution, chunkContext) -> {
            baseEntityList.forEach(entity -> {
                Table table = dynamoDB.getTable(entity.getTableName());
                log.info("Filling table: {}", entity.getTableName());
                try {
                    entity.generateItems(itemsNumber).forEach(item -> {
                        table.putItem(item);
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            return RepeatStatus.FINISHED;
        }).build();
    }

}
