package com.url_shortner.Backend.controller;

import com.url_shortner.Backend.dto.UrlTO;
import com.url_shortner.Backend.services.UrlServices;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

@RestController
@RequestMapping("/api/url")
@CrossOrigin(origins = "http://localhost:3001")
public class UrlController {
    @Autowired
    private UrlServices urlServices;

    @PostMapping("/shorten")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String shorten(@RequestBody UrlTO urlTO)
    {
        String urlString = urlTO.getLongUrl();
        if(urlTO.getLongUrl().isEmpty()) return "URL is Required";
        try
        {
            new URL(urlString).toURI();
        } catch(MalformedURLException|IllegalArgumentException e)
        {
            return "Invalid URL";
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return urlServices.shorten(urlString); // returns the shortUrl
    }

    @GetMapping("/{urlCode}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String redirect(@PathVariable String urlCode, HttpServletResponse response) throws IOException {
        String url = urlServices.redirect(urlCode); // returns Url
        response.sendRedirect(url);
        return url;
    }

}
