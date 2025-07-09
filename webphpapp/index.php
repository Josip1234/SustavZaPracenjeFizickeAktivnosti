<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sustav za praćenje fizičke aktivnosti</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js" integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.min.js" integrity="sha384-7qAoOXltbVP82dhxHAUje59V5r2YsVfBafyUDxEdApLPmcdhBPg1DKg1ERo0BZlK" crossorigin="anonymous"></script>
<link rel="stylesheet" href="styles/physical.css">
<script src="script/physical.js"></script>
</head>
<body>

        <div class="container">
                 <div class="row">
                            <div class="col">
                                <h2>Obrazac za unos težine</h2>
                                <form action='<?php  echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>' method='post'>
                                  <div class="input-group mb-3">
  <span class="input-group-text" id="weight">Unesi svoju trenutnu težinu:</span>
  <input type="number" class="form-control"  aria-label="weight" aria-describedby="weight" name="weight" step="0.1">
</div>
                         <div class="input-group mb-3">
               <input type="submit" class="btn btn btn-light" value="Unesi svoju težinu">
</div>
                                </form>
                            </div>
                 </div>
        </div>
<?php 
include("classes/physical.php");
include("classes/dbconn.php");

if($_SERVER["REQUEST_METHOD"]=="POST"){
    if(empty($_POST["weight"])){
        echo "NE postoji podatak za unos.";
    }else{
    //postavi novu unesenu vrijednost
    $wstat=new Weight_stat($_POST["weight"]);
    //ispiši novu unesenu vrijednost
    echo "Težina: ".$wstat->getWeight();
    echo "<br>";
    //poveži se na bazu
    $conn=new DatabaseConnection();
    $conn->connectToDatabase();
    //izvlači se iz baze zanji zapis
    $last_record=$conn->select_last_record_from_database("weight","weight_daily_stats");
    echo "Zadnja poznata težina: ".$last_record;
     echo "<br>";
     //izračunaj razliku koja će se spremati u bazu
    $razlika=$wstat->countDifference($last_record);
    $wstat->setDifference($razlika);
    echo "Razlika u težini: ".$wstat->getDifference();
    echo "<br>";
    //odredi koji je trend trenutno ako je nova vrijednost veća od stare
    //trend je rastući ako je manji trend je padajući ako je jednak onda je trend neutralan
    $determinet=$wstat->determine_trend($wstat->getWeight(),$last_record);
    $wstat->setTrend($determinet);
    //postavi sliku ovisno o trendu.
    echo $wstat->setImgDependingOnTrend();
    //unesi podatke u bazu preko prepare statment-a
    $query="INSERT INTO `weight_daily_stats` (`weight`, `difference`, `trend`) VALUES (?, ?, ?)";
    $statement=$conn->getDbconn()->prepare($query);
    //dohvati vrijednosti
    $weight=$wstat->getWeight();
    $difference=$wstat->getDifference();
    $trend=$wstat->getTrend();
    //bindaj parametre
    $statement->bind_param('dds',$weight,$difference,$trend);
    //ako je sve uspješno završilo javi poruku da je uspješno spremljen zapis u bazu
    //ako nije javi da zapis nije spremljen.
    if($statement->execute()){
        header("Location: http://localhost/SustavZaPracenjeFizickeAktivnosti/webphpapp/index.php");
         echo "Uspješno unesen zapis";
    }else{
        echo "Neuspješno unesen zapis";
    }
    $conn->close_database();
}

}
//echo Image::OPEN_IMAGE_SRC.'icons/arrow_neutral_trend.png'.Image::ADD_ALT_IMG.'neutral'.Image::SET_CLASS.Image::CLOSE_IMG;


?>
</body>
</html>