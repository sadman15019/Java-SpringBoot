package com.example.bookinfomanagement.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Getter
@Configuration
public class ApplicationProperties {
    @Value("${external-api.book}")
    String bookurl;
}
