# 🧾 ComerceTrack - Carga de Productos y Generación de Facturas con Spring Batch

**ComerceTrack** es una aplicación backend construida con **Java y Spring Boot**, que utiliza **Spring Batch** para automatizar la carga masiva de productos desde archivos CSV y la posterior **generación de facturas en PDF** a partir de los datos almacenados en base de datos.

---

## 🚀 Tecnologías principales

- ✅ **Spring Boot 3**
- ✅ **Spring Batch**
- ✅ **Spring Data JPA**
- ✅ **MySQL**
- ✅ **Docker & Docker Compose**
- ✅ **Apache PDFBox** (para la generación de archivos PDF)

---

## 🧠 ¿Qué hace este proyecto?

### 📦 Carga de productos (Spring Batch)
- Lee archivos `.csv` con productos.
- Procesa cada línea usando Spring Batch:
  - Validación y transformación (`ItemProcessor`)
  - Guardado en base de datos (`ItemWriter`)
- Soporta carga masiva de catálogos/inventarios.

### 📄 Generación de facturas en PDF
- A partir de los productos guardados en la base de datos:
  - Se recuperan los datos con Spring Data JPA.
  - Se genera una **factura en PDF**.
  - Puede ejecutarse de forma programada (`@Scheduled`) o manual.

---
