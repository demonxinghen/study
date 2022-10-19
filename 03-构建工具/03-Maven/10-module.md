### 1.module
用户可以通过在一个打包方式为pom的maven项目中声明任意数量的module来实现模块的整合.

module的值是当前pom.xml的相对目录名, 可以和artifactId不一样.

比如你可以写成<module>children</module>,也可以写成<module>../children</module>

### 2.聚合

通常一个打包为pom的项目下有多个打包为jar的模块,那么这个pom项目也就是聚合项目,并没有相关源代码,也没有src/main/java目录.

maven会首先解析聚合项目的pom,分析要构建的模块,并且计算出一个反应堆构建顺序,然后根据这个顺序构建各个模块.

### 3.继承

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<project xmlns="http://maven.apache.org/POM/4.0.0">
    <parent>
        <groupId>com.test</groupId>
        <artifactId>parent</artifactId>
        <version>1.0.0-SNAPSHOT</version>
        <relativePath>../pom.xml</relativePath> <!-- relativePath,寻找父项目的pom文件,默认值是../pom.xml -->
    </parent>
</project>
```

### 4.可继承的pom元素
* groupId
* version
* description
* organization:项目的组织信息
* inceptionYear:项目的创始年份
* url:项目的URL地址
* developers:项目的开发者信息
* contributors:贡献者信息
* distributionManagement:部署配置
* issueManagement:缺陷跟踪系统信息
* ciManagement:持续集成系统信息
* scm:版本控制系统信息
* mailingLists:邮件列表信息
* properties:自定义的maven属性
* dependencies:依赖配置
* dependencyManagement:依赖管理配置
* repositories:仓库配置
* build:源码目录配置,输出目录配置,插件配置,插件管理配置
* reporting:报告输出目录配置,报告插件配置
### 5.依赖管理
dependencyManagement能让子模块集成到父模块的依赖配置,又不会实际引入依赖.

不使用dependencies的原因是不管子模块需不需要用到父模块中的依赖,都会被引入.

### 6.插件管理
pluginManagement, 同dependencyManagement.

### 7.默认配置
在maven仓库中有提到有一个默认仓库的配置,在那个超级pom.xml中,还有以下配置

```xml

<project xmlns="http://maven.apache.org/POM/4.0.0">
    <build>
        <directory>${project.basedir}/target</directory>
        <outputDirectory>${project.build.directory}/classes</outputDirectory>
        <finalName>${project.artifactId}-${project.version}</finalName>
        <testOutputDirectory>${project.build.directory}/test-classes</testOutputDirectory>
        <sourceDirectory>${project.basedir}/src/main/java</sourceDirectory> <!--这就是为什么maven项目默认源码目录是src/main/java,我们也可以在项目中通过指定sourceDirectory来修改源码目录,一般不建议-->
        <scriptSourceDirectory>${project.basedir}/src/main/scripts</scriptSourceDirectory>
        <testSourceDirectory>${project.basedir}/src/test/java</testSourceDirectory>
        <resources>
            <resource>
                <directory>${project.basedir}/src/main/resources</directory>
            </resource>
        </resources>
        <testResources>
            <testResource>
                <directory>${project.basedir}/src/test/resources</directory>
            </testResource>
        </testResources>
        <pluginManagement>
            <!-- NOTE: These plugins will be removed from future versions of the super POM -->
            <!-- They are kept for the moment as they are very unlikely to conflict with lifecycle mappings (MNG-4453) -->
            <plugins>
                <plugin>
                    <artifactId>maven-antrun-plugin</artifactId>
                    <version>1.3</version>
                </plugin>
                <plugin>
                    <artifactId>maven-assembly-plugin</artifactId>
                    <version>2.2-beta-5</version>
                </plugin>
                <plugin>
                    <artifactId>maven-dependency-plugin</artifactId>
                    <version>2.8</version>
                </plugin>
                <plugin>
                    <artifactId>maven-release-plugin</artifactId>
                    <version>2.5.3</version>
                </plugin>
            </plugins>
        </pluginManagement>
    </build>
</project>
```

### 8.反应堆