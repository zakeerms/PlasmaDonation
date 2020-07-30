<?php
    $host = "localhost"; //on same server we keep localhost
    $user = "id13996618_dhvani";  //username of the database
    $pass = "La4&42O^_lP@1BnR";   //password of the database
    $db = "id13996618_plasma";  //name of database
    
    $con = mysqli_connect($host,$user,$pass,$db);
    
    if($con){
        //echo "Connected to Database";
    }else{
        //echo "Failed to connect ".mysqli_connect_error();
    }
?>
