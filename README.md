This project demonstrates the use of json-schema with java. This 
approach can be used to implement contract first design. Where the 
interface is defined first. 

*  **jsonschema2pojo** used oto generate java objects from a 
json-schema, also generating jsr303 annotations for validation 
*  **Jackson** used to marshall java objects from json, 
*  **Hibernate Validator** used to validate objects marshalled

To compile:
    
    mvn clean compile
    
To run tests:
    
    mvn test