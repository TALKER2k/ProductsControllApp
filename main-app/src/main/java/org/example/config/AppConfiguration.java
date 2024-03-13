package org.example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {
        "org.example.controllers",
        "org.example.services",
        "org.example.models"
})
@EnableAspectJAutoProxy
public class AppConfiguration {
}
