<?php
class Message
{
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
        $this->message_value = $message_value;
    }
}
