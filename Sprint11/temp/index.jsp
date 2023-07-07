<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Test</title>
</head>
<body>

    <h3>Se connecter</h3>
    <form action="login">
        <p> Nom <input type="text" name="Nom"></p>
        <p> Mod de passe<input type="text" name="Mdp"></p>
        <p><input type="submit" value="Valider"></p>
    </form>




    <form action="save" method="get">
        Nom :
        <input type="text" name="Nom">
        <br><br>
        Age :
        <input type="number" name="Age" id=""><br><br>
        <input type="submit" value="Okey">
    </form>
   <p>Voir Emp avec id=3 <a href="getId?id=3"></a> </p> 
    <hr>

    <h3>Upload fichier</h3>
    <form action="saveFile" method="POST" enctype="multipart/form-data">
        Nom : 
        <p><input type="text" name="Nom"></p>
        Photo de profil : 
        <p><input type="file" name="Pdp"></p>
        <input type="submit" value="Upload fichier">
    </form>
    <hr>



    
</body>
</html>


