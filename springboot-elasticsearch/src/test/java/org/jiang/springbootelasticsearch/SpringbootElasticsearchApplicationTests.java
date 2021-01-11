package org.jiang.springbootelasticsearch;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.jiang.springbootelasticsearch.po.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;


@SpringBootTest
class SpringbootElasticsearchApplicationTests {

    @Qualifier("client")
    @Autowired
    private RestHighLevelClient client;

    @Test
    public void restClientAdd() throws IOException {
        IndexRequest indexRequest = new IndexRequest("test", "employee");
        Employee employee = new Employee();
        employee.setName("james harder");
        employee.setSex("man");
        employee.setAge("33");
        employee.setAge("huston");
        ObjectMapper objectMapper = new ObjectMapper();
        String employeeString = objectMapper.writeValueAsString(employee);
        IndexResponse indexResponse = client.index(indexRequest.source(employeeString, employeeString), RequestOptions.DEFAULT);
        System.out.println(indexResponse.toString());
    }

    @Test
    void restClientUpdate() {

    }

    @Test
    void restClientDelete() {

    }

    @Test
    void restClientView() {

    }

}
