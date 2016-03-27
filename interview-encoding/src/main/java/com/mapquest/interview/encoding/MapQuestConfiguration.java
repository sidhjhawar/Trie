package com.mapquest.interview.encoding;

import com.mapquest.interview.dao.WordDao;
import com.mapquest.interview.processor.EncodingProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapQuestConfiguration {

    @Bean
    public WordDao wordDao() {
        return new WordDao();
    }

    @Bean
    public EncodingProcessor encodingProcessor(){
        return new EncodingProcessor();
    }
}
