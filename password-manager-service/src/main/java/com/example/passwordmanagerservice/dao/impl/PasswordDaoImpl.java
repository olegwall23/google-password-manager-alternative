package com.example.passwordmanagerservice.dao.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.example.passwordmanagerservice.config.DynamodbClient;
import com.example.passwordmanagerservice.dao.PasswordDao;
import com.example.passwordmanagerservice.entity.Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PasswordDaoImpl implements PasswordDao {

    @Autowired
    private DynamodbClient dynamodbClient;

    @Override
    public void save(Password password) {
        dynamodbClient.getDynamoDBMapper().save(password);
    }

    @Override
    public void delete(Password password) {
        dynamodbClient.getDynamoDBMapper().delete(password);
    }

    @Override
    public List<Password> findByAccountId(String accountId) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":accountId", new AttributeValue().withS(accountId));

        DynamoDBQueryExpression<Password> queryExpression = new DynamoDBQueryExpression<Password>()
                .withKeyConditionExpression("a = :accountId")
                .withExpressionAttributeValues(eav);

        return dynamodbClient.getDynamoDBMapper().query(Password.class, queryExpression);
    }
}
