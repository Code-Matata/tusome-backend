package ke.co.willynganga.tusomebackend.controllers;

import io.swagger.annotations.ApiOperation;
import ke.co.willynganga.tusomebackend.services.ImageUrlService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class ImageUrlController {

    private final ImageUrlService urlService;

    public ImageUrlController(ImageUrlService urlService) {
        this.urlService = urlService;
    }

    @ApiOperation(
            value = "Get image url by id.",
            notes = "Fetch image url with provided id in DB."
    )
    @GetMapping("/{id}")
    public String getUrl(@PathVariable("id") Long id) {
        return urlService.getUrl(id);
    }

}
