package net.lecigne.somafm.example.config;

import lombok.Setter;
import net.lecigne.somafm.recentlib.SomaFm;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("somafm")
@Setter
class Config {

  private String userAgent;

  @Bean
  SomaFm somaFm() {
    return SomaFm.of(userAgent);
  }

}
