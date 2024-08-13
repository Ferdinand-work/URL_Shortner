package com.url_shortner.Backend.repository;

import com.url_shortner.Backend.model.Url;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepository extends MongoRepository<Url,String> {
    Optional<Url> findByLongUrl(String longUrl);
    Url findByurlCode(String urlCode);
}

