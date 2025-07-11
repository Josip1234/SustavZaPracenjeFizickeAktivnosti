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