# `somafm-recentlib`

`somafm-recentlib` is a Java library that retrieves [SomaFM][1]'s recently played songs.

The latest stable release is [v0.2.0][11]. Development for v0.3.0 is ongoing in the [develop][12]
branch.

Please support SomaFM's awesome work [here][2]!

# About

As stated on [this page][3]:

> SomaFM API is no longer available to third parties

Thus this library works by parsing SomaFM's "Recently Played Songs" page (example [here][4]).

This functionality was originally extracted from the [somafm-song-history][5] project.

This lib is developed for my very limited personal use and as such, it is not published on any
public artifact repository, only on a [private, self-hosted one][6]. You can always grab the jar and
include it in your project (see below). I'll be glad to help if you encounter any [issue][7].

See the [changelog][8].

# Usage

## API

You will find a Spring toy project thats uses the lib in the `example-project` module.

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

### Jitpack

[Jitpack][10] is pretty cool. Just add the JitPack repository to your build file (here, a Maven POM
file):

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
  <version>v0.1.1</version>
</dependency>
```

And voilà! Everything should work.

### JAR file

You can also use the lib's JAR directly by following this workflow:

1. Grab a the JAR and POM files of a given version of the lib from [release section][9] of this
   repository.

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
     <version>0.1.1</version>
   </dependency>
   ```

# Development

The functional tests are an experiment, under development. The goal is to execute them on a regular
basis on a Raspberry Pi to check the lib's compatibility with the page it is parsing, and receive
alerts in case of errors.

[1]: https://somafm.com

[2]: https://somafm.com/support/

[3]: https://somafm.com/linktous/api.html

[4]: https://somafm.com/dronezone/songhistory.html

[5]: https://github.com/alecigne/somafm-song-history

[6]: https://reposilite.com/

[7]: https://github.com/alecigne/somafm-recently-played/issues

[8]: CHANGELOG.md

[9]: https://github.com/alecigne/somafm-recentlib/releases

[10]: https://jitpack.io/

[11]: https://github.com/alecigne/somafm-recentlib/tree/v0.2.0

[12]: https://github.com/alecigne/somafm-recentlib/tree/develop
