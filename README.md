# Selenium Dynamic Web Tables — Roll No. 5

## Fast setup in VS Code (Windows)
1. Install **JDK 21 x64**: https://adoptium.net/temurin/releases/?version=21
2. Install **Apache Maven**: https://maven.apache.org/download.cgi and add its `bin` folder to PATH. Verify `java -version`, `javac -version`, and `mvn -version` in a new Command Prompt.
3. Install **Google Chrome** and **Visual Studio Code**.
4. In VS Code, install the recommended extensions when prompted: **Extension Pack for Java** and **Maven for Java**.
5. Extract the ZIP, then VS Code > File > Open Folder > select this project folder.
6. Wait for Java/Maven project import to finish. Ensure the terminal is opened at the folder containing `pom.xml`.
7. Run `mvn clean test`, or double-click `run-tests.bat`.

The first Maven run needs internet to download dependencies. Selenium Manager attempts to obtain the matching ChromeDriver automatically.

## Test cases
- TC01: verify initial table rows and first employee.
- TC02: add a dynamic row and verify name/role.
- TC03: search/filter employee rows.
- TC04: delete a row and verify count.
- TC05: reject empty required fields.

## Reports and evidence
Inspect `target/surefire-reports/` after running. Capture actual screenshots of the demo page and terminal test summary, then insert them in the report. Do not report tests as passed until you have run them.

## Jenkins/GitHub
The included `Jenkinsfile` is a starter pipeline. Jenkins is not installed/configured by this ZIP. After the local tests work, push the project to GitHub, install/configure Jenkins and its Git/Pipeline plugins, configure a JDK tool named `JDK21`, and run the pipeline. Capture real screenshots of your GitHub repository and Jenkins build.
