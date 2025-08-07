@Test
Feature: Open OrangeHRM

  Scenario Outline: Crear un nuevo empleado con diferentes datos
    Given Ingreso al login de OrangeHRM con usuario "<username>" y contraseña "<password>" y acceso al dashboard
    When Creo un nuevo empleado con primer nombre "<firstName>" segundo nombre "<middleName>" apellido "<lastName>" usuario "<employeeUsername>" contrasena1 "<password1>" contrasena2 "<password2>"
    Then Verifico que el empleado se creo correctamente
    
    Examples:
      | username | password  | firstName | middleName | lastName | employeeUsername | password1 | password2 |
      | Admin    | admin123  | zxccc8    | Silva      | Medrano  | user          | Atest123  | Atest123  |