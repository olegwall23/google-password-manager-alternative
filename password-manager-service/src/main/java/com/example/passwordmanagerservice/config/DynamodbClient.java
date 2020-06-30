package com.example.passwordmanagerservice.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import lombok.Getter;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Getter
@Configuration
public class DynamodbClient {

    private AmazonDynamoDB ddb;

    private DynamoDBMapper dynamoDBMapper;

    private DynamoDB dynamoDB;

    @Value("${secrets.path.dynamodb.accessKeyId}")
    private String accessKeyIdFilePth;

    @Value("${secrets.path.dynamodb.secretAccessKey}")
    private String secretAccessKeyFilePath;

    @PostConstruct
    private void init() throws IOException {
        if (ddb == null) {
            String accessKeyId = FileUtils.readFileToString(new File(accessKeyIdFilePth), StandardCharsets.UTF_8);
            String secretAccessKey = FileUtils.readFileToString(new File(secretAccessKeyFilePath), StandardCharsets.UTF_8);

            BasicAWSCredentials creds = new BasicAWSCredentials(accessKeyId, secretAccessKey);

            ddb = AmazonDynamoDBClientBuilder.standard()
                    .withRegion("eu-west-2")
                    .withCredentials(new AWSStaticCredentialsProvider(creds))
                    .build();

            dynamoDBMapper = new DynamoDBMapper(ddb);
            dynamoDB = new DynamoDB(ddb);
        }
    }

    @PreDestroy
    private void destroy() {
        dynamoDB.shutdown();
        ddb.shutdown();
    }

}
