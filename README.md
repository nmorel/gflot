gflot
=====
gflot is a GWT wrapper of the javascript libray [flot](http://www.flotcharts.org/).

**Current stable version :** 3.3.0

**Current dev version :** 3.3.1-SNAPSHOT

A demo is available here : http://gflot2.appspot.com/

You can check the last release notes here : https://github.com/nmorel/gflot/releases

Documentation
-------------
You can find more documentation here : https://github.com/nmorel/gflot/wiki

Forum
-----
If you have any questions, you can ask them on the [discussion group](https://groups.google.com/forum/?fromgroups#!forum/gflot).

Usage
-----
Download the last version here : https://github.com/nmorel/gflot/releases

Or use Maven :

    <dependency>
      <groupId>com.googlecode.gflot</groupId>
      <artifactId>gflot</artifactId>
      <version>3.3.0</version>
      <scope>provided</scope>
    </dependency>

Snapshots are available through the [OSS Sonatype Snapshot Repository](https://oss.sonatype.org/content/repositories/snapshots/com/googlecode/gflot/gflot/).
You can use them by adding the following <repository> to your own projects pom.xml :

    <repository>
        <id>oss-sonatype-snapshots</id>
        <url>https://oss.sonatype.org/content/repositories/snapshots</url>
        <snapshots>
            <enabled>true</enabled>
        </snapshots>
    </repository>

Then add `<inherits name='com.googlecode.gflot.GFlot'/>` to your module descriptor XML file.
