package br.com.yunikonshine.fillawsdynamo.model;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.GlobalSecondaryIndex;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseEntity {

    public abstract String getTableName();

    public List<Item> generateItems(Integer size) {
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            items.add(generateItem());
        }
        return items;
    }

    protected abstract Item generateItem();

    public abstract List<KeySchemaElement> buildSchema();

    public abstract List<AttributeDefinition> buildAttributes();

    public abstract List<GlobalSecondaryIndex> buildIndices();

}
