# quandoo-automation-tests

### Technologies used

1. [Java SE Development Kit](https://www.oracle.com/technetwork/java/javase/downloads/index.html)  
2. [Maven](https://maven.apache.org/install.html).   
3. [Selenium](https://www.seleniumhq.org/)
4. [TestNG](https://testng.org/doc/index.html)
5. [Allure Report](https://github.com/allure-framework/allure-java)
6. [WebDriverManager](https://github.com/bonigarcia/webdrivermanager)

### Framework overview

The framework is a selenium wrapper where every page is represented with a java Class (Page object Model), and selenium methods are sitting in one place where they can be reused.

Allure is used to generate cute and clear reports with description of test steps. In case of failed test, a screenshot is attached to the report.


### How to execute tests

Run the command line to exectue tests
```bash  
mvn clean test
```
Run the command line to generate allure report and open it in the browser

```bash  
mvn allure:serve
```
