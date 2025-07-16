function disable_button(){
 document.getElementById('Button').disabled=true;
}

function reload_page(){
     disable_button();
    location.reload();
}

function enable_button(){
     document.getElementById('Button').disabled=false;
}
function fetchIdForDeletion(id){
     let identity=id;
     //set new url id
     $location=window.location;
     if($location=="http://localhost/SustavZaPracenjeFizickeAktivnosti/webphpapp/index.php?message=success"){
           location.replace('index.php?id='+identity);
     }else{
         location.replace('index.php?id='+identity);
     }
}