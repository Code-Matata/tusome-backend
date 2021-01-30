package ke.co.willynganga.tusomebackend;

import ke.co.willynganga.tusomebackend.repositories.ImageRepository;
import ke.co.willynganga.tusomebackend.repositories.PaperRepository;
import ke.co.willynganga.tusomebackend.services.ImageService;
import ke.co.willynganga.tusomebackend.services.PaperService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class TusomeBackendApplicationTests {

	@Autowired
	private PaperService paperService;
	@Autowired
	private ImageService imageService;
	@Autowired
	private PaperRepository paperRepository;
	@Autowired
	private ImageRepository imageRepository;

	@Test
	void contextLoads() {
		assertThat(paperRepository).isNotNull();
		assertThat(imageRepository).isNotNull();
		assertThat(imageService).isNotNull();
		assertThat(paperService).isNotNull();
	}

}
