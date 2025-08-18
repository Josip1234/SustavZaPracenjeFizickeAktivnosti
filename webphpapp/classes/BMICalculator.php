<?php
class BMICalculator
{

    const SEX_FEMALE="F";
    const SEX_MALE="M";
    const BMI_ERROR="Greška! Nevažeće vrijednosti.";
    const TOO_LOW_BMI="Indeks tjelesne mase je premal.";
    const IDEAL_BMI="Indeks tjelesne mase je idealan.";
    const EASY_HIGH_BMI="Malo veći indeks tjelesne mase.";
    const HIGH_BMI="Visok indeks tjelesne mase!";
    const TOO_HIGH_BMI="Indeks tjelesne mase je vrlo visok!";
    const WAY_TOO_HIGH_BMI="Indeks tjelesne mase je previše visok!";
    const SEX_BMI_ERROR="Uneseni spol ne postoji.";

    private $mass_in_kg;
    private $person_height_in_meters;
    private $result;
    private $sex;

    public function __construct($mass_in_kg,  $person_height_in_meters,  $result,  $sex)
    {
        $this->mass_in_kg = $mass_in_kg;
        $this->person_height_in_meters = $person_height_in_meters;
        $this->result = $result;
        $this->sex = $sex;
    }
    public function getMassInKg()
    {
        return $this->mass_in_kg;
    }

    public function getPersonHeightInMeters()
    {
        return $this->person_height_in_meters;
    }

    public function getResult()
    {
        return $this->result;
    }

    public function getSex()
    {
        return $this->sex;
    }

    public function setMassInKg($mass_in_kg)
    {
        $this->mass_in_kg = $mass_in_kg;
    }

    public function setPersonHeightInMeters($person_height_in_meters)
    {
        $this->person_height_in_meters = $person_height_in_meters;
    }

    public function setResult($result)
    {
        $this->result = $result;
    }

    public function setSex($sex)
    {
        $this->sex = $sex;
    }

    public function calculateBMI(){
        $this->result=round($this->mass_in_kg/($this->person_height_in_meters*$this->person_height_in_meters),1);
    }
    public function return_result_of_bmi_index(){
        $message="";
         if($this->sex==BMICalculator::SEX_MALE){
            if($this->getResult()<20.7) $message.=BMICalculator::TOO_LOW_BMI;
            else if($this->getResult()>=20.7 && $this->result<=26.4) $message.=BMICalculator::IDEAL_BMI;
            else if($this->getResult()>26.4 && $this->getResult()<=27.8) $message.=BMICalculator::EASY_HIGH_BMI;
            else if($this->getResult()>27.8 && $this->getResult()<=31.1) $message.=BMICalculator::HIGH_BMI;
            else if($this->getResult()>31.1 && $this->getResult()<=45) $message.=BMICalculator::TOO_HIGH_BMI;
            else if($this->getResult()>45) $message.=BMICalculator::WAY_TOO_HIGH_BMI;
            else $message.=BMICalculator::BMI_ERROR;

         }else if($this->sex==BMICalculator::SEX_FEMALE){
               if($this->getResult()<19.1) $message.=BMICalculator::TOO_LOW_BMI;
               else if($this->getResult()>=19.1 && $this->getResult()<=25.8) $message.=BMICalculator::IDEAL_BMI;
               else if($this->getResult()>25.8 && $this->getResult()<=27.3) $message.=BMICalculator::EASY_HIGH_BMI;
               else if($this->getResult()>27.3 && $this->getResult()<=32.3) $message.=BMICalculator::HIGH_BMI;
               else if($this->getResult()>32.3 && $this->getResult()<=45) $message.= BMICalculator::TOO_HIGH_BMI;
               else if($this->getResult()>45) $message.=BMICalculator::WAY_TOO_HIGH_BMI;
               else $message.=BMICalculator::BMI_ERROR;
         }else{
            $message.=BMICalculator::SEX_BMI_ERROR;
         }
         return $message;
    }

}
