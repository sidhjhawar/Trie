package com.mapquest.interview.encoding;

import com.mapquest.interview.dao.WordDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by owner on 3/26/2016.
 */

@Configuration
public class MapQuestConfiguration {

    @Bean
    public WordDao wordDao() {
        return new WordDao();
    }
}
