package ke.co.willynganga.tusomebackend.services;

import ke.co.willynganga.tusomebackend.models.ImageUrl;
import ke.co.willynganga.tusomebackend.models.Paper;
import ke.co.willynganga.tusomebackend.repositories.ImageUrlRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImageUrlService {

    private final ImageUrlRepository urlRepository;

    public ImageUrlService(ImageUrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public void addUrl(String url, Paper paper) {
        urlRepository.save(new ImageUrl(url, paper));
        urlRepository.flush();
    }

    public String getUrl(Long id) {
        Optional<ImageUrl> imageUrl = urlRepository.findById(id);
        return imageUrl.map(ImageUrl::getUrl).orElse(null);
    }

    public void deleteUrl(Long id) {
        urlRepository.deleteById(id);
    }

}
