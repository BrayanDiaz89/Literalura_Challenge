<h1 align="center">LITERALURA - CATÁLOGO DE LIBROS</h1>

![portada project catalogo de libros](https://github.com/user-attachments/assets/d7420998-9673-42dc-82bf-accd00a53772)

<p align="center">
  <img alt="Static Badge" src="https://img.shields.io/badge/Release%20date-November%202024-green">
  <img alt="Static Badge" src="https://img.shields.io/badge/Status-En%20constante%20desarrollo-green">
  <img alt="Static Badge" src="https://img.shields.io/badge/Project%20version-1.0-blue">
  <img alt="Static Badge" src="https://img.shields.io/badge/Java%20version-17.0-blue">
  <img alt="Static Badge" src="https://img.shields.io/badge/Spring%20version-3.3.5-blue">
</p>

<h2>Introducción:</h2>
<p align="justify">
  Disfruta de una experiencia inolvidable, consultando y almacenando tus libros favoritos personalizadamente en tu ambiente local.
</p>
<p align="justify">
  Bienvenido a Literalura, un sistema completo realizado en <b>Java con Spring Boot</b>, que te permitirá consultar tus libros favoritos y almacenarlos localmente en una base de datos propia gestionada por <b>PostgreSQL</b>. Este sistema abstrae los datos de la <b>API</b> <a href="https://gutendex.com/" target="_blank"><strong>Gutendex</strong></a>, brindando facilidad en la manipulación de los datos y su visualización en consola.
</p>

![vistaDeLibro](https://github.com/user-attachments/assets/cdafcab4-523a-4903-b61c-3d4df5aaacd6)

<p align="justify">
  Como se muestra arriba, podrás visualizar la información más relevante de tus libros, así como obtener su imagen de portada como <b>poster</b> y descargar el libro electrónico en formato <b>.zip</b>.
</p>
<hr>

# 🔨 Funcionalidades del proyecto:
- `Funcionalidad 1`: Conexión a la API <a href="https://gutendex.com/" target="_blank"><strong>Gutendex</strong></a> para obtener datos de los libros consultados.
- `Funcionalidad 2`: Buscar libros por título o nombre de autor y almacenarlos en la base de datos gestionada con <b>PostgreSQL</b>, si no están ya guardados.
- `Funcionalidad 3`: Ver libros y autores guardados en la base de datos, incluyendo los nombres de los libros en su autoría.
- `Funcionalidad 4`: Filtrar autores por fecha de nacimiento y fecha de deceso (Base de datos).
- `Funcionalidad 5`: Filtrar libros por idiomas disponibles.
- `Funcionalidad 6`: Listar todos los libros de la base de datos con su recurso electrónico descargable o los de un autor específico.
- `Funcionalidad 7`: Ver estadísticas de tus libros según el autor, incluyendo el promedio de descargas, descargas máximas y mínimas, y el top 3 de libros más descargados.
- `Funcionalidad 8`: Ver el top 10 de libros más descargados en toda la base de datos.

![MenuPrincipal_Funcionalidades](https://github.com/user-attachments/assets/c6c6093d-edf8-46cd-90f5-e21c703bb01d)

<hr>

## 🧠 Tecnologías utilizadas: 
- Java 17.0
  - Java Streams y Lambdas
  - Colecciones Java
- Spring Framework (versión: 3.3.5)
  - Spring Boot
  - Spring Data JPA
  - Componentes de Spring (@Component, @Autowired)
- PostgreSQL
- Hibernate
- Anotaciones JPA (@Entity, @Table, @ManyToMany, etc.) 
- API Gutendex
- Jackson
- Anotaciones JSON (@JsonAlias, @JsonIgnoreProperties, etc.)
- Anotaciones Query (@Query). Consultas JPA
- Manejo de excepciones

<hr>

## 📂 Acceso al proyecto: 
![TutorialdeDescarga](https://github.com/user-attachments/assets/45294179-c3ac-45c6-9989-4311f6b82b50)
<p>Dale click en <strong style="color:red">Download ZIP</strong>.</p>
<p>Posteriormente, asegúrate de tener instalado un editor de código, preferiblemente IntelliJ. Desde allí:</p>

![Tutorialdescarga1](https://github.com/user-attachments/assets/15e48718-903c-444f-9604-d50bf9d2d32c)

<p align="justify">
Dale click en el menú y selecciona "Open File" para abrir la carpeta descomprimida. Luego podrás navegar en las carpetas de la izquierda, como se muestra en el despliegue a continuación:
</p>

![Ejecutar el proyecto](https://github.com/user-attachments/assets/ca556a8b-d13b-47e1-a178-d9b416216fb5)

<p align="justify">
<b>Antes de ejecutar</b>, es importante ajustar tus variables de entorno en Windows o Linux. A continuación, un breve ejemplo de cómo encontrarlas en <b>Windows</b>:
</p>

![variables_de_entorno](https://github.com/user-attachments/assets/04e90722-40ba-4cf3-b2d5-0ad8c1304843)

<p align="justify">
Haz click en "Variables de entorno" y modifícalas según sea necesario. ⬆️
</p>

<p align="justify">
En el menú de la izquierda, haz click en la carpeta `resources`, ingresa a <b>application.properties</b> y verás las variables de entorno configuradas. Puedes ajustarlas para que coincidan con tu configuración:
</p>

![aplication_properties](https://github.com/user-attachments/assets/efd481e1-dcb1-4c58-ac6d-9aba3e72b795)

<p align="justify">
Una vez configurado lo anterior, ya podrás utilizar correctamente la aplicación. Ve a la clase principal y dale click en ejecutar:
</p>

![ejecutar_proyecto](https://github.com/user-attachments/assets/4cda259a-300f-44ea-bcee-5800cb2bebb1)

<p align="justify">
Visualizarás el siguiente menú en consola:
</p>

![Menu_aplicacion](https://github.com/user-attachments/assets/5832e772-e4ca-44d5-bb1b-397ad0fbb192)

<p align="justify">
✔️ ¡Listo! Ahora puedes utilizar <b>LITERALURA</b>. ¡Gracias por tu tiempo e interés! 
</p>
<hr>

## 🥇 Créditos:

<p align="justify"> 
Gracias a <a href="https://www.aluracursos.com/" target="_blank"><strong>Alura LATAM</strong></a> y a <a href="https://www.oracle.com/co/education/oracle-next-education/" target="_blank"><strong>Oracle Next Education</strong></a> por esta gran oportunidad
