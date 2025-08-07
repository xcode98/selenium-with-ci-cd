# language: es

Característica: Formulario

  Esquema del escenario: Completar formulario con validaciones
    Dado completo el formulario con nombre "<nombre>" apellido "<apellido>" direccion "<direccion>" email "<email>" telefono "<telefono>" idioma "<idioma>" skill "<skill>" pais "<pais>" anio "<anio>" mes "<mes>" dia "<dia>" password "<password>" confirmPassword "<confirmPassword>"
    Entonces todas las validaciones del formulario deben ser correctas

  Ejemplos:
    | nombre | apellido | direccion        | email                    | telefono   | idioma  | skill  | pais   | anio | mes      | dia | password    | confirmPassword |
    | John   | Doe      | Jr de la Union   | test@example.com         | 1234567890 | English | Java   | Japan  | 2000 | May      | 15  | password123 | password123     |
    | Maria  | Garcia   | Av Principal 123 | maria.garcia@gmail.com   | 9876543210 | Spanish | Python | Japan  | 1995 | December | 25  | secure456   | secure456       |