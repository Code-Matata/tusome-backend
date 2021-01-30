package ke.co.willynganga.tusomebackend.controllers;

import ke.co.willynganga.tusomebackend.services.ImageUrlService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageUrlController {

    private final ImageUrlService urlService;

    public ImageUrlController(ImageUrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/{id}")
    public String getUrl(@PathVariable("id") Long id) {
        return urlService.getUrl(id);
    }

}
