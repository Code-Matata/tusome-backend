package ke.co.willynganga.tusomebackend;

import ke.co.willynganga.tusomebackend.v2.repository.PaperRepository;
import ke.co.willynganga.tusomebackend.v2.services.PaperService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class TusomeBackendApplicationTests {

	@Autowired
	private PaperService paperService;
	@Autowired
	private PaperRepository paperRepository;

	@Test
	void contextLoads() {
		assertThat(paperRepository).isNotNull();
		assertThat(paperService).isNotNull();
	}

}
