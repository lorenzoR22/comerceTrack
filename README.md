# 🧾 ComerceTrack - Carga de Productos y Generación de Facturas con Spring Batch

**ComerceTrack** es una aplicación de gestion de productos y generacion de facturas construida con **Java y Spring Boot**, que utiliza **Spring Batch** para automatizar la carga masiva de productos desde archivos CSV y la posterior **generación de facturas en PDF** a partir de los datos almacenados en base de datos.

---

## 🚀 Tecnologías principales

- ✅ **Spring Boot 3**
- ✅ **Spring Batch**
- ✅ **Spring Data JPA**
- ✅ **MySQL**
- ✅ **Docker & Docker Compose**
- ✅ **Apache PDFBox** (para la generación de archivos PDF)

---

## 🧠 Funciones principales

### 📦 Carga de productos (Spring Batch)
- Lee archivos `.csv` con productos.
- Procesa cada línea usando Spring Batch:
  - Validación y transformación (`ItemProcessor`)
  - Guardado en base de datos (`ItemWriter`)
- Soporta carga masiva de catálogos/inventarios.

### 📄 Generación de facturas en PDF
- A partir de las ventas guardadas en la base de datos:
  - Se recuperan los datos con Spring Data JPA.
  - Se genera una **factura en PDF**.
  - Se ejecuta de forma programada (`@Scheduled`).

---
## ⚙️ Configuración del proyecto (Clonación y ejecución)

1. Clonar el repositorio y entrar a la carpeta:

```bash
git clone https://github.com/lorenzoR22/comerceTrack.git
cd comerceTrack
```

2. Configurar las variables de entorno:

Opción A: Usar archivo .env
```bash
cp .env.example .env
```
Editar .env y completar con tus datos:
```bash
DB_HOST=localhost
DB_PORT=3306
DB_NAME=comercetrack
DB_USER=tu_usuario
DB_PASSWORD=tu_contraseña
```
Opción B: Usar directamente application.properties

Editar src/main/resources/application.properties con:
```bash
spring.datasource.url=jdbc:mysql://localhost:3306/comercetrack
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
```
3. Levantar la base de datos con Docker:
```bash
docker-compose up -d
```
Esto levantará un contenedor MySQL en el puerto 3306.

4. Ejecutar la aplicación:
```bash
./mvnw spring-boot:run
```
Recordá que necesitás Java 17+ y Maven (o usar el wrapper).
