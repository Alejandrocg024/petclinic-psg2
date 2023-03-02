**Sprint 2 – Reporte Técnico** 

**Historial del proyecto**

## 1. Introducción
En este reporte hablaremos sobre el historial del proyecto después de finalizar las primeras tareas. Encontraremos los distintos cambios que hemos hecho con una pequeña descripción y, más adelante, los posibles problemas que nos hemos encontrado realizando estas tareas.



## 2.  Contenido
Tras tener claro el product backlog y realizar un planning inicial, comenzamos con las tareas anteriores a este documento. Estas eran la A2.4 consistente en crear el repositorio y la A2.5 que consiste en distintas implementaciones. En la siguiente figura, encontramos un gráfico con todos los commits realizados para realizar estas tareas, como se puede observar, todos estos cambios están realizados directamente en la propia rama Main como se nos indica en el Product Backlog.

![Commits a main](/images/Imagen1.png)

El primer commit que podemos observar en este gráfico, se trataba de la tarea A2.4 con la que iniciabamos nuestro repositorio y comenzábamos a trabajar. Entonces, cada uno nos asignamos las 7 subtareas que conformaban la tarea A2.5, a excepción de la tarea A2.5.a que la realizamos todos juntos y, comenzamos a trabajar.

![Todos los commits](/images/Imagen2.png)

En esta imagen, se puede observar un historico con todos los commits realizados tras realizar todas las tareas individualmente,  podemos destacar 4 conflictos con los que nos hemos enfrentado.

En primer lugar, al comenzar a trabajar con este proyecto, hemos tenido que configurar todo el software correctamente en nuestros ordenadores. Encontrábamos distintos problemas pero, llegamos a la conclusión, que todos se trataban de problemas con las variables de entorno de Java. Esto se debe a que, en otras asignaturas, trabajamos con otras versiones. La solución fue tan simple como cambiar la variable a la versión correcta y solucionamos el problema, pese a que nos costó llegar a esta conclusión.

`	`En segundo lugar, tuvimos problemas a la hora de realizar cambios visuales (Tareas A2.5.c y A2.5.g) en la página debido a que no se actualizaba el estilo. La clave de este conflicto, estaba en la caché del navegador. Esta guardaba la versión anterior de la página y no mostraba los cambios. Por tanto, la solución fue limpiar la caché del navegador siempre que se realizase un cambio visual.

`	`En tercer lugar, al realizar la tarea A2.5.b que se trataba de personalizar el pom.xml a nuestro proyecto, se introdujo un error en la aplicación. Este error trataba de la incorrecta modificación de los datos del proyecto, debido a que los datos de nuestro proyecto se introdujeron en la etiqueta “<parent>” que es la etiqueta de nuestro framework. Logramos solucionarlo, modificando correctamente los datos, en la etiqueta correcta.

`	`En último lugar, encontramos un error involuntario que se introdujo el archivo pom.xml en el archivo “.gitIgnore”. Esto se arregló debido a que los cambios del pom.xml se deben ver reflejados en el repositorio, simplemente se borró esta línea y se solucionó.


## 3. Conclusiones
En conclusión, estos han sido nuestros conflictos y las soluciones que hemos tomado. Consideramos que, no hemos tenido grandes problemas y, los que han aparecido, los hemos solucionado ayudándonos entre nosotros. También, podemos destacar como errores que hemos realizado, la falta de buenas prácticas y criterios en la descripción de los commits que trataremos de subsanar en las siguientes tareas.



