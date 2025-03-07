# Task Manager

Una aplicación de gestión de tareas desarrollada con Kotlin Multiplatform, que permite crear, editar y gestionar tareas de manera eficiente tanto en Android como en iOS.

## 🚀 Características

- Gestión completa de tareas (crear, editar, eliminar)
- Interfaz de usuario moderna con Jetpack Compose
- Soporte multiplataforma (Android e iOS)
- Almacenamiento local persistente
- Diseño limpio y minimalista

## 🛠 Tecnologías Utilizadas

- **Kotlin Multiplatform**: Framework base para desarrollo multiplataforma
- **Jetpack Compose**: UI toolkit moderno para la interfaz de usuario
- **Room**: Biblioteca de persistencia para almacenamiento local
- **Koin**: Framework de inyección de dependencias
- **Clean Architecture**: Patrón de arquitectura para una mejor separación de responsabilidades

## 🔒 Seguridad

- Minificación y ofuscación de código en release
- Protección de la base de datos local
- Deshabilitación de backups de aplicación
- Reglas ProGuard personalizadas para proteger código sensible

## 🏗 Estructura del Proyecto

- `/composeApp/`: Código compartido entre plataformas
  - `commonMain/`: Código común para todas las plataformas
  - `androidMain/`: Implementaciones específicas para Android
  - `iosMain/`: Implementaciones específicas para iOS
- `/iosApp/`: Punto de entrada para la aplicación iOS

## 🚦 Comenzando

### Prerrequisitos

- Android Studio 
- Xcode 14 (para desarrollo iOS)
- JDK 17
- Kotlin 1.9.x

### Configuración del Proyecto

1. Clona el repositorio:
```bash
git clone https://github.com/emifra9/taskmanager.git
```

2. Abre el proyecto en Android Studio

3. Sincroniza el proyecto con Gradle

### Ejecutando la Aplicación

#### Android
- Selecciona un dispositivo/emulador Android
- Presiona 'Run' en Android Studio

#### iOS
- Abre el proyecto Xcode en `/iosApp`
- Selecciona un simulador iOS
- Presiona 'Run'


## ✍️ Autor

- Emiliano Fraile
