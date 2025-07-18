<?php
include("classes/images.php");
class Weight_stat
{
    private $id;
    private $weight;
    private $difference;
    private $trend;
    private $date_time;
    const GROWING="growing";
    const FALLING="falling";
    const NEUTRAL="neutral";
    const IMAGE_SMALL_SIZE="small";
    const TABLE_NAME="weight_daily_stats";
    const COMPARE_COLUMN_FOR_DELETION="id";

    public function __construct($id,$weight)
    {
        $this->id=$id;
        $this->weight = $weight;
    }  

    public function __destruct()
    {
        $this->weight = "";
    }

    public function getId(){
        return $this->id;
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
    public function setId($id){
        $this->id=$id;
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
    function setImgDependingOnTrend($image_size)
    {
        $img = "";
        if($image_size==Weight_stat::IMAGE_SMALL_SIZE){
       $arrowNegativeTrend = Image::OPEN_IMAGE_SRC."icons/arrow_negative_trend_small.png";
        $arrowNeutralTrend = Image::OPEN_IMAGE_SRC."icons/arrow_neutral_trend_small.png";
        $arrowPositiveTrend = Image::OPEN_IMAGE_SRC."icons/arrow_positive_trend_small.png";
        }else{
        $arrowNegativeTrend = Image::OPEN_IMAGE_SRC."icons/arrow_negative_trend.png";
        $arrowNeutralTrend = Image::OPEN_IMAGE_SRC."icons/arrow_neutral_trend.png";
        $arrowPositiveTrend = Image::OPEN_IMAGE_SRC."icons/arrow_positive_trend.png";
        }
       

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

    //additional function will return trend depending on valuse from database
    function trend_from_database($value_from_database){
           $trend="";
           if($value_from_database==Weight_stat::GROWING){
            $trend=Weight_stat::GROWING;
           }else if($value_from_database==Weight_stat::FALLING){
            $trend=Weight_stat::FALLING;
           }else{
            $trend=Weight_stat::NEUTRAL;
           }
           return $trend;
    }




	public function getDateTime() {return $this->date_time;}

	public function setDateTime( $date_time): void {$this->date_time = $date_time;}

	
	
}
