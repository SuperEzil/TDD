# 테스트 실행 방법

해당 프로젝트는 Spring Boot에서의 다양한 테스트 방식을 시험하기 위한 프로젝트입니다.



기본적으로 Test Class외에 Build & Debug 실행하여 테스트 하는 경우
환경 변수 설정 후 실행이 필요합니다.

| Environment variables  | description  |
|------------------------|--------------|
| datasource.url         | DB 주소        |
| datasource.username    | 디비 사용자명      |
| datasource.password    | 디비 비밀번호      |
| spring.profiles.active | chaos-monkey |

# ChaosMonkey 실행 방법

- 환경 변수 설정
  spring.profiles.active은 `application.properties` 설정에 고정해서 사용 할 수도 있습니다.



### Reference Documentation

* [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)
* [AssertJ](https://joel-costigliola.github.io/assertj/)
* [mockito](https://site.mockito.org/)
* [Testcontainers](https://www.testcontainers.org/)
* [JMeter](https://jmeter.apache.org/)
* [Chaos Monkey for Spring Boot](https://codecentric.github.io/chaos-monkey-spring-boot/)
* [ArchUnit](https://www.archunit.org/)



