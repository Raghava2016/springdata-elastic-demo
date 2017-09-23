package com.raghava.learning.springelasticdemo.config;

import java.io.File;
import java.io.IOException;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.NodeBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.raghava.learning.springelasticdemo.repository")
public class ElasticConfiguration {

    @Bean
	public NodeBuilder nodeBuilder() {
		return new NodeBuilder();
	}
    
    @Bean
    public ElasticsearchOperations elasticsearchOperations() {
    	    File tempDir = null;  
    	    tempDir = new File("temp-elastic", Long.toString(System.nanoTime()));
		tempDir.mkdir();
		System.out.println("********"+tempDir.isDirectory());   
    	
    	    System.out.println("TempDir Path: "+tempDir.getAbsolutePath());
    	   
    	    Settings.Builder elasticSearchSettings = 
    	    	                 Settings.settingsBuilder()
    	    	                 .put("http.enabled","true")
    	    	                 .put("index.number_of_shards","1")
    	    	                 .put("path.data",new File(tempDir,"data").getAbsolutePath())
    	    	                 .put("path.logs",new File(tempDir,"logs").getAbsolutePath())
    	    	                 .put("path.work",new File(tempDir,"work").getAbsolutePath())
    	    	                 .put("path.home",tempDir);
    	    	   
    	    return new ElasticsearchTemplate(nodeBuilder()
    	    		.local(true)
    	    		.settings(elasticSearchSettings)
    	    		.node()
    	    		.client());
    }
}
