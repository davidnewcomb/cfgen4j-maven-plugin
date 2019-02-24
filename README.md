# Maven plugin for cfgen4j

Not installed in Maven yet. (Coming soon!)
So if you want to use it in your builds you'll have to install it locally, but that's easy:

    for proj in cfgen4j cfgen4j-maven-plugin
    do
        (git clone https://github.com/davidnewcomb/$proj.git ; cd $proj ; mvn install)
    done
Then just include it in your `pom.xml` as normal.

## Usage

    <project>
    <properties><cfgen4j.version>0.0.1</cfgen4j.version></properties>
    <build>
    <plugins>

    <plugin>
        <groupId>uk.co.bigsoft</groupId>
        <artifactId>cfgen4j-maven-plugin</artifactId>
        <version>${cfgen4j.version}</version>
        <executions>
            <execution>
                <id>cfgen4j-cr</id>
                <phase>generate-sources</phase>
                <goals>
                    <goal>generate</goal>
                </goals>
                <configuration>
                    <packageName>uk.bigsoft.cf.models.customer1</packageName>
                    <spaceId>abc123defghi</spaceId>
                    <accessToken>CFPAT-1234567890abcgdef1234567890abcgdef1234567890abcgdefabc123abc1234</accessToken>
                    <folder>${project.build.directory}/generated-sources/cf-cust1</folder>
                    <cacheFile>${project.basedir}/tmp/cf-cust1.json</cacheFile>
                </configuration>
            </execution>
            <execution>
                <id>cfgen4j-bi</id>
                <phase>generate-sources</phase>
                <goals>
                    <goal>generate</goal>
                </goals>
                <configuration>
                    <packageName>uk.bigsoft.cf.models.customer2</packageName>
                    <spaceId>jhgf45jhvbc8</spaceId>
                    <accessToken>CFPAT-...</accessToken>
                    <folder>${project.build.directory}/generated-sources/cf-cust2</folder>
                    <cacheFile>${project.basedir}/tmp/cf-cust2.json</cacheFile>
                </configuration>
            </execution>
        </executions>
    </plugin>

    </plugins>
    </build>
    </project>
