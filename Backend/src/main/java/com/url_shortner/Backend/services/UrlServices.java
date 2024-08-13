package com.url_shortner.Backend.services;

import com.url_shortner.Backend.model.Url;
import com.url_shortner.Backend.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

@Service
public class UrlServices {
    @Autowired
    private UrlRepository urlRepository;

    public String shorten(String longUrl)
    {
        try {
            if (urlRepository.findByLongUrl(longUrl).isPresent()) {
                return urlRepository.findByLongUrl(longUrl).get().getShortUrl();
            }
            String urlCode = Long.toHexString(longUrl.hashCode());
            String shortUrl = "http://localhost:8090/api/url/" + urlCode;
            Url url = Url.builder()
                    .urlCode(urlCode)
                    .longUrl(longUrl)
                    .shortUrl(shortUrl)
                    .build();
            return urlRepository.save(url).getShortUrl();
        } catch (Exception e)
        {
            System.out.println("error");
        }
        return "";
    }

    public String redirect(String urlCode)
    {
        if(urlCode.isEmpty()) return "Invalid";
        Url url;
        try
        {
            url = urlRepository.findByurlCode(urlCode);
            return url.getLongUrl();

        } catch (Exception e)
        {
            return "Invalid";
        }

    }
}
