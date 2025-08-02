<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Unos prošlih mjerenja</title>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js" integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.min.js" integrity="sha384-7qAoOXltbVP82dhxHAUje59V5r2YsVfBafyUDxEdApLPmcdhBPg1DKg1ERo0BZlK" crossorigin="anonymous"></script>
<link rel="stylesheet" href="styles/physical.css">
<script src="script/physical.js"></script>
</head>
<body>
  <nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Sustav za praćenje fizičke aktivnosti</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link" href="index.php">Povratak na početnu stranicu</a>
        </li>
      </ul>
    </div>
  </div>
</nav>
<div class="container">
    <div class="row">
        <div class="col">
            <h2>Obrazac za unos prošlih mjerenja</h2>
                      <form action='<?php  echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>' method='post'>
                       
                                                     <div class="input-group mb-3">
  <span class="input-group-text">Unesi datum mjerenja:</span>
  <input type="datetime-local" id="date_time" name="date_time" class="form-control" required>
</div>
                                  <div class="input-group mb-3">
  <span class="input-group-text">Unesi svoju trenutnu težinu:</span>
  <input type="number" id="weight" class="form-control"  aria-label="weight" aria-describedby="weight" name="weight" step="0.1" value="0.0" min="0.0" required>
</div>
 <div class="input-group mb-3">
   <span class="input-group-text">Unesi trend:</span>
<select class="form-select form-select-sm" aria-label="Small select example" name="trend" required>
  <option selected>Odaberi trend</option>
  <option value="growing">Rastući</option>
  <option value="neutral">Neutralni</option>
  <option value="falling">Padajući</option>
</select>
 </div>
 <div class="input-group mb-3">
  <span class="input-group-text">Unesi razliku:</span>
  <input type="number" id="difference" class="form-control"  aria-label="weight" aria-describedby="weight" name="difference" step="0.1" value="0.0" min="0.0" required>
</div>
                         <div class="input-group mb-3">
               <input type="submit" class="btn btn-light" value="Unesi podatke" onclick="enable_button()">
</div>
                                </form>
        </div>
 <?php 
   include("classes/physical.php");
   include("classes/dbconn.php");
   include("classes/message.php");

   if(isset($_POST['date_time']) && isset($_POST['weight']) && isset($_POST['trend']) && isset($_POST['difference'])){

             if($_POST['trend']=="Odaberi trend"){
              echo Message::CHOOSE_ANOTHER_VALUE_FROM_DROPDOWN;
             }else{
          $physical = new Weight_stat(0,$_POST['weight'],$_POST['date_time'],$_POST['trend'],$_POST['difference']);
        echo "Datum mjerenja: ".$physical->getDateTime()."<br>";
        echo "Težina:".$physical->getWeight()."<br>";
        //za razliku trebamo ispis prijašnjeg zapisa te ga postaviti kao objekt 
        //nova vrijednost iz obrasca će se koristiti za izračun razlike
        //trenutno nemamo prijašnjeg zapisa pa bi vrijednost trebala biti 0
        echo "Razlika:".$physical->countDifference($physical->getWeight())."<br>";
        echo "Trend:".$physical->getTrend()."<br>";
        $database_connection= new DatabaseConnection();
        $database_connection->connectToDatabase();
        $columns=array("weight","date_time","trend","difference");
        $values=array($physical->getWeight(),$physical->getDateTime(),$physical->getTrend(),$physical->getDifference());
        $param_types=array("float","string","string","float");
        $database_connection->insert_into_table($columns,Weight_stat::TABLE_NAME,$values);
        $database_connection->close_database();
             }
   }



?>

    </div>

</div>
</body>
</html>