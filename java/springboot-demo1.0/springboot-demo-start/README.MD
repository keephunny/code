## 通过swing界面窗口启动springboot

springboot在windows环境运行时，一般都通过cmd窗口启动，经常会被误关。为了更安全方便的启停springboot程序，使用swing窗体程序实程序的安全启停和实时日志输出。

### 目录结构
```
-bin
    -start.bat
    -stop.bat
    -app.properties
    -win_start.jar
-conf
-lib

```

### 启动
win_start.jar执行start.bat

### 停止
win_start.jar执行stop.bat

### 打包配置
Project Structure-Artifacts- + - jar - From moduls with dependency
MainIfest File：D:\github\code\java\springboot-demo1.0\springboot-demo-start\src\META-INF\MANIFEST.MF
Main Class：com.springboot.demo.start.Winform