# Simulador de Ecosistema — Versión Swing (MVC + DAO)

Proyecto desarrollado para la **Segunda Instancia Evaluativa** de Interfaz Gráfica (IES).
Transformación del simulador de ecosistema de terminal a una aplicación de escritorio con interfaz gráfica en Java Swing, arquitectura MVC desacoplada y persistencia mediante DAO en archivos de texto plano (.txt).

---

## 👥 Integrantes del Grupo

| Integrante | Rol Principal | Responsabilidades |
| :--- | :--- | :--- |
| **Allasia** | Modelo y Lógica de Negocio | Refactorización de entidades de IE1, desacople de consola, gestión de eventos del turno y cálculo de métricas finales. |
| **Ferreyra** | Persistencia y DAO | Diseño e implementación de `EcosistemaDAO`, lectura/escritura en texto plano (.txt), manejo de I/O y control de excepciones. |
| **Sosa** | UX/UI y Vistas Swing | Diseño de prototipo en Figma, construcción de formularios en NetBeans GUI Builder (`.form`) y navegación visual. |

---

## 🏗️ Arquitectura del Sistema

El proyecto sigue un patrón **MVC (Modelo-Vista-Controlador)** con una capa independiente de persistencia **DAO**, organizado en los siguientes paquetes:

* `modelo`: Contiene la jerarquía de entidades (`Entidad`, `Animal`, `Conejo`, `Lobo`, `Planta`, `PlantaVenenosa`), interfaces (`Reproducible`, `Peligroso`, `Mortal`), el enumerador `Clima` y la clase de gestión `GestorEcosistema`. Libre de dependencias de interfaz o entrada/salida de usuario.
* `dao`: Contiene `EcosistemaDAO`, responsable exclusivo de la lectura y escritura del estado de la simulación en formato plano `.txt`.
* `vista`: Formularios diseñados con el **Diseñador Visual de NetBeans** (`.form`). No contiene lógica de simulación ni llamadas a archivos; únicamente captura eventos y los delega al controlador.
* `controlador`: `EcosistemaController`, intermediario que orquesta el flujo entre la Vista, el Modelo y el DAO.
* `principal`: `EcosistemaMVC`, clase que contiene el punto de entrada (`main`), instancia las piezas, las conecta y despliega la aplicación.

---

## 🚀 Requisitos e Instrucciones de Ejecución

* **JDK:** Java SE Development Kit 17 o superior.
* **IDE:** Apache NetBeans 17 o superior.
* **Librerías:** Únicamente bibliotecas estándar de Java (Java Standard Edition / Swing). No requiere dependencias externas ni Maven.

### Pasos para ejecutar:
1. Clonar el repositorio:
   ```bash
   git clone https://github.com/franferreyradev/simulador-ecosistema-swing.git
   ```
2. Abrir **Apache NetBeans**.
3. Ir a `File -> Open Project...` y seleccionar la carpeta del proyecto clonado.
4. Presionar `F6` o hacer clic derecho en el proyecto y seleccionar `Run`.

---

## 🎨 Diseño UX/UI (Figma)

* **Enlace al archivo de Figma:** *(Insertar link de Figma aquí)*
* Justificaciones de diseño UX/UI documentadas en el Figma y defendidas en el video.

---

## 📹 Video Explicativo

* **Enlace al video:** *(Insertar link del video de 10 a 15 minutos aquí)*

---

## 🤖 Uso de Inteligencia Artificial y Documentación

Las capturas y enlaces correspondientes a las sesiones y consultas realizadas a modelos de IA para asistencia en arquitectura, patrones y documentación se encuentran organizadas en la carpeta [`documentacion/`](documentacion/).
