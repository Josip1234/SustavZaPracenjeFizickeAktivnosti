<?php
class Message
{
    const ERROR_DELETING_RECORD="Cannot delete record from database. There is a problem.";
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
