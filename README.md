## 🤝 **postrack**

> 고객으로부터 여러 택배회사들의 송장번호들을 입력받아 한 페이지에서 택배 배송현황을 관리할 수 있는 서비스입니다.
> 
- 사용자로부터 택배 회사와 송장 번호를 입력받아, Spring의 `WebClient`를 사용하여 해당 택배 회사의 웹페이지를 호출합니다. 응답 받은 웹 페이지의 HTML 데이터는 `Jsoup` 라이브러리를 통해 파싱되어 필요한 배송 현황 정보만을 추출하여 사용자에게 표시됩니다.

  <img width="465" alt="스크린샷 2023-10-02 오후 4 00 53" src="https://github.com/kimihiqq/postrack/assets/134909318/8495b322-41a7-41ea-8692-398847da0f15">


- 프로그램은 1시간마다 자동으로 모든 택배의 배송 상태를 갱신합니다. 또한, 택배의 배송 상태나 날짜가 변경되면 해당 정보가 빨간색으로 표시되어 사용자가 쉽게 변동 사항을 인지할 수 있도록 하였습니다. 이 빨간색 표시는 24시간 후에 자동으로 사라집니다.
- 택배의 배송 상태나 날짜가 변경되면 `Telegram API`를 활용하여 메시지 알림 서비스를 제공합니다

  <img width="413" alt="postrack" src="https://github.com/kimihiqq/postrack/assets/134909318/4d718bef-d18e-4696-94dd-58200d37125f">


### 개발 환경 (Environment)

<img src="https://img.shields.io/badge/Java17-007396?style=flat-square&logo=openJDK&logoColor=white&style=flat"/></a>
<img src="https://img.shields.io/badge/Spring 5-6DB33F?style=flat-square&logo=Spring&logoColor=white&style=flat"/></a>
<img src="https://img.shields.io/badge/Spring Boot 2.7.15-6DB33F?style=flat-square&logo=Springboot&logoColor=white&style=flat"/></a>
<img src="https://img.shields.io/badge/Gradle-4429A1?style=flat-square&logo=gradle&logoColor=white&style=flat"/></a>
