<?php
$action = $_POST['action'];

switch($action){
    case 'register':
        register();
        break;

    case 'login':
        login();
        break;
}
function register(){
    require ('db.php');
    //this connects to the database
    $con = dbConnect();
    //this lets us input a username and pw into the table
    $query = "INSERT INTO Personal_stuff(first_name,last_name,email,password)VALUES(:first_name,:last_name,:email,:password)";
    //prepares the statement to be executed
    $stm = $con->prepare($query);
    //this generates a different pw...so it cant be hacked
    $hashPass = md5($_POST['password']);
    //binds the username and pw so there can be no sql injection
    $stm->bindValue(':first_name', $_POST['first_name'],PDO::PARAM_STR);
    $stm->bindValue(':last_name', $_POST['last_name'],PDO::PARAM_STR);
    $stm->bindValue(':email', $_POST['email'],PDO::PARAM_STR);
    $stm->bindValue(':password', $hashPass, PDO::PARAM_STR);
    //executes the statement
    if($stm->execute()){
        echo 'success';
    }else{
        echo 'failure';
        echo json_encode($stm->errorInfo());    
    } 
}
function login(){
    require('db.php');
    $con = dbConnect();
    // this gets the users pw and email from a table
    $query = "INSERT INTO Personal_stuff(email, password)VALUES(:email,:password)";
    $hashPass = md5($_POST['password']);
    $stm = $con->prepare($query);
    $stm->bindValue(':email', $_POST['email'],PDO::PARAM_STR);
    $stm->bindValue(':password', $hashPass, PDO::PARAM_STR);
    $stm->execute();
    $row = $stm->fetch(PDO::FETCH_ASSOC);
    $hashPass = md5($_POST['password']);
    if($stm->execute()){
        echo 'success';
    }else{
        echo 'failed';
        echo json_encode($stm->errorInfo());
    }
}
?>