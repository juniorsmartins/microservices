# Micro_GatewayServer

em construção.....

## ÍNDICE

1. Tecnologias;
2. Diagramas;

### TECNOLOGIAS

1. __Java (versão 21 LTS)__:


2. __Spring Boot__: é um framework Java de código aberto que visa simplificar e agilizar o desenvolvimento de aplicações Spring. Ele fornece diversas funcionalidades e recursos que eliminam a necessidade de configuração manual complexa, permitindo que você se concentre em escrever o código da sua aplicação e não em configurar o ambiente.


3. __Spring Cloud Gateway (versão 2023.0.1)__: O Spring Cloud Gateway é um componente fundamental em arquiteturas de microsserviços, atuando como um ponto de entrada único e inteligente para todas as requisições. Imagine-o como um porteiro de um prédio de apartamentos, que direciona cada visitante para o apartamento correto. Ele direciona as requisições HTTP para o microsserviço correto com base em regras configuráveis.


4. __Spring Cloud Circuit Breaker Resilience4J__: O Spring Cloud Circuit Breaker, com o suporte da biblioteca Resilience4J, é um mecanismo de proteção para seus microsserviços, atuando como um disjuntor para prevenir que falhas em um serviço se propaguem por toda a aplicação. Imagine um cenário onde um serviço está respondendo lentamente ou até mesmo indisponível. Sem um Circuit Breaker, as chamadas para esse serviço falhariam repetidamente, consumindo recursos e potencialmente derrubando outros serviços. O Circuit Breaker entra em ação para evitar essa cascata de falhas.


5. __Spring Boot Data Redis__: O Spring Boot Data Redis é uma ferramenta poderosa para trabalhar com Redis em aplicações Spring Boot. Ele simplifica o acesso a dados, aumenta a produtividade e facilita a integração com outros componentes do Spring. Usado como Cache para armazenar dados frequentemente acessados para melhorar o desempenho da aplicação.


6. __Spring Boot Actuator__: é uma biblioteca do Spring Boot que fornece endpoints HTTP expondo informações detalhadas sobre o estado interno da sua aplicação Spring. Isso permite que você monitore e gerencie sua aplicação de forma mais eficaz, facilitando a depuração, solução de problemas e otimização.


7. __Spring Cloud Bus__: é um framework para comunicação e coordenação de microsserviços distribuídos. Ele fornece um canal de eventos centralizado para que os microsserviços se comuniquem entre si, permitindo que você implemente eventos, notificações e atualizações de estado em tempo real.


8. __Spring Cloud Eureka Client__: O Spring Cloud Eureka Client é uma biblioteca fundamental para a implementação de arquiteturas de microsserviços, fornecendo um mecanismo robusto e eficiente para a descoberta de serviços. Cada microsserviço que utiliza o Eureka Client se registra em um servidor Eureka, informando seu endereço de IP, porta e outras informações relevantes.


9. __Micrometer__: O Micrometer é uma biblioteca de instrumentação que desempenha um papel fundamental na coleta e monitoramento de métricas em arquiteturas de microsserviços. O Micrometer oferece uma API unificada para coletar métricas de diversas fontes, como sistemas operacionais, bibliotecas, frameworks e aplicações. Isso permite que você tenha uma visão consolidada das métricas de toda a sua aplicação.


10. __JUnit__: O JUnit é um framework open-source amplamente utilizado no desenvolvimento de software em Java para criar e executar testes unitários. Através de testes unitários, o JUnit permite verificar se cada unidade de código (geralmente um método) funciona de acordo com o esperado, isolando e testando cada parte do software individualmente.


10. __Datafaker__: Uma biblioteca Java para gerar dados falsos de forma rápida e fácil, como nomes, endereços, números de telefone, datas, etc.


### DIAGRAMAS

