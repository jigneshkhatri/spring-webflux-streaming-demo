package in.quallit.streaming_service.controllers;

import in.quallit.streaming_service.services.FileCountStreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @author Jigs
 */
@RestController
@RequestMapping("/file-count-stream")
public class FileCountStreamController {

    private FileCountStreamService fileCountStreamService;

    @Autowired
    public void setFileCountStreamService(FileCountStreamService fileCountStreamService) {
        this.fileCountStreamService = fileCountStreamService;
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Long> getFileCount() {
        return this.fileCountStreamService.streamFileCount();
    }
}
