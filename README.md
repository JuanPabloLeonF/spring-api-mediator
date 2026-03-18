# Spring API - Arquitectura Hexagonal con Patrón Mediator

## 📋 Descripción del Proyecto

API RESTful construida con **Spring Boot 4.0.3** que implementa **Arquitectura Hexagonal (Ports & Adapters)** con el patrón **Mediator/CQRS** como dispatcher centralizado. El proyecto demuestra las mejores prácticas de diseño de software con una separación clara de responsabilidades y un desacoplamiento máximo entre capas.

## 🏗️ Arquitectura del Proyecto

### **Arquitectura Hexagonal (Ports & Adapters)**

El proyecto sigue la arquitectura hexagonal donde el dominio está completamente aislado de la infraestructura:

```
┌─────────────────────────────────────────────────────────────┐
│                    INFRASTRUCTURE                          │
│  ┌─────────────────┐    ┌─────────────────────────────────┐ │
│  │   Controllers   │    │     Repositories & DB           │ │
│  │   (Adapters)    │    │      (Adapters)                 │ │
│  └─────────────────┘    └─────────────────────────────────┘ │
└─────────────────────────────────────────────────────────────┘
                              │
                    ┌─────────────────┐
                    │   APPLICATION   │
                    │   (Use Cases)   │
                    └─────────────────┘
                              │
                    ┌─────────────────┐
                    │     DOMAIN      │
                    │  (Business)     │
                    └─────────────────┘
```

### **Estructura de Paquetes**

```
src/main/java/dev/juanleon/spring_api/
├── common/                    # Componentes reutilizables
│   └── mediator/             # Patrón Mediator
├── product/                   # Dominio de productos
│   ├── application/          # Casos de uso y lógica de aplicación
│   │   ├── commands/         # Commands (CQRS - modifican estado)
│   │   ├── queries/          # Queries (CQRS - solo leen)
│   │   ├── dto/              # Data Transfer Objects
│   │   ├── handler/          # Handlers de aplicación
│   │   └── mapper/           # Mappers con MapStruct
│   ├── domain/               # Lógica de negocio pura
│   │   ├── model/            # Entidades de dominio
│   │   ├── service/          # Servicios de dominio
│   │   ├── persistence/      # Puertos de salida
│   │   └── useCases/         # Implementación de casos de uso
│   └── infrastructure/      # Adaptadores de entrada/salida
│       ├── api/
│       │   ├── input/        # Controllers (adaptadores entrada)
│       │   │   └── controller/
│       │   └── output/       # Implementaciones salida
│       │       ├── database/ # Repositorios JPA
│       │       └── exceptions/
│       └── configuration/    # Configuración Spring
└── SpringApiApplication.java # Clase principal
```

## 🔄 Patrón Mediator - El Corazón de la Aplicación

### **¿Qué es el Mediator?**

El **Mediator** es un componente que actúa como **dispatcher centralizado** implementando el patrón **Mediator** con **CQRS (Command Query Responsibility Segregation)**. Desacopla completamente los controllers de la lógica de negocio.

### **Componentes del Mediator**

#### **1. Interfaces Base (`common/mediator/`)**

```java
// Marca para Commands/Queries
public interface IRequest<T> {}

// Interface para Handlers
public interface IRequestHandler<T extends IRequest<R>, R> {
    R handle(T request);
    Class<T> getRequestType();
}

// Interface del Mediator
public interface IMediator {
    <R, T extends IRequest<R>> R dispatch(T request);
}
```

#### **2. Implementación del Mediator**

```java
@Component
public class Mediator {
    Map<? extends Class<?>, IRequestHandler<?, ?>> requestHandlerMap;

    public Mediator(List<IRequestHandler<?, ?>> handlers) {
        // Auto-registro de handlers por tipo
        this.requestHandlerMap = handlers.stream()
                .collect(Collectors.toMap(IRequestHandler::getRequestType, Function.identity()));
    }

    public <R, T extends IRequest<R>> R dispatch(T request) {
        // Encuentra automáticamente el handler correcto
        IRequestHandler<T, R> handler = (IRequestHandler<T, R>) this.requestHandlerMap.get(request.getClass());
        if (handler == null) {
            throw new RuntimeException("No handler found for request type: " + request.getClass().getName());
        }
        return handler.handle(request);
    }
}
```

### **Commands vs Queries (CQRS)**

#### **Commands (Modifican Estado)**
```java
// Ejemplo: SaveProductCommand
public record SaveProductCommand(RequestProduct requestProduct) implements IRequest<ResponseRequest> {}

// Handler correspondiente
@Component
public class SaveProductCommandHandler implements IRequestHandler<SaveProductCommand, ResponseRequest> {
    @Override
    public ResponseRequest handle(SaveProductCommand request) {
        return iProductPostHandler.save(request.requestProduct());
    }
}
```

#### **Queries (Solo Leen)**
```java
// Ejemplo: GetAllProductsQuery
public record GetAllProductsQuery() implements IRequest<List<ResponseProduct>> {}

// Handler correspondiente
@Component
public class GetAllProductsQueryHandler implements IRequestHandler<GetAllProductsQuery, List<ResponseProduct>> {
    @Override
    public List<ResponseProduct> handle(GetAllProductsQuery request) {
        return productGetHandler.getAll();
    }
}
```

### **Flujo Completo con Mediator**

```
HTTP Request → Controller → Mediator → Command/Query Handler → Application Handler → Domain Service → Repository → Database
```

#### **Ejemplo: Petición GET /api/v1/products**

