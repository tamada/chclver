# chclver

[![Build](https://github.com/tamada/chclver/actions/workflows/maven.yml/badge.svg)](https://github.com/tamada/chclver/actions/workflows/maven.yml) [![Coverage Status](https://coveralls.io/repos/github/tamada/chclver/badge.svg?branch=main)](https://coveralls.io/github/tamada/chclver?branch=main)

[![codebeat badge](https://codebeat.co/badges/50cf2989-ec6b-467c-9f63-1856478f94d4)](https://codebeat.co/projects/github-com-tamada-chclver-main)

[![License Apache 2.0](https://img.shields.io/badge/License-Apache%202.0-green?logo=apache)](https://github.com/tamada/chclver/blob/main/LICENSE) [![Version 1.0.0-SNAPSHOT](https://img.shields.io/badge/Version-1.0.0--SNAPSHOT-green)](https://github.com/tamada/chclver/releases/tag/v1.0.0) [![GitHub Discussion](https://img.shields.io/badge/GitHub-Discussions-green?logo=github)](https://github.com/tamada/chclver/discussions)

Change class file versions.

## :speaking_head: Overview

This tool manipulates the class files' version (`major_version` and `minor_version`).

The main features are as follows.

1. Print the version of the given class files.
2. Update the version of the given class files to the specified version.
3. List the class files' version and corresponding JVM version.

## :runner: Usage

```sh
chclver [OPTIONS] <JAR|CLASS_FILE|DIRECTORY...>
OPTIONS
  -d, --destination <DEST>      specify the destination. Default is chclver directory.
      --list-versions           list version of class files and corresponding Java version.
      --to <JAVA_VERSION>       specify the Java version for updating to (e.g., Java7, 
                                Java17).  If this option is absent, print the versions 
                                of the given class files.
      --force-to <MAJOR.MINOR>  specify the versions of the class files directory.
  -v, --verbose                 verbose mode.
  -h, --help                    print this message.
```

The JVM class files have major and minor versions to distinguish the target JVM. The format of the class files is defined in [the JVM specification](https://docs.oracle.com/javase/specs/jvms/se17/html/jvms-4.html#jvms-4.1).

`chclver` prints/updates class files' version by reading JVM class files.

## :anchor: Install

### :beer: Homebrew

Install `chclver` via [Homebrew](https://brew.sh) to run the following commands.

```sh
brew tap tamada/brew # at the first time only
brew install chclver
```

If you want to install native commands, use `chclver-native` instead of `chclver` (not yet).

### :muscle: Compile yourself

Get source codes by `git clone` or download from [GitHub](https://github.com/tamada/chclver), then run `mvn` to build `chclver`.

```sh
git clone https://github.com/tamada/chclver.git
cd chclver
mvn package
```

## :smile: About

### :speech_balloon: Discussions

[![GitHub Discussion](https://img.shields.io/badge/GitHub-Discussions-green?logo=github)](https://github.com/tamada/chclver/discussions)

Please post the message.

### :man_cook: How to Contribute :woman_cook:

1. Fork it (https://github.com/tamada/chclver)
2. Create your feature branch (`git checkout -b my-new-feature`)
3. Run test suite (`mvn package`)
4. Commit your changes (`git commit -m 'feat: Add some feature'`)
   * Should follow [Conventional commits](https://www.conventionalcommits.org/) to the commit messages.
5. Push to the branch (`git push origin my-new-feature`)
6. Create a new pull request

### :man_office_worker: Developers :woman_office_worker:

* Haruaki Tamada ([tamada](https://github.com/tamada))

### :scroll: License

[![License Apache 2.0](https://img.shields.io/badge/License-Apache%202.0-green?logo=apache)](https://github.com/tamada/chclver/blob/main/LICENSE)

>    Copyright 2021 Haruaki TAMADA
>
>    Licensed under the Apache License, Version 2.0 (the "License");
>    you may not use this file except in compliance with the License.
>    You may obtain a copy of the License at
>
>        http://www.apache.org/licenses/LICENSE-2.0
>
>    Unless required by applicable law or agreed to in writing, software
>    distributed under the License is distributed on an "AS IS" BASIS,
>    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
>    See the License for the specific language governing permissions and
>    limitations under the License.
