<?php 
class DatabaseConnection{
    private $host;
    private $user;
    private $pass;
    private $db;
    private $charset;
    public $dbconn;


    function __construct()
    {
        $this->host="localhost";
        $this->user="root";
        $this->pass="";
        $this->db="physical";
        $this->charset="utf8";
    }


    public function setDbconn( $dbconn) {$this->dbconn = $dbconn;}

    function connectToDatabase(){
           $dbconn=mysqli_connect($this->host,$this->user,$this->pass,$this->db);
           mysqli_set_charset($dbconn,$this->charset);
           $this->setDbconn($dbconn);
    }
    public function getHost() {return $this->host;}

	public function getUser() {return $this->user;}

	public function getPass() {return $this->pass;}

	public function getDb() {return $this->db;}

	public function getCharset() {return $this->charset;}

	public function getDbconn() {return $this->dbconn;}

    public function close_database(){
        mysqli_close($this->dbconn);
    }

    public function select_last_record_from_database($what_to_select,$table){
        $query="SELECT $what_to_select FROM $table WHERE id=(SELECT max(id) FROM $table)";
        $execute_query=mysqli_query($this->getDbconn(),$query);
        $result=$execute_query->fetch_column();
        return $result;
    }
    //return array of results as a result
    public function select_number_of_records_desc_sort($what_to_select,$table,$order_by,$limit){
        $array_result=array();
        $sql_query="SELECT ";
        //for any of the value of given array concatinate to sql query 
        //they are values from tables which we want to get
        //need to check last element in the field 
        //we need a index to increase it if index equal end of array do not concatinate ,
        $size_of_array=sizeof($what_to_select);
        $index=1;
        foreach ($what_to_select as $value) {
            if($index==$size_of_array){
                $sql_query .= "".$value."";
            }else if($index<$size_of_array){
                $sql_query .= "".$value.",";
            }
            $index++;
        }
        $sql_query .= " FROM $table ORDER BY $order_by DESC LIMIT $limit;";
        $exe=mysqli_query($this->getDbconn(),$sql_query);
        while($res=mysqli_fetch_array($exe)){
            for($i=0;$i<$size_of_array;$i++){
                $array_result[]=$res[$i];
            }
        }
        return $array_result;
    }
   
    public function delete_from_database($table,$values,$id,$compare_column,$bind_variable){
        $deleted=0;
        $single_value="";
        $multiple_values=array();
        $query_string="DELETE ";
        if(gettype($values)=="string"){
            if($values=="*"){
                $values=" ";
            }
           $single_value=$values;
        }else{
          $single_value=$values;
        } 
        if($single_value==$values){
              $query_string .= $single_value;
        }else{
              $multiple_values=$values;
              foreach($multiple_values as $val){
                $query_string .= $val.",";
              }
        }
        $query_string .= " FROM ";
        $query_string .= $table;
        $query_string .= " WHERE ";
        $query_string .= $compare_column;
        $query_string .= "=?";
        //$query_string .= $id.";";
        $statement=$this->getDbconn()->prepare($query_string);
        $statement->bind_param($bind_variable,$id);
        if($statement->execute()){
          $deleted=1;
        }else{
            $deleted=0;
        }
     return $deleted;



    }
	

}



?>