package net.lecigne.somafm.recentlib;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectPackage;

import lombok.extern.slf4j.Slf4j;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;

@Slf4j
public class TestRunner {
  public static void main(String[] args) {
    LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
        .selectors(selectPackage("net.lecigne.somafm.recentlib"))
        .build();
    Launcher launcher = LauncherFactory.create();
    var listener = new SummaryGeneratingListener();
    launcher.registerTestExecutionListeners(listener);
    launcher.execute(request);
    log.info("{} functional test(s) succeeded", listener.getSummary().getTestsSucceededCount());
  }
}
