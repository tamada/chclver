---
title: ":anchor: Usage & Install"
---

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
