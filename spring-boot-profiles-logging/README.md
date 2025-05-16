# Spring Boot Profiles and Logging Example

Proyecto de ejemplo para configurar perfiles (dev, test, prod) y logging específico con Spring Boot.

## Perfiles configurados

- **dev**: DEBUG e INFO en consola, puerto 8081.
- **test**: INFO en consola, puerto 8082.
- **prod**: WARN y ERROR en archivo `logs/app.log`, puerto 8083.

## Endpoints

- `GET /users` - Obtener todos los usuarios.
- `POST /users` - Crear usuario. JSON: `{ "name": "Nombre", "email": "correo@ejemplo.com" }`
- `DELETE /users/{id}` - Borrar usuario por ID.

## Cómo usar

1. Cambiar el perfil activo en `src/main/resources/application.properties`, por ejemplo:

```
spring.profiles.active=dev
```

2. Ejecutar la aplicación:

```
./mvnw spring-boot:run
```

3. Probar con Postman o curl:

```
curl -X POST localhost:8081/users -H "Content-Type: application/json" -d '{"name":"Juan","email":"juan@example.com"}'
curl localhost:8081/users
curl -X DELETE localhost:8081/users/1
```

4. Revisar logs en consola (dev, test) o en archivo `logs/app.log` (prod).

---

