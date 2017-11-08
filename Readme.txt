Cómo levantar la aplicación?

Esta aplicación requiere el API Rest de hoteles, en la clase services/WSHoteles se encuentra la URL del mismo.
Esta aplicación se puede ejecutar desde cualquier dispositivo Android 4.0 o superior.
Se requiere una conexión a internet del dispositivo.
Asegurese que el API Rest es visible desde el dispositivo.

Cómo está compuesta?

Las capas definidas para esta aplicación son: adapters, controllers, helpers, models, services
La aplicación usa rxjava y rxandroid para consumir el servicio rest
La aplicación inicia con un splash de presentación y a continuación muestra el listado de hoteles cargados, la otra vista posible es el detalle del hoteles
El listado de hoteles cuenta con un carrousel de imágenes armado con controles nativos
El detalle del hotel cuenta con la ubicación por medio de Google Maps
Es posible ordenar el listado de hoteles por medio del nombre o de la cantidad de estrellas que tienen los hoteles, o se permite hacer filtros por el nombre