1. **Controller** crea `GetAllProductsQuery`
2. **Mediator** busca `GetAllProductsQueryHandler` automáticamente
3. **QueryHandler** delega a `IProductGetHandler`
4. **ProductHandler** llama a `IProductService`
5. **Service** ejecuta lógica de negocio
6. **Repository** accede a base de datos
7. **Retorno** en orden inverso

### **Ventajas del Mediator**

1. **Desacoplamiento Total**: Controllers no conocen handlers específicos
2. **Auto-registro**: Los handlers se registran automáticamente con `@Component`
3. **Tipado Seguro**: Manejo genérico con tipos seguros
4. **Extensibilidad**: Fácil agregar nuevas operaciones sin modificar código existente
5. **Testabilidad**: Cada componente es testeable independientemente

## 🚀 Tecnologías y Dependencias

### **Core Framework**
- **Spring Boot 4.0.3** - Framework principal
- **Java 25** - Versión Java más reciente
- **Gradle 8.0** - Sistema de construcción

### **Persistencia**
- **Spring Data JPA** - ORM y acceso a datos
- **PostgreSQL** - Base de datos relacional
- **MapStruct 1.6.3** - Mapeo automático de objetos

### **Validación y Web**
- **Spring Boot Web MVC** - API REST
- **Spring Boot Validation** - Validación de entrada
- **Spring Boot DevTools** - Desarrollo rápido

### **Testing**
- **Spring Boot Test** - Testing integrado
- **JUnit Platform** - Framework de testing

## 📡 Endpoints de la API

### **Productos**

| Método | Endpoint | Descripción | Tipo |
|--------|----------|-------------|------|
| `GET` | `/api/v1/products` | Obtener todos los productos | Query |
| `GET` | `/api/v1/products/{id}` | Obtener producto por ID | Query |
| `POST` | `/api/v1/products` | Crear nuevo producto | Command |

### **Formato de Peticiones**

#### **Crear Producto (POST)**
```json
{
  "name": "Producto Ejemplo",
  "price": 99.99
}
```

#### **Respuestas**
```json
{
  "id": "123e4567-e89b-12d3-a456-426614174000",
  "name": "Producto Ejemplo",
  "price": 99.99
}
```

## 🔄 Flujo de Datos Detallado

### **Capa de Infrastructure (Adaptadores de Entrada)**
- **ProductRestController**: Expone endpoints REST
- **Mediator**: Dispatcher centralizado
- **GlobalExceptionsHandler**: Manejo centralizado de excepciones

### **Capa de Application (Casos de Uso)**
- **Commands/Queries**: Objetos que representan operaciones
- **Handlers**: Manejan Commands/Queries específicos
- **ProductHandler**: Lógica de aplicación y coordinación
- **DTOs**: Transferencia de datos entre capas
- **Mappers**: Transformación de objetos con MapStruct

### **Capa de Domain (Negocio Puro)**
- **ProductModel**: Entidad de dominio
- **IProductService**: Contrato de servicios de negocio
- **ProductUseCase**: Implementación de casos de uso
- **IProductPersistence**: Puerto de salida a persistencia

### **Capa de Infrastructure (Adaptadores de Salida)**
- **ProductAdapter**: Implementación del puerto de persistencia
- **ProductEntity**: Entidad JPA
- **ProductRepository**: Repository Spring Data JPA
- **NotFoundProductException**: Excepción personalizada

## 🎯 Patrones de Diseño Implementados

### **1. Arquitectura Hexagonal (Ports & Adapters)**
- Aislamiento completo del dominio
- Dependencias siempre hacia adentro
- Adaptadores para entrada/salida

### **2. Patrón Mediator**
- Dispatcher centralizado
- Desacoplamiento máximo
- Auto-registro de handlers

### **3. CQRS (Command Query Responsibility Segregation)**
- Separación de operaciones de lectura/escritura
- Commands para modificaciones
- Queries para consultas

### **4. Dependency Inversion**
- Dependencias hacia abstracciones
- Inyección de dependencias con Spring

### **5. Single Responsibility Principle**
- Cada clase tiene una responsabilidad única
- Separación clara de preocupaciones

## 🔧 Configuración y Ejecución

### **Prerrequisitos**
- Java 25+
- PostgreSQL 14+
- Gradle 8.0+

### **Configuración de Base de Datos**
```properties
# application.properties
spring.datasource.url=jdbc:postgresql://localhost:5432/spring_api
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

### **Ejecución**
```bash
# Clonar repositorio
git clone <repository-url>

# Ejecutar aplicación
./gradlew bootRun

# O usar IDE
# Ejecutar SpringApiApplication.java
```

### **Testing**
```bash
# Ejecutar tests
./gradlew test
```

## 🎖️ Beneficios de esta Arquitectura

1. **Mantenibilidad**: Cambios en una capa no afectan a otras
2. **Escalabilidad**: Fácil agregar nuevas funcionalidades
3. **Testabilidad**: Cada componente es testeable independientemente
4. **Flexibilidad**: Fácil cambiar implementaciones (DB, APIs externas)
5. **Calidad**: Código limpio, desacoplado y documentado

## 📈 Próximos Pasos y Mejoras

1. **Agregar más Commands/Queries**: Update, Delete, Search
2. **Implementar caché**: Para optimizar queries
3. **Agregar logging**: Traza completa del flujo
4. **Implementar seguridad**: JWT/OAuth2
5. **Agregar documentación**: Swagger/OpenAPI
6. **Testing end-to-end**: Integración completa

## 🤝 Contribuciones

Este proyecto es una demostración de arquitectura limpia y patrones de diseño modernos. Siéntete libre de usarlo como referencia o base para tus proyectos.

---

**Desarrollado con ❤️ siguiendo las mejores prácticas de ingeniería de software**
