package net.lecigne.somafm.example.application;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;
import net.lecigne.somafm.recentlib.Broadcast;
import net.lecigne.somafm.recentlib.CustomChannel;
import net.lecigne.somafm.recentlib.PredefinedChannel;
import net.lecigne.somafm.recentlib.SomaFm;
import org.springframework.stereotype.Service;

@Service
public class SomaFmRecentService {

  private final SomaFm somaFm;

  public SomaFmRecentService(SomaFm somaFm) {
    this.somaFm = somaFm;
  }

  // Although Groove Salad is a predefined channel, we use it here to illustrate the use of CustomChannel.
  public List<Broadcast> fetchRecent() {
    return Stream.of(
            PredefinedChannel.DRONE_ZONE,
            CustomChannel.of("groovesalad", "Groove Salad", false)
        )
        .map(channel -> CompletableFuture.supplyAsync(() -> somaFm.fetchRecent(channel)))
        .map(CompletableFuture::join)
        .flatMap(List::stream)
        .sorted(Comparator.comparing(Broadcast::time).reversed())
        .toList();
  }

}
