package ke.co.willynganga.tusomebackend.repositories;

import ke.co.willynganga.tusomebackend.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
