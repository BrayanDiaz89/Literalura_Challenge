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
  Disfruta de una experiencia inolvidable, consultando y almacenando tus libros favoritos personalizada mente en tú ambiente     local.
</p>
<p align="justify">
  Bienvenido a Literalura, un sistema completo realizado en <b>Java con Spring boot</b>, el cual te permitirá consultar tus libros favoritos, y almacenarlos localmente en una base de datos propia gestionada por <b>PostgreSql</b>, abstrayendo los datos de la <b>API</b> <a href="https://gutendex.com/" target="_blank">Gutendex</a>, brindando así una gran facilidad en la manipulación de los datos y posteriormente su visualización en consola:

![vistaDeLibro](https://github.com/user-attachments/assets/cdafcab4-523a-4903-b61c-3d4df5aaacd6)

Como observas anteriormente, podrás visualizar la información más relevante de tus libros, como también obtener su imagen de portada como <b>poster</b> y la descarga de su libro electrónico, por medio de la representación <b>Libro electrónico (.zip)</b>.
</p>

# 🔨Funcionalidades del proyecto:
- `Funcionalidad 1`: Conexión a la API <a href="https://gutendex.com/" target="_blank">Gutendex</a> para abstraer los datos de los libros consultados.
- `Funcionalidad 2`: Buscar libros por titulo o nombre de autor y almacenarlos en la base de datos gestionada con <b>PostgreSql</b>, en caso de que no estén ya guardados, para posteriormente visualizarlos.
- `Funcionalidad 3`: Ver libros guardados en tú base de datos, como también ver autores guardados en tú base de datos, con los nombres de los libros en su autoría.
- `Funcionalidad 4`: Filtrar autores por fecha de nacimiento y fecha de deceso (Base de datos)
- `Funcionalidad 5`: Filtrar libros por idiomas disponibles.
- `Funcionalidad 6`: Listar todos los libros de la base de datos con su recurso electrónico descargable, como también listar solo los libros de un autor específico con su recurso electrónico descargable.
- `Funcionalidad 7`: Ver estadísticas de tus libros (Según autor relacionado), visualizando sus números de libros en su autoría, promedio de descargas de sus libros, número de descargas más alto en uno de sus libros y número más bajo. También su top 3 de libros más descargados.
- `Funcionalidad 8`: Ver el top 10 de los libros más descargados en toda tú base de datos.

![MenuPrincipal_Funcionalidades](https://github.com/user-attachments/assets/c6c6093d-edf8-46cd-90f5-e21c703bb01d)

<hr>

## 🧠 Tecnologías utilizadas: 
- Java 17.0
  - Java Streams y Lambdas
  - Colecciones Java
- Spring Framework (versión: 3.3.5)
  - Spring boot
  - Spring Data JPA
  - Componentes de Spring (@Component, @Autowired)
- PostgreSQL
- Hibernate
- Anotaciones JPA (@Entity, @Table, @ManyToMany, etc) 
- API Gutendex
- Jackson
- Anotaciones JSON (@JsonAlias, @JsonIgnoreProperties, etc).
- Anotaciones Query (@Query). Consultas JPA
- Manejo de excepciones.

<hr>

## 📂 Accceso al proyecto: 
![TutorialdeDescarga](https://github.com/user-attachments/assets/45294179-c3ac-45c6-9989-4311f6b82b50)
<p>Dale click en <strong color="red">Download ZIP</strong></p>
<p>Posteriormente asegurate de tener instalado un editor de código, preferiblemente IntelliJ, desde allí: </p>

![Tutorialdescarga1](https://github.com/user-attachments/assets/15e48718-903c-444f-9604-d50bf9d2d32c)

<p align="justify">Dale click en el menú, para posteriormente darle en Open File, y abre la carpeta descomprimida, una vez lo realices, podrás ejecutar desde la clase main el programa:  </p>

![Tutorialdescarga2](https://github.com/user-attachments/assets/6d4277b8-7f8f-4183-b9cc-90dd4b3f9273)
