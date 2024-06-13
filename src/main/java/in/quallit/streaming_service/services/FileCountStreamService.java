package in.quallit.streaming_service.services;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.stream.Stream;

/**
 * @author Jigs
 */
@Service
public class FileCountStreamService {

    public Flux<Long> streamFileCount() {
        Flux<Long> interval = Flux.interval(Duration.ofSeconds(3));
        Flux<Long> events = Flux.fromStream(Stream.generate(() -> {
            try {
                Long cnt = this.readCount();
                System.out.println(cnt);
                return cnt;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }));
        return Flux.zip(events, interval, (key, value) -> key);
    }

    private long readCount() throws IOException {
        var path = Path.of("E:\\Personal\\Projects\\SpringFluxStreamingDemo\\test");
        return Files.walk(path).filter(Files::isRegularFile).count();
    }
}
