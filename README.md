# Gestión de Agenda Telefónica

## Grupo 1
### Integrantes:
1. Alejandro Gutierrez
2. Katherine Conche
3. Marlon Quintero
4. Natalia Ramos

## Descripción del Proyecto
Este proyecto implementa un sistema de gestión de una agenda telefónica mediante un menú interactivo en consola. La agenda permite agregar, buscar, listar, modificar y eliminar contactos siguiendo ciertas restricciones y validaciones.

## Definición de un Contacto
Cada contacto en la agenda está definido por:
- **Nombre**
- **Apellido**
- **Teléfono** (No se valida el formato del teléfono)

Un contacto se considera duplicado si tiene el mismo nombre y apellido, sin importar el teléfono y sin distinguir entre mayúsculas y minúsculas.

## Características de la Agenda
- Se puede crear con un tamaño máximo especificado o con un tamaño por defecto de 10 contactos.
- Los nombres y apellidos no pueden estar vacíos.
- No se pueden añadir contactos duplicados.

## Funcionalidades
### 1. `añadirContacto(Contacto c)`
- Añade un contacto a la agenda.
- Verifica que no existan contactos duplicados.
- No permite agregar contactos si el nombre o apellido están vacíos.
- Si la agenda está llena, muestra un mensaje al usuario.

### 2. `existeContacto(Contacto c)`
- Comprueba si un contacto ya existe en la agenda.

### 3. `listarContactos()`
- Muestra todos los contactos en el formato:
  ```
  Nombre Apellido - Teléfono
  ```
- Ordena los contactos alfabéticamente por nombre y apellido.

### 4. `buscaContacto(String nombre, String apellido)`
- Permite buscar un contacto por nombre y apellido.
- Si el contacto existe, muestra el teléfono.
- Si no se encuentra, muestra un mensaje indicando que no existe.

### 5. `eliminarContacto(Contacto c)`
- Elimina un contacto de la agenda.
- Informa si la eliminación fue exitosa o si el contacto no existe.

### 6. `modificarTelefono(String nombre, String apellido, String nuevoTelefono)`
- Permite modificar el teléfono de un contacto existente.
- Si el contacto no existe, muestra un mensaje.

### 7. `agendaLlena()`
- Indica si la agenda está llena.

### 8. `espaciosLibres()`
- Muestra cuántos contactos más se pueden agregar a la agenda según su tamaño máximo.

## Cómo Ejecutar el Programa
1. Compilar el código fuente en Java.
2. Ejecutar el programa desde la terminal o consola.
3. Seguir las instrucciones del menú interactivo para gestionar los contactos.

## Tecnologías Utilizadas
- Lenguaje de programación: **Java**
- Entrada y salida de datos a través de **consola**

## Autores
Proyecto desarrollado por el Grupo 1 dentro del marco de aprendizaje en programación orientada a objetos con Java.

