# Selenium Automation Project

Este proyecto utiliza Selenium WebDriver con Cucumber y Allure para automatización de pruebas web.

## Estructura del Proyecto

```
src/
└── test/
    ├── java/
    │   ├── pages/
    │   │   ├── BasePage.java
    │   │   └── AmazonPage.java
    │   ├── runner/
    │   │   └── TestRunner.java
    │   └── steps/
    │       └── AmazonSteps.java
    └── resources/
        └── features/
            └── amazon.feature
```

## Tecnologías Utilizadas

- **Java 17**
- **Gradle** - Gestión de dependencias y build
- **Selenium WebDriver** - Automatización web
- **WebDriverManager** - Gestión automática de drivers
- **Cucumber** - BDD framework
- **Allure** - Reportes de pruebas

## Dependencias Principales

- `selenium-java:4.15.0`
- `webdrivermanager:5.6.2`
- `cucumber-java:7.14.0`
- `allure-cucumber7-jvm:2.24.0`

## Configuración

### Prerrequisitos

- Java 17 instalado
- Node.js v23.11.0 y npm 10.9.2 (para Allure)

### Instalación de Allure (opcional para reportes)

```bash
npm install -g allure-commandline
```

## Ejecución de Pruebas

### Ejecutar todas las pruebas

```bash
./gradlew test
```

### Ejecutar pruebas específicas

```bash
./gradlew test --tests runner.TestRunner
```

## Generar Reportes Allure

```bash
# Generar reporte
./gradlew allureReport

# Servir reporte
./gradlew allureServe
```

## Características del Proyecto

### BasePage
Clase base que contiene todos los métodos esenciales de Selenium:
- Inicialización y cierre del WebDriver
- Métodos de interacción con elementos
- Esperas explícitas
- Navegación

### AmazonPage
Clase que extiende BasePage e implementa funcionalidades específicas para Amazon:
- Navegación a Amazon
- Verificación de carga de página
- Búsqueda de productos

### Configuración del WebDriver
- Utiliza WebDriverManager para gestión automática de drivers
- Configurado con Chrome en modo maximizado
- Opciones optimizadas para automatización

## Escenarios de Prueba

El archivo `amazon.feature` contiene:
- **Escenario**: Visitar la página de Amazon
- **Dado**: visito amazon

## Notas

- No es necesario descargar ChromeDriver manualmente gracias a WebDriverManager
- Los reportes se generan automáticamente en `target/cucumber-reports/`
- Allure genera reportes detallados con screenshots y logs
