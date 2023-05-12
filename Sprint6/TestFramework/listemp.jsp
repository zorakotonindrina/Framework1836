
<%@page import="etu1836.framework.modele.Emp"%>
<%@page import="java.util.*"%>
<%
    ArrayList<Emp> list= new ArrayList<Emp>();
    if(request.getAttribute("listemp")==null){
      out.print("tsy tonga");
    }else{
       list = (ArrayList<Emp>)request.getAttribute("listemp");
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
          <% for (int idx = 0; idx < list.size(); idx++) { %>
          <tr>
            <td><% out.print((list.get(idx).getEmpno())); %></td>
            <td><% out.print((list.get(idx).getDeptno())); %></td>
            <td><% out.print((list.get(idx).getName())); %></td>
            <td><% out.print((list.get(idx).getSalaire())); %></td>
            <td><% out.print((list.get(idx).getJob())); %></td>
          </tr>
          <%  } %>
        
        </tbody>
      </table>

</body>
</html>
