# `somafm-recentlib`

`somafm-recentlib` is a Java library that retrieves [SomaFM][somafm]'s recently played songs.

The latest stable release is [v0.2.3][stable]. Development for v0.3.0 is ongoing in the
[develop][develop] branch.

Please support SomaFM's awesome work [here][soma-support]!

# About

As stated on [this page][soma-api]:

> SomaFM API is no longer available to third parties

Thus this library works by parsing SomaFM's "Recently Played Songs" page (example
[here][soma-history]).

This functionality was originally extracted from the [somafm-song-history][somafm-song-history]
project.

This lib is developed for my very limited personal use and as such, it is not published on any
public artifact repository, only on a [private, self-hosted one][reposilite]. You can always grab
the JAR and include it in your project (see below). I'll be glad to help if you encounter any
[issue][issues].

See the [changelog][changelog].

# Usage

You need Java 17+ to include this library.

## API

You will find a Spring toy project that uses the lib in the `example-project` module.

Basic usage:

```java
// Normal usage
SomaFm somaFm = SomaFm.of("your-user-agent");
List<Broadcast> broadcasts = somaFm.fetchRecent(PredefinedChannel.GROOVE_SALAD);

// With a channel not yet supported by the lib
CustomChannel myCustomChannel = CustomChannel.of("newchannel", "New Channel", false);
List<Broadcast> otherBroadcasts = somaFm.fetchRecent(myCustomChannel);
```

In case you are working from user input:

```java
String publicName = getUserInput();
Channel channel = PredefinedChannel
    .getByPublicName(publicName)
    .orElseThrow(() -> new UnknownChannelException(publicName)); // Create your own exception
```

## Dependency

This lib might be published on a public repository one day. In the meantime, you can use the lib in
two different ways.

### JitPack

[JitPack][jitpack] is pretty cool. Just add the JitPack repository to your build file (here, a Maven
POM file):

```xml
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>
```

...then the dependency to the lib's Maven submodule (and not the entire repo, or you will pull the
demo project which depends on Spring!):

```xml
<dependency>
  <groupId>com.github.alecigne.somafm-recentlib</groupId>
  <artifactId>somafm-recentlib</artifactId>
  <version>v0.2.3</version>
</dependency>
```

And voilà! Everything should work.

### JAR file

You can also use the lib's JAR directly by following this workflow:

1. Grab the JAR and POM files from a given version of the lib from [releases section][releases] of
   this repository.

2. Create a `lib/` directory in your project.

3. Still in your project, execute the following Maven command after filling the placeholders:

   ```shell
   ./mvnw install:install-file \
     -Dfile=[path-to-jar] \
     -DpomFile=[path-to-pom] \
     -DlocalRepositoryPath=lib/
   ```

4. Add this to your POM:

   ```xml
   <repositories>
     <repository>
       <id>local-repo</id>
       <url>file://${project.basedir}/lib/</url>
       <releases>
         <enabled>true</enabled>
       </releases>
     </repository>
   </repositories>
   ```

5. Add this to your dependencies:

   ```xml
   <dependency>
     <groupId>net.lecigne</groupId>
     <artifactId>somafm-recentlib</artifactId>
     <version>0.2.3</version>
   </dependency>
   ```

# Development

The functional tests are an experiment, under development. The goal is to execute them regularly on
a Raspberry Pi to check the lib's compatibility with the page it is parsing, and receive alerts in
case of errors.

[somafm]:
https://somafm.com

[soma-support]:
https://somafm.com/support/

[soma-api]:
https://somafm.com/linktous/api.html

[soma-history]:
https://somafm.com/dronezone/songhistory.html

[somafm-song-history]:
https://github.com/alecigne/somafm-song-history

[reposilite]:
https://reposilite.com/

[issues]:
https://github.com/alecigne/somafm-recentlib/issues

[changelog]:
CHANGELOG.md

[releases]:
https://github.com/alecigne/somafm-recentlib/releases

[jitpack]:
https://jitpack.io/

[stable]:
https://github.com/alecigne/somafm-recentlib/tree/v0.2.3

[develop]:
https://github.com/alecigne/somafm-recentlib/tree/develop
