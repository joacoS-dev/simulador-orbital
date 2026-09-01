# Simulación gravitacional
 
Simulación gravitacional de N-cuerpos, renderizada en 3D con JavaFX.
 
## Modelo físico
 
- Unidades: distancias en UA (unidades astronómicas), masas relativas a la masa solar (Sol = 1), tiempo en años.
- Constante gravitacional ajustada a estas unidades: `G = 39.4784` (≈ 4π²), consistente con la 3ª ley de Kepler en UA/años solares.
- En cada paso de simulación:
  1. Se calcula la fuerza gravitacional neta sobre cada cuerpo debido a todos los demás (`calculateOrbitalForce` + `calculateAceleration`).
  2. Se integra velocidad y posición con el método de Euler explícito (`update(dt)`).

## Requisitos
 
- JDK 17 o superior
- JavaFX SDK
- Maven o Gradle.
 
## Parámetros ajustables
 
- `dt` en `simulation.steps(0.0001)` (dentro de `OrbitApp`): controla la velocidad/precisión de la simulación. Valores más pequeños son más precisos pero más lentos de ver evolucionar.
- `positionScale` y `bodyScale` en `OrbitApp`: controlan la escala visual de distancias y tamaños de los cuerpos para que sean visibles.

## Ejecutar

- mvn javafx:run
 
