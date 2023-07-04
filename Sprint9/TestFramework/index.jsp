
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
    <h3>Upload fichier</h3>
    <form action="saveFile" method="POST" enctype="multipart/form-data">
        Nom : 
        <p><input type="text" name="Nom"></p>
        Photo de profil : 
        <p><input type="file" name="Pdp"></p>
        <input type="submit" value="Upload fichier">
    </form>

</body>
</html>
