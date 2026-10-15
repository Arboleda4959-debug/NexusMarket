# Validación de esta entrega

- Código principal: compilado con `javac --release 17 -Xlint:all` sin errores ni advertencias.
- Pruebas: 36 métodos de prueba están incluidos en `src/test/java`.
- Se ejecutó una comprobación local de los casos de prueba con un runner temporal compatible con las aserciones utilizadas; resultado: 36 pasaron, 0 fallaron. Ese runner temporal no forma parte del proyecto y no sustituye Maven/Surefire.
- La ejecución oficial `mvn clean test` no pudo ejecutarse en el entorno de preparación porque no había Maven instalado y el Maven Wrapper no pudo descargar la distribución por falta de acceso al repositorio. Debe ejecutarse en el equipo local del estudiante.

Comando oficial requerido:

```bash
mvn clean test
```

O en Windows, si Maven no está instalado y la red permite descargarlo:

```powershell
.\mvnw.cmd clean test
```
