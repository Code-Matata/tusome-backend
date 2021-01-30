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

    public Image addImage(MultipartFile file) {
        if (file != null) {
            try {
                byte[] imageBytes = file.getBytes();
                Image image = new Image(imageBytes);
                imageRepository.save(image);
                imageRepository.flush();
                return image;
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        return null;
    }

    public String deleteImage(long id) {
        Optional<Image> image = imageRepository.findById(id);
        image.ifPresent(imageRepository::delete);
        imageRepository.flush();
        return image.isPresent() ? "Image deleted successfully!" : "Image not found, please provide a valid Id";
    }
}
