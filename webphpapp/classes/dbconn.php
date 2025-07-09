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

	

}



?>