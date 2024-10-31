# Use a versão do Tomcat
FROM tomcat:10-jdk11

# Exponha a porta 8080
EXPOSE 8080

# Copie o conteúdo do diretório webapp para o diretório de webapps do Tomcat
COPY src/main/webapp /usr/local/tomcat/webapps/myfintech