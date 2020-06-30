package com.example.passwordmanagerservice.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.*;

@Builder
@AllArgsConstructor
@ToString
@Setter
@Getter
@NoArgsConstructor
@DynamoDBTable(tableName="password")
public class Password {

    @DynamoDBHashKey(attributeName="a")
    private String accountId;

    @DynamoDBRangeKey(attributeName="b")
    private String key;

    @DynamoDBAttribute(attributeName="c")
    private String website;

    @DynamoDBAttribute(attributeName="c")
    private String username;

    @DynamoDBAttribute(attributeName="d")
    private String password;

}