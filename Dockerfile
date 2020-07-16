# Author zheng.zhang
# Version 0.0.1
# Feign客户端部署到服务器
# 基础镜像使用Java8
FROM java:8
# 作者
MAINTAINER zheng.zhang <2215751025@qq.com>
# VOLUME 指定了临时文件目录为/feign/tmp
VOLUME /feign-client/tmp
# 将jar包添加到容器中并更名为app.jar
ADD target/feign-client-0.0.1-SNAPSHOT.jar app.jar
# 运行jar包
RUN bash -c 'touch /app.jar'
# 启动时容器进程
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]
# 暴露端口
EXPOSE 8025