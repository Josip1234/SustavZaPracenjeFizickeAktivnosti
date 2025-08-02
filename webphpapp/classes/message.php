<?php
class Message
{
    const ERROR_DELETING_RECORD="Cannot delete record from database. There is a problem.";
    const SUCCESSFULL_INSERT_IN_DATABASE="Record has been succesfully inserted into our database.";
    const CHOOSE_ANOTHER_VALUE_FROM_DROPDOWN="Please choose another value from dropdown.";
    const ERROR_INSERTING_DATA_INTO_DATABASE="Something went wrong. Please, try again.";
    private $message_value;
    public function __construct($message_value)
    {
        $this->message_value = $message_value;
    }
    public function getMessageValue()
    {
        return $this->message_value;
    }

    public function setMessageValue($message_value)
    {
        $this->message_value = "<div class='row'><div class='col'>".$message_value."</div></div>";
    }
}
