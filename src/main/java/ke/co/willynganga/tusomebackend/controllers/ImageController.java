package ke.co.willynganga.tusomebackend.controllers;

import ke.co.willynganga.tusomebackend.services.ImageService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/images")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/getImage/{id}")
    public byte[] getImage(@PathVariable("id") long id) {
        return imageService.getImageById(id).getImage();
    }

    @DeleteMapping("deleteImage/{id}")
    public String deleteImage(@PathVariable("id") long id) {
        return imageService.deleteImage(id);
    }
}
