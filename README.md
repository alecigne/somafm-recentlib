# `somafm-recentlib`

Library that retrieves [SomaFM][1]'s recently played songs.

Please support SomaFM's awesome work [here][2].

# About

As stated on [this page][3]:

> SomaFM API is no longer available to third parties

Thus this library works by parsing SomaFM's "Recently Played Songs" page (example [here][4]).

This functionality was originally extracted from the [somafm-song-history][5] project.

This lib is developed for my very limited personal use and as such, it is not published on any
public artifact repository, only on a [private, self-hosted one][6]. You can always grab the jar and
include it in your project. I'll be glad to help if you encounter any [issue][7].

See the [changelog][8].

# Usage

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
