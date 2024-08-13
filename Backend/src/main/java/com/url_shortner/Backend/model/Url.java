package com.url_shortner.Backend.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(value = "urls")
@Data
@Builder
public class Url {
    @Id
    private String id;

    @Indexed(unique = true)
    private String urlCode;

    private String longUrl;

    @Indexed(unique = true)
    private String shortUrl;
}
