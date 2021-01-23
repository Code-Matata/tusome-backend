package ke.co.willynganga.tusomebackend.services;

import ke.co.willynganga.tusomebackend.models.Image;
import ke.co.willynganga.tusomebackend.repositories.ImageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class ImageService {

    private Logger logger = LoggerFactory.getLogger(ImageService.class);
    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Image getImageById(long id) {
        return imageRepository.findById(id).orElse(null);
    }

    public boolean addImage(long id, MultipartFile file) {
        if (file != null) {
            try {
                byte[] imageBytes = file.getBytes();
                imageRepository.save(new Image(id, imageBytes));
                imageRepository.flush();
                return true;
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        return false;
    }

    public String deleteImage(long id) {
        Optional<Image> image = imageRepository.findById(id);
        image.ifPresent(imageRepository::delete);
        return image.isPresent() ? "Image deleted successfully!" : "Image not found, please provide a valid Id";
    }
}
