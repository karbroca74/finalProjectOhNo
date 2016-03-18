<?php
$action = $_POST['action'];

switch($action){
    case 'register':
        register();
        break;

    case 'login':
        login();
        break;
    
    case 'personalInfo':
        personalInfo();
        break;
    
    case 'search':
        search();
        break;
}
function register(){
    require ('db.php');
    //this connects to the database
    $con = dbConnect();
    //this lets us input a username and pw into the table
    $query = "INSERT INTO Personal_info(first_name,last_name,email,password)VALUES(:first_name,:last_name,:email,:password)";
    //prepares the statement to be executed
    $stm = $con->prepare($query);
    //this generates a different pw...so it cant be hacked
    $hashPass = md5($_POST['password']);
    //binds the username and pw so there can be no sql injection
    $stm->bindValue(':first_name', $_POST['first_name'],PDO::PARAM_STR);
    $stm->bindValue(':last_name', $_POST['last_name'],PDO::PARAM_STR);
    $stm->bindValue(':email', $_POST['email'],PDO::PARAM_STR);
    $stm->bindValue(':password', $hashPass, PDO::PARAM_STR);
    $res = new stdClass();
    //executes the statement
    if($stm->execute()){
        $res->success = true;
        $res->user_id = $con->lastInsertId();
    }else{
        $res->success = false;  
    } 
    echo json_encode($res);
}
function login(){
    require('db.php');
    $con = dbConnect();
    // this gets the users pw and user id from a table
    $query = "SELECT user_id, password, email FROM Personal_info WHERE email = :email;";
    $stm = $con->prepare($query);
    $stm->bindValue(':email', $_POST['email'],PDO::PARAM_STR);
    $stm->execute();
    $row = $stm->fetch(PDO::FETCH_ASSOC);
    $hashPass = md5($_POST['password']);
    $res = new stdClass();
    if(strcmp($row['password'],$hashPass) == 0){
        $res->success = true;
        $res->user_id = $row['user_id'];
    }else{
        $res->success = false;
    }
    echo json_encode($res);
}
function personalInfo(){
    require('db.php');
    $con = dbConnect();
    $query = "UPDATE Personal_info SET
            city = :city,
            my_status = :my_status,
            my_gender = :my_gender,
            my_height = :my_height,
            my_age = :my_age,
            longest_relationship = :longest_relationship,
            my_likes = :my_likes,
            my_partner_characteristics = :my_partner_characteristics,
            
            his_body_type = :his_body_type,
            his_height = :his_height,
            his_ethnicity = :his_ethnicity,
            his_status = :his_status,
            his_age = :his_age,
            
            my_words = :my_words
        WHERE user_id=:user_id";
    $stm = $con->prepare($query);
    $stm->bindValue(':city', $_POST['city'],PDO::PARAM_STR);
    $stm->bindValue(':my_gender', $_POST['my_gender'],PDO::PARAM_STR);
    $stm->bindValue(':my_status', $_POST['my_status'],PDO::PARAM_STR);
    $stm->bindValue(':my_height', $_POST['my_height'],PDO::PARAM_INT);
    $stm->bindValue(':my_age', $_POST['my_age'],PDO::PARAM_INT);
    $stm->bindValue(':longest_relationship', $_POST['longest_relationship'],PDO::PARAM_STR);
    $stm->bindValue(':my_likes', $_POST['my_likes'],PDO::PARAM_STR);
    $stm->bindValue(':my_partner_characteristics', $_POST['my_partner_characteristics'],PDO::PARAM_STR);
    
    $stm->bindValue(':his_body_type', $_POST['his_body_type'],PDO::PARAM_STR);
    $stm->bindValue(':his_height', $_POST['his_height'],PDO::PARAM_INT);
    $stm->bindValue(':his_ethnicity', $_POST['his_ethnicity'],PDO::PARAM_STR);
    $stm->bindValue(':his_status', $_POST['his_status'],PDO::PARAM_STR);
    $stm->bindValue(':his_age', $_POST['his_age'],PDO::PARAM_INT);
    
    $stm->bindValue(':my_words', $_POST['my_words'],PDO::PARAM_STR);
    $stm->bindValue(':user_id', $_POST['user_id'],PDO::PARAM_INT);
    //$stm->bindValue('my_gender', $_POST['my_gender'],PDO::PARAM_STR);
    //$stm->bindValue('their_gender', $_POST['their_gender'],PDO::PARAM_STR);
    //$stm->bindValue('min_age', $_POST['min_age'],PDO::PARAM_INT);
    //$stm->bindValue('max_age', $_POST['max_age'],PDO::PARAM_INT);
    if($stm->execute()){
        echo "success";
    }else{
        echo "failed";
        echo json_encode($stm->errorInfo());
    }
}
    
function search(){
    require('db.php');
    $con = dbConnect();
    $query = "  SELECT my_words,user_id,email,city,my_age,my_height
                FROM MEET_SESSION.Personal_info
                WHERE
                    my_gender=:their_gender AND
                    my_age >= :min_age AND my_age <= :max_age;";
    $stm = $con->prepare($query);
    $stm->bindValue(':their_gender', $_POST['their_gender'],PDO::PARAM_STR);
    $stm->bindValue(':min_age', $_POST['min_age'],PDO::PARAM_INT);
    $stm->bindValue(':max_age', $_POST['max_age'],PDO::PARAM_INT);
    $res = new stdClass();
    //executes the statement
    if($stm->execute()){
        $res->success = true;
        $res->results = array();
        while($row = $stm->fetch(PDO::FETCH_ASSOC)){
            array_push($res->results,$row);
        }
    }else{
        $res->success = false;
        $res->message = $stm->errorInfo();
    }
    echo json_encode($res);
}
    

?>