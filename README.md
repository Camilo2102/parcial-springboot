# README

## Proyecto Spring Boot

Este proyecto Spring Boot fue desarrollado por Camilo Eduardo Muñoz Duque (Código: 201920630). A continuación, se detallan los controladores disponibles en la aplicación:

### Puerto y Swagger

El servicio está configurado para ejecutarse en el puerto `7979`. La documentación Swagger se encuentra disponible en `/docs`, proporcionando información detallada sobre todos los endpoints y operaciones disponibles en este proyecto.


### Controladores

#### CustomerController

- **Ruta Base:** `/customer`

  - **GET `/`**  
    Obtiene la lista de clientes.
    
  - **GET `/{id}`**  
    Obtiene un cliente por su ID.
    
  - **POST**  
    Crea un nuevo cliente.
    
  - **DELETE `/{id}`**  
    Elimina un cliente por su ID.

#### ProductController

- **Ruta Base:** `/product`

  - **GET `/`**  
    Obtiene la lista de productos.
    
  - **GET `/{id}`**  
    Obtiene un producto por su ID.
    
  - **POST**  
    Crea un nuevo producto.
    
  - **PUT `/stock`**  
    Actualiza el stock de un producto.

#### SaleController

- **Ruta Base:** `/sale`

  - **GET `/customer/{customerId}`**  
    Encuentra ventas por ID de cliente.
    
  - **POST `/registerSale`**  
    Registra una venta.
    
  - **GET `/stock/{productId}/{amount}`**  
    Encuentra el stock de un producto por su ID y cantidad.

Para ejecutar el proyecto, asegúrese de tener todas las dependencias necesarias y luego inicie la aplicación Spring Boot. Una vez en ejecución, puede acceder a la documentación Swagger para explorar y probar los diferentes endpoints disponibles.

¡Gracias por utilizar este proyecto! Si tiene alguna pregunta o sugerencia, no dude en comunicarse con el desarrollador.
