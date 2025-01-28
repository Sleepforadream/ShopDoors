FROM gradle:8.6.0-jdk21-jammy AS builder

# Указываем рабочую директорию
WORKDIR /app

# Копируем проект из корня, относительно места сборки
COPY ../ .

# Собираем приложение
RUN gradle bootJar --no-daemon

FROM amazoncorretto:17

# Копируем скомпилированный JAR из этапа сборки
COPY --from=builder /app/build/libs/*.jar /app/Shop-Doors.jar

# Добавляем пользователя
RUN adduser --disabled-password --gecos '' appuser
RUN chown -R appuser:appuser /app

# Переключаемся на пользователя
USER appuser

# Устанавливаем рабочую директорию
WORKDIR /app

# Команда запуска
CMD ["java", "-jar", "./Shop-Doors.jar"]