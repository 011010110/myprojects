package org.jiang.springbootelasticsearch.config;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.jiang.springbootelasticsearch.po.Address;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.config.ElasticsearchConfigurationSupport;
import org.springframework.data.elasticsearch.core.ElasticsearchEntityMapper;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.EntityMapper;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchCustomConversions;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description
 * @Author li.linhua
 * @Date 2020/5/3
 * @Version 1.0
 */
@Configuration
public class ElasticsearchConfig extends ElasticsearchConfigurationSupport {

    /**
     * 配置highLevelClient
     *
     * @return
     */
    @Bean
    public RestHighLevelClient elasticsearchRestClient() {

        ClientConfiguration configuration = ClientConfiguration.builder()
                .connectedTo("192.168.232.129:9200", "192.168.232.131:9200", "192.168.232.132:9200")
                .build();
        return RestClients.create(configuration).rest();
    }

    @Bean
    public Client elasticsearchClient() throws UnknownHostException {
        Settings settings = Settings.builder().put("cluster.name", "my-elasticsearch").build();
        TransportClient client = new PreBuiltTransportClient(settings);
        client.addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.232.129"), 9300));
        return client;
    }

    @Bean(name = {"elasticsearchOperations", "elasticsearchTemplate"})
    public ElasticsearchTemplate elasticsearchTemplate() throws UnknownHostException {
        return new ElasticsearchTemplate(elasticsearchClient(), entityMapper());
    }

    @Bean
    @Override
    public EntityMapper entityMapper() {

        ElasticsearchEntityMapper entityMapper = new ElasticsearchEntityMapper(
                elasticsearchMappingContext(), new DefaultConversionService()
        );
        entityMapper.setConversions(elasticsearchCustomConversions());

        return entityMapper;
    }

    @Bean
    @Override
    public ElasticsearchCustomConversions elasticsearchCustomConversions() {
        return new ElasticsearchCustomConversions(
                Arrays.asList(new AddressToMap(), new MapToAddress()));
    }

    @WritingConverter
    static class AddressToMap implements Converter<Address, Map<String, Object>> {

        @Override
        public Map<String, Object> convert(Address source) {

            LinkedHashMap<String, Object> target = new LinkedHashMap<>();
            target.put("city", source.getCity());
            return target;
        }
    }

    @ReadingConverter
    static class MapToAddress implements Converter<Map<String, Object>, Address> {

        @Override
        public Address convert(Map<String, Object> source) {
            Address address = new Address();
            return address;
        }
    }
}
