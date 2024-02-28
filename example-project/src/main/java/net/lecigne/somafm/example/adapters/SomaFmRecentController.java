package net.lecigne.somafm.example.adapters;

import java.util.List;
import net.lecigne.somafm.example.application.SomaFmRecentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class SomaFmRecentController {

  private final SomaFmRecentService somaFmRecentService;

  SomaFmRecentController(SomaFmRecentService somaFmRecentService) {
    this.somaFmRecentService = somaFmRecentService;
  }

  @GetMapping("/recent")
  ResponseEntity<List<BroadcastDto>> fetchRecent() {
    List<BroadcastDto> broadcastDtos = BroadcastDto.fromDomain(somaFmRecentService.fetchRecent());
    return ResponseEntity.ok().body(broadcastDtos);
  }

}
