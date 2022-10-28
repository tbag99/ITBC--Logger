# ITBC--Logger


An application that enables the registration of clients and allows clients to log in, create a log .

In addition to clients, the application also allows admins to log in, as well as their client search, as well as changing the client's password.

The authorization of the user using a token was also done, as was the validation of the email and password

Endpotints in this project with little change --->

1.Register 
- HTTP Method: POST
- Endpoint URL: /api/clients/register
- Requested body
- Response : -> 201 Registered
             -> 400 Bad Request
             -> 409 Conflict
             
2.Login
-HTTP Method: POST
-Endpoint URL: /api/clients/login
-Requested body
-Response : -> 200 OK
            -> 400 Bad Request

3.Create log
-HTTP Method: POST
-Endpoint URL: /api/logs/create
-Request body
-Request headers -> Token
-Response :  -> 201 Created
             -> 400 Bad Request
             -> 401 Unauthorized
             -> 413 Payload too large
             
4.Get all clients
HTTP Method: GET
Endpoint URL: /api/clients
Request headers -> Admin token
Response : -> 200 OK
           -> 401 Unauthorized
           -> 403 Forbidden
           
5.Change client password
HTTP Method: PATCH
Endpoint URL: /api/clients/{clientId}/reset-password
Request body
Request headers -> Admin token
Responses : -> 204 No content
            -> 401 Unauthorized
