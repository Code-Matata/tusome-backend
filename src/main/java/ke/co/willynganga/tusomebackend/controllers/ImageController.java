package ke.co.willynganga.tusomebackend.controllers;

import ke.co.willynganga.tusomebackend.models.Image;
import ke.co.willynganga.tusomebackend.services.ImageService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/images")
@CrossOrigin
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping(
            path = "/getImage/{id}",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public byte[] getImage(@PathVariable("id") long id) {
        Image image = imageService.getImageById(id);
        if (image != null) {
            return image.getImage();
        } else {
            return null;
        }
    }

    @DeleteMapping("deleteImage/{id}")
    public String deleteImage(@PathVariable("id") long id) {
        return imageService.deleteImage(id);
    }
}
