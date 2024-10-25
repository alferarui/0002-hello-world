package be.abis.twohelloworld.controller;

import be.abis.twohelloworld.Version;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class VersionApiController {

    @GetMapping("/version")
    Version getVersion(){
        return new Version();
    }
}
