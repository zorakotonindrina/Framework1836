<%@page import="employee.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Redirection</title>
</head>
<body>
    <h1>Liste des employees</h1>
        
    <% 
       // Emp l = (Emp)request.getAttribute("all_emp");
        out.print(request.getAttribute("all_emp")); 
    
    %>
</body>
</html>