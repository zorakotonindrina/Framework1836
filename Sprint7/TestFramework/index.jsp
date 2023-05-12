

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Doc</title>
</head>
<body>
    <p>Holla</p>
        <form  action="insert">
        <h1>Formulaire</h1>

        <p> Departement :  <select name="deptno" class="form-control" required>
                 <option value="1"> Departement 1</option>
                 <option value="2"> Departement 2</option>
                 <option value="3"> Departement 3</option>           
            </select>
        </p>
        <p> Nom :<input type="text" name="name" id="name"> </p> 
        <p> Salaire :<input type="text" name="salaire" id="salaire"> </p>
        <p> Poste : <input type="text" name="job" id="job"> </p>
        <input type="submit" value="Soumetre">
    </form>

</body>
</html>
