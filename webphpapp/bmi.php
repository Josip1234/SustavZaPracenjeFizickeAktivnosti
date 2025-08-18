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
  <nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Sustav za praćenje fizičke aktivnosti</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link" href="history_entry.php">Unos prošlih mjerenja</a>
        </li>
           <li class="nav-item">
          <a class="nav-link" href="index.php">Povratak na početnu stranicu</a>
        </li>
      </ul>
    </div>
  </div>
</nav>
        </div>
        <div class="container">
            <div class="row">
                <div class="col">
                    <form action='<?php  echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>' method='post'>
                        <?php include("classes/BMICalculator.php"); ?>
                        <div class="mb-3">
  <label for="mass_in_kg" class="form-label">Unesi tjelesnu masu u kilogramima:</label>
    <input type="number" id="mass_in_kg" class="form-control"  aria-label="mass_in_kg" aria-describedby="mass_in_kg" name="mass_in_kg" step="0.1" value="0.0" min="0.0">
</div>
                 <div class="mb-3">
  <label for="person_height_in_meters" class="form-label">Unesi visinu osobe u metrima:</label>
    <input type="number" id="person_height_in_meters" class="form-control"  aria-label="person_height_in_meters" aria-describedby="person_height_in_meters" name="person_height_in_meters" step="0.01" value="0.00" min="0.00">
</div>
          <div class="mb-3">
            <div class="form-check">
  <input class="form-check-input" type="radio" name="sex" id="M" value="<?php echo BMICalculator::SEX_MALE; ?>">
  <label class="form-check-label" for="M">
    Muški spol 
  </label>
</div><div class="form-check">
  <input class="form-check-input" type="radio" name="sex" id="F" value="<?php echo BMICalculator::SEX_FEMALE; ?>">
  <label class="form-check-label" for="F">
    Ženski spol
  </label>
</div>
</div>
<input type="submit" class="btn btn-light" value="Unesi podatke">
                    </form>
                    <?php 
                    
                    if(isset($_POST['mass_in_kg']) && isset($_POST['person_height_in_meters']) && isset($_POST['sex'])){
                        $bmi=new BMICalculator($_POST['mass_in_kg'],$_POST['person_height_in_meters'],0.0,$_POST['sex']);
                         $bmi->calculateBMI();
                         echo $bmi->return_result_of_bmi_index();

                    }


?>

                </div>

            </div>

        </div>

</body>
</html>