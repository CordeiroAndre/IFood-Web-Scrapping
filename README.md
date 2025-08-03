# IFood-Web-Scrapping 🕷️🍗
---- 

### Introdução
Este projeto Java foi desenvolvido para realizar o web scraping de cardápios de restaurantes do iFood. Ele permite a coleta de dados reais de menus para diversas finalidades, como estudos de SQL, análise de dados e desenvolvimento de projetos baseados em dados reais.

O sistema é capaz de extrair informações de cardápios a partir da definição de uma determinada localização e de um tipo de comida específico. Todos os dados coletados são salvos em um banco de dados PostgreSQL, fornecendo uma base robusta para suas análises e aplicações.

### Requisitos 

Para executar este projeto em sua máquina, você precisará ter os seguintes softwares instalados:
• **Java 21**
• **PostgreSQL**


### Como usar ?
1. Configure suas credenciais do banco de dados:
    ◦ Abra o arquivo _application.properties_.
    ◦ Preencha-o com o seu usuário e senha do banco de dados PostgreSQL onde você deseja salvar os dados.
2. Defina o tipo de **comida** para o scraping:
    ◦ No arquivo IfoodApplication.java, localize a linha 25.
    ◦ Altere o texto "_hamburguer_" (que é um parâmetro do comando OpenIfoodPage()) para qualquer outro tipo de comida que deseje explorar (por exemplo, "pizza", "sushi", "massas").
3. Defina a **localização** (cidade) para o scraping:
    ◦ No mesmo arquivo IfoodApplication.java, localize a linha 26.
    ◦ Altere o texto "_tubarão_" (que é um parâmetro do comando setLocation()) para o nome da cidade que você deseja explorar.

### Problemas comuns: 

1. Caso se depare com qualquer tipo de lentidão ou falhas para carregar os dados durante o scrapping, uma possível solução pode ser redefinir o seu IP. Para fazer isso, basta abrir o SHELL e digitar o comando '**ipconfig /renew** 


### Tecnologias: 

Esse projeto foi construído usando __Java, Selenium, JSoup e Spring Data__.  
