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
    $query = "INSERT INTO Personal_stuff(first_name,last_name,email,password, confirm_password)VALUES(:first_name,:last_name,:email,:password, :confirm_password)";
    //prepares the statement to be executed
    $stm = $con->prepare($query);
    //this generates a different pw...so it cant be hacked
    $hashPass = md5($_POST['password']);
    //binds the username and pw so there can be no sql injection
    $stm->bindValue(':first_name', $_POST['first_name'],PDO::PARAM_STR);
    $stm->bindValue(':last_name', $_POST['last_name'],PDO::PARAM_STR);
    $stm->bindValue(':email', $_POST['email'],PDO::PARAM_STR);
    $stm->bindValue(':password', $hashPass, PDO::PARAM_STR);
    $stm->bindValue(':confirm_password',$hashPass, PDO::PARAM_STR);
    //executes the statement
    if($stm->execute()){
        echo 'success';
    }else{
        echo 'failure';
    }
    
       
    
}
function login(){
    require('db.php');
    $con = dbConnect();
    // this gets the users pw and user id from a table
    $query = "SELECT user_password, user_id FROM chat_users WHERE user_name = :username;";
    $stm = $con->prepare($query);
    $stm->bindValue(':username', $_POST['username'],PDO::PARAM_STR);
    $stm->execute();
    $row = $stm->fetch(PDO::FETCH_ASSOC);
    $hashPass = md5($_POST['password']);
    if(strcmp($row['password'],$hashPass) == 0){
        echo 'success';
        session_start();
        $_SESSION['loggedIn'] = true;
        $_SESSION['user_id'] = $row['user_id'];
    }else{
        echo 'failed';
    }
}
?>