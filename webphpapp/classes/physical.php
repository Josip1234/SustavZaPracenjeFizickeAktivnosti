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
        $arrowNegativeTrend = Image::OPEN_IMAGE_SRC."icons/arrow_negative_trend.png";
        $arrowNeutralTrend = Image::OPEN_IMAGE_SRC."icons/arrow_neutral_trend.png";
        $arrowPositiveTrend = Image::OPEN_IMAGE_SRC."icons/arrow_positive_trend.png";

        if ($this->getTrend() == "growing") {
            $img .= $arrowNegativeTrend;
            $img .= Image::ADD_ALT_IMG;
            $img .= "growing";
            $img .= Image::SET_CLASS;
            $img .= Image::CLOSE_IMG;
        } else if ($this->getTrend() == "neutral") {
            $img = $arrowNeutralTrend;
            $img .= Image::ADD_ALT_IMG;
            $img .= "neutral";
            $img .= Image::SET_CLASS;
            $img .= Image::CLOSE_IMG;
        } else if ($this->getTrend() == "falling") {
            $img = $arrowPositiveTrend;
            $img .= Image::ADD_ALT_IMG;
            $img .= "falling";
            $img .= Image::SET_CLASS;
            $img .= Image::CLOSE_IMG;
        }
        return $img;
    }

    /**
     * This function will be used to determine trend between inserted new value, and last known record in our database.
     *  function will be used to set trend pictures when records is being printed
     */
    function determine_trend($last_record,$new_value){
           $trend="";
           if($last_record>$new_value){
            $trend="growing";
           }else if($last_record<$new_value){
            $trend="falling";
           }else{
            $trend="neutral";
           }
           return $trend;
    }

	public function getDateTime() {return $this->date_time;}

	public function setDateTime( $date_time): void {$this->date_time = $date_time;}

	
	
}
