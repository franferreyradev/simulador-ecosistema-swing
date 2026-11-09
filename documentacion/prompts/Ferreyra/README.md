# Documentación de Prompts e IA — Francisco Ferreyra (Modelo y Lógica de Negocio)

En cumplimiento con el criterio de **Uso transparente de Inteligencia Artificial**, se adjuntan las capturas de las sesiones de consulta y asistencia técnica:

### 1. Desacople Arquitectónico MVC y Contrato de Vistas
* **Archivo:** [`1-desacople-arquitectura-mvc.jpg`](1-desacople-arquitectura-mvc.jpg)
* **Temática:** Debate sobre la transición de la IE1 a Swing sin violar la separación de responsabilidades:
  - Eliminación total de `Scanner` y `System.out.println` dentro del modelo.
  - Generación desacoplada de listas de eventos en `procesarTurno()`.
  - Definición de la interfaz `IVistaEcosistema` para evitar código Swing en el controlador.
  - Diseño del DTO `ReporteFinal` para transportar estadísticas a la vista.

### 2. Depuración y Balanceo Biológico (Resolución de Conflictos)
* **Archivo:** [`2-balanceo-biologico-poblacion.jpg`](2-balanceo-biologico-poblacion.jpg)
* **Temática:** Diagnóstico y resolución de la falla crítica de escalabilidad en runtime:
  - Detección de la causa raíz del `OutOfMemoryError` en el turno 16 (crecimiento exponencial $2^n$).
  - Implementación del costo metabólico/energético en la reproducción (-20 en conejos, -15 en plantas).
  - Aplicación de capacidad de carga ecológica ($K$) basada en dinámica poblacional de Lotka-Volterra.
