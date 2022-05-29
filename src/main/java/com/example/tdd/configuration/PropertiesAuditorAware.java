package com.example.tdd.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * Entity 감시자<br/>
 * Spring Securty 부재로 Propertis의 DbUser 정보를 가져와 사용함.
 */
@Service
public class PropertiesAuditorAware implements AuditorAware<String> {

    @Value("${spring.datasource.username}")
    private String databaseUserName;

    @Override
    public Optional<String> getCurrentAuditor() {
        if (!StringUtils.hasText(databaseUserName))
            return Optional.empty();

        return Optional.of(databaseUserName);
    }
}
