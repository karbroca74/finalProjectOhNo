<?php
function dbConnect(){
    // Connecting to databases with PHP we will be using the PDO class.
    $db_uid = "root";       //The username of the mysql database
    $db_pwd = "root";   //The password for the mysql user
    $db_conn_string = "mysql:host=localhost;dbname=MEET_SESSION;charset=utf8";
    // NOTE: By default, the WAMP mysql root user has no password,
    // you need to set one in the user privileges area of MysqlWorkbench;
    
    //makes a new php data object
    $dbConnection = new PDO($db_conn_string, $db_uid, $db_pwd);
    //this is code to show errors
    //$dbConnection->setAttribute(PDO::ATTR_ERRMODE,PDO::ERRMODE_EXCEPTION);
    return $dbConnection;
}
?>