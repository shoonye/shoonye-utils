# shoonye-utils 
This project consists of various utilities that almost any standard Java EE project will need. Compiled libraries are available on Maven Central repository.

*Version: 1.0.0

## System Requirements

JDK 8 or above.

## Documentation

The documentation of the distribution package or can be accessed [online](http://javadocs.io/to_be_done/).

## Build from Source

You can build the utilities from source by cloning the git repository.You will also need a JDK 8+ and Maven 3 (>= 3.3.1). With these prerequisites in place you can compile the source via:

    mvn clean install
    
## simple-utils 
These utilities do not have any dependencies execpt for standard java libraries. The utilities contains utilities related to to common File/InputStream operations, utilites to manipulate String, Map, email validator etc.

This module also contains some utility bean object such as Duple, OrderedSearchResult etc. 

* Add the following artifact to your Maven/Ivy/Gradle dependency list:

        <dependency>
            <groupId>shoonye.utils</groupId>
            <artifactId>simple-utils</artifactId>
            <version>1.0.0</version>
        </dependency>

## date-utils
This contains a simple useful Stopwatch, TimeFilter, TimeUnit, and some utility date operations in DateUtil.

* Add the following artifact to your Maven/Ivy/Gradle dependency list:

        <dependency>
            <groupId>shoonye.utils</groupId>
            <artifactId>date-utils</artifactId>
            <version>1.0.0</version>
        </dependency>

## hibernate-utils 
Standard classes that every Hibernate Project will need, BaseDao, AuditedEntity, GenericDao, JodaDateType (if you still use Joda Time).

* Add the following artifact to your Maven/Ivy/Gradle dependency list:

        <dependency>
            <groupId>shoonye.utils</groupId>
            <artifactId>hibernate-utils</artifactId>
            <version>1.0.0</version>
        </dependency>

## json-utils 
Defines FexliObject, which itself is a very interesting concept when you deal with JSON and JAVA object. Also various utilities around JSON and FlexiObject.

* Add the following artifact to your Maven/Ivy/Gradle dependency list:

        <dependency>
            <groupId>shoonye.utils</groupId>
            <artifactId>json-utils</artifactId>
            <version>1.0.0</version>
        </dependency>
        
## spring-utils 
Lone utility here is SpringContext.

* Add the following artifact to your Maven/Ivy/Gradle dependency list:

        <dependency>
            <groupId>shoonye.utils</groupId>
            <artifactId>spring-utils</artifactId>
            <version>1.0.0</version>
        </dependency>

### License

See the [LICENSE](LICENSE.txt) file for license rights and limitations (Apache 2).