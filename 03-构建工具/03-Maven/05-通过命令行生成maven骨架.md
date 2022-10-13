任意目录下执行
```shell
mvn archetype:generate
```
接下来会有一大段的下载,直到出现
```text
[INFO] Generating project in Interactive mode
[WARNING] No archetype found in remote catalog. Defaulting to internal catalog
[INFO] No archetype defined. Using maven-archetype-quickstart (org.apache.maven.archetypes:maven-archetype-quickstart:1.0)
Choose archetype:
1: internal -> org.apache.maven.archetypes:maven-archetype-archetype (An archetype which contains a sample archetype.)
2: internal -> org.apache.maven.archetypes:maven-archetype-j2ee-simple (An archetype which contains a simplifed sample J2EE application.)
3: internal -> org.apache.maven.archetypes:maven-archetype-plugin (An archetype which contains a sample Maven plugin.)
4: internal -> org.apache.maven.archetypes:maven-archetype-plugin-site (An archetype which contains a sample Maven plugin site.
      This archetype can be layered upon an existing Maven plugin project.)
5: internal -> org.apache.maven.archetypes:maven-archetype-portlet (An archetype which contains a sample JSR-268 Portlet.)
6: internal -> org.apache.maven.archetypes:maven-archetype-profiles ()
7: internal -> org.apache.maven.archetypes:maven-archetype-quickstart (An archetype which contains a sample Maven project.)
8: internal -> org.apache.maven.archetypes:maven-archetype-site (An archetype which contains a sample Maven site which demonstrates
      some of the supported document types like APT, XDoc, and FML and demonstrates how
      to i18n your site. This archetype can be layered upon an existing Maven project.)
9: internal -> org.apache.maven.archetypes:maven-archetype-site-simple (An archetype which contains a sample Maven site.)
10: internal -> org.apache.maven.archetypes:maven-archetype-webapp (An archetype which contains a sample Maven Webapp project.)

Choose a number or apply filter (format: [groupId:]artifactId, case sensitive contains): 7:
```
出现了10个骨架可供选择, 可输入编号选择自己想要的, 默认是7, 敲击回车.
```text
Define value for property 'groupId': com.juvenxu.mvnbook
Define value for property 'artifactId': hello-world2
Define value for property 'version' 1.0-SNAPSHOT: : 直接回车, 默认版本号为1.0-SNAPSHOT
Define value for property 'package' com.juvenxu.mvnbook: : 直接回车, 默认创建包路径
Confirm properties configuration:
groupId: com.juvenxu.mvnbook
artifactId: hello-world2
version: 1.0-SNAPSHOT
package: com.juvenxu.mvnbook
 Y: :  最后确认配置是否正确, 正确的话输入Y, 则开始创建.
```

创建完成.