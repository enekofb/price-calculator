package org.eneko.test.unit.prices;

import lombok.NoArgsConstructor;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.NodeBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

/**
 * Created by eneko on 24/06/17.
 */
@Configuration
@NoArgsConstructor
@EnableElasticsearchRepositories(basePackages = "org.eneko.prices",repositoryImplementationPostfix = "CustomImpl")
public class PriceRepositoryTestConfig{
    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        Settings settings = Settings.builder()
                .put("node.name", "elastic")
                .put("path.home", "/tmp")
                .put("http.enabled", false).build();

        return new ElasticsearchTemplate(nodeBuilder().settings(settings).local(true).node().client());
    }
}