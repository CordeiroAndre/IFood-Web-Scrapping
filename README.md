# IFood-Web-Scrapping 🕷️🍗
---- 

### Introdução
_Estudando SQL, análise de dados, ou qualquer outro tema e quer desenvolver um projeto com **dados reais**?_

Esse projeto Java faz o scrapping dos cardápios de restaurantes, a partir da definição de uma determinada localização e salva os dados coletados em um Banco PostgreSQL. 

### Requisitos 
 1. Ter o **_Java 21_** instalado na máquina.
 2. Ter o **_PostgreSQL_** instalado na máquina.

### Como usar ?
 1. Configure o seu arquivo **application.properties** com o seu usuário e senha do banco de dados que deseja salvar os dados. 
 2. Dentro do arquivo **IfoodApplication.java** altere o texto de **"hamburguer"** (na linha 25, parâmetro do comando _OpenIfoodPage()_) para qualquer outro tipo de comida que deseje explorar.
 3. Dentro do mesmo arquivo, altere o texto de **"tubarão"** (na linha 26, parâmetro do comando _setLocation()_) para qualquer outra cidade que deseje explorar.

### Problemas comuns: 

1. Caso se depare com qualquer tipo de lentidão ou falhas para carregar os dados durante o scrapping, uma possível solução pode ser redefinir o seu IP. Para fazer isso, basta abrir o SHELL e digitar o comando '**ipconfig /renew** 


### Tecnologias: 

Esse projeto foi construído usando __Java, Selenium, JSoup e Spring Data__.  
