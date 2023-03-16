github地址 https://github.com/JetBrains/intellij-community

| 变量           | 对应Java类                                              |
|--------------|------------------------------------------------------|
| StringUtil   | com.intellij.openapi.util.text.StringUtil            |
| field        | org.jetbrains.java.generate.element.FieldElement     |
| helper       | org.jetbrains.java.generate.element.GenerationHelper |
| settings     | com.intellij.psi.codeStyle.JavaCodeStyleSettings     |
| class        | org.jetbrains.java.generate.element.ClassElement     |
| java_version | 无,就是返回Java版本                                         |

由于StringUtil是个普通类,理论上其他类也可以通过该方式引入,尚未尝试