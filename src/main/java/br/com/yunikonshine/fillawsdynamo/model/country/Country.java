package br.com.yunikonshine.fillawsdynamo.model.country;

import br.com.yunikonshine.fillawsdynamo.model.BaseEntity;
import br.com.yunikonshine.fillawsdynamo.utils.Utils;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.GlobalSecondaryIndex;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Country extends BaseEntity {

    private final String tableName = "country";

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    protected Item generateItem() {
        return new Item()
                .withPrimaryKey("id", Utils.generateRandomInteger(8, 10))
                .withString("name", Utils.generateHash());
    }

    @Override
    public List<KeySchemaElement> buildSchema() {
        List<KeySchemaElement> keySchema = new ArrayList<>();
        keySchema.add(new KeySchemaElement().withAttributeName("id").withKeyType(KeyType.HASH));
        return keySchema;
    }

    @Override
    public List<AttributeDefinition> buildAttributes() {
        List<AttributeDefinition> attributeDefinitions = new ArrayList<>();
        attributeDefinitions.add(new AttributeDefinition().withAttributeName("id").withAttributeType("N"));
        return attributeDefinitions;
    }

    @Override
    public List<GlobalSecondaryIndex> buildIndices() {
        return null;
    }

}
