package net.lecigne.somafm.recentlib;

public class SomaFmException extends RuntimeException {

  SomaFmException(String message, Throwable cause) {
    super(message, cause);
  }

  SomaFmException(String message) {
    super(message);
  }

}
