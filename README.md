# ionix
prueba tecnica

Referente al ejercicio esta disponible en el repositorio se encuentra el codigo java.

el ejercicio 2:

Creo que se lo que se debería hacer es:
- Utilizar cifrado de datos, algoritmo de cifrado simétrico, puede ser AES(Advanced Encryption Standard).
- El cliente cifra los datos con la clave secreta y los envía al autorizador a través de la api y el autorizador descifra con la clave secreta que se realizó el cifrado.
- El cliente y autorizador deben tener clave secreta compartida, para esto usar algun alamcen ejemplo en aws existe el secret manager que adminsitra claves. 
