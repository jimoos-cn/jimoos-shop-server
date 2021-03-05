package cn.jimoos.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * Ping
 *
 * @author keepcleargas
 */
@RestController
@RequestMapping("/v1/pings")
public class PingApi {
    @GetMapping
    public String ping() {
        return new String("pinging," + LocalDateTime.now());
    }
}
