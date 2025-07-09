<?php
include("classes/images.php");
class Weight_stat
{
    private $weight;
    private $difference;
    private $trend;
    private $date_time;

    public function __construct($weight)
    {
        $this->weight = $weight;
    }


    public function getWeight()
    {
        return $this->weight;
    }

    public function getDifference()
    {
        return $this->difference;
    }

    public function getTrend()
    {
        return $this->trend;
    }

    public function setWeight($weight)
    {
        $this->weight = $weight;
    }

    public function setDifference($difference)
    {
        $this->difference = $difference;
    }

    public function setTrend($trend)
    {
        $this->trend = $trend;
    }

    //as difference weight, it will count last value and previous weight value 
    //it will subtract 
    public function countDifference($new_value)
    {
        $difference = 0.0;
        if ($new_value > $this->getWeight()) {
            $difference =  $new_value - $this->getWeight();
        } else {
            $difference =$this->getWeight()-$new_value;
        }
        return round($difference,1);
    }
    //this function will set picture trend if trend is growing then arrow will be growing arrow
    //negative trend in our case is weight grow, positive is weight fall, neutral there is no difference between 
    //two values
    function setImgDependingOnTrend()
    {
        $img = "";
        $growingImgUrl = "icons/arrow_negative_trend.png";
        $neutralImgUrl = "icons/arrow_neutral_trend.png";
        $arrowPositiveTrend = "icons/arrow_positive_trend.png";

        if ($this->getTrend() == "growing") {
            $img = $growingImgUrl;
        } else if ($this->getTrend() == "neutral") {
            $img = $neutralImgUrl;
        } else if ($this->getTrend() == "falling") {
            $img = $arrowPositiveTrend;
        }
        return $img;
    }

	public function getDateTime() {return $this->date_time;}

	public function setDateTime( $date_time): void {$this->date_time = $date_time;}

	
	
}
