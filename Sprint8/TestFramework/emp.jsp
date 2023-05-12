
<%@page import="etu1836.framework.modele.Emp"%>
<%@page import="java.util.*"%>
<%
   Emp emp = new Emp();
    if(request.getAttribute("emp")==null){
      out.print("tsy tonga");
    }else{
       emp = (Emp)request.getAttribute("emp");
    } 
     
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>bb</title>
</head>
<body>
  
    <table class="table" border="1">
        <thead>
          <tr>
            <th scope="col">Numero</th>
            <th scope="col">Departement</th>
            <th scope="col">Nom</th>
            <th scope="col"> Salaire</th>
            <th scope="col"> Job</th>

          </tr>
        </thead>
        <tbody>
          <tr>
            <td><% out.print((emp.getEmpno())); %></td>
            <td><% out.print((emp.getDeptno())); %></td>
            <td><% out.print((emp.getName())); %></td>
            <td><% out.print((emp.getSalaire())); %></td>
            <td><% out.print((emp.getJob())); %></td>
          </tr>
        
        </tbody>
      </table>

</body>
</html>
