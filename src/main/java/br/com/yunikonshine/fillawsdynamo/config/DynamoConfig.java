package br.com.yunikonshine.fillawsdynamo.config;

import br.com.yunikonshine.fillawsdynamo.model.BaseEntity;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.CreateTableResult;
import com.amazonaws.services.dynamodbv2.model.GlobalSecondaryIndex;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Slf4j
@Configuration
public class DynamoConfig {

    @Value("${amazon.dynamodb.endpoint}")
    private String DynamoEndpoint;

    @Value("${amazon.dynamodb.region}")
    private String DynamoRegion;

    @Autowired
    private List<BaseEntity> baseEntityList;

    @Bean
    public AmazonDynamoDB getAmazonDynamoDBClient() {
        return AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration(DynamoEndpoint, DynamoRegion)
                ).build();
    }

    @Bean
    public DynamoDB getDynamoDBClient() {
        return new DynamoDB(getAmazonDynamoDBClient());
    }

    @Bean
    public void createTables() {
        AmazonDynamoDB amazonDynamoDB = getAmazonDynamoDBClient();
        baseEntityList.forEach(entity -> {
            try {
                amazonDynamoDB.describeTable(entity.getTableName());
                log.info("Table named \'{}\' already exists", entity.getTableName());
            } catch (ResourceNotFoundException exception) {
                CreateTableResult result = amazonDynamoDB.createTable(createTableSchema(
                        entity.buildAttributes(),
                        entity.buildSchema(),
                        entity.buildIndices(),
                        entity.getTableName()
                ));
                log.info("Table created: {}", result.getTableDescription().getTableName());
            }
        });
    }

    private static CreateTableRequest createTableSchema(List<AttributeDefinition> attributes, List<KeySchemaElement> schema, List<GlobalSecondaryIndex> globalSecondaryIndices, String tableName) {
        return new CreateTableRequest()
                .withProvisionedThroughput(new ProvisionedThroughput(10L, 10L))
                .withAttributeDefinitions(attributes)
                .withKeySchema(schema)
                .withTableName(tableName)
                .withGlobalSecondaryIndexes(globalSecondaryIndices);
    }

}
